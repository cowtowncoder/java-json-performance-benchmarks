package com.cowtowncoder.jsonperf.dzone.read;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.cowtowncoder.jsonperf.dzone.DZoneMapReadTestBase;
import com.fasterxml.jackson.jr.ob.JSON;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadMapJacksonJr extends DZoneMapReadTestBase
{
    protected final JSON json;

    public DZoneReadMapJacksonJr()
    {
        json = JSON.std
                /* 01-Jun-2016, tatu: Should NOT use deferred maps as they can
                 *    skew results
                 */
                .without(JSON.Feature.USE_DEFERRED_MAPS);
    }

    @Override
    public Map<?,?> _readMap(byte[] input) throws Exception {
        return json.mapFrom(input);
    }

    @Override
    public Map<?,?> _readMap(InputStream input) throws Exception {
        return json.mapFrom(input);
    }

    @Override
    public Map<?,?> _readMap(String input) throws Exception {
        return json.mapFrom(input);
    }
}
