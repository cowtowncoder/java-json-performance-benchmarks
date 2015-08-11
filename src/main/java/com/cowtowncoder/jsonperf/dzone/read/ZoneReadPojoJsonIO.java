package com.cowtowncoder.jsonperf.dzone.read;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cedarsoftware.util.io.JsonReader;
import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class ZoneReadPojoJsonIO extends DZoneReadTestBase
{
    private final static Map<String,Object> NO_ARGS = Collections.emptyMap();

    public ZoneReadPojoJsonIO() { }

    @Override
    public MeasurementPOJO _readItems(byte[] input) throws Exception {
        return (MeasurementPOJO) JsonReader.jsonToJava(new ByteArrayInputStream(input), NO_ARGS);
    }

    @Override
    public MeasurementPOJO _readItems(String input) throws Exception {
        return (MeasurementPOJO) JsonReader.jsonToJava(input);
    }
}
