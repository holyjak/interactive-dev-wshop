Workshop Cheat Sheet
====================

# Clojure - A minimal Clojure subset

## Tips

**Always start with typing a `(` when writing code (unless you you just want to refer to a named piece of data)** (thus preventing confusing both yourself and Calva :-)).

## General

`,` is a whitespace - ignored, add for readability where you want

Everything is an expression and returns a value (no need for `return ...`).
The last expression inside a function is its return value.

## Data & data literals

`{:my-key "value", :another 123}` - a (hash)map (~ JS object)

To get the value, use the keyword as a function of the map: `(:my-key my-map)`

`[1 2 :three]` - a vector (~ JS array)

`nil`, `:keyword`, `1`, `"string"`, `true` - primitives

## Definitions and functions

[`def`](https://clojuredocs.org/clojure.core/def) - `(def <name> <value>)` - give a name to a piece of data or a function (i.e. "define" a "var" in Clojure speak); ex.: `(def pi 3.14)`

[`fn`](https://clojuredocs.org/clojure.core/fn) - `(fn [arg1 arg2 ...] <body>)` - define a function (To call a function: `(my-fn arg1 arg2 ...)`)

[`defn`](https://clojuredocs.org/clojure.core/defn) - ± same as `(def my-name (fn [...] ...))`

Catch: We _define_ the arguments of a function inside a vector but when we call it, we include them directly: `(defn plus [x y] (+ x y))` -> `(plus 1 2)`

## Collections and sequences

[`map`](https://clojuredocs.org/clojure.core/map) - `(map <function> <sequence>)` - change each element; ex.: `(map (fn [n] (+ 1)) [1 2])`

[`filter`](https://clojuredocs.org/clojure.core/filter) - `(filter <function> <sequence>)` - keep only elements the function returns anything but nil/false for

TIP: When you use `map`/`filter`, write type arguments first before working out the function, i.e. begin with `(map (fn [x] x) my-data)`

[`first`](https://clojuredocs.org/clojure.core/first) - `(first <sequence>)` - return the first element of the sequence

[`select-keys`](https://clojuredocs.org/clojure.core/select-keys) - make a subset of a map; ex.: `(select-keys {:a 1,:b 2,:c 3} [:a :b])` produces `{:a 1, :b 2}`

## Logic and comparison

[`=`](https://clojuredocs.org/clojure.core/%3D) - `(= arg1 arg2 ...)` - are all the arguments equal?

[`not=`](https://clojuredocs.org/clojure.core/%3D) - the opposite of `=`

[`if`](https://clojuredocs.org/clojure.core/if) - `(if condition true-expression false-expression)` (`false-expression` defaults to `nil`)

## Flow control

[`->>`](https://clojuredocs.org/clojure.core/->>) - `(->> input (fn1) (fn2) ...)` - a transformation pipeline, "threading" data through a series of functions (transformations). Invokes fn1 with the input as the last argument, then fn2 with the result of that as the last argument etc. Example:

```clojure
(->> 6
     (- 10)   ; = 10 - 6
     (* 2 2)) ; = 2 * 2 * 4 
;; => 16
```


[`let`](https://clojuredocs.org/clojure.core/let) - `(let [name1 value1, ...] body)` - introduce local constants (bindings) available inside its body. (Note: the `[]` here is not really creating a vector of data, it just tells to let "here come the bindings..." similarly as in a `defn` it tells "here comes the declaration of arguments".) Example below:

```clojure
(let [five 5,
      ten (* 2 five)]
  (= 15 (+ five ten)))
```

## REPL troubleshooting

[`println`](https://clojuredocs.org/clojure.core/println) - print the thing(s)

[`pr-str`](https://clojuredocs.org/clojure.core/pr-str)  - print Clojure data into a string so that Clojure can read them back

# VS Code and Calva 

## VS Code and Calva shortcuts

 You will need these shortcuts during the workshop:

1. `Ctrl-Alt-C SPACE` - evaluate the current _top-level_ expresion in the REPL - i.e. a function definition, an expression inside a `comment` block (your cursor can be ± anywhere on the line; if you experience trubles but it to the very end)
2. `Ctrl-Alt-C E` (OSX; `Ctrl-Alt-C V` on Windows) - evaluate the _thing the cursor is on/right after_ - similar to nr. 1 but useful if you want to evaluate a smaller thing inside a bigger expression, f.ex. to look at the value a name refers to
3. `Ctrl-right arrow` - "slurp" the following element into the current list: `(def| x) 42` -> `(def| x 42)` (`ctrl-left arrow` does the opposite but we will likely not need it)
   BEWARE: On OSX it migth conflict with a sytem shortcut. Fix or use `Ctrl-W`, cut, paste.
4. `Ctrl-W` - expand selection (press repeatedly) - useful to select the thing you will move/change (often in combination with Cut and Paste)
5. (Bonus: OSX - `Cmd-|` jumps between the opening and closing parentheses.)

(If you love shortcuts, check out https://calva.io/commands-top10/ :-).)

## Editing tips

**Use Ctrl-W** to (select, cut, and) move code around - this ensures you won't end up with mismatched parentheses.

You can only delete empty parentheses/braces/.. so Ctrl-W to select and cut the content, then backspace to delete the opening and thus also closing parenthese/....

If you screw up, use Ctrl-Z (Cmd-Z on OSX) to back up.