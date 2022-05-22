(ns thumby.core
  (:require
   [muuntaja.core :as m]
   [reitit.ring :as ring]
   [reitit.ring.middleware.exception :refer [exception-middleware] ]
   [reitit.ring.middleware.muuntaja :as mw]
   [org.httpkit.server :refer [run-server]]
   [thumby.routes :as routes])
  (:gen-class))


(def router-meta {:muuntaja m/instance
                  :middleware [mw/format-request-middleware
                               mw/format-response-middleware
                               exception-middleware
                               mw/format-negotiate-middleware]})

(def router
  (ring/router
    [["/api/ping" routes/ping]
     ["/api/v/:url" ::convert-video]]
    {:data router-meta}))

(def app
  (ring/ring-handler router))


(defn -main
  "I'm a smple server"
  [& _args]
  (println "Starting server at port 8080")
  (run-server app {:port 8080}))

(comment
  (ring/match-by-path router "/api/ping"))
