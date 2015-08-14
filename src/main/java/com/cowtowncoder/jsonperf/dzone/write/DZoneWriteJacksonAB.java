package com.cowtowncoder.jsonperf.dzone.write;

import java.io.OutputStream;
import java.io.Writer;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

// Jackson test, but using Afterburner module for extra speed!
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneWriteJacksonAB extends DZoneWriteTestBase
{
    protected final ObjectWriter objectWriter;

    public DZoneWriteJacksonAB()
    {
        ObjectMapper mapper = new ObjectMapper()
            .registerModule(new AfterburnerModule());
        objectWriter = mapper.writerFor(MeasurementPOJO.class);
    }

    @Override
    public int _writeItems(MeasurementPOJO items, OutputStream out) throws Exception
    {
        objectWriter.writeValue(out, items);
        return items.size();
    }

    @Override
    public int _writeItems(MeasurementPOJO items, Writer out) throws Exception
    {
        objectWriter.writeValue(out, items);
        return items.size();
    }

    @Override
    public String _writeAsString(MeasurementPOJO items) throws Exception {
        return objectWriter.writeValueAsString(items);
    }
}
