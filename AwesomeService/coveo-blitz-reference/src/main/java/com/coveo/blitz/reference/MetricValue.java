package com.coveo.blitz.reference;

public class MetricValue
{
    private String metric;
    private long value;

    public MetricValue(String metric, long value)
    {
        this.metric = metric.toUpperCase();
        this.value = value;
    }

    public String getMetric()
    {
        return metric;
    }

    public long getValue()
    {
        return value;
    }

    @Override public String toString()
    {
        return metric + ":" + value;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        MetricValue that = (MetricValue) o;

        if (value != that.value)
            return false;
        if (metric != that.metric)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = metric != null ? metric.hashCode() : 0;
        result = 31 * result + (int) (value ^ (value >>> 32));
        return result;
    }
}
