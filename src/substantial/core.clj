(ns substantial.core
  (:require
   [clojure.java.io :as io]
   [clojure.string :refer [ends-with?]]
   [substantial.notes :refer [get-note get-notes]]
   [substantial.metadata :refer [get-meta-dictionary reset-meta-dictionary]]
   [substantial.utilities :refer [create-config  get-config write-file]]
   [substantial.pages :refer [create-index-page create-note-page]]))

(defn create-note-pages
  "Create note pages with `notes` and `<template>-page`."
  [config notes]
  (for [[page-name note] notes]
    [page-name (create-note-page config note)]))

(defn write-pages
  "Write `pages` to files with."
  [path pages]
  (map (fn [[page-name content]]
         (spit (str path "/" (name page-name) ".html") content))
       pages))

(defn parse-args
  [args]
  (keyword (first args)))

(defn get-opts
  "Gets map of option paths to resources
   template-path must includes `/` e.g. `resources/org/substantial/new/`"
  [{:keys [template-path]}]
  {:root-path (str template-path (if (empty? template-path) "" "root/"))
   :content-path (str template-path "content/")
   :static-path (str template-path "static/")})

(defn html-file?
  [file]
  (ends-with? (.getName file) ".html"))

(defn count-pages
  [static-path]
  (count (filter html-file? (file-seq (io/file static-path)))))

;; TODO find idiomatic way to merge default opts
(defn build
  "Builds html pagse from markdown content

   Requires a site config.edn. Create a site config.edn with `create`.

   - `(build)` with no args is for template project.
   - `(build args)` with args is for template development."
  ([] (build {:template-path ""}))
  ([args]
   (println "Building site.")
   (get-meta-dictionary (:content-path (get-opts args)))
   (let [opts (get-opts args)
         config (get-config)
         notes (get-notes (:content-path opts))
         note-pages (create-note-pages config notes)
         index (create-index-page config)]
     (write-pages (:static-path opts) note-pages)
     (write-file (str (:static-path opts) "index.html") index)
     (println (str "Built " (count-pages (:static-path opts)) " pages."))
     (reset-meta-dictionary))))

(defn create
  "Creates site config using `template-path` from args
   or at project root if called with no args.
   `template-path` must include a `/`."
  ([] (create {:template-path ""}))
  ([args]
   (println "Creating site config.")
   (create-config (:root-path (get-opts args)))))

(def test-args {:template-path "resources/org/substantial/new/"})

(comment
  (get-opts {})
  (get-opts test-args)
  (create)
  (create test-args)
  (build)
  (build test-args))
