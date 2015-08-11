package com.cowtowncoder.jsonperf.dzone.write;

import java.io.*;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

/**
 * Test codec for Moshi (https://github.com/square/moshi)
 *<p>
 * Challenges here are mostly due to rather exotic API that Moshi exposes: it seems
 * to aim at highly optimized I/O handling, buffering; but the end result is something
 * that is difficult use in ways other than just buffering the whole contents in
 * memory. Since the goal here is to minimize actual buffering overhead there does not
 * seem to be a good match, and what we shall do then is to build a JSON String,
 * write that in varius no-op outputs. This is not optimal, but with given API there
 * isn't much we can do, without digging knee-deep in obscure buffer/sink classes
 * that Moshi exposes from ok-io.
 *<p>
 * If anyone wants to tackle write-to-Stream / write-to-Writer cases, PRs happily accepted.
 */
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneWriteMoshi extends DZoneWriteTestBase
{
    protected final JsonAdapter<MeasurementPOJO> adapter;

    public DZoneWriteMoshi()
    {
        adapter = new Moshi.Builder().build()
                .adapter(MeasurementPOJO.class);
    }

    @Override
    public int _writeItems(MeasurementPOJO items, OutputStream out) throws Exception
    {
        OutputStreamWriter w = new OutputStreamWriter(out, "UTF-8");
        w.write(adapter.toJson(items));
        w.close();
        return items.size();
    }

    @Override
    public int _writeItems(MeasurementPOJO items, Writer out) throws Exception
    {
        out.write(adapter.toJson(items));
        return items.size();
    }

    @Override
    public String _writeAsString(MeasurementPOJO items) throws Exception {
        return adapter.toJson(items);
    }
}
