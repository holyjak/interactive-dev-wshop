# The "Experience interactive development" workshop

Join us to experience what interactive development (also known as REPL-driven development) is about! Feel the joy of immediate feedback, growing your program in tiny increments, of restart-less development!

Suitable for anyone who has some experience with programming. Having experience with JavaScript is pretty useful (since a few key things there are similar to Clojure, which we will use).

I want to thank Jingyi Li, Maria Vu, and Mathias Årstad Olsen, whose help has made this workshop a whole lot better.

## Instructions

For instructors: Read the [Instructor guide](Instructor%20guide.md).

### Preparation prior to the workshop

Instal Docker (see the [requirements](https://code.visualstudio.com/docs/remote/containers#_system-requirements)), [VS Code](https://code.visualstudio.com/), and its [extension Remote - Containers](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers).

Get a local copy of https://github.com/holyjak/interactive-dev-wshop (`git clone` or [download a zip file](https://github.com/holyjak/interactive-dev-wshop/archive/master.zip))<sup>1</sup>. 

In VS Code, in the [Command Palette](https://code.visualstudio.com/docs/getstarted/userinterface#_command-palette), search for *Remote-Containers: Open Folder in Container...* and navigate to the downloaded `interactive-dev-wshop` folder. This will open the project in a Docker container with all the dependencies pre-installed. It might take a few minutes. 

When finished, try *Calva: Start a project REPL and connect (aka Jack-in)* (and select *Clojure CLI* and no aliases, when asked). A new tab called `output.calva-repl` should open and eventually contain `; Jack-in done.`. After that you are all set up and can close VS Code for now. Repeat the same process just before the workshop (it should then go faster).

You can watch a silent [screencast of me going through the preparations](https://youtu.be/ydtsUgE2RAg), which might help you if you run into any troubles. (The screencast goes a little further, to evaluating the code and starting the backend server.)

<sup>1</sup>) Beware: This directory must be at a place where both VS Code and Docker can see it. That might be an issue under Windows and WSL based on your setup.

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

#### Connecting VS Code to the server REPL and starting the server

Make sure that you have opened the project in a [Remote Container](https://code.visualstudio.com/docs/remote/containers) as described above under *Preparation prior to the workshop*, and follow those instructions also to also start and connect to a REPL (a.k.a. "jack-in").

Load the server code into the REPL - open `server.clj` (_menu - Go - Go to file_) and run Calva - Load Current File and Dependencies.
(This should switch the bottom view from "Terminal" to "Output" after a while and you should see some info there.)

Now, inside `server.clj`, put your cursor on the line `(-main)` which is nearly at the very end of the file and evaluate it in the REPL via `Ctrl+Alt+C SPACE`. This should start the server. The line _Jetty running on: http://localhost:8088/_ should be displayed in the Output.

Navigate to the frontend at [localhost:8088](http://localhost:8088/). You should see the Interactive development workshop webapp.

## Bonus resources

* [Clojure(Script) Syntax in 15 minutes](https://github.com/shaunlebron/ClojureScript-Syntax-in-15-minutes)
