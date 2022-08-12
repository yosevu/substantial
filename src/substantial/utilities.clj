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
  (println file)
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
   :site-url "https://notes.myname.com"})

;; TODO pass opts to merge with default config
(defn create-config
  [path]
  (spit (str path "/" "config.edn") default-config))

;; TODO run with different config path from core vs template
;; Default to template, current directory
(defn get-config
  "Get config object: `(get-config)` with default root path
   Get config entry: `(get-config path)` path arg."
  ([]
   (edn/read-string (slurp  "/" "config.edn")))
  ([path]
   (println (str path "/" "config.edn"))
   (edn/read-string (slurp (str path "/" "config.edn")))))

(defn get-config-entry
  ([entry-key]
   (entry-key (get-config)))
  ([entry-key path]
   (println "get-config-entry" (get-config path))
   (entry-key (get-config path))))

(comment
  (get-files "content")
  (get-config "resources/org/substantial/new/root")
  (get-config-entry :site-url "resources/org/substantial/new/root"))
