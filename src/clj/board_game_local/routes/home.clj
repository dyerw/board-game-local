(ns board-game-local.routes.home
  (:require [board-game-local.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]))

(defn home-page []
  (layout/render
    "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(def register-params [{:name "firstname" :icon "fa fa-user fa" :display "First Name"}
                 {:name "lastname" :icon "fa fa-user fa" :display "Last Name"}
                 {:name "age" :icon "" :display "Age"}
                 {:name "gender" :icon "fa fa-transgender-alt fa" :display "Gender"}
                 {:name "confirmpassword" :icon "fa fa-lock fa-lg" :display "Confirm Password"}
                 {:name "location" :icon "fa fa-map fa" :display "Location"}])

(def login-params [{:name "email" :icon "fa fa-envelope fa" :display "Email"}
                   {:name "password" :icon "fa fa-lock fa-lg" :display "Password"}])
 
(defn login-page []
  (layout/render "login.html" 
                 {:type "login"
                  :user-params login-params})) 

(defn register-page []
  (layout/render "login.html" {:user-params (concat login-params register-params)}))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/login" [] (login-page))
  (GET "/register" [] (register-page))
  (GET "/about" [] (about-page)))

