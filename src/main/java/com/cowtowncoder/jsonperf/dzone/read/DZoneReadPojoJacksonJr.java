package com.cowtowncoder.jsonperf.dzone.read;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;
import com.fasterxml.jackson.jr.ob.JSON;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadPojoJacksonJr extends DZoneReadTestBase<MeasurementPOJO>
{
    protected final JSON json;

    public DZoneReadPojoJacksonJr()
    {
        json = JSON.std
                /* 01-Jun-2016, tatu: Should NOT use deferred maps as they can
                 *    skew results
                 */
                .without(JSON.Feature.USE_DEFERRED_MAPS);
    }

    @Override
    public MeasurementPOJO _readItems(byte[] input) throws Exception {
        return json.beanFrom(MeasurementPOJO.class, input);
    }

    @Override
    public MeasurementPOJO _readItems(InputStream input) throws Exception {
        return json.beanFrom(MeasurementPOJO.class, input);
    }

    @Override
    public MeasurementPOJO _readItems(String input) throws Exception {
        return json.beanFrom(MeasurementPOJO.class, input);
    }
}
