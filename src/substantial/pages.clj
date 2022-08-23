(ns substantial.pages
  (:require
   [hiccup.element :refer [link-to]]
   [substantial.metadata :refer [get-meta-dictionary]]
   [substantial.partials :refer [create-page create-head create-body]]))

(defn create-note-page
  "Create a page for a note."
  [config {:keys [html backlinks]}]
  (create-page
   (create-head config)
   (create-body config [html backlinks])))

(defn create-index-link
  [site-url]
  (fn [[_ metadata]]
    [:li (link-to (str site-url "/" (:id metadata)) (:heading metadata))]))

(defn build-index
  [site-url]
  [:ul (map (create-index-link site-url) (get-meta-dictionary))])

(defn create-index-page
  "Build an index page

  - Create list of all files
  - Render links with dates
  "
  [config]
  (let [html (build-index "notes.yosevu.com")]
    (create-page
     (create-head config)
     (create-body config [html]))))

(comment)
  ;; TODO not rendering list of links
  ;; (build-index {:site-url "notes.yosevu.com"})
  ;; ((create-index-link "notes.yosevu.com") ["hello" {:id "hello" :heading "Hello"}])
  ;; (create-index-page {}))
  ;; (create-index-page {} (build-index {:site-url "notes.yosevu.com"})))
