package com.cowtowncoder.jsonperf.dzone;

import java.io.OutputStream;
import java.io.Writer;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.fasterxml.jackson.jr.ob.JSON;

/**
 * Test codec for Jackson jr (https://github.com/FasterXML/jackson-jr)
 */
@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneWriteJacksonJr extends DZoneTestBase
{
    protected final JSON json;
    
    public DZoneWriteJacksonJr()
    {
        json = JSON.std;
    }

    @Override
    public int _writeItems(MeasurementPOJO items, OutputStream out) throws Exception
    {
        json.write(items, out);
        return items.size();
    }

    @Override
    public int _writeItems(MeasurementPOJO items, Writer out) throws Exception
    {
        json.write(items, out);
        return items.size();
    }

    @Override
    public String _writeAsString(MeasurementPOJO items) throws Exception {
        return json.asString(items);
    }
}
