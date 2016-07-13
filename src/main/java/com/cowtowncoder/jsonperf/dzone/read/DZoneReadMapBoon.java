package com.cowtowncoder.jsonperf.dzone.read;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.DZoneReadTestBase;

import org.boon.json.JsonFactory;
import org.boon.json.ObjectMapper;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadMapBoon extends DZoneReadTestBase<Map<?,?>>
{
    protected final ObjectMapper mapper;

    public DZoneReadMapBoon()
    {
        mapper = JsonFactory.create();
    }

    @Override
    public Map<?,?> _readItems(byte[] input) throws Exception {
        return mapper.readValue(input, Map.class);
    }

    @Override
    public Map<?,?> _readItems(InputStream input) throws Exception {
        return mapper.readValue(input, Map.class);
    }

    @Override
    public Map<?,?> _readItems(String input) throws Exception {
        return mapper.readValue(input, Map.class);
    }
}
