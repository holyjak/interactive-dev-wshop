(ns interactive.ui
  (:require
   [clojure.edn :as edn]
   [clojure.string :as str]
   [helix.core :refer [<> defnc $]]
   [helix.hooks :as hooks]
   [helix.dom :as d]
   ["react" :as react]
   ["react-dom" :as rdom]
   ["semantic-ui-react" :as semantic :refer [Header Message Modal]]))

(defn init [])

(defn handle-response 
  ([uri resp] (handle-response "fetch" uri resp))
  ([action uri resp]
   (-> (if (.-ok resp)
         (.then (.text resp) edn/read-string)
         (.reject js/Promise (js/Error. (str "Request returned non-OK status " (.-status resp) 
                                             "Perhaps you forgot to change the :status of your response to 200?"))))
       (.catch (fn [err]
                 (js/console.error "Failed to " action uri err)
                 ::error)))))

(defn fetch-people []
  (let [uri "/api/people"]
    (-> (js/fetch uri #js {"method" "POST", "body" (pr-str [:fname :email])})
       (.then (partial handle-response uri)))))

(defn fetch-person [email]
  (let [uri (str "/api/person/" email)]
    (-> (js/fetch uri #js {"method" "POST", "body" (pr-str [:fname :lname :email])})
        (.then (partial handle-response uri)))))

(defn update-person [email new-fname]
  (let [uri (str "/api/person/" email)]
    (-> (js/fetch uri #js {"method" "PUT", "body" (pr-str {:fname new-fname})})
        (.then (partial handle-response "PUT" uri))
        (.then #(when (= ::error %) (throw (js/Error. "Failed")))))))

;; ===================================================================================================================

(def my-context (react/createContext "default"))

(defnc person-modal [{:keys [email]}]
  (let [[state set-state] (hooks/use-state {:open? false, :person nil, :saving? false, :error? false})
        loadfn (fn [] (set-state assoc :open? true)
                 (-> (fetch-person email) (.then (fn [p] (set-state assoc :person p)))))
        button (d/button {:className "ui button"
                          :onClick loadfn}
                         "Edit")]
    ;(hooks/use-effect :once (loadfn)) ;; FIXME rm
    ($ Modal {:trigger button
              :open (:open? state)
              :onClose (fn [] (set-state assoc :open? false))}
       (let [{:keys [fname lname email] :as person} (:person state)
             reload (:reload (hooks/use-context my-context))]
         (<>
          ($ Header {:content (str "Edit " fname " " lname)})
          (cond
            (= ::error person)
            ($ Message {:error true} "Loading failed, see the Console for details")

            (nil? person)
            (d/p {:className "ui active loader"} "Loading...")
            
            (some nil? [fname lname email])
            ($ Message {:error true} "Bad response from the server - missing one of First name, Last name, Email")
            
            (> (count (keys person)) 3)
            ($ Message {:error true} "Bad response from the server - we only want First name, Last name, Email but got more attributes back")

            :else
            (d/form
             {:className (str/join " " ["ui form" (when (:error? state) "error")]) :style {:padding " 1em"}}
             (d/div {:className "field"}
                    (d/label "First name")
                    (d/input {:type "text", :defaultValue fname, :on-change #(set-state assoc-in [:person :fname] (.. % -target -value))}))
             (d/div {:className "disabled field"}
                    (d/label "Last name")
                    (d/input {:type "text", :value lname, :readOnly true}))
             (d/div {:className "disabled field"}
                    (d/label "Email")
                    (d/input {:type "text", :value email, :readOnly true}))
             (when true ;(:error? state)
               (d/div {:className "ui error message"}
                      (d/p "Saving the person failed - see the Console for details. Perhaps you haven't implemented `handle-person-update` yet?")))
             (d/button {:className (str/join " " ["ui primary submit button" (when (:saving? state) "loading")]) 
                        :type "submit"
                        :on-click (fn [evt]
                                    (.preventDefault evt)
                                    (set-state assoc :saving? true)
                                    (-> (update-person email fname)
                                        (.then (fn [_]
                                                 (set-state assoc :open? false)
                                                 (reload)
                                                 (set-state assoc :saving? false)))
                                        (.catch (fn [_]
                                                  (set-state assoc :error? true)
                                                  (set-state assoc :saving? false)))))} 
                       "Save"))))))))

(defnc person [{:keys [fname email] :as person}]
  (let [error-td #(d/td {:colSpan 3} ($ Message %))]
   (d/tr 
    (cond
      (< (count person) 2) (error-td "An incomplete person this is!")
      (> (count person) 2) (error-td "Too many attributes this person has! I only wanted 2!")
      (not= (set (keys person)) #{:fname :email}) (error-td "A confusion of attributes befell us!")
      :else
      (<>
       (d/td fname)
       (d/td email)
       (d/td ($ person-modal {:email email}))
       #_(d/td (d/button {:className "ui button"
                        :onClick #(println "edit" %)} 
                       "Edit")))))))

;(.-Content Modal)

(defnc people [{:keys [people]}]
  (d/div 
   (d/h3 "My Clojure heroes")
   (if (seq people)
     (d/table {:className "ui celled striped table"}
      (d/thead
       (d/tr
        (d/th "Name")
        (d/th "E-mail")
        (d/th {:className "one wide"} "")))
      (d/tbody
       (map #($ person {:key (or (:fname %) %) & %}) people)))
     ($ Message {} "It is so lonely here, with no people around!"))))

(defnc app []
  (let [[people-list set-people-list] (hooks/use-state [])
        reload #(-> (fetch-people) (.then set-people-list))]
    (hooks/use-effect :once (reload))
    (helix.core/provider
     {:context my-context
      :value {:reload reload}}
     (d/div 
      (d/div {:className "ui fixed inverted menu"}
             (d/div {:className "ui container"}
                    (d/div {:className "header item"} "Interactive development workshop")))
      (d/div {:className "ui main text container" :style {:margin-top "2.85714em"}}
             (d/h1 {:className "ui header"} "Welcome to the interactive development workshop!")
             (if (= ::error people-list)
               ($ Message {:error true} "Fetching people failed - see the Console for details")
               ($ people {:people people-list, :reload reload})))))))

;; start your app with your favorite React renderer
(rdom/render ($ app) (js/document.getElementById "app"))