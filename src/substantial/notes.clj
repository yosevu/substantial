(ns substantial.notes
  "Trasform notes in Markdown files to HTML strings.

   `get-notes`
   `get-note`
   `get-meta-dictionary`"
  (:require
   [markdown.core :refer [md-to-html-string-with-meta]]
   [markdown.transformers :refer [transformer-vector]]
   [substantial.backlinks :refer [add-backlinks collect-backlinks]]
   [substantial.utilities :refer [get-files]]))

(defn- sort-by-date [notes]
  (reverse (sort-by #(get-in (:metadata %) [:date]) notes)))

(defn- get-id [metadata]
  (keyword (first (:id metadata))))

(defn md-to-html-with-backlinks
  [filestring]
  (md-to-html-string-with-meta
   filestring
   :replacement-transformers
   (into [add-backlinks] transformer-vector)
   :footnotes? true))

(defn create-note
  "(create-note map string)
   Create note map with metadata, html, and backlinks."
  [note-map filestring]
  (let [{:keys [metadata html]} (md-to-html-with-backlinks filestring)
        id (get-id metadata)
        backlinks (collect-backlinks filestring)]
    (assoc note-map id {:metadata metadata
                        :html html
                        :backlinks backlinks})))

;; TODO create default content path
(defn get-notes
  "(get-notes)
   (get-notes string)
   Get notes from filepath.

   content/ is the default path"
  [content-path]
  (reduce create-note
          {}
          (get-files content-path)))

;; TODO update pass path
(defn get-note
  "(get-note string)
   Get note by id."
  [id]
  ((keyword id) (get-notes "template/content/")))

(comment
  (get-notes "template/content/")
  (sort-by-date (get-notes "template/content/")))
