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

## Tests inspired by Dzone article

Following tests were written inspired by a [DZone Java Performance](https://t.co/10lR0tQJjV] article.
The original tests had many unfortunate problems; starting with the fact that it does not perform proper warmup, nor run long enough to give statistically meaning results.
One can also argue whether serialization as String is a meanginful test (since it is rarely used for production), but that test case is included as-is along with other options.

Test as implemented here relies on `jmh` to provide proper performance test setup, measurements, warmup, and to avoid common gotchas that plague naive Java performance tests.
If not done so yet, you may want to read the longer explanation of [reasons to use JMH](http://psy-lob-saw.blogspot.com/2013/04/writing-java-micro-benchmarks-with-jmh.html).
And for common problems with Java/JSON performance testing, you may want to read [On proper performance testing of Java JSON processing](http://www.cowtowncoder.com/blog/archives/2011/05/entry_455.html).

### Writing a List of POJOs

There are 3 flavors of the test for writing out a List of 10, 1000 and 100,000 items of type `MeasurementRecord`:

* `write10AsString` (and `write1kAsString`, `write100kAsString`): serialization as `java.lang.String`
* `write10UsingWriter` (`write1kUsingWriter`, `write100kUsingWriter`): serialization using a "do-nothing" `java.io.Writer`
* `write10UsingStream` (`write1kUsingStream`, `write100kUsingStream`): serialization using a "do-nothing" `java.io.OutputStream`

the idea being that different types of output incur different kinds and amounts of overhead.
For example, aggregating output as a `java.lang.String` requires much more memory allocation and (indirectly) more Garbage Collection for discarded JSON Strings.
Conversely libraries are optimized differently for different types of output targets; some implement native output for all targets and others only have one basic target.

Size of each POJO is quite small; field names are long and values mostly numbers.
This may not be the most commonly found kind of content, but was used by the original set up
and is used here unmodified.

To run the test with a List of 10 items, serializing results as a Java String, you could use:

    java -Xmx256m -jar target/microbenchmarks.jar ".*DZoneWrite.*write10AsString" -wi 4 -i 5 -f 9

or 1000 items into `OutputStream`

    java -Xmx256m -jar target/microbenchmarks.jar ".*DZoneWrite.*write1kUsingStream" -wi 4 -i 5 -f 9

### Reading a List of POJOs

Similar to writing, there are similar variations for reading JSON as POJOs.
These tests are named like `DZoneReadPojoJackson`:

* `read10FromString` (and `read1kFromString`, `read100kFromString`): deserialization from `java.lang.String`
* `read10FromBytes` (`read1kFromBytes`, `read100kFromBytes`): deserialization from given `byte[]`

But in addition, there are also alternatives for reading same JSON as "untyped" values; that is, as `java.util.Map`s, `java.util.List`s, `String`s, `Number`s and `Boolean`s:

* `read10FromString` (and `read1kFromString`, `read100kFromString`): deserialization from `java.lang.String`
* `read10FromBytes` (`read1kFromBytes`, `read100kFromBytes`): deserialization from given `byte[]`

and the difference is from naming tests classes like `DZoneReadMapJackson` (replacing `POJO` with `Map`); test names are the same.

## Sample results

Since results may vary significantly between different platforms and JVM versions, it is best to
run benchmark on systems you are using.
But to give some idea of typical results, here are samples I get running tests on my work laptop:

### DZone tests

Results as reported on console, except sorted in descending order of performance (fastest first)

#### Writing 1000 item list as String

```
% java -Xmx256m -jar target/microbenchmarks.jar ".*DZoneWrite.*write1k.*AsString.*" -wi 4 -i 5 -f 19

# Run complete. Total time: 00:20:58

Benchmark                             Mode  Cnt     Score    Error  Units
DZoneWriteJackson.write1kAsString    thrpt   95  4377.870 ± 22.375  ops/s
DZoneWriteJacksonJr.write1kAsString  thrpt   95  3880.734 ± 32.488  ops/s
DZoneWriteBoon.write1kAsString       thrpt   95  2614.780 ± 15.105  ops/s
DZoneWriteJohnzon.write1kAsString    thrpt   95  2088.878 ± 12.612  ops/s
DZoneWriteMoshi.write1kAsString      thrpt   95  1655.735 ±  9.793  ops/s
DZoneWriteGSON.write1kAsString       thrpt   95  1209.408 ± 10.565  ops/s
DZoneWriteJsonIO.write1kAsString     thrpt   95   883.185 ±  7.002  ops/s
```

Given that the test measures throughput, Jackson (the fastest) is about 5x as fast as json.io (the slowest) for this test.

#### Reading 1000 item (POJO) list from String

```
% java -Xmx256m -jar target/microbenchmarks.jar ".*DZoneReadPojo.*read1k.*FromString.*" -wi 4 -i 5 -f 19

# Run complete. Total time: 00:15:44

Benchmark                                 Mode  Cnt     Score    Error  Units
DZoneReadPojoJackson.read1kFromString    thrpt   95  2283.530 ± 15.011  ops/s
DZoneReadPojoJacksonJr.read1kFromString  thrpt   95  2247.371 ± 15.732  ops/s
DZoneReadPojoBoon.read1kFromString       thrpt   95  1934.645 ± 58.358  ops/s
DZoneReadPojoGSON.read1kFromString       thrpt   95  1535.834 ± 14.694  ops/s
DZoneReadPojoMoshi.read1kFromString      thrpt   95   918.641 ±  9.411  ops/s
DZoneReadPojoJohnzon.read1kFromString    thrpt   95   746.234 ±  3.917  ops/s
```

And in this case, Jackson is 50% faster than GSON, twice as fast as Moshi and triple Johnzon speed.

Json-io is not (yet) included because it seems to require JSON content to be specifically written by `json-io`
itself, and not accept standard json representation (probably since it requires type information embedded).

#### Reading items as "untyped" (`Map`) from String

Benchmark                                Mode  Cnt     Score    Error  Units
DZoneReadMapBoon.read1kFromString       thrpt   95  4908.977 ± 64.932  ops/s
DZoneReadMapJacksonJr.read1kFromString  thrpt   95  2444.146 ± 27.406  ops/s
DZoneReadMapJackson.read1kFromString    thrpt   95  2116.899 ± 25.963  ops/s
DZoneReadMapGSON.read1kFromString       thrpt   95  1687.396 ± 17.711  ops/s
DZoneReadMapJsonMoshi.read1kFromString  thrpt   95   886.267 ±  5.675  ops/s
DZoneReadMapJohnzon.read1kFromString    thrpt   95   763.741 ±  8.208  ops/s
DZoneReadMapJsonIO.read1kFromString     thrpt   95   329.130 ±  6.042  ops/s

In this test, `Boon` is the somewhat surprising winner, and by clear margin: twice as fast as the
second contestant `jackson-jr`.
Boon seems to specifically optimized for the combination of "untyped" (Lists, Maps) result, and use of `String` as input.
