heading: 4Clojure
date: 2020-05-28
id: 4clojure

# 4Clojure Exercises

Annotated solutions to 4Clojure&rsquo;s Clojure problems.

## 1. Nothing but the Truth

This is a clojure form. Enter a value which will make the form evaluate to true.
```clojure
(defn nothing-but-truth []
  (= true true))
```
## 2. Simple Math

If you are not familiar with [polish notation](http://en.wikipedia.org/wiki/Polish_notation), simple arithmetic might seem confusing.
```clojure
(=
  (- 10 (* 2 3))4)
```
## 3. Intro to strings

Clojure strings are Java strings. This means that you can use any of the Java string methods on Clojure strings.
```clojure
(= "HELLO WORLD"
    (.toUpperCase "hello world"))
```
## 4. Intro to lists

Lists can be constructed with either a function or a quoted form.
```clojure
(= (list :a :b :c)
   '(:a :b :c))
```
## 5. Lists: conj

When operating on a list, the conj function will return a new list with one or more items &ldquo;added&rdquo; to the front.
```clojure
(= '(1 2 3 4)
    (conj '(2 3 4)))

(= '(1 2 3 4)
    (conj '(3 4) 2 1))
```
## 6. Intro to vectors

Vectors can be constructed several ways. You can compare them with lists.
```clojure
(= [:a :b :c]
    (list :a :b :c)
    (vec '(:a :b :c))
    (vector :a :b :c))
```
## 7. Vectors: conj

When operating on a Vector, the conj function will return a new vector with one or more items &ldquo;added&rdquo; to the end.
```clojure
(= [1 2 3 4]
    (conj [1 2 3] 4))

(= [1 2 3 4]
    (conj [1 2] 3 4))
```
`conj` is a clipping, (an apocope) of conjoin.

## 8. Intro to sets

Sets are collections of unique values.
```clojure
(= #{:a :b :c :d}
    (set '(:a :a :b :c :c :c :c :d :d)))
(= #{:a :b :c :d}
    (clojure.set/union #{:a :b :c} #{:b :c :d}))
```
&ldquo;In [set theory](/set-theory.md), the union of a collection of sets is the set of all elements in the collection.&rdquo; - [Union (set theory), Wikipedia](https://en.wikipedia.org/wiki/Union_(set_theory))

In the second example above, there are two sets in the collection.

1.  `#{:a :b :c}`
2.  `#{:b :c :d}`

The union of the sets A and B (A ∪ B) is the set `#{:a :b :c :d}`.

`=` returns `true` when called as a unary function (with one argument): `(= 1)`.

## 9. Sets: conj

When operating on a set, the conj function returns a new set with one or more keys &ldquo;added&rdquo;.
```clojure
(= #{1 2 3 4}
    (conj #{1 4 3} 2))
```
## 10. Intro to maps

Maps store key-value pairs. Both maps and keywords can be used as lookup functions. Commas can be used to make maps more readable, but they are not required.
```clojure
(= 20
    ((hash-map :a 10 :b 20 :c 30) :b))

(= 20
    (:b {:a 10 :b 20 :c 30}))
```
## 11. Maps: conj

When operating on a map, the conj function returns a new map with one or more key-value pairs &ldquo;added&rdquo;.
```clojure
(= {:a 1, :b 2, :c 3}
   (conj {:a 1} {:b 2} [:c 3]))
```
## 12. Intro to Sequences

All Clojure collections support sequencing. You can operate on sequences with functions like first, second, and last.
```clojure
(= 3 (first '(3 2 1)))
(= 3 (second [2 3 4]))
(= 3 (last (list 1 2 3)))
```
## 13. Sequences: rest
```clojure
(= [20 30 40]
    (rest [10 20 30 40]))
```
`rest` returns a sequence. `[20 30 40]` and `'(20 30 40)` are equal because they are the same sequence.

## 14. Intro to Functions

Clojure has many different ways to create functions.

```clojure
(= 8
    ((fn add-five [x] (+ x 5)) 3))

(= 8
    ((fn [x] (+ x 5)) 3))

(= 8
    (#(+ % 5) 3))

(= 8
    ((partial + 5) 3))
```
There is also `defn`:
```clojure
(defn add-five
  "Add five to a number."
  [x]
  (+ x 5))

(= 8
    (add-five 3))
```
## 15. Double Down

Write a function which doubles a number.
```clojure
(defn double-down [n]
  (+ n n)) ; (* n 2)

(= (double-down 2) 4)
(= (double-down 3) 6)
(= (double-down 11) 22)
(= (double-down 7) 14)
```
## 16. Hello World

**Function**
```clojure
(defn hello-world
  "Hello, World!"
  [name]
  (str "Hello, " name "!"))

(= (hello-world "Dave") "Hello, Dave!")
(= (hello-world "Jenn"), "Hello, Jenn!")
(= (hello-world "Rhea"), "Hello, Rhea!")
```
**Anonymous function**
```clojure
  (fn [name] (str "Hello, " name "!"))
```
**Shorthand anonymous function**
```clojure
#(str "Hello, " % "!")
```
## 17. Sequences: map
```clojure
(= '(6 7 8)
    (map #(+ % 5) '(1 2 3)))
```
`map` is an essential [tool for thinking functionally](/functional-thinking). It takes two arguments: a function (f) and a sequence (s). Map returns a new sequence consisting of the result of applying f to each item of s. Do not confuse the map function with the map data structure.

## 18. Sequences: filter
```clojure
(= '(6 7)
    (filter #(> % 5) '(3 4 5 6 7)))
```
`filter` is an essential [tool for thinking functionally](/functional-thinking). It takes two arguments: a predicate function (f) and a sequence (s). Filter returns a new sequence consisting of all the items of s for which (f item) returns true.

## 19. Last element

Write a function which returns the last element in a sequence.

Special Restrictions: last

**Iteration 1**
```clojure
(defn last-element [s]
  (nth s (- (count s) 1)))
```
**Other solutions**
```clojure
(defn last-element [s]
  (nth s (dec (count s))))
```
## 20. Penultimate element

Write a function which returns the second to last element from a sequence.

**Iteration 1**
```clojure
(defn penultimate-element [s]
  (nth s (- (count s) 2)))
```
**Other solutions**
```clojure
(defn penultimate-element [s]
  (comp second reverse))
```
## 21. Nth element

Write a function which returns the Nth element from a sequence.

Special Restrictions: nth

**Iteration 1**
```clojure
(defn nth-element [s index]
  (get (vec s) index))
```
**Other solutions**
```clojure
(defn nth-element [coll n]
  (first (drop n coll)))
```
## 22. Count a sequence

Write a function which returns the total number of elements in a sequence.

Special Restrictions: count

**Iteration 1**
```clojure
(defn count-a-sequence [s]
  (reduce (fn [total item]
            (inc total)) 0 s))
```
**Other solutions**
```clojure
(defn count-a-sequence [s]
  (reduce + (map (constantly 1) s)))
```
## 23. Reverse a sequence

Write a function which reverses a sequence.

Special Restrictions: reverse rseq

**Iteration 1**
```clojure
(defn reverse-a-sequence [s])
```
## 24. Sum it all up

Write a function which returns the sum of a sequence of numbers.

**Iteration 1**
```clojure
(defn sum-it-all-up [nums]
  (reduce + nums))
```
## 25. Find the odd numbers

Write a function which returns only the odd numbers from a sequence.
```clojure
(defn find-the-odd-numbers [nums]
  (filter odd? nums))
```
## 26. Fibonacci sequence

*<span class="timestamp-wrapper"><span class="timestamp">[2020-10-17 Sat]</span></span>*

**Solution**
```clojure
(defn fib
  "Generate a lazy fibonacci sequence"
  ([]
    (fib 1 1))
  ([a b]
    (lazy-seq (cons a (fib b (+ a b))))))

(defn fibonacci-sequence
  "Write a function which returns the first X fibonacci numbers."
  [x]
  (take x (fib)))
```
**4Clojure Solution**
```clojure
#(take % ((fn fib
              ([]
              (fib 1 1))
              ([a b]
              (lazy-seq (cons a (fib b (+ a b))))))))
```
**Tests**
```clojure
(deftest test-fibonacci-sequence
  (is (= (fibonacci-sequence 3) '(1 1 2)))
  (is (= (fibonacci-sequence 6) '(1 1 2 3 5 8)))
  (is (= (fibonacci-sequence 8) '(1 1 2 3 5 8 13 21))))
```
**Notes**

-   The [anonymous function special](https://clojure.org/reference/special_forms#fn) form can take a symbol name, which is can be used to reference the function recursively.
-   Each arity is a list in multi-arity function definitions.

**Questions**

-   How is a the base case resolved in a lazy sequence?
    `(cons 1 (cons 1 (cons 2 (cons 3 ...)))`

## 27. Palindrome detector

*<span class="timestamp-wrapper"><span class="timestamp">[2020-10-14 Wed]</span></span>*
```clojure
(defn palindrome-detector
  "Write a function which returns true if the given sequence is a palindrome."
  [x]
  (= (seq x) (reverse (seq x))))
```
**Short version**
```clojure
#(= (seq %) (reverse (seq %)))
```
**Tests**
```clojure
(deftest palindrome-detector
  (is (false? (palindrome-detector '(1 2 3 4 5))))
  (is (true? (palindrome-detector "racecar")))
  (is (true? (palindrome-detector [:foo :bar :foo])))
  (is (true? (palindrome-detector '(1 1 3 3 1 1))))
  (is (false? (palindrome-detector '(:a :b :c)))))
```
## 28. Flatten a sequence

Write a function which flattens a sequence.

Special Restrictions: flatten

**Iteration 1**
```clojure
(defn my-flatten [coll]
  (seq (reduce (fn [result item]
            (if (coll? item)
                (into result (my-flatten (first (list item))))
                (conj result item)))
          []
          coll)))
```
**Iteration 2**
```clojure
(defn my-flatten [item]
  (cond
    (coll? item) (mapcat my-flatten item)
    :else [item]))
```
## 36. Let it be

Can you bind x, y, and z so that these are all true?
```clojure
(deftest let-it-be
  (is (= 10 (let [x 7 y 3 z 1] (+ x y))))
  (is (=  4 (let [x 7 y 3 z 1] (+ y z))))
  (is (=  1 (let [x 7 y 3 z 1] z))))
```
`let` binds pairs of symbol-value pairs in a vector.

## 37. Regular expressions

Regex patterns are supported with a special reader macro.
```clojure
(deftest reglar-expressions
  (is (= "ABC" (apply str (re-seq #"[A-Z]+" "bA1B3Ce")))))
```
`re-seq` returns a sequence of matches in a string.

## 38. Maximum value

*<span class="timestamp-wrapper"><span class="timestamp">[2020-10-10 Sat]</span></span>*
```clojure
(defn maximum-value
  "Takes a variable number of parameters and returns the maximum value."
  [& vals]
  (reduce #(if (> %1 %2) %1 %2) vals))
```
**Tests**

    (deftest maximum-value
      (is (= (maximum-value 1 8 3 4) 8)))

`reduce` is an essential [tool for thinking functionally](/functional-thinking). It has two arities. The first takes a function and a collection and the second takes a function, an initial value, and a collection. The function passed to reduce has to parameters, the result and the current item in the collection. If there is an initial value argument, it is passed as the result parameter. If not, the first item of the collection is passed by default.

## 39. Interleave two seqs

*<span class="timestamp-wrapper"><span class="timestamp">[2020-10-11 Sun]</span></span>*
```clojure
(defn interleave-two-seqs
  "Write a function which takes two sequences and returns the first item from each,
    then the second item from each, then the third, etc."
  [coll-a coll-b]
  (mapcat #(conj [] %1 %2) coll-a coll-b))
```
**Tests**
```clojure
(deftest interleave-two-seqs
  (is (= (interleave-two-seqs[1 2 3] [:a :b :c]) '(1 :a 2 :b 3 :c)))
  (is (= (interleave-two-seqs [1 2] [3 4 5 6]) '(1 3 2 4)))
  (is (= (interleave-two-seqs [1 2 3 4] [5]) [1 5]))
  (is (= (interleave-two-seqs [30 20] [25 15]) [30 25 20 15])))
```
`mapcat` is an essential [tool for thinking functionally](/functional-thinking). It concatenates the results applying a map function to a set of collections.

## 40. Interpose a seq

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
## 41. Drop every nth item

*<span class="timestamp-wrapper"><span class="timestamp">[2020-10-13 Tue]</span></span>*
```clojure
(defn drop-every-nth-item
  "Write a function which drops every Nth item from a sequence."
  [n coll]
  (apply concat (partition-all (dec n) n coll)))
```
**Tests**
```clojure
(deftest drop-every-nth-item
  (is (= (drop-every-nth-item 3 [1 2 3 4 5 6 7 8]) [1 2 4 5 7 8]))
  (is (= (drop-every-nth-item 2 [:a :b :c :d :e :f]) [:a :c :e]))
  (is (= (drop-every-nth-item 4 [1 2 3 4 5 6]) [1 2 3 5 6])))
```
`partition` is an essential [tool for thinking functionally](/functional-thinking). It allows you to divide a list of items into a collection of smaller lists. The step option lets you skip items in the list that you do not want to include in the smaller lists.

**Note**: I flipped the parameters in order to pass the data as the last argument. This simplifies function composition as described in [Introducing Ramda](http://buzzdecafe.github.io/code/2014/05/16/introducing-ramda).

## 42. Factorial fun
```clojure
(defn factorial-fun
  "Write a function which calculates factorials."
  [n]
  (reduce * (range 1 (inc n))))
```
**Short solution**
```clojure
#(reduce * (range 1 (inc %)))
```
**Tests**
```clojure
(deftest factorial-fun
  (is (= (factorial-fun 1) 1))
  (is (= (factorial-fun 3) 6))
  (is (= (factorial-fun 5) 120))
  (is (= (factorial-fun 8) 40320)))
```
## 43.

Apply cf. unapply
Packs vs. unpacks a value

<https://clojure.org/guides/learn/functions#_variadic_functions>
<https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Function/apply>
<https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Math/max>

## 64. Intro to reduce
```clojure
(deftest intro-to-reduce
  (is (= 15 (reduce + [1 2 3 4 5])))
  (is (=  0 (reduce + [])))
  (is (=  6 (reduce + 1 [2 3]))))
```
