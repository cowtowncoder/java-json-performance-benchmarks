package com.cowtowncoder.jsonperf.dzone.read;

import java.io.*;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadPojoGSON extends DZoneReadTestBase
{
    protected final Gson gson;

    public DZoneReadPojoGSON()
    {
        gson = new GsonBuilder().create();
    }

    @Override
    public MeasurementPOJO _readItems(byte[] input) throws Exception {
        InputStreamReader r = new InputStreamReader(new ByteArrayInputStream(input), "UTF-8");
        MeasurementPOJO value = gson.fromJson(r, MeasurementPOJO.class);
        r.close();
        return value;
    }

    @Override
    public MeasurementPOJO _readItems(String input) throws Exception {
        return gson.fromJson(input, MeasurementPOJO.class);
    }
}
