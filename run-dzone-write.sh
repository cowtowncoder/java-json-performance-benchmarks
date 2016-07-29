#!/bin/sh

java -Xmx256m -jar target/microbenchmarks.jar ".*DZoneWriteJackson2x.*write1kUsingStream" -wi 4 -i 5 -f 9
