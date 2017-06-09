(ns board-game-local.routes.auth
  (:require [compojure.core :refer [defroutes POST]]
            [ring.util.response :refer [redirect]]
            [buddy.hashers :as hashers]
            [board-game-local.routes.home :refer [login-page]]
            [board-game-local.db.core :refer [get-user-by-email]]
            [ring.util.http-response :as response]))

(defn authenticate-user [session email password]
  (let [user (get-user-by-email {:email email})]
    (if (nil? user) (login-page {:error "Email not registerd"})
      (if (hashers/check password (:password user))
          (let [new-session (assoc session :user-id (:id user))]
            (-> (redirect "/")
                (assoc :session new-session)))
          (login-page {:error "Wrong password"})))))

(defn logout-user [session]
  (-> (redirect "/")
      (assoc :session (assoc session :user nil))))

(defroutes auth-routes
  (POST "/auth" req (let [{email :email password :password} (:params req)]
                       (authenticate-user (:session req) email password)))
  (POST "/logout" req (logout-user (:session req))))
  
