# The workshop "Experience interactive development, creating a REST service without a single restart"

Join us to experience what interactive development (also known as REPL-driven development) is about. Feel the joy of immediate feedback, growing your program in tiny increments, of restart-less development! You are likely used to the code-compile-restart cycle. Here you will code and play with a REST service without a single restart, talk to the database from your editor, and discover unexpected productivity.

Suitable for anyone who has some experience with programming. Having experience with JavaScript is pretty useful (since a few key things there are similar to Clojure, which we will use).

I want to thank Jingyi Li, Maria Vu, and Mathias Årstad Olsen, whose help has made this workshop a whole lot better.

## Instructions

For instructors: Read the [Instructor guide](Instructor%20guide.md).

### Preparation prior to the workshop

You can either run the workshop app locally, which requires some setup, or you can run it in a Gitpod-provided remote development environment, with an in-browser VS Code.

Follow the appropriate instructions below and then read briefly through the rest of this file.
#### Variant 1: Local development

Instal Docker (see the [requirements](https://code.visualstudio.com/docs/remote/containers#_system-requirements)), [VS Code](https://code.visualstudio.com/), and its [extension Remote - Containers](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.remote-containers)\*.

_\*) Note: Last tested with Calva 2.0.303_

Get a local copy of https://github.com/holyjak/interactive-dev-wshop (`git clone` or [download a zip file](https://github.com/holyjak/interactive-dev-wshop/archive/master.zip))<sup>1</sup>. 

In VS Code, in the [Command Palette](https://code.visualstudio.com/docs/getstarted/userinterface#_command-palette) (_View - Command Palette..._), search for *Remote-Containers: Open Folder in Container...* and navigate to the downloaded `interactive-dev-wshop` folder. This will open the project in a Docker container with all the dependencies pre-installed. It might take a few minutes.

(Tip: You might get an error about `npx shadow-cljs`, which you can ignore.)

<sup>1</sup>) Beware: This directory must be at a place where both VS Code and Docker can see it. That might be an issue under Windows and WSL based on your setup.

#### Variant 2: Remote dev env with Gitpod

For a setup-less development environment with an in-browser VS Code, you can use Gitpod:

[![Open in Gitpod](https://gitpod.io/button/open-in-gitpod.svg)](https://gitpod.io/from-referrer/)

Gitpod requires login via GitHub / GitLab / Bitbucket and offers enough free usage time for this workshop.

NOTE: You may get the following two pop-ups:

> A service is available on port 6080
> 
> A service is available on port 5900

If that happens then just close them.

### Intro

You are going to experience what "interactive development" is about. The only language build around interactive development that I know is Clojure, so we will use that. You don't need to know it and the goal is not to teach it. I have minimized the amount of Clojure you will need to understand so that we can focus on the interactive development side. (And the code is thus not a good example of production code.)

Your task is to implement a backend server for a people management service. When the frontend wants data, it POSTs to an endpoint with a list of the fields it wants. You need to deliver the data, from the built-in database - see the description of the tasks below.

The instructor will give you a brief [introduction into Clojure syntax (3 slides)](doc/Clojure%20syntax%20intro%20slides.pdf), will go through the [Cheatsheet](Cheatsheet.md) for Clojure and key VS Code/Calva shortcuts with you, and will walk you through the code and provide support and help.

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

### Recommended Calva layout

For me it works best to structure Calva as shown below, i.e. `server.clj` on the left, Markdown preview of the `Cheatsheet.md` on the right, and a tiny `output.calva-repl` at the bottom. (You can drag and drop the tabs where you want them.)

![Recommended Calva layout](./doc/recommended-calva-layout.png)

### Running the server

Before you can start coding, you need to start the REPL - the "programming terminal" and runtime environment we will be using - for evaluating the code and for running the server.

#### Connecting VS Code to the server REPL and starting the server

[Local dev] Make sure that you have opened the project in a _Remote Container_ as described above under *[Preparation prior to the workshop](#preparation-prior-to-the-workshop)*.

##### Start the REPL

In the menu (in GitPod it is the ☰ in the top-left corner) select _View - Command Palette... - [Calva: Start a Project REPL and Connect (aka Jack-In)](https://calva.io/connect/)_ ![](doc/images/jack-in-1.png) and, in the "Please select a project type" dropdown, select **Workshop Experience interactive development**.

A new tab called `output.calva-repl` should open and eventually contain `; Jack-in done.`.

##### Start the server

[Gitpod] Load the server code into the REPL - open `server.clj` (via _menu - Go - Go to File_) and run _Calva: Load/Evaluate Current File and its Requires/Dependencies_ (via _menu - View - Command Palette..._).
(The line _; Evaluating file: server.clj_ should appear in the _output.calva-repl_ window.)

* Note: Should not be necessary locally as the server should be started automatically. In Gitpod it mostly fails to do so.
 

Now, inside `server.clj`, put your cursor on the line `(-main)` which is nearly at the very end of the file and evaluate it in the REPL via `Alt+ENTER`. This should start the server. The line:

>  Jetty running on: http://localhost:8088/

should be displayed in the _output.calva-repl_ window. Also, VS Code pops up the following message:

![Code: App is running popup](./doc/vs-code-open-in-browser.png)

You can click the [Open in Browser] button to show the frontend app or you can manually navigate to [localhost:8088](http://localhost:8088/). You should see the Interactive development workshop webapp.

Finally, go to the beginning of `server.clj` and read the comments.

## Bonus resources

* [Clojure(Script) Syntax in 15 minutes](https://github.com/shaunlebron/ClojureScript-Syntax-in-15-minutes)
* [Interactive development - community resources](https://clojure.org/guides/repl/annex_community_resources) - including great demos
