A minimal Clojure subset cheat sheet
====================================

## General

`,` is a whitespace - ignored, add for readability where you want

Everything is an expression and returns a value (no need for `return ...`).
The last expression inside a function is its return value.

## Data & data literals

`{:my-key "value", :another 123}` - a map (~ JS object)

To get the value, use the keyword as a function of the map: `(:my-key my-map)`

`[1 2 :three]` - a vector (~ JS array)

`nil`, `:keyword`, `1`, `"string"`, `true` - primitives

## Definitions and functions

[`def`](https://clojuredocs.org/clojure.core/def) - `(def <name> <value>)` - create a global "constant" (a "var" in Clojure speak)

[`fn`](https://clojuredocs.org/clojure.core/fn) - `(fn [arg1 arg2 ...] <body>)` - define a function

[`defn`](https://clojuredocs.org/clojure.core/defn) - ± same as `(def my-name (fn [...] ...))`

## Collections and sequences

[`map`](https://clojuredocs.org/clojure.core/map) - `(map <function> <sequence>)` - change each element

[`filter`](https://clojuredocs.org/clojure.core/filter) - `(filter <sequence>)` - keep only elements the function returns anything but nil/false for

[`first`](https://clojuredocs.org/clojure.core/first) - `(first <sequence>)` - return the first element of the sequence

## Logic and comparison

[`=`](https://clojuredocs.org/clojure.core/%3D) - `(= arg1 arg2 ...)` - are all the arguments equal?

[`if`](https://clojuredocs.org/clojure.core/if) - `(if condition true-expression false-expression)` (`false-expression` defaults to `nil`)

## Flow control

[`->>`](https://clojuredocs.org/clojure.core/->>) - `(->> input (fn1) (fn2) ...)` - a transformation pipeline, "threading" data through a series of functions (transformations). Invokes fn1 with the input as the last argument, then fn2 with the result of that as the last argument etc. Example:

```clojure
(->> 6
     (- 10)   ; = 10 - 6
     (* 2 2)) ; = 2 * 2 * 4 
;; => 16
```


[`let`](https://clojuredocs.org/clojure.core/let) - `(let [var1 value1, ...] body)` - introduce local constants (bindings). Example below:

```clojure
(let [five 5
      ten (* 2 five)]
  (= 15 (+ five ten)))
```

## REPL troubleshooting

[`println`](https://clojuredocs.org/clojure.core/println) - print the thing(s)

[`pr-str`](https://clojuredocs.org/clojure.core/pr-str)  - print Clojure data into a string so that Clojure can read them back
