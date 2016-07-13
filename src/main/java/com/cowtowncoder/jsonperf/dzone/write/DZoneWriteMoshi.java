package com.cowtowncoder.jsonperf.dzone.write;

import java.io.OutputStream;
import java.io.Writer;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.DZoneWriteTestBase;
import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import okio.BufferedSink;
import okio.Okio;

/**
 * Test codec for Moshi (https://github.com/square/moshi)
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
        BufferedSink sink = Okio.buffer(Okio.sink(out));
        adapter.toJson(sink, items);
        sink.close();
        return items.size();
    }

    @Override
    public int _writeItems(MeasurementPOJO items, Writer out) throws Exception
    {
        out.write(_writeAsString(items));
        return items.size();
    }

    @Override
    public String _writeAsString(MeasurementPOJO items) throws Exception {
        return adapter.toJson(items);
    }
}
