package com.cowtowncoder.jsonperf.dzone;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadJackson extends DZoneReadTestBase
{
    protected final ObjectReader objectReader;

    public DZoneReadJackson()
    {
        objectReader = new ObjectMapper().readerFor(MeasurementPOJO.class);
    }

    @Override
    public MeasurementPOJO _readItems(byte[] input) throws Exception {
        return objectReader.readValue(input);
    }

    @Override
    public MeasurementPOJO _readItems(String input) throws Exception {
        return objectReader.readValue(input);
    }
}
