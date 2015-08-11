package com.cowtowncoder.jsonperf.dzone.write;

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
public class DZoneWriteGSON extends DZoneWriteTestBase
{
    protected final Gson gson;

    public DZoneWriteGSON()
    {
        gson = new GsonBuilder().create();
    }

    @Override
    public int _writeItems(MeasurementPOJO items, OutputStream out) throws Exception
    {
        // Alas, GSON does not have direct/native method for OutputStream, use JDK wrapper
        gson.toJson(items, new OutputStreamWriter(out, "UTF-8"));
        return items.size();
    }

    @Override
    public int _writeItems(MeasurementPOJO items, Writer out) throws Exception
    {
        gson.toJson(items, out);
        return items.size();
    }

    @Override
    public String _writeAsString(MeasurementPOJO items) throws Exception {
        return gson.toJson(items);
    }
}
