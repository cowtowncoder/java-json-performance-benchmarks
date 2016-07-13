package com.cowtowncoder.jsonperf.dzone.read;

import com.cowtowncoder.jsonperf.dzone.DZoneReadTestBase;
import com.cowtowncoder.jsonperf.dzone.MeasurementPOJO;
import com.dslplatform.json.DslJson;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

import java.io.InputStream;
import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@OutputTimeUnit(TimeUnit.SECONDS)
public class DZoneReadPojoDslJson extends DZoneReadTestBase<MeasurementPOJO>
{
    private final DslJson<Object> dsl;
    private final byte[] buffer = new byte[8192];

    public DZoneReadPojoDslJson()
    {
        dsl = new DslJson<>();
    }

    @Override
    public MeasurementPOJO _readItems(byte[] input) throws Exception {
        return dsl.deserialize(MeasurementPOJO.class, input, input.length);
    }

    @Override
    public MeasurementPOJO _readItems(InputStream input) throws Exception {
        return dsl.deserialize(MeasurementPOJO.class, input, buffer);
    }

    @Override
    public MeasurementPOJO _readItems(String input) throws Exception {
        return _readItems(input.getBytes("UTF-8"));
    }
}
