package com.cowtowncoder.jsonperf.dzone.write;

import com.cowtowncoder.jsonperf.dzone.DZoneWriteTestBase;
import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;
import com.dslplatform.json.DslJson;
import com.dslplatform.json.JsonWriter;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.io.OutputStream;
import java.io.Writer;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneWriteDslJson extends DZoneWriteTestBase
{
    private final DslJson<Object> json;
    private final JsonWriter writer;

    public DZoneWriteDslJson()
    {
        json =  new DslJson<>();
        writer = new JsonWriter();
    }

    @Override
    public int _writeItems(MeasurementPOJO items, OutputStream out) throws Exception
    {
        writer.reset();
        json.serialize(writer, items);
        writer.toStream(out);
        return items.size();
    }

    @Override
    public int _writeItems(MeasurementPOJO items, Writer out) throws Exception
    {
        writer.reset();
        json.serialize(writer, items);
        out.write(writer.toString());
        return items.size();
    }

    @Override
    public String _writeAsString(MeasurementPOJO items) throws Exception {
        writer.reset();
        json.serialize(writer, items);
        return writer.toString();
    }
}
