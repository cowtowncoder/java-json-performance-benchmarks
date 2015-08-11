package com.cowtowncoder.jsonperf.dzone;

public class TestData
{
    protected final static MeasurementPOJO list10 = new MeasurementPOJO(MeasurementRecord.construct(10));
    protected final static MeasurementPOJO list1000 = new MeasurementPOJO(MeasurementRecord.construct(1000));
    protected final static MeasurementPOJO list100000 = new MeasurementPOJO(MeasurementRecord.construct(100000));

    public static class Input {
        protected final static String list10String;
        protected final static String list1000String;
        protected final static String list100000String;

        protected final static byte[] list10Bytes;
        protected final static byte[] list1000Bytes;
        protected final static byte[] list100000Bytes;

        // Bit problematic no matter how we choose; but let's use Gson here
        // to try to reduc
        static {
            list10String = list10.asJSON();
            list1000String = list1000.asJSON();
            list100000String = list100000.asJSON();

            try {
                list10Bytes = list10String.getBytes("UTF-8");
                list1000Bytes = list1000String.getBytes("UTF-8");
                list100000Bytes = list100000String.getBytes("UTF-8");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
