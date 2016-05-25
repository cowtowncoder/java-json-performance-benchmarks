package com.cowtowncoder.jsonperf.dzone.read;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

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
        // In theory there may be a way to use Writer etc; but it gets complicated
        // enough with the strange API that... yeah.
        return adapter.fromJson(new String(input, "UTF-8"));
    }

    @Override
    public MeasurementPOJO _readItems(InputStream input) throws Exception {
        // In theory there may be a way to use Writer etc; but it gets complicated
        // enough with the strange API that... yeah.
        return adapter.fromJson(IOUtils.toString(input, "UTF-8"));
    }

    @Override
    public MeasurementPOJO _readItems(String input) throws Exception {
        return adapter.fromJson(input);
    }
}
