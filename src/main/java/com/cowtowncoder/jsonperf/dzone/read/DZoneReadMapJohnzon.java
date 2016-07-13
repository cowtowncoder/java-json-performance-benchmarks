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

import com.cowtowncoder.jsonperf.dzone.DZoneReadTestBase;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadMapJohnzon extends DZoneReadTestBase<Map<?,?>>
{
    protected final Mapper mapper;

    public DZoneReadMapJohnzon()
    {
        mapper = new MapperBuilder().build();
    }

    @Override
    public Map<?,?> _readItems(byte[] input) throws Exception {
        return _readItems(new ByteArrayInputStream(input));
    }

    @Override
    public Map<?,?> _readItems(InputStream input) throws Exception {
        // force assign to ensure casting checks the type
        Map<?,?> value = mapper.readObject(input, Object.class);
        return value;
    }

    @Override
    public Map<?,?> _readItems(String input) throws Exception {
        Map<?,?> value =  mapper.readObject(input, Object.class);
        return value;
    }
}
