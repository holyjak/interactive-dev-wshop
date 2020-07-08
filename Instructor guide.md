Instructor guide
================

Agenda
------

### Brief intro

Why are we doing this? What will you gain? What are we going to do?

> Interactive, REPL-driven development is about developing your application _while_ it is running,
without ever restarting it. It is about trying out each new tiny snippet of code _in the context of the running application_ (i.e. with access to its data, state, and connections) and thus about growing the code in a series of small, tested steps.
>
> What is a REPL? An acronym for Read-Eval-Print-Loop and you can think of it as a coding terminal into your running application: you send it commands and new code and get back data.

### Brief Clojure syntax intro

1. Talk through the [Clojure syntax intro slides](doc/Clojure%20syntax%20intro%20slides.pdf).
2. Browse through the [Cheatsheet](Cheatsheet.md), focus on the map literal and getting data out

### Get up and running!

Is everybody ready, with the prerequisities (this folder, VS Code + Calva, Docker)?
Has everybody browsed through the README and the Cheatsheet?

Is everybody familiar with map and filter?

Everybody & me: Start the server, browse to [localhost:8088](http://localhost:8088/).

(Self: Ready to develop handle-people?)

### Code walk-through

Everybody opens `src/interactive/server.clj`, walk through the code briefly, focusing on the big picture and the constructs they are going to need.

### Coding warm-up

Guide the people through the tasks in the top comment block.

### Tasks

#### Guided task 1

Complete the task 1 on the screen, explaining - use `def` to capture the request (and explain its use in deving vs. prod), explore it in the REPL, ... .

TIP: Forget to eval the fn after you add a def => explain we need 1. eval fn, 2. then repeat the UI action, 3. our req' is ready
TIP 2: Also forget to eval the fn after you implement it :-)

#### Individual work on tasks 2-4

Give them a few minutes for each task, walking around and helping.

Then do it on the screen and explain.

### Review and questions & comments