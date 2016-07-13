package com.cowtowncoder.jsonperf.dzone.read;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.cowtowncoder.jsonperf.dzone.DZoneReadTestBase;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadMapFastjson extends DZoneReadTestBase<Map<Object,Object>>
{

    public DZoneReadMapFastjson()
    {
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<Object,Object> _readItems(byte[] input) throws Exception {
        return (Map<Object,Object>) JSON.parse(input, Feature.DisableCircularReferenceDetect);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<Object,Object> _readItems(InputStream input) throws Exception {
        return (Map<Object,Object>) JSON.parseObject(input, Map.class, Feature.DisableCircularReferenceDetect);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<Object,Object> _readItems(String input) throws Exception {
        return (Map<Object,Object>) JSON.parse(input, Feature.DisableCircularReferenceDetect);
    }
}
