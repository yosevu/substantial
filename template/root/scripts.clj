(ns scripts
  (:require [clojure.java.shell :refer [sh]]
            [clojure.string :refer [split trim]]))

(defn- git-subtree []
  (trim (:out (apply sh (split "git subtree split --prefix public main" #" ")))))

(defn- git-push []
  (let [commit-sha (git-subtree)]
    (apply sh (split (str "git push origin " commit-sha ":gh-pages --force") #" "))))

(defn upgrade [_]
  (println "Upgrading substantial."))

(defn publish [_]
  (println (:out (sh "clj" "-M:build")))
  (println "Publishing site.")
  (let [{:keys [out err]} (git-push)]
    (when (seq out) (print out))
    (when (seq err) (print err)))
  (println "Published site."))
