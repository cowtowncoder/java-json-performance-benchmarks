package com.cowtowncoder.jsonperf.dzone.read;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.apache.johnzon.mapper.Mapper;
import org.apache.johnzon.mapper.MapperBuilder;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadPojoJohnzon extends DZoneReadTestBase<MeasurementPOJO>
{
    protected final Mapper mapper;

    public DZoneReadPojoJohnzon()
    {
        mapper = new MapperBuilder().build();
    }

    @Override
    public MeasurementPOJO _readItems(byte[] input) throws Exception {
        return mapper.readObject(new ByteArrayInputStream(input), MeasurementPOJO.class);
    }

    @Override
    public MeasurementPOJO _readItems(InputStream input) throws Exception {
        return mapper.readObject(input, MeasurementPOJO.class);
    }

    @Override
    public MeasurementPOJO _readItems(String input) throws Exception {
        return mapper.readObject(input, MeasurementPOJO.class);
    }
}
