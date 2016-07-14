package com.cowtowncoder.jsonperf.dzone.read;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.cowtowncoder.jsonperf.dzone.DZoneMapReadTestBase;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadMapFastjson extends DZoneMapReadTestBase
{
    public DZoneReadMapFastjson() { }

    @SuppressWarnings("unchecked")
    @Override
    public Map<Object,Object> _readMap(byte[] input) throws Exception {
        return (Map<Object,Object>) JSON.parse(input, Feature.DisableCircularReferenceDetect);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<Object,Object> _readMap(InputStream input) throws Exception {
        return (Map<Object,Object>) JSON.parseObject(input, Map.class, Feature.DisableCircularReferenceDetect);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<Object,Object> _readMap(String input) throws Exception {
        return (Map<Object,Object>) JSON.parse(input, Feature.DisableCircularReferenceDetect);
    }
}
