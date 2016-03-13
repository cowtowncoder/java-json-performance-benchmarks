package com.cowtowncoder.jsonperf.dzone;

import com.dslplatform.json.CompiledJson;

import java.util.List;

/**
 * Simple wrapper used to contain List of items to (de)serialize; used to avoid
 * need to  handle generic types like Lists for root values;
 */
@CompiledJson
public class MeasurementPOJO
{
    public List<MeasurementRecord> items;

    public MeasurementPOJO() { }
    public MeasurementPOJO(List<MeasurementRecord> i) { items = i; }

    public List<MeasurementRecord> getItems() { return items; }
    public void setItems(List<MeasurementRecord> l) { items = l; }
    
    // just used to return a non-trivial value after tests
    public int size() { return items.size(); }

    public String asJSON()
    {
        StringBuilder sb = new StringBuilder(items.size() * 20);
        try {
            sb.append("{\"items\":[\n");
            for (int i = 0, end = items.size(); i < end; ++i) {
                if (i > 0) {
                    sb.append(",");
                }
                if ((i % 3) == 0) { // just for fun separate lines, easier to debug if need be too
                    sb.append("\n");
                }
                items.get(i).appendAsJSON(sb);
            }
            sb.append("]}\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }
}
