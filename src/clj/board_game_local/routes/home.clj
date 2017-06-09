(ns board-game-local.routes.home
  (:require [board-game-local.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]))

(defn home-page []
  (layout/render
    "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))

(def register-params [{:name "firstname" :icon "fa fa-user fa" :display "First Name"
                       :placeholder "e.g. Alex"}
                 {:name "lastname" :icon "fa fa-user fa" :display "Last Name"
                  :placeholder "e.g. Smith"}
                 {:name "age" :icon "" :display "Age"
                  :placeholder "e.g. 32"}
                 {:name "gender" :icon "fa fa-transgender-alt fa" :display "Gender"
                  :placeholder "Enter the term that best describes your gender"}
                 {:name "confirmpassword" :icon "fa fa-lock fa-lg" :display "Confirm Password"
                  :placeholder "You know how account registration works by now"}
                 {:name "location" :icon "fa fa-map fa" :display "Location"
                  :placeholder "This won't be listed on your profile"}])

(def login-params [{:name "email" :icon "fa fa-envelope fa" :display "Email"}
                   {:name "password" :icon "fa fa-lock fa-lg" :display "Password"}])
 
(defn login-page [{error :error}]
  (layout/render "login.html" 
                 {:type "login"
                  :error error
                  :user-params login-params})) 

(defn register-page [{error :error}]
  (layout/render "login.html" 
                 {:error error
                  :user-params (concat login-params register-params)}))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/login" [] (login-page {:error nil}))
  (GET "/register" [] (register-page {:error nil}))
  (GET "/about" [] (about-page)))

