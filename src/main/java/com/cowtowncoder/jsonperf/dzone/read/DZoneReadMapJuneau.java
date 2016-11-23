package com.cowtowncoder.jsonperf.dzone.read;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.DZoneMapReadTestBase;

import org.apache.juneau.json.JsonParser;
import org.apache.juneau.parser.ReaderParser;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadMapJuneau extends DZoneMapReadTestBase
{
    private final ReaderParser parser = JsonParser.DEFAULT_STRICT;

    public DZoneReadMapJuneau() { }

    @Override
    public Map<?,?> _readMap(byte[] input) throws Exception {
        return parser.parseIntoMap(input, new HashMap<String,Object>(),
                String.class, Object.class);
    }

    @Override
    public Map<?,?> _readMap(InputStream input) throws Exception {
        return parser.parseIntoMap(input, new HashMap<String,Object>(),
                String.class, Object.class);
    }

    @Override
    public Map<?,?> _readMap(String input) throws Exception {
        return parser.parseIntoMap(input, new HashMap<String,Object>(),
                String.class, Object.class);
    }
}
