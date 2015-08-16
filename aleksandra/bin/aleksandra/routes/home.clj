(ns aleksandra.routes.home
  (:require [compojure.core :refer :all]
            [aleksandra.views.layout :as layout]
            [hiccup.form :refer :all]
            ))

(defn home []
  (layout/common [:h1 "Hello Anaa!"]
                 [:p "Welcome to my guestbook"]
                 [:hr]
                 (form-to [:post "/"]
                 [:p "Name:" (text-field "name")]
                 [:p "Message:" (text-area {:rows 10 :cols 40} "message")]
                (submit-button "comment"))
                 ))

(defn secondPage []
  (layout/common [:h1 "Second page!"]
                 ))


(defroutes home-routes
  (GET "/" [] (home))
  (POST "/" [] (secondPage)))
