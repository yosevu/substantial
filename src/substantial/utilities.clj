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
   :description "my description"})

(defn create-config
  [site-path]
  (.mkdir (file site-path))
  (write-file (str site-path "/config.edn") default-config))

(defn create-example-notes
  [site-path]
  (let [example-index "notes/index.md"
        example-note-1 "notes/my-note.md"
        example-note-2 "notes/another-note.md"]

    (make-parents (str site-path example-index))
    (make-parents (str site-path example-note-1))
    (make-parents (str site-path example-note-2))

    (copy (file example-index) (file (str site-path example-index)))
    (copy (file example-note-1) (file (str site-path example-note-1)))
    (copy (file example-note-2) (file (str site-path example-note-2)))))

(defn create-resources
  "Create resources including:
  - css
  - js"
  [site-path]
  (let [main-css-path "resources/css/main.css"
        highlight-css-path "resources/css/highlight.css"
        highlight-js-path "resources/js/highlight.js"]

    (make-parents (str site-path main-css-path))
    (make-parents (str site-path highlight-css-path))
    (make-parents (str site-path highlight-js-path))

    (copy (file main-css-path) (file (str site-path main-css-path)))
    (copy (file highlight-css-path) (file (str site-path highlight-css-path)))
    (copy (file highlight-js-path) (file (str site-path highlight-js-path)))))

(defn get-config
  "Get config object: `(get-config)`
   Get config entry: `(get-config :site-url)`"
  ([] (edn/read-string (slurp "config.edn")))
  ([entry-key] (entry-key (get-config))))

(comment
  (write-file "example-notes/config.edn" "{}")
  (create-config "example-site/")
  (create-resources "example-site/")
  (create-example-notes "example-site"))
