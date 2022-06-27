(ns templates.note
  (:require [templates.partials :refer [create-page create-head create-body]]))

(defn create-note-page
  "Create a page for a note."
  [config {:keys [metadata html backlinks]}]
  (create-page
   (create-head config)
   (create-body config [html backlinks])))
