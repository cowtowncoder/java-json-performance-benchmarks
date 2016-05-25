package com.cowtowncoder.jsonperf.dzone.read;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.module.afterburner.AfterburnerModule;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadPojoJacksonAB extends DZoneReadTestBase<MeasurementPOJO>
{
    private final ObjectReader objectReader;

    public DZoneReadPojoJacksonAB()
    {
        ObjectMapper mapper = new ObjectMapper()
            .registerModule(new AfterburnerModule());
        objectReader = mapper.readerFor(MeasurementPOJO.class);
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
