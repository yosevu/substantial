(ns substantial.utilities
  (:require [clojure.java.io :as io]
            [clojure.edn :as edn]))

(defn valid-filename
  "Matches a markdown file with an alphanumeric filename."
  [file]
  (re-matches #"[a-z0-9|-]+.md" (.getName file)))

(defn get-file
  "Gets a valid file or throws."
  [file]
  (if (valid-filename file)
    (slurp file)
    (throw (Exception. (str "Error: " (.getName file) " is not a valid filename.")))))

(defn get-files
  "Gets a list of valid files from `path`."
  [path]
  (map get-file (->> path io/file file-seq rest)))

(defn get-config
  "Get config object: `(get-config)`
   Get config entry: `(get-config :site-url)`"
  ([] (edn/read-string (slurp "config.edn")))
  ([entry-key] (entry-key (get-config))))

(comment)
