#!/bin/sh

java -Xmx256m -jar target/microbenchmarks.jar ".*DZoneReadPojo.*read1kFromStream" -wi 4 -i 5 -f 9
