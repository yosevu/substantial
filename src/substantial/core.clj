(ns substantial.core
  (:require [clojure.java.io :as io]
            [substantial.notes :refer [get-note get-notes]]
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
  "Write `notes` to files with `<template>-pages`."
  [notes]
  (let [write-results (write-pages (note-pages notes))]
    (println (str "Wrote " (count write-results) " pages."))))

(comment
  (let [notes (get-notes "notes")] 
    (println notes)
    (write-page (first notes)))
  
  (get-notes)
  (get-note "backlinks-test")
  (-main (get-notes))
  
  (io/delete-file "note-2.html")

  )