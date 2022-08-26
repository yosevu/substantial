(ns substantial.pages
  (:require
   [hiccup.element :refer [link-to]]
   [substantial.metadata :refer [get-meta-dictionary]]
   [substantial.partials :refer [create-page create-head create-body create-date]]))

(defn create-note-page
  "Create a page for a note."
  [config {:keys [html backlinks]}]
  (create-page
   (create-head config)
   (create-body config [html backlinks])))

(defn create-index-link
  "The base URL is prepended to relative paths by GitHub Pages."
  [site-url]
  (fn [[_ metadata]]
    [:li {:class "index-link"}
     (link-to (:id metadata) (:heading metadata))
     (create-date (:date metadata))]))

(defn sort-by-date [items]
  (reverse (sort-by #(get-in (:metadata %) [:date]) items)))

;; TODO reuse substantial.notes.exclude from
(defn exclude-index
  [[id]]
  (contains? #{:index} id))

(defn build-index
  [site-url]
  [:ul {:class "index-links"}
   (map (create-index-link site-url) (sort-by-date (remove exclude-index (get-meta-dictionary))))])

(defn create-index-page
  "Build an index page

  - Create list of all files excluding index.md
  - Render links with dates
  "
  [config]
  (let [html (build-index "notes.yosevu.com")]
    (create-page
     (create-head config)
     (create-body config [html]))))

(comment
  (println (remove exclude-index (get-meta-dictionary))))
  ;; (sort-by-date (get-meta-dictionary)))
  ;; TODO not rendering list of links
  ;; (build-index {:site-url "notes.yosevu.com"}))
  ;; ((create-index-link "notes.yosevu.com") ["hello" {:id "hello" :heading "Hello"}])
  ;; (create-index-page {}))
  ;; (create-index-page {} (build-index {:site-url "notes.yosevu.com"})))
