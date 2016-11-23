package com.cowtowncoder.jsonperf.dzone.write;

import java.io.*;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.DZoneWriteTestBase;
import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;

import org.apache.juneau.json.JsonSerializer;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneWriteJuneau extends DZoneWriteTestBase
{
    private final JsonSerializer serializer = JsonSerializer.DEFAULT;
    
    public DZoneWriteJuneau() { }

    @Override
    public int _writeItems(MeasurementPOJO items, OutputStream out) throws Exception
    {
        serializer.serialize(items, out);
        return items.size();
    }

    @Override
    public int _writeItems(MeasurementPOJO items, Writer out) throws Exception
    {
        serializer.serialize(items, out);
        return items.size();
    }

    @Override
    public String _writeAsString(MeasurementPOJO items) throws Exception {
        return serializer.serialize(items);
    }
}
