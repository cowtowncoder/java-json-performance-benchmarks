package com.cowtowncoder.jsonperf.dzone.write;

import java.io.OutputStream;
import java.io.Writer;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.DZoneWriteTestBase;
import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneWriteJackson extends DZoneWriteTestBase
{
    protected final ObjectWriter objectWriter;

    public DZoneWriteJackson()
    {
        objectWriter = new ObjectMapper().writerFor(MeasurementPOJO.class);
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
