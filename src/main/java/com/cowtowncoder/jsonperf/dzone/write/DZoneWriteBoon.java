package com.cowtowncoder.jsonperf.dzone.write;

import java.io.*;
import java.util.concurrent.TimeUnit;

import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneWriteBoon extends DZoneWriteTestBase
{
    protected final ObjectMapper mapper;

    public DZoneWriteBoon()
    {
        mapper =  JsonFactory.create();
    }

    @Override
    public int _writeItems(MeasurementPOJO items, OutputStream out) throws Exception
    {
        mapper.writeValue(out, items);
        return items.size();
    }

    @Override
    public int _writeItems(MeasurementPOJO items, Writer out) throws Exception
    {
        mapper.writeValue(out, items);
        return items.size();
    }

    @Override
    public String _writeAsString(MeasurementPOJO items) throws Exception {
        return mapper.writeValueAsString(items);
    }
}
