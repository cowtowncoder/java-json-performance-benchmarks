package com.cowtowncoder.jsonperf.dzone.read;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.johnzon.mapper.Mapper;
import org.apache.johnzon.mapper.MapperBuilder;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.DZoneMapReadTestBase;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadMapJohnzon extends DZoneMapReadTestBase
{
    protected final Mapper mapper;

    public DZoneReadMapJohnzon()
    {
        mapper = new MapperBuilder().build();
    }

    @Override
    public Map<?,?> _readMap(byte[] input) throws Exception {
        return _readMap(new ByteArrayInputStream(input));
    }

    @Override
    public Map<?,?> _readMap(InputStream input) throws Exception {
        // force assign to ensure casting checks the type
        Map<?,?> value = mapper.readObject(input, Object.class);
        return value;
    }

    @Override
    public Map<?,?> _readMap(String input) throws Exception {
        Map<?,?> value =  mapper.readObject(input, Object.class);
        return value;
    }
}
