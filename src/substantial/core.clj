(ns substantial.core
  (:require [clojure.tools.cli :refer [parse-opts]]
            [clojure.java.io :as io]
            [substantial.notes :refer [get-note get-notes]]
            [substantial.metadata :refer [get-meta-dictionary reset-meta-dictionary]]
            [substantial.utilities :refer [create-site create-config create-resources create-example-notes get-config]]
            [templates.note :refer [create-note-page]])
  (:gen-class))

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
  [opts]
  (println "Building site.")
  (get-meta-dictionary)
  (let [config (get-config)
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
  (create-site (str (:name opts)))
  (create-config (str (:name opts)))
  (create-resources (str (:name opts)))
  (create-example-notes (str (:name opts))))

;; (defn -main
;;   "Build `notes` to files with `<template>-pages`."
;;   [& args]
;;   (let [option (parse-args args)]
;;     (println args)
;;     (case option
;;       (:build nil) (build)
;;       :init (create)
;;       :help "help"
;;       (println (str "Invalid option " (print-str option))))))

(comment
  (create-site "example site")
  (get-notes)
  (get-note "backlinks-test")
  (vec (set [[1] [1] [2] [3]]))
  (io/delete-file "note-2.html"))
