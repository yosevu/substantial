(ns substantial.utilities
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(defn get-files [path]
  (map slurp (rest (file-seq (io/file path)))))

(defn get-config []
  (edn/read-string (slurp "config.edn")))

(comment
  (println (:site-title (get-config))))
