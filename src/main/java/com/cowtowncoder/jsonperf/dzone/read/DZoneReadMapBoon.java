package com.cowtowncoder.jsonperf.dzone.read;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.DZoneMapReadTestBase;

import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadMapBoon extends DZoneMapReadTestBase
{
    protected final ObjectMapper mapper;

    public DZoneReadMapBoon()
    {
        mapper = JsonFactory.create();
    }

    @Override
    public Map<?,?> _readMap(byte[] input) throws Exception {
        return mapper.readValue(input, Map.class);
    }

    @Override
    public Map<?,?> _readMap(InputStream input) throws Exception {
        return mapper.readValue(input, Map.class);
    }

    @Override
    public Map<?,?> _readMap(String input) throws Exception {
        return mapper.readValue(input, Map.class);
    }
}
