(ns substantial.core
  (:require [substantial.notes :refer [get-notes]]
            [templates.note :refer [note-page]]))

(defn note-pages
  "Create pages for notes."
  [notes]
  (for [[page-name note] notes]
    [page-name (note-page note)]))

(defn write-page [[page-name content]]
  (spit (str "public/" (name page-name) ".html") content))

(defn write-pages [notes]
  (map write-page (note-pages notes)))

(defn -main
  "Write note pages to files."
  []
  (write-pages (get-notes "notes"))
  (println "Called (-main)"))

(comment
  ;; (get-notes "notes")
  ;; (for [[_ note] (get-notes "notes")] note)
  ;; (let [notes (get-notes "notes")] notes)
  ;; (note-pages (get-notes "notes"))
  ;; (map write-page (note-pages (get-notes "notes")))
  (-main)
  )