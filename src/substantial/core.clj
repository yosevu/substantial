(ns substantial.core
  (:require
   [clojure.java.io :as io]
   [substantial.notes :refer [get-note get-notes]]
   [substantial.metadata :refer [get-meta-dictionary reset-meta-dictionary]]
   [substantial.utilities :refer [create-config  get-config]]
   [substantial.pages :refer [create-note-page]]))

(def site-path "example-site/")
(def ext "html")

(defn create-note-pages
  "Create note pages with `notes` and `<template>-page`."
  [config notes]
  (for [[page-name note] notes]
    [page-name (create-note-page config note)]))

(defn write-pages
  "Write `pages` to files with."
  [site-path pages]
  (.mkdir (io/file site-path))
  (map (fn [[page-name content]] (spit (str site-path (name page-name) "." ext) content))
       pages))

(defn parse-args
  [args]
  (keyword (first args)))

(defn build
  "Builds html pagse from markdown content

  Requires a config.edn"
  [opts]
  (println "Building site.")
  (get-meta-dictionary)
  (let [config (get-config (:path opts))
        notes (get-notes)
        note-pages (create-note-pages config notes)
        results (write-pages site-path note-pages)]
    (println (str "Built " (count results) " pages."))
    (println "Done.")
    (reset-meta-dictionary)))

(defn create
  "Creates a site with custom configuration.

  - Creates config.edn
  - Creates resources
  - Creates example notes
  - Creates custom README

  Example: clj -Tsub :name example-site"
  [opts]
  (println "Creating site.")
  (create-config))

(comment
  (create {:name "my-notes"})
  (build {:site-url "" :path "template/root/"}))
