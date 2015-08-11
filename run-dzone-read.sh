#!/bin/sh

java -Xmx256m -jar target/microbenchmarks.jar ".*DZoneReadPojo.*read1k.*FromString.*" -wi 4 -i 5 -f 9
