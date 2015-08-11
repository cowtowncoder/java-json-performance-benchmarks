package com.cowtowncoder.jsonperf.dzone.read;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;
import com.fasterxml.jackson.jr.ob.JSON;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadPojoJacksonJr extends DZoneReadTestBase
{
    protected final JSON json;

    public DZoneReadPojoJacksonJr()
    {
        json = JSON.std;
    }

    @Override
    public MeasurementPOJO _readItems(byte[] input) throws Exception {
        return json.beanFrom(MeasurementPOJO.class, input);
    }

    @Override
    public MeasurementPOJO _readItems(String input) throws Exception {
        return json.beanFrom(MeasurementPOJO.class, input);
    }
}
