package com.cowtowncoder.jsonperf.dzone.read;

import java.io.*;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cedarsoftware.util.io.JsonReader;
import com.cowtowncoder.jsonperf.dzone.DZoneMapReadTestBase;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadMapJsonIO extends DZoneMapReadTestBase
{
    public DZoneReadMapJsonIO() { }

    @Override
    public Map<?,?> _readMap(byte[] input) throws Exception {
        return _readMap(new ByteArrayInputStream(input));
    }

    @Override
    public Map<?,?> _readMap(InputStream input) throws Exception {
        JsonReader r = new JsonReader(input);
        Map<?,?> value = (Map<?,?>) r.readObject();
        r.close();
        return value;
    }

    @Override
    public Map<?,?> _readMap(String input) throws Exception {
        Map<?,?> value = (Map<?,?>) JsonReader.jsonToJava(input);
        return value;
    }
}
