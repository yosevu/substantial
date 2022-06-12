(ns substantial.core
  (:require [substantial.notes :refer [get-notes]]
            [templates.note :refer [note-page]]))

(defn note-pages
  "Create pages for notes."
  [notes]
  (for [[_ note] notes]
    (note-page note)))

(defn -main
  "Write note pages to files."
  []
  (println "Called (-main)"))

(comment
  (get-notes "notes")
  (for [[_ note] (get-notes "notes")]
    note)
  (note-pages (get-notes "notes"))
  ;; (map note-page (get-notes "notes"))
  (-main))