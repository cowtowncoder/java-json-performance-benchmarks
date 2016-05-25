package com.cowtowncoder.jsonperf.dzone.read;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;

import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadPojoBoon extends DZoneReadTestBase<MeasurementPOJO>
{
    protected final ObjectMapper mapper;

    public DZoneReadPojoBoon()
    {
        mapper = JsonFactory.create();
    }

    @Override
    public MeasurementPOJO _readItems(byte[] input) throws Exception {
        return mapper.readValue(input, MeasurementPOJO.class);
    }

    @Override
    public MeasurementPOJO _readItems(InputStream input) throws Exception {
        return mapper.readValue(input, MeasurementPOJO.class);
    }

    @Override
    public MeasurementPOJO _readItems(String input) throws Exception {
        return mapper.readValue(input, MeasurementPOJO.class);
    }
}
