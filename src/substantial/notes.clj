(ns substantial.notes
  "Trasform notes in Markdown files to HTML strings.
  
   `get-notes`
   `get-note`
   `get-meta-dictionary`"
  (:require
   [markdown.core :refer [md-to-html-string-with-meta]]
   [markdown.transformers :refer [transformer-vector]]
   [substantial.backlinks :refer [add-backlinks collect-backlinks]]
   [substantial.utilities :refer [slurp-dir]]))

(def default-notes-path "notes")

(defn- sort-by-date [notes]
  (reverse (sort-by #(get-in (:metadata %) [:date]) notes)))

(defn- get-slug [metadata]
  (keyword (first (:slug metadata))))

(defn md-to-html-with-backlinks
  [filestring]
  (md-to-html-string-with-meta
   filestring
   :replacement-transformers
   (into [add-backlinks] transformer-vector)))

(defn create-note
  "(create-note map string)
   Create note map with metadata, html, and backlinks."
  [note-map filestring]
  (let [{:keys [metadata html]} (md-to-html-with-backlinks filestring)
        slug (get-slug metadata)
        backlinks (collect-backlinks filestring)]
    (assoc note-map slug {:metadata metadata
                       :html html
                       :backlinks backlinks})))

(defn get-notes
  "(get-notes)
   (get-notes string)
   Get notes from filepath."
  ([] (get-notes default-notes-path))
  ([notes-path]
   (reduce create-note
           {}
           (slurp-dir notes-path))))

(defn get-note
  "(get-note string)
   Get note by id."
  [slug]
  ((keyword slug) (get-notes)))

(comment
  (sort-by-date (get-notes))

  )