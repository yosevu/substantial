heading: Backlink Test
subhead:
date: 2020-05-28
slug: backlink-test
tags

## 40. Interpose a seq

-   The [anonymous function special](https://clojure.org/reference/special_forms#fn) form can take a symbol name, which is can be used to reference the function recursively.

*<span class="timestamp-wrapper"><span class="timestamp">[2020-10-12 Mon]</span></span>*
[Today I learned](/learning-journal) that nested `#()` are not allowed.
```clojure
(defn interpose-a-seq
  "Write a function which separates the items of a sequence by an arbitrary value."
  [sep coll]
  (drop-last (reduce #(conj %1 %2 sep) [] coll)))
```
**Tests**
```clojure
(deftest interpose-a-seq
  (is (= (interpose-a-seq 0 [1 2 3]) [1 0 2 0 3]))
  (is (= (apply str (interpose-a-seq ", " ["one" "two" "three"])) "one, two, three"))
  (is (= (interpose-a-seq :z [:a :b :c :d]) [:a :z :b :z :c :z :d])))
```