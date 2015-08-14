package com.cowtowncoder.jsonperf.dzone;

import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Main test item. Contains both fields and getters/setters, given that different
 * libraries access data in different ways: some require fields (Gson at least),
 * others getters/setters (Jackson jr); others work with either (Jackson)
 */
@JsonPropertyOrder({ "measurementId", "type", "duration", "time" })
public class MeasurementRecord
{
    public String measurementId;
    public MeasurementType type;

    public long duration;
    public long time;

    // for deser
    protected MeasurementRecord() { }
    
    public MeasurementRecord(String measurementId, MeasurementType type,
            long duration, long time)
    {
        this.measurementId = measurementId;
        this.type = type;
        this.duration = duration;
        this.time = time;
    }

    public String getMeasurementId() { return measurementId; }
    public MeasurementType getType() { return type; }
    public long getDuration() { return duration; }
    public long getTime() { return time; }

    public void setMeasurementId(String s) { measurementId = s; }
    public void setType(MeasurementType t) { type = t; }
    public void setDuration(long l) { duration = l; }
    public void setTime(long l) { time = l; }

    public static List<MeasurementRecord> construct(int count)
    {
        List<MeasurementRecord> result = new ArrayList<>(count);
        long now = System.currentTimeMillis();
        for (int i = 0; i < count; ++i) {
            result.add(new MeasurementRecord("/test.html", MeasurementType.WEB_REQUEST,
                    10, now+i));
        }
        return result;
    }

    // quick and dirty; needed just to avoid having to use any of impls being
    // tested
    public void appendAsJSON(Appendable target) throws IOException
    {
        target.append(String.format("{\"measurementId\":\"%s\","
                +"\"type\":\"%s\","
                +"\"duration\":%d,"
                +"\"time\":%d }",
                measurementId, type.name(), duration, time
                ));
    }
}
