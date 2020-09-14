#!/bin/sh
docker build -t holyjak/interactive-dev-wshop -f ./Dockerfile ..
docker tag holyjak/interactive-dev-wshop:latest holyjak/interactive-dev-wshop:vscode
