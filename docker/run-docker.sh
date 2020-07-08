#!/bin/sh
docker run -it -p 8088:8088 -p 52162:52162 --volume "$PWD":/app holyjak/interactive-dev-wshop
