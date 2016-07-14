package com.cowtowncoder.jsonperf.dzone.read;

import com.cowtowncoder.jsonperf.dzone.DZoneMapReadTestBase;
import com.dslplatform.json.DslJson;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadMapDslJson extends DZoneMapReadTestBase
{
    private final DslJson<Object> dsl;
    private final byte[] buffer = new byte[8192];

    public DZoneReadMapDslJson()
    {
        dsl = new DslJson<>();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> _readMap(byte[] input) throws Exception {
        return dsl.deserialize(Map.class, input, input.length);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Object> _readMap(InputStream input) throws Exception {
        return dsl.deserialize(Map.class, input, buffer);
    }

    @Override
    public Map<String, Object> _readMap(String input) throws Exception {
        return _readMap(input.getBytes("UTF-8"));
    }
}
