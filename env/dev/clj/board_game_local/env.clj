(ns board-game-local.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [board-game-local.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[board-game-local started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[board-game-local has shut down successfully]=-"))
   :middleware wrap-dev})
