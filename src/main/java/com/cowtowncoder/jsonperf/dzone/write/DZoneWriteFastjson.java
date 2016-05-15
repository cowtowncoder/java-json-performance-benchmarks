package com.cowtowncoder.jsonperf.dzone.write;

import java.io.OutputStream;
import java.io.Writer;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneWriteFastjson extends DZoneWriteTestBase
{

    public DZoneWriteFastjson()
    {
    }

    @Override
    public int _writeItems(MeasurementPOJO items, OutputStream out) throws Exception
    {
        JSON.writeJSONString(out, items, SerializerFeature.DisableCircularReferenceDetect);
        return items.size();
    }

    @Override
    public int _writeItems(MeasurementPOJO items, Writer out) throws Exception
    {
        JSON.writeJSONString(out, items, SerializerFeature.DisableCircularReferenceDetect);
        return items.size();
    }

    @Override
    public String _writeAsString(MeasurementPOJO items) throws Exception {
        return JSON.toJSONString(items, SerializerFeature.DisableCircularReferenceDetect);
    }
}
