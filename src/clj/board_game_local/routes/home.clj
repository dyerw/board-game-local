(ns board-game-local.routes.home
  (:require [board-game-local.layout :as layout]
            [compojure.core :refer [defroutes GET]]
            [board-game-local.config.form :refer [login-params register-params event-params]]
            [ring.util.http-response :as response]
            [clojure.java.io :as io]))

(defn home-page []
  (layout/render
    "home.html" {:docs (-> "docs/docs.md" io/resource slurp)}))


(defn login-page [{error :error}]
  (layout/render "login.html" 
                 {:type "login"
                  :error error
                  :user-params login-params})) 

(defn register-page [{error :error}]
  (layout/render "login.html" 
                 {:error error
                  :user-params (concat login-params register-params)}))

(defn create-event-page [{error :error}]
  (layout/render "create.html"
                 {:error error
                  :event-params event-params}))

(defn about-page []
  (layout/render "about.html"))

(defroutes home-routes
  (GET "/" [] (home-page))
  (GET "/login" [] (login-page {:error nil}))
  (GET "/register" [] (register-page {:error nil}))
  (GET "/create-event" [] (create-event-page {:error nil}))
  (GET "/about" [] (about-page)))

