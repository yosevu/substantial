(ns substantial.core
  (:require [clojure.java.io :as io]
            [substantial.notes :refer [get-note get-notes]]
            [substantial.metadata :refer [get-meta-dictionary reset-meta-dictionary]]
            [templates.note :refer [note-page]]))

(def dir "public")
(def ext "html")

(defn note-pages
  "Create note pages with `notes` and `<template>-page`."
  [notes]
  (for [[page-name note] notes]
    [page-name (note-page note)]))

(defn write-page
  "Write `page-name` and `content` to `dir`."
  [[page-name content]]
  (spit (str dir "/" (name page-name) "." ext) content))

(defn write-pages
  "Write `pages` to files with `write-page`."
  [pages]
  (map write-page pages))

(defn -main
  "Build `notes` to files with `<template>-pages`."
  []
  (println "Building site...")
  (get-meta-dictionary)
  (let [write-results (write-pages (note-pages (get-notes)))]
    (println (str "Built " (count write-results) " pages.")))
  (reset-meta-dictionary))

(comment
  (write-page (first (get-notes "notes"))) 
  (get-notes)
  (get-note "backlinks-test")
  (vec (set [[1] [1] [2] [3]]))
  (io/delete-file "note-2.html")
)