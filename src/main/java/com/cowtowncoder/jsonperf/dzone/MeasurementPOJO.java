package com.cowtowncoder.jsonperf.dzone;

import java.util.List;

/**
 * Simple wrapper used to contain List of items to (de)serialize; used to avoid
 * need to  handle generic types like Lists for root values;
 */
public class MeasurementPOJO
{
    public List<MeasurementRecord> items;

    public MeasurementPOJO() { }
    public MeasurementPOJO(List<MeasurementRecord> i) { items = i; }

    public List<MeasurementRecord> getItems() { return items; }
    public void setItems(List<MeasurementRecord> l) { items = l; }
    
    // just used to return a non-trivial value after tests
    public int size() { return items.size(); }
}
