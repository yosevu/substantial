(ns substantial.scripts
  ""
  (:require [clojure.java.shell :refer [sh]]
            [clojure.string :refer [split trim]]))

(defn- git-subtree
  "Requires static asset pathname to be `static`."
  []
  (trim (:out (apply sh (split "git subtree split --prefix static main" #" ")))))

(defn- git-push []
  (let [commit-sha (git-subtree)]
    (apply sh (split (str "git push origin " commit-sha ":gh-pages --force") #" "))))

(defn update [_]
  (println "Not implemented."))

(defn publish
  "Requires remote named gh-pages."
  [_]
  (println (:out (sh "clj" "-X:build")))
  (println "Publishing site.")
  (let [{:keys [out err]} (git-push)]
    (when (seq out) (print out))
    (when (seq err) (print err)))
  (println "Published site."))
