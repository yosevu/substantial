(ns substantial.utilities
  (:require [clojure.java.io :refer [copy file make-parents]]
            [clojure.edn :as edn]))

(defn valid-filename
  "Matches a markdown file with an alphanumeric filename."
  [file]
  (re-matches #"[a-z0-9|-]+.md" (.getName file)))

(defn write-file
  [filepath content]
  (make-parents filepath)
  (spit filepath content))

(defn get-file
  "Gets a valid file or throws."
  [file]
  (if (valid-filename file)
    (slurp file)
    (throw (Exception. (str "Error: " (.getName file) " is not a valid filename.")))))

(defn get-files
  "Gets a list of valid files from `path`."
  [path]
  (map get-file (->> path file file-seq rest)))

(def default-config
  {:title "my title"
   :author "my author"
   :description "my description"
   :site-url ""})

;; TODO pass opts to merge with default config
(defn create-config
  []
  (spit (str "template/root/config.edn") default-config))

(defn get-config
  "Get config object: `(get-config)`
   Get config entry: `(get-config :site-url)`"
  ([]
   (if (.exists (file "config.edn"))
     (edn/read-string (slurp "config.edn"))
     (do (spit "config.edn" default-config)
         (edn/read-string (slurp "config.edn")))))
  ([entry-key]
   (entry-key (get-config))))

(comment)
