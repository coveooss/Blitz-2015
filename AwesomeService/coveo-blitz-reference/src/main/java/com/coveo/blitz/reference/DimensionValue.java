package com.coveo.blitz.reference;

public class DimensionValue
{
    private String dimension;
    private String value;

    public DimensionValue(String dimension, String value)
    {
        this.dimension = dimension.toUpperCase();
        this.value = value;
    }

    public String getDimension()
    {
        return dimension;
    }

    public String getValue()
    {
        return value;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DimensionValue that = (DimensionValue) o;

        if (dimension != that.dimension) {
            return false;
        }
        if (value != null ? !value.equals(that.value) : that.value != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = dimension != null ? dimension.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override public String toString()
    {
        return dimension + ":" + value;
    }
}
