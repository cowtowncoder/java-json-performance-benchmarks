package com.cowtowncoder.jsonperf.dzone;

import java.io.OutputStream;
import java.io.Writer;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.infra.Blackhole;

import com.cowtowncoder.jsonperf.util.NopOutputStream;
import com.cowtowncoder.jsonperf.util.NopWriter;

/**
 * Base class for tests inspired by
 * <a href="https://dzone.com/articles/compare-json-api">DZone Jackson vs GSON</a>
 * test.
 */
abstract class DZoneTestBase
{
    protected final static List<MeasurementRecord> list10 = MeasurementRecord.construct(10);
    protected final static List<MeasurementRecord> list1000 = MeasurementRecord.construct(1000);
    protected final static List<MeasurementRecord> list100000 = MeasurementRecord.construct(100000);

    // These are reusable, thread-safe; reuse to minimize overhead

    protected final static NopWriter NOP_WRITER = new NopWriter();
    protected final static NopOutputStream NOP_OUTPUT_STREAM = new NopOutputStream();

    // // // Abstract methods for sub-classes
    
    public abstract int _writeItems(List<MeasurementRecord> items, Writer out) throws Exception;

    public abstract int _writeItems(List<MeasurementRecord> items, OutputStream out) throws Exception;
    
    public abstract String _writeAsString(List<MeasurementRecord> items) throws Exception;

    // // // Using OutputStream

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void write10UsingStream(Blackhole bh) throws Exception {
        bh.consume(_writeItems(list10, NOP_OUTPUT_STREAM));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void write1kUsingStream(Blackhole bh) throws Exception {
        bh.consume(_writeItems(list1000, NOP_OUTPUT_STREAM));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void write100kUsingStream(Blackhole bh) throws Exception {
        bh.consume(_writeItems(list100000, NOP_OUTPUT_STREAM));
    }

    // // // Using Writer

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void write10UsingWriter(Blackhole bh) throws Exception {
        bh.consume(_writeItems(list10, NOP_WRITER));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void write1kUsingWriter(Blackhole bh) throws Exception {
        bh.consume(_writeItems(list1000, NOP_WRITER));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void write100kUsingWriter(Blackhole bh) throws Exception {
        bh.consume(_writeItems(list100000, NOP_WRITER));
    }

    // // // As String: much more time spent on String construction; more overhead

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void write10AsString(Blackhole bh) throws Exception {
        bh.consume(_writeAsString(list10));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void write1kAsString(Blackhole bh) throws Exception {
        bh.consume(_writeAsString(list1000));
    }

    @Benchmark
    @OutputTimeUnit(TimeUnit.SECONDS)
    public void write100kAsString(Blackhole bh) throws Exception {
        bh.consume(_writeAsString(list100000));
    }
}
