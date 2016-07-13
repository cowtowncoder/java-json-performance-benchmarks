package com.cowtowncoder.jsonperf.dzone.write;

import java.io.OutputStream;
import java.io.Writer;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cedarsoftware.util.io.JsonWriter;
import com.cowtowncoder.jsonperf.dzone.DZoneWriteTestBase;
import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneWriteJsonIO extends DZoneWriteTestBase
{
    public DZoneWriteJsonIO() { }

    @Override
    public int _writeItems(MeasurementPOJO items, OutputStream out) throws Exception
    {
        JsonWriter w = new JsonWriter(out);
        w.write(items);
        w.close();
        return items.size();
    }
    
    @Override
    public int _writeItems(MeasurementPOJO items, Writer out) throws Exception
    {
        /* 05-Aug-2015, tatu: Looks like json-io does not expose option to write JSON via
         *    java.io.Writer. Because of this, we have to choose one of alternatives, and
         *    trying to do that without unfair added overhead; or unfair advantage.
         *    Serializing as a String would add significant amount of overhead, so let's
         *    instead just swap to using "bogus" NOP OutputStream: should be close enough.
         * 
         */
        JsonWriter w = new JsonWriter(NOP_OUTPUT_STREAM);
        w.write(items);
        w.close();
        return items.size();
    }

    @Override
    public String _writeAsString(MeasurementPOJO items) throws Exception {
        return JsonWriter.objectToJson(items);
    }
}
