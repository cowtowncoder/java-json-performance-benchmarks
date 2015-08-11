package com.cowtowncoder.jsonperf.dzone.read;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;

import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;
import com.cowtowncoder.jsonperf.dzone.TestData;

abstract class DZoneReadTestBase
{
    protected final static byte[] list10b = TestData.Input.list10Bytes;
    protected final static byte[] list1000b = TestData.Input.list1000Bytes;
    protected final static byte[] list100000b = TestData.Input.list100000Bytes;

    protected final static String list10s = TestData.Input.list10String;
    protected final static String list1000s = TestData.Input.list1000String;
    protected final static String list100000s = TestData.Input.list100000String;

    // // // Abstract methods for sub-classes

    public abstract MeasurementPOJO _readItems(byte[] input) throws Exception;
    public abstract MeasurementPOJO _readItems(String input) throws Exception;

    // // // Using OutputStream

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void read10FromBytes(Blackhole bh) throws Exception {
        bh.consume(_readItems(list10b));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void read1kFromBytes(Blackhole bh) throws Exception {
        bh.consume(_readItems(list1000b));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void read100kFromBytes(Blackhole bh) throws Exception {
        bh.consume(_readItems(list100000b));
    }

    // // // Using readr

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void read10FromString(Blackhole bh) throws Exception {
        bh.consume(_readItems(list10s));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void read1kFromString(Blackhole bh) throws Exception {
        bh.consume(_readItems(list1000s));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void read100kFromString(Blackhole bh) throws Exception {
        bh.consume(_readItems(list100000s));
    }

}
