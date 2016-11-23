package com.cowtowncoder.jsonperf.dzone.read;

import java.io.*;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.DZoneReadTestBase;
import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;

import org.apache.juneau.json.JsonParser;
import org.apache.juneau.parser.ReaderParser;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadPojoJuneau extends DZoneReadTestBase<MeasurementPOJO>
{
    private final ReaderParser parser = JsonParser.DEFAULT_STRICT;

    public DZoneReadPojoJuneau() { }

    @Override
    public MeasurementPOJO _readItems(byte[] input) throws Exception {
        return parser.parse(input, MeasurementPOJO.class);
    }

    @Override
    public MeasurementPOJO _readItems(InputStream input) throws Exception {
        return parser.parse(input, MeasurementPOJO.class);
    }

    @Override
    public MeasurementPOJO _readItems(String input) throws Exception {
        return parser.parse(input, MeasurementPOJO.class);
    }
}
