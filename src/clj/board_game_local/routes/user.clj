(ns board-game-local.routes.user
  (:require [board-game-local.layout :as layout]
            [compojure.core :refer [defroutes POST]]
            [compojure.coercions :refer [as-int]]
            [buddy.hashers :as hashers]
            [struct.core :as st]
            [clj-time.format :as f]
            [ring.util.http-response :as response]
            [board-game-local.db.core :refer [create-user! get-user-by-email]]
            [board-game-local.routes.home :refer [register-page]]
            [clojure.java.io :as io]))

(def user-scheme
 {:firstname [st/required st/string]
  :lastname  [st/required st/string]
  :dob       [st/required st/string]
  :gender    [st/required st/string]
  :email     [st/required st/email]
  :location  [st/required st/string]
  :password  [st/required st/string]
  :confirmpassword [st/required st/string]}) 

(defn dob->time-in-params [params]
  (merge params
         {:dob (f/parse (f/formatter "YYYY-MM-DD") (:dob params))})) 

(defn hash-password-in-params [params]
  (merge params
         {:password (hashers/encrypt (:password params))}))

(defn create-user [params]
  (let [validation (-> params
                       (st/validate user-scheme
                                    {:strip true}))
        errors (first validation)
        coerced-params (last validation)
        existing-user (get-user-by-email {:email (:email params)})]
   (cond
        (not (nil? errors))
        (register-page {:error (str "Validation errors: " 
                                    errors)})

        (not (nil? existing-user)) 
        (register-page {:error "User with this email already exists"})

        (not= (:password coerced-params) 
              (:confirmpassword coerced-params)) 
        (register-page {:error "Passwords do not match"})

        :else (if-let [new-user (-> coerced-params
                                    hash-password-in-params
                                    dob->time-in-params
                                    create-user!)]
                (register-page {:error nil})
                (register-page {:error "Error creating user"})))))

(defroutes user-routes
  (POST "/user" req (create-user (:params req))))
 
