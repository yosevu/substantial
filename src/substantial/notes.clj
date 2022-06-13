(ns substantial.notes
  (:require [clojure.java.io :as io]
            [markdown.core :refer [md-to-html-string-with-meta]]))

(defn slurp-dir [path]
  (map slurp (rest (file-seq (io/file path)))))

(defn md->html [files]
  (map md-to-html-string-with-meta files))
  
(defn sort-by-date [notes]
  (reverse (sort-by #(get-in (:metadata %) [:date]) notes)))

(defn into-map [notes-map note]
  (assoc notes-map (keyword (first (:id (:metadata note)))) note))

(defmacro get-notes [path]
  (reduce into-map {} (sort-by-date (md->html (slurp-dir path)))))

(defn -main []
  (println "Called (-main)"))
