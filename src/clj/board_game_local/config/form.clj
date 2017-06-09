(ns board-game-local.config.form)

(def register-params [
                 {:name "firstname" 
                  :icon "fa fa-user fa" 
                  :display "First Name"
                  :placeholder "e.g. Alex"}
                 {:name "lastname" 
                  :icon "fa fa-user fa" 
                  :display "Last Name"
                  :placeholder "e.g. Smith"}
                 {:name "dob" 
                  :icon "fa fa-calendar fa" 
                  :display "Date Of Birth"
                  :placeholder "YYYY-MM-DD please, datepicker coming soon"}
                 {:name "gender" 
                  :icon "fa fa-transgender-alt fa" 
                  :display "Gender"
                  :placeholder "Enter the term that best describes your gender"}
                 {:name "confirmpassword" 
                  :icon "fa fa-lock fa-lg" 
                  :display "Confirm Password"
                  :placeholder "You know how account registration works by now"}
                 {:name "location" 
                  :icon "fa fa-map fa" 
                  :display "Location"
                  :placeholder "This won't be listed on your profile"}])

(def login-params [{:name "email" 
                    :icon "fa fa-envelope fa" 
                    :display "Email"}
                   {:name "password" 
                    :icon "fa fa-lock fa-lg" 
                    :display "Password"}])

(def event-params [{:name "name"
                    :icon ""
                    :display "Name"}
                   {:name "description"
                    :icon "fa fa-pencil"
                    :display "Description"}
                   {:name "game"
                    :icon "fa fa-rocket"
                    :display "Game"}
                   {:name "datetime"
                    :icon "fa fa-calendar"
                    :display "Date & Time"}
                   {:name "location"
                    :icon "fa fa-map-marker"
                    :display "Address"
                    :placeholder "This will not be visible until you accept a request"}
                   {:name "tags"
                    :icon "fa fa-tag"
                    :display "Tags"}])
                    
                    
