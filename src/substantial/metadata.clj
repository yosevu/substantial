(ns substantial.metadata
  (:require
   [clojure.string :as string]
   [substantial.utilities :refer [get-files]]))

(def meta-dictionary (atom nil))

(defn parse-metadata-line [line]
  (let [parsed-line (string/split line #":")]
    [(keyword (first parsed-line)) (string/trim (second parsed-line))]))

(defn parse-metadata
  ""
  [filestring]
  (->> (take 3 (string/split filestring #"\n"))
       (map (fn [line] (parse-metadata-line line)))
       (into {})))

(defn set-meta-dictionary
  [path]
  (->> (get-files path)
       (map (fn [filestring] (parse-metadata filestring)))
       (reduce (fn [my-map meta] (assoc my-map (keyword (:id meta)) meta)) {})
       (swap! meta-dictionary (into {}))))

;; FIXME dynamic filepath
(defn get-meta-dictionary [path]
  (if (nil? @meta-dictionary) (set-meta-dictionary path) @meta-dictionary))

(defn reset-meta-dictionary []
  (reset! meta-dictionary nil))

(comment
  (get-meta-dictionary "resources/org/substantial/new/content")
  @meta-dictionary
  (reset-meta-dictionary))
