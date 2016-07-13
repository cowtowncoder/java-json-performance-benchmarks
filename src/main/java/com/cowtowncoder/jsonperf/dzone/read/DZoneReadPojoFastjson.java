package com.cowtowncoder.jsonperf.dzone.read;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.cowtowncoder.jsonperf.dzone.DZoneReadTestBase;
import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadPojoFastjson extends DZoneReadTestBase<MeasurementPOJO>
{

    public DZoneReadPojoFastjson()
    {
    }

    @Override
    public MeasurementPOJO _readItems(byte[] input) throws Exception {
        return JSON.parseObject(input, MeasurementPOJO.class, Feature.DisableCircularReferenceDetect);
    }

    @Override
    public MeasurementPOJO _readItems(InputStream input) throws Exception {
        return JSON.parseObject(input, MeasurementPOJO.class, Feature.DisableCircularReferenceDetect);
    }

    @Override
    public MeasurementPOJO _readItems(String input) throws Exception {
        return JSON.parseObject(input, MeasurementPOJO.class, Feature.DisableCircularReferenceDetect);
    }
}
