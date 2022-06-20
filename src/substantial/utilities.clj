(ns substantial.utilities
  (:require [clojure.java.io :as io]))

(defn slurp-dir [path]
  (map slurp (rest (file-seq (io/file path)))))
