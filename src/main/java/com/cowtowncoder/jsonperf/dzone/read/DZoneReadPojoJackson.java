package com.cowtowncoder.jsonperf.dzone.read;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.DZoneReadTestBase;
import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadPojoJackson extends DZoneReadTestBase<MeasurementPOJO>
{
    private final ObjectReader objectReader;

    public DZoneReadPojoJackson()
    {
        objectReader = new ObjectMapper().readerFor(MeasurementPOJO.class);
    }

    @Override
    public MeasurementPOJO _readItems(byte[] input) throws Exception {
        return objectReader.readValue(input);
    }

    @Override
    public MeasurementPOJO _readItems(InputStream input) throws Exception {
        return objectReader.readValue(input);
    }

    @Override
    public MeasurementPOJO _readItems(String input) throws Exception {
        return objectReader.readValue(input);
    }
}
