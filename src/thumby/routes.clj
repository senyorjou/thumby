(ns thumby.routes
  (:require [thumby.handlers :as handle]))

(def ping
 {:name ::ping
  :get handle/ping})
