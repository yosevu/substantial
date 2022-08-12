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
   :site-url "https://notes.myname.com"})

;; TODO pass opts to merge with default config
(defn create-config
  "path includes `/`"
  [path]
  (spit (str path "config.edn") default-config))

;; TODO make dynamic based on env.
(defn get-config
  "Get config object: `(get-config)` with default root path
   Get config entry: `(get-config path)` path arg.
   path includes `/`"
  []
  (if (.exists (file "config.edn"))
    (edn/read-string (slurp "config.edn"))
    (edn/read-string (slurp "resources/org/substantial/new/root/config.edn"))))

(comment
  (get-config))
