package com.cowtowncoder.jsonperf.dzone;

import java.util.*;

public class MeasurementRecord
{
    public String measurementId;
    public long duration;
    public long time;

    public MeasurementType type;

    // for deser
    protected MeasurementRecord() { }
    
    public MeasurementRecord(String measurementId, long duration, long time,
            MeasurementType type)
    {
        this.measurementId = measurementId;
        this.duration = duration;
        this.time = time;
        this.type = type;
    }

    public enum MeasurementType {
        WEB_REQUEST,SQL,EXCEPTION,METHOD_CALL
    }

    public static List<MeasurementRecord> construct(int count)
    {
        List<MeasurementRecord> result = new ArrayList<>(count);
        long now = System.currentTimeMillis();
        for (int i = 0; i < count; ++i) {
            result.add(new MeasurementRecord("/test.html",
                    10, now+i, MeasurementType.WEB_REQUEST));
        }
        return result;
    }
}
