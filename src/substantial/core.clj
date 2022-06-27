(ns substantial.core
  (:require [clojure.java.io :as io]
            [substantial.notes :refer [get-note get-notes]]
            [substantial.metadata :refer [get-meta-dictionary reset-meta-dictionary]]
            [substantial.utilities :refer [get-config]]
            [templates.note :refer [create-note-page]]))

(def dir "public")
(def ext "html")

(defn create-note-pages
  "Create note pages with `notes` and `<template>-page`."
  [config notes]
  (for [[page-name note] notes]
    [page-name (create-note-page config note)]))

(defn write-pages
  "Write `pages` to files with."
  [pages]
  (map (fn [[page-name content]] (spit (str dir "/" (name page-name) "." ext) content))
       pages))

(defn -main
  "Build `notes` to files with `<template>-pages`."
  []
  (println "Building site.")
  (get-meta-dictionary)
  (let [config (get-config)
        notes (get-notes)
        note-pages (create-note-pages config notes)
        results (write-pages note-pages)]
    (println (str "Built " (count results) " pages.")))
  (reset-meta-dictionary))

(comment
  (-main)
  (get-notes)
  (get-note "backlinks-test")
  (vec (set [[1] [1] [2] [3]]))
  (io/delete-file "note-2.html"))
