;; This is the server code. I will walk you through it and explain it briefly.
;; You certainly are not expected to understand everything, it is enough to get
;; a general picture.
;; 
;; Remember to follow the instructions and connect to the REPL
;; (View - Command Palette - ...) and to
;; load this file into it (ctrl-alt-c Enter)
(ns interactive.server
  "Backend for the Interactive Development Workshop"
  {:author "Jakub Holý"}
  (:require
   [clojure.edn :as edn]
   [clojure.string :as str]
   [next.jdbc :as jdbc]
   [next.jdbc.result-set :as jdbc-rs]
   [ring.adapter.jetty]
   [ring.middleware.resource]))

;;-------------------------------------------------------- PLAYGROUND
(comment
  ;; This is a "Rich Comment Block" used for valid code that is useful when playing
  ;; around in the REPL but that shouldn't be in the production code.
  ;; You can send an expression from here to the REPL by placing your cursor on it
  ;; and ctrl-alt-c SPACE
  ;; We will use it after the walk-through to get our hands dirty with some Clojure as a warm-up.
  ;; TIP: Open and browse through Cheatsheet.md now to get familiar with it.
  
  ;; ## Try these: ##
  ;; 1. Define a map called person with :name John and :age 42
  
  ;; 2. Get the age out of the person map (tip: keywords can be used as functions of a map)
  
  ;; 3. Verify that the person's age is 42 (tip: use `=`)
  
  ;; 3. Make an anonymous function that takes a single argument and returns it as-is
  
  ;; 4. Make the function `fortytwo?` that takes a person and tells you whether s/he is 42 (tip: use `def`, `fn`)
  
  ;; 4. Define a vector of numbers 40 - 45 called nums
  
  ;; 5. Remove 42 from it (tip: use `fn` and `filter` and `not=`)
  
  ;; 6. Increase each element by of nums 3 and get the first one (use `map`, `+`, and `first`)
  ;;    Tip: Do this in 3 steps, trying each step before coding further: 
  ;;    1. map over nums; 2. extract the first element; 3. refactor the code leveraging `->>`
  )

;;-------------------------------------------------------- DATABASE (DO NOT TOUCH!)
;; Database-related stuff. You will use these functions.

;; A connection to an in-memory SQL database
(def ds (jdbc/get-datasource {:dbtype "h2" :dbname "people"}))

(defn fetch-all-people []
  (jdbc/execute! ds ["SELECT * from people"] {:builder-fn jdbc-rs/as-unqualified-maps}))

(defn update-person-first-name [id first-name]
  (assert id "id is required")
  (assert first-name "first-name is required")
  (jdbc/execute! ds ["UPDATE people SET fname=? WHERE id=?" first-name id]))

(comment
  (fetch-all-people))

;;-------------------------------------------------------- REQUEST HANDLING (fill in the code!)
;; The functions that deliver the data the UI is asking for.
;; You will evolve them to fullfil the tasks and satisfy the UI.

(defn handle-people 
  "Return the list of people"
  [req]
  ;; TODO Task 1 (guided): Return fake, hard-coded data for people => 
  ;;      Capture req to see what is required, create a vector with a corresponding map(s).
  ;;      Lesson: Capturing args for REPL interaction/focused invocation, change without restart/ui reloads. 
  ;;
  ;; TODO Task 2: Replace the hardcoded data with data from the DB =>
  ;;      Use the REPL to find out what does `(fetch-all-people)` return, `map` it into what the UI expects.
  ;;      Tip: Use a `(comment ...)` block to play with the data until it does what you want.
  ;;      Lesson: Experimenting with small bits of code in the REPL as we evolve the program.
  {:status 200
   :headers {"Content-Type" "text/plain"}
   :body (pr-str [{:fname "John" :email "snow@example.com"}
                  {:fname "John 2" :email "2@example.com"}])})

(defn handle-person 
  "Return the data needed when an 'Edit' button is pressed."
  [req email]
  ;; TODO Task 3: Find the person with the given `email` in the DB and return the requested data =>
  ;;      Use `->>`, `fetch-all-people`, `filter`, `map`, and `first`. Remember to check what data the UI wants.
  ;;      Tip: Develop inside a `(comment ...)`, test every tiny step, remember to change the status.
  ;;      Lesson: Capture arguments as a global var, evolve the code via trying snippets in the REPL.
  {:status 500
    :headers {"Content-Type" "text/plain"}
    :body (pr-str "Not implemented")})

(defn handle-person-update 
  "Update the given person in the DB and return the status 'OK' to the client."
  [req email]
  ;; TODO Task 4: Update the person in the DB based on the request
  ;;      => Find out what the request contains and use the same approach as above to find the person.
  ;;         then use `update-person-first-name` to update him/her. Tip: Use `let`.
  ;; <your code here>
  {:status 500 :headers {"Content-Type" "text/plain"} :body (pr-str "Not implemented")})

;;-------------------------------------------------------- ROUTING, SERVER ETC. (DO NOT TOUCH)
;; Routing of requests to the appropriate handler above,
;; setup to parse bodies into Clojure data and to serve our frontend files,
;; the -main function that starts the server.

(defn api-handler [req]
  (cond
    (= (:uri req) "/api/people")
    (handle-people req)

    (and
     (str/starts-with? (:uri req) "/api/person/")
     (= :post (:request-method req)))
    (handle-person req (str/replace-first (:uri req) "/api/person/" ""))

    (and
     (str/starts-with? (:uri req) "/api/person/")
     (= :put (:request-method req)))
    (handle-person-update req (str/replace-first (:uri req) "/api/person/" ""))

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
      (wrap-edn-request)
      (ring.middleware.resource/wrap-resource "public")))

(defn -main 
  "Sturt the server from REPL or CLI (the latter via `clj -m interactive.server`)." 
  []
  (println "Starting Ring server...")
  (ring.adapter.jetty/run-jetty (var app) {:port 8088 :join? false})
  (let [uri "http://localhost:8088/"]
    (println (str "Jetty running on: " uri))
    (try
      (.browse (java.awt.Desktop/getDesktop) (java.net.URI. uri))
      (catch java.awt.HeadlessException _))))

(comment
  (-main)
  (app {:uri "/api/people" :method :post :body "[]"}))
