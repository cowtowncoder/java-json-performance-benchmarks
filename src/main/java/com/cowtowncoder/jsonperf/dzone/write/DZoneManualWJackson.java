package com.cowtowncoder.jsonperf.dzone.write;

import java.io.OutputStream;
import java.io.Writer;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.DZoneWriteTestBase;
import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.core.io.SegmentedStringWriter;
import com.fasterxml.jackson.core.util.BufferRecycler;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneManualWJackson extends DZoneWriteTestBase
{
    private final JsonFactory _jsonFactory;
    private final SegmentedStringWriter _writer;

    public DZoneManualWJackson()
    {
        _jsonFactory = new JsonFactory();
        _writer = new SegmentedStringWriter(new BufferRecycler());
    }

    @Override
    public int _writeItems(MeasurementPOJO items, OutputStream out) throws Exception
    {
        int count = 0;
        try (JsonGenerator g = _jsonFactory.createGenerator(out)) {
            items.writeTo(g);
            count = items.size();
        }
        return count;
    }

    @Override
    public int _writeItems(MeasurementPOJO items, Writer out) throws Exception
    {
        int count = 0;
        try (JsonGenerator g = _jsonFactory.createGenerator(out)) {
            items.writeTo(g);
            count = items.size();
        }
        return count;
    }
    
    
    @Override
    public String _writeAsString(MeasurementPOJO items) throws Exception
    {
        try (JsonGenerator g = _jsonFactory.createGenerator(_writer)) {
            items.writeTo(g);
            return _writer.getAndClear();
        }
    }
}
