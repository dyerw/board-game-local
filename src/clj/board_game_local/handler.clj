(ns board-game-local.handler
  (:require [compojure.core :refer [routes wrap-routes]]
            [board-game-local.layout :refer [error-page]]
            [board-game-local.routes.home :refer [home-routes]]
            [board-game-local.routes.user :refer [user-routes]]
            [board-game-local.routes.auth :refer [auth-routes]]
            [compojure.route :as route]
            [board-game-local.env :refer [defaults]]
            [mount.core :as mount]
            [board-game-local.middleware :as middleware]))

(mount/defstate init-app
                :start ((or (:init defaults) identity))
                :stop  ((or (:stop defaults) identity)))

(def app-routes
  (routes
    #'auth-routes
    #'user-routes
    (-> #'home-routes
        (wrap-routes middleware/wrap-csrf)
        (wrap-routes middleware/wrap-formats))
    (route/not-found
      (:body
        (error-page {:status 404
                     :title "page not found"})))))


(defn app [] (middleware/wrap-base #'app-routes))
