package com.cowtowncoder.jsonperf.dzone;

import java.io.OutputStream;
import java.io.Writer;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneWriteJackson extends DZoneTestBase
{
    protected final ObjectWriter objectWriter;
    
    public DZoneWriteJackson()
    {
        objectWriter = new ObjectMapper().writer();
    }

    @Override
    public int _writeItems(List<MeasurementRecord> items, OutputStream out) throws Exception
    {
        objectWriter.writeValue(out, items);
        return items.size();
    }

    @Override
    public int _writeItems(List<MeasurementRecord> items, Writer out) throws Exception
    {
        objectWriter.writeValue(out, items);
        return items.size();
    }

    @Override
    public String _writeAsString(List<MeasurementRecord> items) throws Exception {
        return objectWriter.writeValueAsString(items);
    }
}
