#!/bin/sh

java -Xmx256m -jar target/microbenchmarks.jar ".*DZoneRead.*read1k.*FromString.*" -wi 4 -i 5 -f 9
