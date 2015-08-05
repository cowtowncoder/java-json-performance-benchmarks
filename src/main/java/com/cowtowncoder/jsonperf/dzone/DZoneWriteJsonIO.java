package com.cowtowncoder.jsonperf.dzone;

import java.io.OutputStream;
import java.io.Writer;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cedarsoftware.util.io.JsonWriter;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneWriteJsonIO extends DZoneTestBase
{
    public DZoneWriteJsonIO() { }

    @Override
    public int _writeItems(List<MeasurementRecord> items, OutputStream out) throws Exception
    {
        JsonWriter w = new JsonWriter(out);
        w.write(items);
        w.close();
        return items.size();
    }
    
    @Override
    public int _writeItems(List<MeasurementRecord> items, Writer out) throws Exception
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
    public String _writeAsString(List<MeasurementRecord> items) throws Exception {
        return JsonWriter.objectToJson(items);
    }
}
