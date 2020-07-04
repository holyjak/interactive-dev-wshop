(ns interactive.ui
  (:require
   [clojure.edn :as edn]
   [helix.core :refer [defnc $]]
   [helix.hooks :as hooks]
   [helix.dom :as d]
   ["react-dom" :as rdom]
   ["semantic-ui-react" :as semantic]))

(defn init [])

(defn fetch-people []
  (-> (js/fetch "/api/people" #js {"method" "POST", "body" []})
      (.then #(.text %))
      (.then edn/read-string)))

(defnc person [{:keys [fname email]}]
  (d/li (str fname " " email)))

(defnc people [{:keys [people]}]
  (d/div 
   (d/h3 "People")
   (d/ul
    (map #($ person {:key (:fname %) & %}) people))))

(defnc app [props]
  (d/div
   (d/h1 "Welcome!")
   ($ people {& props})))

;; start your app with your favorite React renderer
(-> (fetch-people)
    (.then #(rdom/render ($ app {:people %}) (js/document.getElementById "app"))))