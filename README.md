# The "Experience interactive development" workshop

Join us to experience what interactive development (also known as REPL-driven development) is about! Feel the joy of immediate feedback, growing your program in tiny increments, of restart-less development!

Suitable for anyone who has some experience with programming. Having experience with JavaScript is pretty useful (since a few key things there are similar to Clojure, which we will use).

I want to thank Jingyi Li, Maria Vu, and Mathias Årstad Olsen, whose help has made this workshop a whole lot better.

## Instructions

For instructors: Read the [Instructor guide](Instructor%20guide.md).

### Preparation prior to the workshop

Instal Docker, [VS Code](https://code.visualstudio.com/) and its Clojure [extension Calva](https://code.visualstudio.com/), get a local copy of https://github.com/holyjak/interactive-dev-wshop (`git clone` or [a zip file](https://github.com/holyjak/interactive-dev-wshop/archive/master.zip)). Execute `docker run -it holyjak/interactive-dev-wshop /bin/echo` (in any directory) to download the workshop Docker container.

Beware: This directory must be at a place where both VS Code and Docker can see it.

Next, read briefly through this file.

### Intro

You are going to experience what "interactive development" is about. We will use Clojure but the goal is not to teach it and I minimized the amount of Clojure you will need to understand. (And the code is thus not a good example of a production code.)

Your task is to implement the backend server for a people management service. When the frontend wants data, it POSTs to an endpoint with a list of the fields it wants. You need to deliver the data, from the built-in database - see the description of the tasks below.

The instructor will give you a brief [introduction into Clojure syntax (3 slides)](doc/Clojure%20syntax%20intro%20slides.pdf), will review with you the [Cheatsheet](Cheatsheet.md) for code and key VS Code/Calva shortcuts, and will walk you through the code and provide support and help.

(Tip: When you open the Cheatsheet, run the command "_Markdown: Open Preview_" or [open it in a browser](https://github.com/holyjak/interactive-dev-wshop/blob/master/Cheatsheet.md); it is more readable that way. Have it available, perhaps on a half of the screen, you will need it a lot.)

### Interactive development techniques you will learn:

1. Capture arguments and expensive results as globals (so that you can run sub-expressions from the REPL; mis-uses `def`.)
2. Experiment with small parts of the code (sub-expressions) in the REPL

### Tasks

The tasks are described in more detail in the code. Only briefly:

1. (Guided) Return fake, hard-coded data for people.
2. Replace the hardcoded people data with data from the DB.
3. Find the requested person in the DB, return.
4. Update the person in the DB based on the request.

## At the workshop

### Running the server

Before you can start coding, you need to start the REPL for evaluating the code and for running the server.

#### Running the REPL

Start the server: _menu - Terminal - New Terminal_, in it execute `./docker/run-docker.sh` (for Windows see below). (Note: Some output and errors will be printed out in this terminal.)

On Windows, paste the _content_ of the file into a cmd.exe terminal (not PowerShell; it complains about something). You will likely need to replace `$PWD` in the command with the actual absolute path to the workshop directory (something like `C:\\Users\xyz\Documents\interactive-dev-wshop`).

Note: The server uses the localhost ports 8088 and 52162 so they need to be free.

#### Connecting VS Code to the server REPL

The REPL is running but we also need to connect VS Code/Calva to it.

_Menu - View - Command Palette_ - type "_Calva Conn_" and select "_Connect [..] in Project_" then choose "_Clojure CLI_" then connect to `localhost:52162`.

Load the server code into the REPL - open `server.clj` (_menu - Go - Go to file_) and type `Ctrl+Alt+C Enter`.
(This should switch the bottom view from "Terminal" to "Output" after a while and you should see some info there.)

Now, inside `server.clj`, put your cursor on the line `(-main)` nearly at the very end of the file and evaluate it in the REPL via `Ctrl+Alt+C SPACE`. This should start the server. The line _Jetty running on: http://localhost:8088/_ should be displayed in the Output.

Navigate to the frontend at [localhost:8088](http://localhost:8088/). You should see the Interactive development workshop webapp.

## Bonus resources

* [Clojure(Script) Syntax in 15 minutes](https://github.com/shaunlebron/ClojureScript-Syntax-in-15-minutes)
