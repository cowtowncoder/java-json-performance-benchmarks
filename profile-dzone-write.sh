#!/bin/sh

java -Xmx256m -server -Xrunhprof:cpu=samples,depth=10,verbose=n,interval=2  -jar target/microbenchmarks.jar ".*DZoneWrite.*write1k.*AsString.*" -wi 7 -i 30 -f 1
