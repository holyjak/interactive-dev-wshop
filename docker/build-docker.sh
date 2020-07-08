#!/bin/sh
cp ../deps.edn .
docker build -t holyjak/interactive-dev-wshop .
