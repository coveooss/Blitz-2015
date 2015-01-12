/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.coveo.blitz.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Generated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked"})
@Generated(value = "Autogenerated by Thrift Compiler (0.9.2)", date = "2015-1-9")
public class DataPoint implements org.apache.thrift.TBase<DataPoint, DataPoint._Fields>, java.io.Serializable, Cloneable, Comparable<DataPoint> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("DataPoint");

  private static final org.apache.thrift.protocol.TField DIMENSIONS_FIELD_DESC = new org.apache.thrift.protocol.TField("dimensions", org.apache.thrift.protocol.TType.MAP, (short)1);
  private static final org.apache.thrift.protocol.TField METRICS_FIELD_DESC = new org.apache.thrift.protocol.TField("metrics", org.apache.thrift.protocol.TType.MAP, (short)2);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new DataPointStandardSchemeFactory());
    schemes.put(TupleScheme.class, new DataPointTupleSchemeFactory());
  }

  public Map<String,String> dimensions; // required
  public Map<String,Long> metrics; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    DIMENSIONS((short)1, "dimensions"),
    METRICS((short)2, "metrics");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // DIMENSIONS
          return DIMENSIONS;
        case 2: // METRICS
          return METRICS;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.DIMENSIONS, new org.apache.thrift.meta_data.FieldMetaData("dimensions", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING))));
    tmpMap.put(_Fields.METRICS, new org.apache.thrift.meta_data.FieldMetaData("metrics", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.MapMetaData(org.apache.thrift.protocol.TType.MAP, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING), 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(DataPoint.class, metaDataMap);
  }

  public DataPoint() {
  }

  public DataPoint(
    Map<String,String> dimensions,
    Map<String,Long> metrics)
  {
    this();
    this.dimensions = dimensions;
    this.metrics = metrics;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public DataPoint(DataPoint other) {
    if (other.isSetDimensions()) {
      Map<String,String> __this__dimensions = new HashMap<String,String>(other.dimensions);
      this.dimensions = __this__dimensions;
    }
    if (other.isSetMetrics()) {
      Map<String,Long> __this__metrics = new HashMap<String,Long>(other.metrics);
      this.metrics = __this__metrics;
    }
  }

  public DataPoint deepCopy() {
    return new DataPoint(this);
  }

  @Override
  public void clear() {
    this.dimensions = null;
    this.metrics = null;
  }

  public int getDimensionsSize() {
    return (this.dimensions == null) ? 0 : this.dimensions.size();
  }

  public void putToDimensions(String key, String val) {
    if (this.dimensions == null) {
      this.dimensions = new HashMap<String,String>();
    }
    this.dimensions.put(key, val);
  }

  public Map<String,String> getDimensions() {
    return this.dimensions;
  }

  public DataPoint setDimensions(Map<String,String> dimensions) {
    this.dimensions = dimensions;
    return this;
  }

  public void unsetDimensions() {
    this.dimensions = null;
  }

  /** Returns true if field dimensions is set (has been assigned a value) and false otherwise */
  public boolean isSetDimensions() {
    return this.dimensions != null;
  }

  public void setDimensionsIsSet(boolean value) {
    if (!value) {
      this.dimensions = null;
    }
  }

  public int getMetricsSize() {
    return (this.metrics == null) ? 0 : this.metrics.size();
  }

  public void putToMetrics(String key, long val) {
    if (this.metrics == null) {
      this.metrics = new HashMap<String,Long>();
    }
    this.metrics.put(key, val);
  }

  public Map<String,Long> getMetrics() {
    return this.metrics;
  }

  public DataPoint setMetrics(Map<String,Long> metrics) {
    this.metrics = metrics;
    return this;
  }

  public void unsetMetrics() {
    this.metrics = null;
  }

  /** Returns true if field metrics is set (has been assigned a value) and false otherwise */
  public boolean isSetMetrics() {
    return this.metrics != null;
  }

  public void setMetricsIsSet(boolean value) {
    if (!value) {
      this.metrics = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case DIMENSIONS:
      if (value == null) {
        unsetDimensions();
      } else {
        setDimensions((Map<String,String>)value);
      }
      break;

    case METRICS:
      if (value == null) {
        unsetMetrics();
      } else {
        setMetrics((Map<String,Long>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case DIMENSIONS:
      return getDimensions();

    case METRICS:
      return getMetrics();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case DIMENSIONS:
      return isSetDimensions();
    case METRICS:
      return isSetMetrics();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof DataPoint)
      return this.equals((DataPoint)that);
    return false;
  }

  public boolean equals(DataPoint that) {
    if (that == null)
      return false;

    boolean this_present_dimensions = true && this.isSetDimensions();
    boolean that_present_dimensions = true && that.isSetDimensions();
    if (this_present_dimensions || that_present_dimensions) {
      if (!(this_present_dimensions && that_present_dimensions))
        return false;
      if (!this.dimensions.equals(that.dimensions))
        return false;
    }

    boolean this_present_metrics = true && this.isSetMetrics();
    boolean that_present_metrics = true && that.isSetMetrics();
    if (this_present_metrics || that_present_metrics) {
      if (!(this_present_metrics && that_present_metrics))
        return false;
      if (!this.metrics.equals(that.metrics))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    List<Object> list = new ArrayList<Object>();

    boolean present_dimensions = true && (isSetDimensions());
    list.add(present_dimensions);
    if (present_dimensions)
      list.add(dimensions);

    boolean present_metrics = true && (isSetMetrics());
    list.add(present_metrics);
    if (present_metrics)
      list.add(metrics);

    return list.hashCode();
  }

  @Override
  public int compareTo(DataPoint other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetDimensions()).compareTo(other.isSetDimensions());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetDimensions()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.dimensions, other.dimensions);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetMetrics()).compareTo(other.isSetMetrics());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetMetrics()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.metrics, other.metrics);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("DataPoint(");
    boolean first = true;

    sb.append("dimensions:");
    if (this.dimensions == null) {
      sb.append("null");
    } else {
      sb.append(this.dimensions);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("metrics:");
    if (this.metrics == null) {
      sb.append("null");
    } else {
      sb.append(this.metrics);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class DataPointStandardSchemeFactory implements SchemeFactory {
    public DataPointStandardScheme getScheme() {
      return new DataPointStandardScheme();
    }
  }

  private static class DataPointStandardScheme extends StandardScheme<DataPoint> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, DataPoint struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // DIMENSIONS
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map0 = iprot.readMapBegin();
                struct.dimensions = new HashMap<String,String>(2*_map0.size);
                String _key1;
                String _val2;
                for (int _i3 = 0; _i3 < _map0.size; ++_i3)
                {
                  _key1 = iprot.readString();
                  _val2 = iprot.readString();
                  struct.dimensions.put(_key1, _val2);
                }
                iprot.readMapEnd();
              }
              struct.setDimensionsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // METRICS
            if (schemeField.type == org.apache.thrift.protocol.TType.MAP) {
              {
                org.apache.thrift.protocol.TMap _map4 = iprot.readMapBegin();
                struct.metrics = new HashMap<String,Long>(2*_map4.size);
                String _key5;
                long _val6;
                for (int _i7 = 0; _i7 < _map4.size; ++_i7)
                {
                  _key5 = iprot.readString();
                  _val6 = iprot.readI64();
                  struct.metrics.put(_key5, _val6);
                }
                iprot.readMapEnd();
              }
              struct.setMetricsIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, DataPoint struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.dimensions != null) {
        oprot.writeFieldBegin(DIMENSIONS_FIELD_DESC);
        {
          oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, struct.dimensions.size()));
          for (Map.Entry<String, String> _iter8 : struct.dimensions.entrySet())
          {
            oprot.writeString(_iter8.getKey());
            oprot.writeString(_iter8.getValue());
          }
          oprot.writeMapEnd();
        }
        oprot.writeFieldEnd();
      }
      if (struct.metrics != null) {
        oprot.writeFieldBegin(METRICS_FIELD_DESC);
        {
          oprot.writeMapBegin(new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.I64, struct.metrics.size()));
          for (Map.Entry<String, Long> _iter9 : struct.metrics.entrySet())
          {
            oprot.writeString(_iter9.getKey());
            oprot.writeI64(_iter9.getValue());
          }
          oprot.writeMapEnd();
        }
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class DataPointTupleSchemeFactory implements SchemeFactory {
    public DataPointTupleScheme getScheme() {
      return new DataPointTupleScheme();
    }
  }

  private static class DataPointTupleScheme extends TupleScheme<DataPoint> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, DataPoint struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetDimensions()) {
        optionals.set(0);
      }
      if (struct.isSetMetrics()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetDimensions()) {
        {
          oprot.writeI32(struct.dimensions.size());
          for (Map.Entry<String, String> _iter10 : struct.dimensions.entrySet())
          {
            oprot.writeString(_iter10.getKey());
            oprot.writeString(_iter10.getValue());
          }
        }
      }
      if (struct.isSetMetrics()) {
        {
          oprot.writeI32(struct.metrics.size());
          for (Map.Entry<String, Long> _iter11 : struct.metrics.entrySet())
          {
            oprot.writeString(_iter11.getKey());
            oprot.writeI64(_iter11.getValue());
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, DataPoint struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TMap _map12 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.STRING, iprot.readI32());
          struct.dimensions = new HashMap<String,String>(2*_map12.size);
          String _key13;
          String _val14;
          for (int _i15 = 0; _i15 < _map12.size; ++_i15)
          {
            _key13 = iprot.readString();
            _val14 = iprot.readString();
            struct.dimensions.put(_key13, _val14);
          }
        }
        struct.setDimensionsIsSet(true);
      }
      if (incoming.get(1)) {
        {
          org.apache.thrift.protocol.TMap _map16 = new org.apache.thrift.protocol.TMap(org.apache.thrift.protocol.TType.STRING, org.apache.thrift.protocol.TType.I64, iprot.readI32());
          struct.metrics = new HashMap<String,Long>(2*_map16.size);
          String _key17;
          long _val18;
          for (int _i19 = 0; _i19 < _map16.size; ++_i19)
          {
            _key17 = iprot.readString();
            _val18 = iprot.readI64();
            struct.metrics.put(_key17, _val18);
          }
        }
        struct.setMetricsIsSet(true);
      }
    }
  }

}

