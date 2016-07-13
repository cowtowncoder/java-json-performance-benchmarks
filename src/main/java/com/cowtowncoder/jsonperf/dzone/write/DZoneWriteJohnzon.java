package com.cowtowncoder.jsonperf.dzone.write;

import java.io.*;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.apache.johnzon.mapper.*;

import com.cowtowncoder.jsonperf.dzone.DZoneWriteTestBase;
import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneWriteJohnzon extends DZoneWriteTestBase
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
