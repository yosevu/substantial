(ns substantial.scripts
  ""
  (:require [clojure.java.shell :refer [sh]]
            [clojure.string :refer [split trim]]))

(defn- git-subtree [path]
  (trim (:out (apply sh (split (str "git subtree split --prefix " path " main") #" ")))))

(defn- git-push [path]
  (let [commit-sha (git-subtree path)]
    (apply sh (split (str "git push origin " commit-sha ":gh-pages --force") #" "))))

(defn update [_]
  (println "Not implemented."))

(defn publish
  "Requires remote named gh-pages."
  [_]
  (println (:out (sh "clj" "-X:build")))
  (println "Publishing site.")
  (let [{:keys [out err]} (git-push "static")]
    (when (seq out) (print out))
    (when (seq err) (print err)))
  (println "Published site."))
