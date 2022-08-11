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
  [path pages]
  (map (fn [[page-name content]] (spit (str path (name page-name) "." ext) content))
       pages))

(defn parse-args
  [args]
  (keyword (first args)))

(defn build
  "Builds html pagse from markdown content

  Requires a config.edn"
  [opts]
  (println "Building site.")
  (get-meta-dictionary (:content-path opts))
  (let [config (get-config (:root-path opts))
        notes (get-notes (:content-path opts))
        note-pages (create-note-pages config notes)
        results (write-pages (:assets-path opts) note-pages)]
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
  (create-config (:root-path opts)))

;; FIXME reconsider using "/" in direcotry name move it to utility functions
(comment
  (create {:root-path "template/root/"})
  (create {}) ;; "." from template root
  (build {:root-path "template/root/"
          :content-path "template/content/"
          :assets-path "template/static/"})
  (build {:root-path ""
          :content-path "content/"
          :assets-path "static/"}))
