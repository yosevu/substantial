(ns substantial.notes
  (:require [clojure.java.io :as io]
            [clojure.string :as string]
            [markdown.core :refer [md-to-html-string-with-meta]]))

(def site-url "https://notes.yosevu.com/")

(defn slurp-dir [path]
  (map slurp (rest (file-seq (io/file path)))))

(def backlink-match #"(.*)(\]\()([a-z0-9|-]+)(.*)")

(defn backlink-replacement [site-url]
  (str "$1" "$2" site-url "$3" ".html" "$4"))

(defn add-backlink [backlink-text state]
  [(string/replace backlink-text backlink-match
                   (backlink-replacement site-url)) state])
(defn md-to-html-with-backlinks
  [filestring]
  (md-to-html-string-with-meta
    filestring
    :replacement-transformers
    [add-backlink]))

(defn md->html [filestrings]
  (map md-to-html-with-backlinks filestrings))
  
(defn sort-by-date [notes]
  (reverse (sort-by #(get-in (:metadata %) [:date]) notes)))

(defn into-map [notes-map note]
  (assoc notes-map (keyword (first (:id (:metadata note)))) note))

(defmacro get-notes [path]
  (reduce into-map {} (sort-by-date (md->html (slurp-dir path)))))

(defn -main []
  (println "Called (-main)"))

(comment
  (def backlink-text "abc[Today I learned](learning-journal)123")

  (string/replace backlink-text backlink-match
                  (backlink-replacement site-url))
  ;; => "[Today I learned](https://notes.yosevu.com/learning-journal.html)"

  ;; (md-to-html-string-with-meta "abc[Today I learned](/learning-journal)123" :custom-transformers [add-backlink])
  (md-to-html-string-with-meta
   backlink-text
   :replacement-transformers
   [add-backlink])
  
  (md-to-html-with-backlinks (first (get-notes "notes")))
  
  ;; (defn capitalize [text state]
  ;;   (println state)
  ;;   [(.toUpperCase text) state])
  ;; (md-to-html-string-with-meta "#foo" :custom-transformers [capitalize])
  )
