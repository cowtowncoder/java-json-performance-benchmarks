package com.cowtowncoder.jsonperf.dzone.read;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.DZoneMapReadTestBase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadMapJackson extends DZoneMapReadTestBase
{
    private final ObjectReader objectReader;

    public DZoneReadMapJackson()
    {
        objectReader = new ObjectMapper().readerFor(Map.class);
    }

    @Override
    public Map<Object,Object> _readMap(byte[] input) throws Exception {
        return objectReader.readValue(input);
    }

    @Override
    public Map<Object,Object> _readMap(InputStream input) throws Exception {
        return objectReader.readValue(input);
    }

    @Override
    public Map<Object,Object> _readMap(String input) throws Exception {
        return objectReader.readValue(input);
    }
}
