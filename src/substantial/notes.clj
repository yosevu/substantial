(ns substantial.notes
  (:require [clojure.java.io :as io]
            [clojure.string :as string]
            [markdown.core :refer [md-to-html-string-with-meta]]
            [markdown.transformers :refer [transformer-vector]]))

(def site-url "https://notes.yosevu.com")
(def default-notes-path "notes")

(defn slurp-dir [path]
  (map slurp (rest (file-seq (io/file path)))))

(def backlink-match 
  " Matches a relative backlink.
   
    Example:
   
    before text [I am a backlink](/i-am-a-backlink) after text
            
    (.*)(\]\()(\/)([a-z0-9|-]+)(.*)
    $1  $2    $3  $4           $5
   
    $1: (.*)         : before text [I am a backlink
    $2: (\]\()       : ](
    $3: (\/)         : /
    $4: ([a-z0-9|-]+): i-am-a-backlink
    $5: (.*)         : ) after text
  "
  #"(.*)(\]\()(\/)([a-z0-9|-]+)(.*)")

(defn backlink-replacement
  "Prepends: site-url to relative backlink.
   
   Example:

   [I am a backlink](/i-am-a-backlink)
   [I am a backlink](https://<site-url>/i-am-a-backlink.html)
  "
  [site-url]
  (str "$1" "$2" site-url "$3" "$4" ".html" "$5"))

(defn add-backlink [backlink-text state]
  [(string/replace backlink-text backlink-match
                   (backlink-replacement site-url)) state])
(defn md-to-html-with-backlinks
  [filestring]
  (md-to-html-string-with-meta
    filestring
    :replacement-transformers
    (into [add-backlink] transformer-vector)))

(defn md->html [filestrings]
  (map md-to-html-with-backlinks filestrings))
  
(defn sort-by-date [notes]
  (reverse (sort-by #(get-in (:metadata %) [:date]) notes)))

(defn into-map [notes-map note]
  (assoc notes-map (keyword (first (:slug (:metadata note)))) note))

(defn get-notes
  ([] (get-notes default-notes-path))
  ([notes-path]
   (reduce into-map {} (sort-by-date (md->html (slurp-dir notes-path))))))

(defn get-note [slug]
  ((keyword slug) (get-notes)))

(comment
  (let [backlink-text "abc[Today I learned](/learning-journal)123"]
    (string/replace backlink-text backlink-match
                  (backlink-replacement site-url)))
  ;; => "[Today I learned](https://notes.yosevu.com/learning-journal.html)"

  ;; (md-to-html-string-with-meta
  ;;  backlink-text
  ;;  :replacement-transformers
  ;;  [add-backlink]) 
  )
