package com.cowtowncoder.jsonperf.dzone;

import java.io.InputStream;
import java.util.Map;

public abstract class DZoneMapReadTestBase
    extends DZoneReadTestBase<Map<?,?>>
{
    @Override
    public Map<?,?> _readItems(byte[] input) throws Exception {
        Map<?,?> result = _readMap(input);
        // do minimal (performance-wise) access that avoids lazy loading/map-creation
        // (although not necessarily recursively)
        result.size();
        return result;
    }

    @Override
    public Map<?,?> _readItems(InputStream input) throws Exception {
        Map<?,?> result = _readMap(input);
        // do minimal (performance-wise) access that avoids lazy loading/map-creation
        // (although not necessarily recursively)
        result.size();
        return result;
    }

    @Override
    public Map<?,?> _readItems(String input) throws Exception {
        Map<?,?> result = _readMap(input);
        // do minimal (performance-wise) access that avoids lazy loading/map-creation
        // (although not necessarily recursively)
        result.size();
        return result;
    }

    public abstract Map<?,?> _readMap(byte[] input) throws Exception;

    public abstract Map<?,?> _readMap(InputStream input) throws Exception;

    public abstract Map<?,?> _readMap(String input) throws Exception;
}
