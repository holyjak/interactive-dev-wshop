(ns interactive.server
  ""
  {:author "Jakub Holý"}
  (:require 
   [next.jdbc :as jdbc]
   [ring.adapter.jetty]
   [ring.middleware.resource]))

;;-------------------------------------------------------- DATABASE

;; A connection to an in-memory SQL database
(def ds 
  (jdbc/get-datasource {:dbtype "h2" :dbname "temporary"}))

(defn fetch-all-people []
  (jdbc/execute! ds ["SELECT * from people"]))

(comment
  ;; TODO Move it off to a separate file, load upon start, add "reset" instructions
  (jdbc/execute! ds ["
create table people (
  id int auto_increment primary key,
  fname varchar(32),
  lname varchar (32),
  email varchar(255)
)"])
  
  (jdbc/execute! ds ["
insert into people(fname,lname,email) values
  ('Rich', 'Hickey','rich@example.com'),
  ('Stu', 'Halloway','stu@example.com'),
  ('Zach', 'Tellman','zach@example.com')"])

  nil)
;;-------------------------------------------------------- REQUEST HANDLING

(defn handle-people [_req]
  {:status 200 :body "people" :headers {"Content-Type" "application/json"}})

(defn api-handler [req]
  (cond
    (not= (:method req) :POST)
    {:status 400 :body "Only POST supported"}
    
    (re-matches #"/api/people" (:uri req))
    (handle-people req)))

(defn handler [req]  
  (def *req req)
  (cond
    (re-matches #"^/api/" (:uri req))
    (api-handler req)
    
    (re-matches #"^/?$" (:uri req))
    {:status 302 :headers {"location" "/index.html"}}
    
    :else
    {:status 404 :body "So sad!!!"}))

(def app
  (-> (var handler)
      ;; Add wrap-content-type ? wrap-params? json?
      (ring.middleware.resource/wrap-resource "public")))

(defn -main "For `lein run`, etc." []
  (println "Starting example Ring server...")
  (ring.adapter.jetty/run-jetty app {:port 8088 :join? false})
  (let [uri "http://localhost:8088/"]
    (println (str "Jetty running on: " uri))
    (try
      (.browse (java.awt.Desktop/getDesktop) (java.net.URI. uri))
      (catch java.awt.HeadlessException _))))

(comment (-main))
