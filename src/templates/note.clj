(ns templates.note
  (:require [templates.partials :refer [page head body]]))


(defn note-page
  "Create a page for a note."
  [{:keys [metadata html]}]
  (page
   (head {:title "Yosevu's notes" :desc "Yosevu's notes."})
   (body html)))