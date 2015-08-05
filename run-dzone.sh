#!/bin/sh

java -Xmx256m -jar target/microbenchmarks.jar ".*DZoneWrite.*write1k.*AsString.*" -wi 4 -i 5 -f 9 -t 1
