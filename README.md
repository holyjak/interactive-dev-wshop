# interactive-dev-wshop

## Instructions

### Prerequisities

Docker, VS Code with the Calva plugin, a local copy of https://github.com/holyjak/interactive-dev-wshop.

### Intro

You are going to experience what "interactive development" is about. We will use Clojure but the goal is not to teach it and I minimized the amount of Clojure you will need to understand. (And the code is thus not a good example of a production code.)

Your task is to implement the backend server for a people management service. When the frontend wants data, it POSTs to an endpoint with a list of the fields it wants. You need to deliver the data, from the built-in database - see the description of the tasks below.

You will need to learn a few Calva commands and shortcuts - see (and follow) _Running the server and working with Calva_ below. You will need to get a brief [introduction into Clojure syntax (3 slides)](doc/Clojure%20syntax%20intro%20slides.pdf) and you can refer to `Cheatsheet.md` for a reference.

### Interactive dev techniques to master:

1. Capture arguments as globals (so that you can run sub-expressions from the REPL; mis-uses `def`.)
2. Experiment with small parts of the code (sub-expressions) in the REPL

### Tasks

The tasks are described in more detail in the code. Only briefly:

1. (Guided) Return fake, hard-coded data for people.
2. Replace the hardcoded people data with data from the DB.
3. Find the requested person in the DB, return.
4. Update the person in the DB based on the request.

## Running the server and working with Calva

### Running the server

Open `server.clj` (Go - Go to file).

View - Command Palette - type "Calva Conn" and select "Connect [..] in Project" then choose "Clojure CLI".

Follow the instructions in the open window to load the current file into the REPL.

    $ clojure -m holyjak.interactive-dev-wshop

Navigate to the frontend at [localhost:8088](http://localhost:8088/).

### Working with Calva

https://calva.io/commands-top10/

* Expand selection: ctrl+w
* Evaluate Current Form Inline command, ctrl+alt+c e (ctrl+alt+c v on Windows)
* Evaluate Current Top Level Form (defun) command (ctrl+alt+c space)
* Load current file: alt+ctrl+c enter

## Bonus resources

* [Clojure(Script) Syntax in 15 minutes](https://github.com/shaunlebron/ClojureScript-Syntax-in-15-minutes)