package com.cowtowncoder.jsonperf.dzone.read;

import java.io.*;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cedarsoftware.util.io.JsonReader;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadMapJsonIO extends DZoneReadTestBase<Map<?,?>>
{
    public DZoneReadMapJsonIO() { }

    @Override
    public Map<?,?> _readItems(byte[] input) throws Exception {
        return _readItems(new ByteArrayInputStream(input));
    }

    @Override
    public Map<?,?> _readItems(InputStream input) throws Exception {
        JsonReader r = new JsonReader(input);
        Map<?,?> value = (Map<?,?>) r.readObject();
        r.close();
        return value;
    }

    @Override
    public Map<?,?> _readItems(String input) throws Exception {
        Map<?,?> value = (Map<?,?>) JsonReader.jsonToJava(input);
        return value;
    }
}
