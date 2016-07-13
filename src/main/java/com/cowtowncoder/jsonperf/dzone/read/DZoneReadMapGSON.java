package com.cowtowncoder.jsonperf.dzone.read;

import java.io.*;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.DZoneReadTestBase;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadMapGSON extends DZoneReadTestBase<Map<?,?>>
{
    protected final Gson gson;

    public DZoneReadMapGSON()
    {
        gson = new GsonBuilder().create();
    }

    @Override
    public Map<?,?> _readItems(byte[] input) throws Exception {
        return _readItems(new ByteArrayInputStream(input));
    }

    @Override
    public Map<?,?> _readItems(InputStream input) throws Exception {
        InputStreamReader r = new InputStreamReader(input, "UTF-8");
        Map<?,?> value = gson.fromJson(r, Map.class);
        r.close();
        return value;
    }

    @Override
    public Map<?,?> _readItems(String input) throws Exception {
        return gson.fromJson(input, Map.class);
    }
}
