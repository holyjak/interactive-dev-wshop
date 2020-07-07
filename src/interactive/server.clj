(ns interactive.server
  "Backend for the Interactive Development Workshop"
  {:author "Jakub Holý"}
  (:require
   [clojure.edn :as edn]
   [clojure.string :as str]
   [next.jdbc :as jdbc]
   [ring.adapter.jetty]
   [ring.middleware.resource]))

;;-------------------------------------------------------- DATABASE (DO NOT TOUCH!)

;; A connection to an in-memory SQL database
(def ds (jdbc/get-datasource {:dbtype "h2" :dbname "people"}))

(defn fetch-all-people []
  (jdbc/execute! ds ["SELECT * from people"]))

(defn update-person-first-name [id first-name]
  (assert id "id is required")
  (assert first-name "first-name is required")
  (jdbc/execute! ds ["UPDATE people SET fname=? WHERE id=?" first-name id]))

(comment
  (fetch-all-people))

;;-------------------------------------------------------- REQUEST HANDLING

(defn handle-people [_req]
  ;; TODO Fetch the people from the DB using `fetch-all-people` and `map` it to what the UI wants 
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (pr-str (->> (fetch-all-people)
                      (map (fn [p] {:fname (:PEOPLE/FNAME p)
                                    :email (:PEOPLE/EMAIL p)}))) #_[{:fname "Rich" :email "rich@example.com"}])})

(defn handle-person [req]
  (let [id (str/replace-first (:uri req) "/api/person/" "")]
    ;; TODO Find the person in the DB using `fetch-all-people` and `filter` and `first` and transform it into the form the UI wants
    {:status 200
     :headers {"Content-Type" "text/plain"}
     :body (pr-str (->> (fetch-all-people)
                        (filter (fn [p] (= id (:PEOPLE/EMAIL p))))
                        (map (fn [p] {:fname (:PEOPLE/FNAME p)
                                      :lname (:PEOPLE/LNAME p)
                                      :email (:PEOPLE/EMAIL p)}))
                        (first)))}))

(defn handle-person-update [req]
  ;; TODO Find the person in the DB using `fetch-all-people` and `filter` and `first`
  (let [email (str/replace-first (:uri req) "/api/person/" "")
        person    (->> (fetch-all-people)
                       (filter (fn [p] (= email (:PEOPLE/EMAIL p))))
                       (first))
        id    (:PEOPLE/ID person)]
    (update-person-first-name id (-> req :body :fname))
    {:status 200 :headers {"Content-Type" "text/plain"}}))

;;-------------------------------------------------------- ROUTING, SERVER ETC. (DO NOT TOUCH)

(defn api-handler [req]
  (cond
    (= (:uri req) "/api/people")
    (handle-people req)

    (and
     (str/starts-with? (:uri req) "/api/person/")
     (= :post (:request-method req)))
    (handle-person req)

    (and
     (str/starts-with? (:uri req) "/api/person/")
     (= :put (:request-method req)))
    (handle-person-update req)

    :else
    {:status 404 :body (str "Unsupported uri " (:uri req))}))

(defn handler [req]
  (cond
    (str/starts-with? (:uri req) "/api/")
    (api-handler req)

    (or (= "" (:uri req)) (= "/" (:uri req)))
    {:status 302 :headers {"location" "/index.html"}}

    :else
    {:status 404 :body "So sad!!!"}))

(defn try-parse-edn [body]
  (try
    (if body (-> body slurp edn/read-string))
    (catch Exception _ignored
      body)))

(defn wrap-edn-request [handler]
  (fn [req]
    (-> (update req :body try-parse-edn)
        (handler))))

(def app
  (-> handler
      ;; Add wrap-content-type ? wrap-params? json?
      (wrap-edn-request)
      (ring.middleware.resource/wrap-resource "public")))

(defn -main "For `lein run`, etc." []
  (println "Starting example Ring server...")
  (ring.adapter.jetty/run-jetty (var app) {:port 8088 :join? false})
  (let [uri "http://localhost:8088/"]
    (println (str "Jetty running on: " uri))
    (try
      (.browse (java.awt.Desktop/getDesktop) (java.net.URI. uri))
      (catch java.awt.HeadlessException _))))

(comment
  (-main)
  (app {:uri "/api/people" :method :post :body "[]"}))
