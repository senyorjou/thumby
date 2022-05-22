(ns thumby.handlers)


(defn ping [request]
  {:status 200
   :body {:status "ok"
          :now (java.time.LocalDateTime/now)}})
