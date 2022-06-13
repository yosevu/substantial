heading: This is Note 1
subheading: This is the subtitle for Note 1.
date: 2019-12-01
id: note-1
tags: tag1 tag2

This is Note 1!


```clojure
(def ^:dynamic chunk-size 17)

(defn next-chunk [rdr]
  (let [buf (char-array chunk-size)
        s (.read rdr buf)]
  (when (pos? s)
    (java.nio.CharBuffer/wrap buf 0 s))))

(defn chunk-seq [rdr]
  (when-let [chunk (next-chunk rdr)]
    (cons chunk (lazy-seq (chunk-seq rdr)))))
```