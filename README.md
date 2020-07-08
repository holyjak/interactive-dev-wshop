# interactive-dev-wshop

## Instructions

### Preparation prior to the workshop

Instal Docker, [VS Code](https://code.visualstudio.com/) and its Clojure [extension Calva](https://code.visualstudio.com/), get a local copy of https://github.com/holyjak/interactive-dev-wshop. Execute `docker run -it holyjak/interactive-dev-wshop /bin/echo` (in any directory) to download the workshop Docker container.

Beware: This directory must me at a place where both VS Code and Docker can see it.

Next, read briefly through this file and learn these few Calva shortcuts:

1. `Ctrl-Alt-C Enter` - load the whole file in the REPL (we will do this once)
2. `Ctrl-Alt-C SPACE` - evaluate the current top-level expresion in the REPL (your cursor can be ± anywhere on the line; if you experience trubles but it to the very end)
3. `Ctrl-right arrow` - "slurp" the following element into the current list: `(def| x) 42` -> `(def| x 42)` (`ctrl-left arrow` does the opposite but we will likely not need it)
4. `Ctrl-Alt-C E` (OSX; `Ctrl-Alt-C V` on Windows) - evaluate the thing the cursor is on/right after - similar to nr. 2 but useful if you want to evaluate a smaller thing inside a bigger expression
5. `Ctrl-W` - expand selection - useful to select the thing you will move/change (often in combination with Cut and Paste)
6. (Bonus: OSX - `Cmd-|` jumps between the opening and closing parentheses.)

#### FAQ

* What is a REPL?

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

### Running the REPL

Start the server: menu - Terminal - New Terminal, in it execute `./docker/run-docker.sh` (or the one-liner from that file)
(Note: Some output and errors will be printed out in this terminal.)

On a Windows terminal, paste the _content_ of the file. And you will likely need to replace `$PWD` in it with the actual absolute path to the workshop directory (something like `C:\\Users\xyz\Documents\interactive-dev-wshop`).

### Connecting to the server REPL

Open `server.clj` (menu - Go - Go to file).

Menu - View - Command Palette - type "Calva Conn" and select "Connect [..] in Project" then choose "Clojure CLI" then connec to `localhost:52162`.

Follow the instructions in the newly opened "CLJ REPL" tab to load the current file into the REPL, i.e. switch back to `server.clj` and type `Ctrl+Alt+C Enter`.
(This should switch the bottom view from "Terminal" to "Output" and you should see some info there.)

Now, inside `server.clj`, put your cursor on the line `(-main)` nearly at the very end if the file and evaluate it in the REPL via `Ctrl+Alt+C SPACE`. This should start the server. The line _Jetty running on: http://localhost:8088/_ should be displayed in the Output.

Navigate to the frontend at [localhost:8088](http://localhost:8088/). You should see the Interactive development workshop webapp.

### Working with Calva

https://calva.io/commands-top10/

* Expand selection: ctrl+w
* Evaluate Current Form Inline command, ctrl+alt+c e (ctrl+alt+c v on Windows)
* Evaluate Current Top Level Form (defun) command (ctrl+alt+c space)
* Load current file: alt+ctrl+c enter

## Bonus resources

* [Clojure(Script) Syntax in 15 minutes](https://github.com/shaunlebron/ClojureScript-Syntax-in-15-minutes)