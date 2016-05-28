package com.cowtowncoder.jsonperf.dzone.read;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import okio.Buffer;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadPojoMoshi extends DZoneReadTestBase<MeasurementPOJO>
{
    protected final JsonAdapter<MeasurementPOJO> adapter;

    public DZoneReadPojoMoshi()
    {
        adapter = new Moshi.Builder().build()
                .adapter(MeasurementPOJO.class);
    }

    @Override
    public MeasurementPOJO _readItems(byte[] input) throws Exception {
        Buffer buffer = new Buffer();
        buffer.write(input);
        return adapter.fromJson(buffer);
    }

    @Override
    public MeasurementPOJO _readItems(String input) throws Exception {
        return adapter.fromJson(input);
    }
}
