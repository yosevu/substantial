(ns substantial.core-test
  (:require [clojure.test :refer [deftest is]]))

(deftest addition
  (is (= 5 (+ 2 2)))
  (is (= 7 (+ 3 4))))

(defn run [_]
  (println "Running tests...")
  (addition))

