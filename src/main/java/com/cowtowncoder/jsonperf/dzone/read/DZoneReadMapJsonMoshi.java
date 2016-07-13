package com.cowtowncoder.jsonperf.dzone.read;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.IOUtils;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.DZoneReadTestBase;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

@SuppressWarnings("rawtypes")
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadMapJsonMoshi extends DZoneReadTestBase<Map>
{
    protected final JsonAdapter<Map> adapter;

    public DZoneReadMapJsonMoshi()
    {
        adapter = new Moshi.Builder().build()
                .adapter(Map.class);
    }

    @Override
    public Map _readItems(byte[] input) throws Exception {
        // In theory there may be a way to use Writer etc; but it gets complicated
        // enough with the strange API that... yeah.
        return adapter.fromJson(new String(input, "UTF-8"));
    }

    @Override
    public Map _readItems(InputStream input) throws Exception {
        return adapter.fromJson(IOUtils.toString(input, "UTF-8"));
    }

    @Override
    public Map _readItems(String input) throws Exception {
        return adapter.fromJson(input);
    }
}
