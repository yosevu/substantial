(ns substantial.utilities
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

;; TODO use spec for this?
(defn valid-filename [file]
  (re-matches #"[a-z0-9|-]+(.md)" (.getName file)))

(defn get-file [file]
  (if (valid-filename file)
    (slurp file)
    (throw (Exception. (str "Error: " (.getName file) " is not a valid filename.")))))

(defn get-files
  "Gets a list of files from `path`."
  [path]
  (map get-file (rest (file-seq (io/file path)))))

(defn get-config []
  (edn/read-string (slurp "config.edn")))

(comment
  (get-files "notes")
  (re-matches #"[a-z0-9|-]+(.md)" (.getName (second (file-seq (io/file "notes")))))
  (println (count (get-files "notes")))
  (println (:site-title (get-config))))
