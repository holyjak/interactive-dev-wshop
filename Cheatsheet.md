---
title: "Cheatsheet - interactive dev (2 pages)"
geometry: margin=0.7cm
output: pdf_document
---

TIP: Starting the REPL, server - see [README - Connecting VS Code to the server REPL and starting the server](https://github.com/holyjak/interactive-dev-wshop#connecting-vs-code-to-the-server-repl-and-starting-the-server) (at the bottom).
(In short: run "Calva Start REPL.." with _Experience interactive development workshop_ and then open `server.clj`, "Calva Load ..", Alt-Enter at the line `(-main)`.)

# Clojure - A minimal Clojure subset

## General

`,` is a whitespace - ignored, add for readability where you want

Everything is an expression and returns a value (no need for `return ...`).
The last expression inside a function is its return value.

## Data & data literals

`{:my-key "value", :another 123}` - a (hash)map (~ JS object)

To get the value, use the keyword as a function of the map: `(:my-key my-map)`

`[1 2 :three]` - a vector (~ JS array)

`nil`, `:keyword`, `1`, `"string"`, `true`, `false` - primitives

## Definitions and functions

[`def`](https://clojuredocs.org/clojure.core/def) - `(def <name> <value>)` - give a name to a piece of data or a function (i.e. "define" a "var" in Clojure speak). Treat as a constant! Ex.: `(def pi 3.14)`

[`fn`](https://clojuredocs.org/clojure.core/fn) - `(fn [arg1 arg2 ...] <body>)` - define a function

[`defn`](https://clojuredocs.org/clojure.core/defn) - ± same as `(def my-name (fn [...] ...))`

Catch: We _define_ the arguments of a function inside a vector but when we call it, we include them directly: `(defn plus [x y] (+ x y))` -> `(plus 1 2)`

## Collection and sequence function

[`map`](https://clojuredocs.org/clojure.core/map) - `(map <function> <sequence>)` - change each element; ex.: `(map (fn [n] (+ n 1)) [1 2]) ; => (2 3)`

[`filter`](https://clojuredocs.org/clojure.core/filter) - `(filter <function> <sequence>)` - keep only the elements for which the function does not return nil or false

TIP: When you use `map`/`filter`, type all the arguments first before working out the function, i.e. begin with `(map (fn [x] x) my-data)`

[`first`](https://clojuredocs.org/clojure.core/first) - `(first <sequence>)` - return the first element of the sequence

[`select-keys`](https://clojuredocs.org/clojure.core/select-keys) - make a subset of a map; ex.: `(select-keys {:a 1,:b 2,:c 3} [:a :b])` produces `{:a 1, :b 2}`

## Logic and comparison

[`=`](https://clojuredocs.org/clojure.core/%3D) - `(= arg1 arg2 ...)` - are all the arguments equal?

[`not=`](https://clojuredocs.org/clojure.core/%3D) - the opposite of `=`

[`if`](https://clojuredocs.org/clojure.core/if) - `(if condition true-expression false-expression)` (you can leave out the `false-expression`, it defaults to `nil`)

## Flow control

[`->>`](https://clojuredocs.org/clojure.core/->>) - `(->> input (fn1) (fn2) ...)` - a transformation pipeline, "threading" data through a series of functions (transformations). Invokes _fn1_ with the _input_ as the last argument, then _fn2_ with the result of that as the last argument etc. Example:

```clojure
(->> 6
     (- 10)   ; = (- 10 6)
     (* 2 2)) ; = (* 2 2 4)
;; => 16
```

[`let`](https://clojuredocs.org/clojure.core/let) - `(let [name1 value1, ...] body)` - introduce local constants (bindings) available inside its body, giving names to pieces of data. (Note: the `[]` here is not really creating a vector of data, it just tells to let "here come the bindings..." similarly as in a `defn` it tells "here comes the declaration of arguments".) Example below:

```clojure
(let [five 5,
      ten (* 2 five)]
  (= 15 (+ five ten)))
```

## REPL troubleshooting

[`println`](https://clojuredocs.org/clojure.core/println) - print the thing(s)

[`pr-str`](https://clojuredocs.org/clojure.core/pr-str)  - print Clojure data into a string so that Clojure can read them back

<section style="background-color:#e1dede; padding: 1em 0.5em; margin: 2em 0em">

<h2 id="golden-rules">Golden Rules</h2>

<p><b>Always start with typing a <code>(</code></b> when writing code (unless you you just want to <i>refer</i> to a named piece of data) - thus preventing confusing both yourself and Calva :-).</p>

<p><b>Function name comes always <i>first</i></b>, right after (: <code>(function-name ...)</code> - even <code>+</code> and similar.</p>
</section>

# VS Code and Calva 

## VS Code and Calva shortcuts

 You will need these shortcuts during the workshop:

1. `Alt ENTER` - evaluate the current _top-level_ expression in the REPL - i.e. a function definition, an expression inside a `comment` block (your cursor can be ± anywhere at the line; if you experience troubles then move it to the very end)
2. (`Ctrl ENTER` - evaluate the _thing the cursor is on/right after_ - similar to nr. 1 but useful if you want to evaluate a smaller thing inside a bigger expression, f.ex. to look at the value a name refers to)
3. `Ctrl-Alt-right arrow` (OSX and Win; Linux: `.` instead of `->`) - "slurp" the following element into the current list: `(def| x) 42` -> `(def| x 42)` (`ctrl-alt-left arrow` (`,`) does the opposite but we will likely not need it)
   BEWARE: On OSX it might conflict with a system shortcut. Fix or use `Ctrl-W`, cut, paste.
4. `Ctrl-W` (OSX) / `Shift-Alt-right arrow` (Win, ?Lin) - expand selection (press repeatedly) - useful to select the thing you will move/change (often in combination with Cut and Paste)
5. (Bonus: `Cmd-|` (OSX; `Ctrl-Shift-\` on Windows, `Ctrl+Shift+\` Linux, cmd "Go to Bracket") jumps between the opening and closing parentheses.)
6. `Escape` - hide the inline results of evaluation (as per 1. or 2.) 

(If you love shortcuts, check out https://calva.io/commands-top10/ There is also [Linux](https://code.visualstudio.com/shortcuts/keyboard-shortcuts-linux.pdf), [MacOS](https://code.visualstudio.com/shortcuts/keyboard-shortcuts-macos.pdf), and [Windows](https://code.visualstudio.com/shortcuts/keyboard-shortcuts-windows.pdf) keyboard shortcut cheatsheet for VS Code.)

## Editing tips

**Use Ctrl-W / Shift-Alt-right arrow** to (select, cut, and) move code around - this ensures you won't end up with mismatched parentheses.

You can only delete empty parentheses/braces/.. so Ctrl-W to select and cut the content, then backspace to delete the opening and thus also closing parenthesis/....

If you screw up, use Ctrl-Z (Cmd-Z on OSX) to back up.
