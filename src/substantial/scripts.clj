(ns substantial.scripts
  (:require [clojure.java.shell :refer [sh]]
            [clojure.string :refer [split trim]]))

(defn git-subtree []
  (trim (:out (apply sh (split "git subtree split --prefix public main" #" ")))))
  
(defn git-push []
  (let [commit-sha (git-subtree)]
    (apply sh (split (str "git push origin " commit-sha ":gh-pages --force") #" "))))
  
(defn publish [_]
  (println (:out (sh "clj" "-M:build")))
  (println "Publishing site.")
  (let [{:keys [out err]} (git-push)]
    (when (seq out) (print out))
    (when (seq err) (print err)))
  (println "Published site."))

(defn init
  "Initializes substantial notes with custom configuration.

  - Creates custom config
  - Creates custom README"
  [_]
  (println "Initializing notes."))

(defn upgrade
  "Upgrades substantial.

  git sparse-checkout
  https://github.blog/2020-01-17-bring-your-monorepo-down-to-size-with-sparse-checkout/

  - Updtes deps.edn
  - Updates src
  - Updates test
  - Updates public/css
  - Updates public/js"
  [_]
  (println "Upgrading substantial."))

(comment)
