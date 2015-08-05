package com.cowtowncoder.jsonperf.dzone;

import java.io.*;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import org.apache.johnzon.mapper.*;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneWriteJohnzon extends DZoneTestBase
{
    protected final Mapper mapper;

    public DZoneWriteJohnzon()
    {
        mapper = new MapperBuilder().build();
    }

    @Override
    public int _writeItems(MeasurementPOJO items, OutputStream out) throws Exception
    {
        mapper.writeObject(items, out);
        return items.size();
    }

    @Override
    public int _writeItems(MeasurementPOJO items, Writer out) throws Exception
    {
        mapper.writeObject(items, out);
        return items.size();
    }

    @Override
    public String _writeAsString(MeasurementPOJO items) throws Exception {
        return mapper.writeObjectAsString(items);
    }
}
