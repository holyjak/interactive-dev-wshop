Instructor guide
================

Agenda
------

### Note on timing

From experience, ~with junior developers~:

* From start to the warm-up exercise: ~ 1/2h
* Warm-up: ~1h - 1.5h
* Tasks:  ~1h - 1.5h (but can stop anytime)

Whole workshop, with experienced folks: ± 2h

### Brief intro

_NOTE: First check who isn't done preparing and let them run it while listening_

Welcome & thank everyone.

Answer: Why are we doing this? What will you gain? What are we going to do?

> Why are we here? Why this workshop? B/c I want to share the joy of interactive development and allow you to taste it. 
> Now I don't know why you are here but hopefully we will find out :-).
>
> There are two styles of development: the common compile/transpile - wait - restart - wait - recreate application state one and the interactive one. This interactive, REPL-driven development is about developing your application _while_ it is running,
without ever restarting it. It is about trying out each new tiny snippet of code _in the context of the running application_ (i.e. with access to its data, state, and connections) and thus about growing the code in a series of small, tested steps.
>
> What is a REPL? An acronym for Read-Eval-Print-Loop and you can think of it as a coding terminal into your running application: you send it commands and new code and get back data.

Now describe briefly the tasks and warm-up exercises. Mention we will use a minimalist subset of Clojure & why.

### Brief Clojure syntax intro

1. Talk through the [Clojure syntax intro slides](doc/Clojure%20syntax%20intro%20slides.pdf) here [or online](https://docs.google.com/presentation/d/1_toJRBJeBrOO6sDtjxSNoq8A3EVE3VxdqXXcOKp1-kM/edit?usp=sharing) (also have a look at the one with the speaker notes).
2. Browse through the [Cheatsheet](Cheatsheet.md), focus on the map literal and getting data out

### Get up and running!

Is everybody ready, with the prerequisites (this folder, VS Code + Calva, Docker)?
Has everybody browsed through the README?

Is everybody familiar with map and filter?

Everybody & me: Start the server (-> Cheatsheet), browse to [localhost:8088](http://localhost:8088/).

_BEWARE: Folks fail at this => point them to instructions then show slowly on screen._

(Self: Ready to develop `handle-people?`)

### Code walk-through

Everybody opens `src/interactive/server.clj`, walk through the code briefly, focusing on the big picture and the constructs they are going to need.

Perhaps skip the handlers and server part beyond showing how and when the functions they are to work with are invoked.

### Coding warm-up

Everybody should open the Cheatsheet (preview, not .md) next to the editor or have a printout.
They should read through it so that they know what is where and can find it.
They should review the _Calva shortcuts_ from the cheatsheet and, ideally, have them readily available.

Ask them to carefully read the comments, especially the 'ESSENTIAL TIPS' part.
Then let them work through the exercises in the top comment block, walking around, checking on them, helping.

BREAK TIME!
### Tasks

NOTE: Mention that people can look at the `final-code` branch to see a correct solution, if they struggle.

#### Guided task 1

Complete the task 1 on the screen, explaining - use `def` to capture the request (and explain its use in deving vs. prod), explore it in the REPL, ... . At the end use the comment block to invoke the handler with the captured _req and verify the output.

Explain: We use `def` to capture local arguments (ex.: function inputs) and data that is expensive
to retrieve (ex.: DB data) so that we can play with small pieces of code out of context. Mention that def creates a shared, mutable "variable" and thus we never, ever do this in prod code.

Then let everyone finish the task for themselves.

TIPS: 

1. Forget to eval the fn after you add a def and reload => explain we need 1) eval fn, 2) then repeat the UI action, 3) our captured `_req` is ready
2. Demonstrate the diff. between Alt-Enter (eval top-level) and Ctrl-Enter (eval current), recommend the former - ppl mess it up
3. Forget to reload the page (to invoke the handler) => why is the `_req` "unbound"?
4. Also forget to eval the fn after you finish the task so that the browser still shows nothing :-)

#### Individual work on tasks 2-4

TIPS for the people: Read the function docstring and task description carefully. Proceed in minimal steps, trying each out in the REPL.

Give them a few minutes for each task, walking around and helping.

Then do it on the screen and explain.

### Review and questions & comments

#### Summary

You have now experienced what interactive, REPL-driven development is about:

* Develop an application while it is running, interact with it from code, without any restarts and waiting for compilation 
* Write tiny pieces of code and try them out at once, build up a little more, try it again, ...
* Get an immediate feedback on what your code does and on what data is there

You have learned to proceed in these tiny, verified steps and to capture local arguments as globals (using `def`) so that you can play with a piece of code on its own.

----

# Handouts

To print PDF: 

    docker run --rm --volume (pwd):/data pandoc/latex Cheatsheet.md -o Cheatsheet.pdf

Make sure there is a pointer to the next side from the first one!