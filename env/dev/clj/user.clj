(ns user
  (:require [mount.core :as mount]
            board-game-local.core))

(defn start []
  (mount/start-without #'board-game-local.core/repl-server))

(defn stop []
  (mount/stop-except #'board-game-local.core/repl-server))

(defn restart []
  (stop)
  (start))


