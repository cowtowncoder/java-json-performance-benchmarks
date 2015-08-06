# Overview

This project contains a set of performance micro-benchmarks, based on excellent
[JMH](http://openjdk.java.net/projects/code-tools/jmh/) package.
Benchmarks exercise JSON reading and/or writing performance,
using widely-used, popular Java JSON libraries like:

* [GSON](https://github.com/google/gson)
* [Jackson](https://github.com/FasterXML/jackson)
    * Also: lighter-weight [Jackson-jr](https://github.com/FasterXML/jackson-jr)

as well as some of newer Java JSON library options:

* [Boon](https://github.com/boonproject/boon/wiki/Boon-JSON-in-five-minutes)
* [Johnzon](http://johnzon.incubator.apache.org/)
    * pre-1.0, incubation release
* [json-io](https://github.com/jdereg/json-io)
* [Moshi](https://github.com/square/moshi): Android-optimized lib (by some of GSON authors)

The criteria for inclusion here is that for a library to be included it should

1. be published to the central Maven repository (so we can include official builds)
2. be able to read and write POJOs, not just "Lists and Maps" or "custom tree nodes library defines" (although some tests may also exercise these styles as well)

and for this reason some commonly used libraries (like old "org.json" library) are not included.

## Usage

To run the tests, you will first need to build the test jar which contains all test code as well as libraries being tested. This can be done by:

    mvn clean install

after this, tests are run the way `jmh` tests are, by just running "executable" jar with:

    java [jvm-options] -jar target/microbenchmarks.jar [options] [test-regexp]

for example:

    java -Xmx256m -jar target/microbenchmarks.jar -wi 4 -i 5 -f 9 ".*DZoneWrite.*write10AsString" 

which would run the "DZone" write test with 10 items, using 9 iterations of 5 seconds, with warmup time of 4 seconds.
All options are explained by jmh documentation; an easy way to see options available is to enter:

    java -jar target/microbenchmarks.jar -h

## Write Tests inspired by Dzone article

Following tests were written inspired by a [DZone Java Performance](https://t.co/10lR0tQJjV] article.
The original tests had many unfortunate problems; starting with the fact that it does not perform proper warmup, nor run long enough to give statistically meaning results.
One can also argue whether serialization as String is a meanginful test (since it is rarely used for production), but that test case is included as-is along with other options.

Test as implemented here relies on `jmh` to provide proper performance test setup, measurements, warmup, and to avoid common gotchas that plague naive Java performance tests.
If not done so yet, you may want to read the longer explanation of [reasons to use JMH](http://psy-lob-saw.blogspot.com/2013/04/writing-java-micro-benchmarks-with-jmh.html).

### Writing a List of POJOs

There are 3 flavors of the test for writing out a List of 10, 1000 and 100,000 items of type `MeasurementRecord`:

* `write10AsString` (and `write1kAsString`, `write100kAsString`): serialization as `java.lang.String`
* `write10UsingWriter` (`write1kUsingWriter`, `write100kUsingWriter`): serialization using a "do-nothing" `java.io.Writer`
* `write10UsingStream` (`write1kUsingStream`, `write100kUsingStream`): serialization using a "do-nothing" `java.io.OutputStream`

the idea being that different types of output incur different kinds and amounts of overhead.
For example, aggregating output as a `java.lang.String` requires much more memory allocation and (indirectly) more Garbage Collection for discarded JSON Strings.
Conversely libraries are optimized differently for different types of output targets; some implement native output for all targets and others only have one basic target.

To run the test with a List of 10 items, serializing results as a Java String, you could use:

    java -Xmx256m -jar target/microbenchmarks.jar ".*DZoneWrite.*write10AsString" -wi 4 -i 5 -f 9


## Sample results

Since results may vary significantly between different platforms and JVM versions, it is best to
run benchmark on systems you are using.
But to give some idea of typical results, here are samples I get running tests on my work laptop:

### DZone write tests

Results as reported on console, except sorted in descending order of performance (fastest first)

#### 1000 item list, as String

```
% java -Xmx256m -jar target/microbenchmarks.jar ".*DZoneWrite.*write1k.*AsString.*" -wi 4 -i 5 -f 19

# Run complete. Total time: 00:20:58

Benchmark                             Mode  Cnt     Score    Error  Units
DZoneWriteJacksonJr.write1kAsString  thrpt   95  5060.624 ± 32.975  ops/s
DZoneWriteJackson.write1kAsString    thrpt   95  4377.870 ± 22.375  ops/s
DZoneWriteBoon.write1kAsString       thrpt   95  2614.780 ± 15.105  ops/s
DZoneWriteJohnzon.write1kAsString    thrpt   95  2531.030 ± 16.696  ops/s
DZoneWriteMoshi.write1kAsString      thrpt   95  1655.735 ±  9.793  ops/s
DZoneWriteGSON.write1kAsString       thrpt   95  1209.408 ± 10.565  ops/s
DZoneWriteJsonIO.write1kAsString     thrpt   95   883.185 ±  7.002  ops/s
```

Given that the test measures throughput, Jackson-jr (the fastest) is about 5x as fast as json.io (the slowest) for this test.
