/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package OpenSourceAPM.esper;

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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UIEvent implements org.apache.thrift.TBase<UIEvent, UIEvent._Fields>, java.io.Serializable, Cloneable, Comparable<UIEvent> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("UIEvent");

  private static final org.apache.thrift.protocol.TField STAGE_FIELD_DESC = new org.apache.thrift.protocol.TField("stage", org.apache.thrift.protocol.TType.STRING, (short)1);
  private static final org.apache.thrift.protocol.TField CLIENT_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("clientId", org.apache.thrift.protocol.TType.STRING, (short)3);
  private static final org.apache.thrift.protocol.TField CURRENT_PLACE_FIELD_DESC = new org.apache.thrift.protocol.TField("currentPlace", org.apache.thrift.protocol.TType.STRING, (short)4);
  private static final org.apache.thrift.protocol.TField UUID_FIELD_DESC = new org.apache.thrift.protocol.TField("uuid", org.apache.thrift.protocol.TType.STRING, (short)5);
  private static final org.apache.thrift.protocol.TField URL_FIELD_DESC = new org.apache.thrift.protocol.TField("url", org.apache.thrift.protocol.TType.STRING, (short)7);
  private static final org.apache.thrift.protocol.TField TIMESTAMP_FIELD_DESC = new org.apache.thrift.protocol.TField("timestamp", org.apache.thrift.protocol.TType.I64, (short)9);
  private static final org.apache.thrift.protocol.TField ELAPSED_TIME_FIELD_DESC = new org.apache.thrift.protocol.TField("elapsedTime", org.apache.thrift.protocol.TType.I64, (short)11);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new UIEventStandardSchemeFactory());
    schemes.put(TupleScheme.class, new UIEventTupleSchemeFactory());
  }

  public String stage; // required
  public String clientId; // required
  public String currentPlace; // optional
  public String uuid; // required
  public String url; // required
  public long timestamp; // required
  public long elapsedTime; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    STAGE((short)1, "stage"),
    CLIENT_ID((short)3, "clientId"),
    CURRENT_PLACE((short)4, "currentPlace"),
    UUID((short)5, "uuid"),
    URL((short)7, "url"),
    TIMESTAMP((short)9, "timestamp"),
    ELAPSED_TIME((short)11, "elapsedTime");

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
        case 1: // STAGE
          return STAGE;
        case 3: // CLIENT_ID
          return CLIENT_ID;
        case 4: // CURRENT_PLACE
          return CURRENT_PLACE;
        case 5: // UUID
          return UUID;
        case 7: // URL
          return URL;
        case 9: // TIMESTAMP
          return TIMESTAMP;
        case 11: // ELAPSED_TIME
          return ELAPSED_TIME;
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
  private static final int __TIMESTAMP_ISSET_ID = 0;
  private static final int __ELAPSEDTIME_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.CURRENT_PLACE,_Fields.ELAPSED_TIME};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.STAGE, new org.apache.thrift.meta_data.FieldMetaData("stage", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CLIENT_ID, new org.apache.thrift.meta_data.FieldMetaData("clientId", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.CURRENT_PLACE, new org.apache.thrift.meta_data.FieldMetaData("currentPlace", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.UUID, new org.apache.thrift.meta_data.FieldMetaData("uuid", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.URL, new org.apache.thrift.meta_data.FieldMetaData("url", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.STRING)));
    tmpMap.put(_Fields.TIMESTAMP, new org.apache.thrift.meta_data.FieldMetaData("timestamp", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.ELAPSED_TIME, new org.apache.thrift.meta_data.FieldMetaData("elapsedTime", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(UIEvent.class, metaDataMap);
  }

  public UIEvent() {
  }

  public UIEvent(
    String stage,
    String clientId,
    String uuid,
    String url,
    long timestamp)
  {
    this();
    this.stage = stage;
    this.clientId = clientId;
    this.uuid = uuid;
    this.url = url;
    this.timestamp = timestamp;
    setTimestampIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public UIEvent(UIEvent other) {
    __isset_bitfield = other.__isset_bitfield;
    if (other.isSetStage()) {
      this.stage = other.stage;
    }
    if (other.isSetClientId()) {
      this.clientId = other.clientId;
    }
    if (other.isSetCurrentPlace()) {
      this.currentPlace = other.currentPlace;
    }
    if (other.isSetUuid()) {
      this.uuid = other.uuid;
    }
    if (other.isSetUrl()) {
      this.url = other.url;
    }
    this.timestamp = other.timestamp;
    this.elapsedTime = other.elapsedTime;
  }

  public UIEvent deepCopy() {
    return new UIEvent(this);
  }

  @Override
  public void clear() {
    this.stage = null;
    this.clientId = null;
    this.currentPlace = null;
    this.uuid = null;
    this.url = null;
    setTimestampIsSet(false);
    this.timestamp = 0;
    setElapsedTimeIsSet(false);
    this.elapsedTime = 0;
  }

  public String getStage() {
    return this.stage;
  }

  public UIEvent setStage(String stage) {
    this.stage = stage;
    return this;
  }

  public void unsetStage() {
    this.stage = null;
  }

  /** Returns true if field stage is set (has been assigned a value) and false otherwise */
  public boolean isSetStage() {
    return this.stage != null;
  }

  public void setStageIsSet(boolean value) {
    if (!value) {
      this.stage = null;
    }
  }

  public String getClientId() {
    return this.clientId;
  }

  public UIEvent setClientId(String clientId) {
    this.clientId = clientId;
    return this;
  }

  public void unsetClientId() {
    this.clientId = null;
  }

  /** Returns true if field clientId is set (has been assigned a value) and false otherwise */
  public boolean isSetClientId() {
    return this.clientId != null;
  }

  public void setClientIdIsSet(boolean value) {
    if (!value) {
      this.clientId = null;
    }
  }

  public String getCurrentPlace() {
    return this.currentPlace;
  }

  public UIEvent setCurrentPlace(String currentPlace) {
    this.currentPlace = currentPlace;
    return this;
  }

  public void unsetCurrentPlace() {
    this.currentPlace = null;
  }

  /** Returns true if field currentPlace is set (has been assigned a value) and false otherwise */
  public boolean isSetCurrentPlace() {
    return this.currentPlace != null;
  }

  public void setCurrentPlaceIsSet(boolean value) {
    if (!value) {
      this.currentPlace = null;
    }
  }

  public String getUuid() {
    return this.uuid;
  }

  public UIEvent setUuid(String uuid) {
    this.uuid = uuid;
    return this;
  }

  public void unsetUuid() {
    this.uuid = null;
  }

  /** Returns true if field uuid is set (has been assigned a value) and false otherwise */
  public boolean isSetUuid() {
    return this.uuid != null;
  }

  public void setUuidIsSet(boolean value) {
    if (!value) {
      this.uuid = null;
    }
  }

  public String getUrl() {
    return this.url;
  }

  public UIEvent setUrl(String url) {
    this.url = url;
    return this;
  }

  public void unsetUrl() {
    this.url = null;
  }

  /** Returns true if field url is set (has been assigned a value) and false otherwise */
  public boolean isSetUrl() {
    return this.url != null;
  }

  public void setUrlIsSet(boolean value) {
    if (!value) {
      this.url = null;
    }
  }

  public long getTimestamp() {
    return this.timestamp;
  }

  public UIEvent setTimestamp(long timestamp) {
    this.timestamp = timestamp;
    setTimestampIsSet(true);
    return this;
  }

  public void unsetTimestamp() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TIMESTAMP_ISSET_ID);
  }

  /** Returns true if field timestamp is set (has been assigned a value) and false otherwise */
  public boolean isSetTimestamp() {
    return EncodingUtils.testBit(__isset_bitfield, __TIMESTAMP_ISSET_ID);
  }

  public void setTimestampIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TIMESTAMP_ISSET_ID, value);
  }

  public long getElapsedTime() {
    return this.elapsedTime;
  }

  public UIEvent setElapsedTime(long elapsedTime) {
    this.elapsedTime = elapsedTime;
    setElapsedTimeIsSet(true);
    return this;
  }

  public void unsetElapsedTime() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ELAPSEDTIME_ISSET_ID);
  }

  /** Returns true if field elapsedTime is set (has been assigned a value) and false otherwise */
  public boolean isSetElapsedTime() {
    return EncodingUtils.testBit(__isset_bitfield, __ELAPSEDTIME_ISSET_ID);
  }

  public void setElapsedTimeIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ELAPSEDTIME_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case STAGE:
      if (value == null) {
        unsetStage();
      } else {
        setStage((String)value);
      }
      break;

    case CLIENT_ID:
      if (value == null) {
        unsetClientId();
      } else {
        setClientId((String)value);
      }
      break;

    case CURRENT_PLACE:
      if (value == null) {
        unsetCurrentPlace();
      } else {
        setCurrentPlace((String)value);
      }
      break;

    case UUID:
      if (value == null) {
        unsetUuid();
      } else {
        setUuid((String)value);
      }
      break;

    case URL:
      if (value == null) {
        unsetUrl();
      } else {
        setUrl((String)value);
      }
      break;

    case TIMESTAMP:
      if (value == null) {
        unsetTimestamp();
      } else {
        setTimestamp((Long)value);
      }
      break;

    case ELAPSED_TIME:
      if (value == null) {
        unsetElapsedTime();
      } else {
        setElapsedTime((Long)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case STAGE:
      return getStage();

    case CLIENT_ID:
      return getClientId();

    case CURRENT_PLACE:
      return getCurrentPlace();

    case UUID:
      return getUuid();

    case URL:
      return getUrl();

    case TIMESTAMP:
      return Long.valueOf(getTimestamp());

    case ELAPSED_TIME:
      return Long.valueOf(getElapsedTime());

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case STAGE:
      return isSetStage();
    case CLIENT_ID:
      return isSetClientId();
    case CURRENT_PLACE:
      return isSetCurrentPlace();
    case UUID:
      return isSetUuid();
    case URL:
      return isSetUrl();
    case TIMESTAMP:
      return isSetTimestamp();
    case ELAPSED_TIME:
      return isSetElapsedTime();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof UIEvent)
      return this.equals((UIEvent)that);
    return false;
  }

  public boolean equals(UIEvent that) {
    if (that == null)
      return false;

    boolean this_present_stage = true && this.isSetStage();
    boolean that_present_stage = true && that.isSetStage();
    if (this_present_stage || that_present_stage) {
      if (!(this_present_stage && that_present_stage))
        return false;
      if (!this.stage.equals(that.stage))
        return false;
    }

    boolean this_present_clientId = true && this.isSetClientId();
    boolean that_present_clientId = true && that.isSetClientId();
    if (this_present_clientId || that_present_clientId) {
      if (!(this_present_clientId && that_present_clientId))
        return false;
      if (!this.clientId.equals(that.clientId))
        return false;
    }

    boolean this_present_currentPlace = true && this.isSetCurrentPlace();
    boolean that_present_currentPlace = true && that.isSetCurrentPlace();
    if (this_present_currentPlace || that_present_currentPlace) {
      if (!(this_present_currentPlace && that_present_currentPlace))
        return false;
      if (!this.currentPlace.equals(that.currentPlace))
        return false;
    }

    boolean this_present_uuid = true && this.isSetUuid();
    boolean that_present_uuid = true && that.isSetUuid();
    if (this_present_uuid || that_present_uuid) {
      if (!(this_present_uuid && that_present_uuid))
        return false;
      if (!this.uuid.equals(that.uuid))
        return false;
    }

    boolean this_present_url = true && this.isSetUrl();
    boolean that_present_url = true && that.isSetUrl();
    if (this_present_url || that_present_url) {
      if (!(this_present_url && that_present_url))
        return false;
      if (!this.url.equals(that.url))
        return false;
    }

    boolean this_present_timestamp = true;
    boolean that_present_timestamp = true;
    if (this_present_timestamp || that_present_timestamp) {
      if (!(this_present_timestamp && that_present_timestamp))
        return false;
      if (this.timestamp != that.timestamp)
        return false;
    }

    boolean this_present_elapsedTime = true && this.isSetElapsedTime();
    boolean that_present_elapsedTime = true && that.isSetElapsedTime();
    if (this_present_elapsedTime || that_present_elapsedTime) {
      if (!(this_present_elapsedTime && that_present_elapsedTime))
        return false;
      if (this.elapsedTime != that.elapsedTime)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(UIEvent other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetStage()).compareTo(other.isSetStage());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetStage()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.stage, other.stage);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetClientId()).compareTo(other.isSetClientId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetClientId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.clientId, other.clientId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetCurrentPlace()).compareTo(other.isSetCurrentPlace());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetCurrentPlace()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.currentPlace, other.currentPlace);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUuid()).compareTo(other.isSetUuid());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUuid()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.uuid, other.uuid);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetUrl()).compareTo(other.isSetUrl());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUrl()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.url, other.url);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetTimestamp()).compareTo(other.isSetTimestamp());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetTimestamp()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.timestamp, other.timestamp);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetElapsedTime()).compareTo(other.isSetElapsedTime());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetElapsedTime()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.elapsedTime, other.elapsedTime);
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
    StringBuilder sb = new StringBuilder("UIEvent(");
    boolean first = true;

    sb.append("stage:");
    if (this.stage == null) {
      sb.append("null");
    } else {
      sb.append(this.stage);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("clientId:");
    if (this.clientId == null) {
      sb.append("null");
    } else {
      sb.append(this.clientId);
    }
    first = false;
    if (isSetCurrentPlace()) {
      if (!first) sb.append(", ");
      sb.append("currentPlace:");
      if (this.currentPlace == null) {
        sb.append("null");
      } else {
        sb.append(this.currentPlace);
      }
      first = false;
    }
    if (!first) sb.append(", ");
    sb.append("uuid:");
    if (this.uuid == null) {
      sb.append("null");
    } else {
      sb.append(this.uuid);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("url:");
    if (this.url == null) {
      sb.append("null");
    } else {
      sb.append(this.url);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("timestamp:");
    sb.append(this.timestamp);
    first = false;
    if (isSetElapsedTime()) {
      if (!first) sb.append(", ");
      sb.append("elapsedTime:");
      sb.append(this.elapsedTime);
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    if (stage == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'stage' was not present! Struct: " + toString());
    }
    if (clientId == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'clientId' was not present! Struct: " + toString());
    }
    if (uuid == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'uuid' was not present! Struct: " + toString());
    }
    if (url == null) {
      throw new org.apache.thrift.protocol.TProtocolException("Required field 'url' was not present! Struct: " + toString());
    }
    // alas, we cannot check 'timestamp' because it's a primitive and you chose the non-beans generator.
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
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class UIEventStandardSchemeFactory implements SchemeFactory {
    public UIEventStandardScheme getScheme() {
      return new UIEventStandardScheme();
    }
  }

  private static class UIEventStandardScheme extends StandardScheme<UIEvent> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, UIEvent struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // STAGE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.stage = iprot.readString();
              struct.setStageIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // CLIENT_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.clientId = iprot.readString();
              struct.setClientIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // CURRENT_PLACE
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.currentPlace = iprot.readString();
              struct.setCurrentPlaceIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 5: // UUID
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.uuid = iprot.readString();
              struct.setUuidIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 7: // URL
            if (schemeField.type == org.apache.thrift.protocol.TType.STRING) {
              struct.url = iprot.readString();
              struct.setUrlIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 9: // TIMESTAMP
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.timestamp = iprot.readI64();
              struct.setTimestampIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 11: // ELAPSED_TIME
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.elapsedTime = iprot.readI64();
              struct.setElapsedTimeIsSet(true);
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
      if (!struct.isSetTimestamp()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'timestamp' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, UIEvent struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      if (struct.stage != null) {
        oprot.writeFieldBegin(STAGE_FIELD_DESC);
        oprot.writeString(struct.stage);
        oprot.writeFieldEnd();
      }
      if (struct.clientId != null) {
        oprot.writeFieldBegin(CLIENT_ID_FIELD_DESC);
        oprot.writeString(struct.clientId);
        oprot.writeFieldEnd();
      }
      if (struct.currentPlace != null) {
        if (struct.isSetCurrentPlace()) {
          oprot.writeFieldBegin(CURRENT_PLACE_FIELD_DESC);
          oprot.writeString(struct.currentPlace);
          oprot.writeFieldEnd();
        }
      }
      if (struct.uuid != null) {
        oprot.writeFieldBegin(UUID_FIELD_DESC);
        oprot.writeString(struct.uuid);
        oprot.writeFieldEnd();
      }
      if (struct.url != null) {
        oprot.writeFieldBegin(URL_FIELD_DESC);
        oprot.writeString(struct.url);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldBegin(TIMESTAMP_FIELD_DESC);
      oprot.writeI64(struct.timestamp);
      oprot.writeFieldEnd();
      if (struct.isSetElapsedTime()) {
        oprot.writeFieldBegin(ELAPSED_TIME_FIELD_DESC);
        oprot.writeI64(struct.elapsedTime);
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class UIEventTupleSchemeFactory implements SchemeFactory {
    public UIEventTupleScheme getScheme() {
      return new UIEventTupleScheme();
    }
  }

  private static class UIEventTupleScheme extends TupleScheme<UIEvent> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, UIEvent struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeString(struct.stage);
      oprot.writeString(struct.clientId);
      oprot.writeString(struct.uuid);
      oprot.writeString(struct.url);
      oprot.writeI64(struct.timestamp);
      BitSet optionals = new BitSet();
      if (struct.isSetCurrentPlace()) {
        optionals.set(0);
      }
      if (struct.isSetElapsedTime()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetCurrentPlace()) {
        oprot.writeString(struct.currentPlace);
      }
      if (struct.isSetElapsedTime()) {
        oprot.writeI64(struct.elapsedTime);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, UIEvent struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.stage = iprot.readString();
      struct.setStageIsSet(true);
      struct.clientId = iprot.readString();
      struct.setClientIdIsSet(true);
      struct.uuid = iprot.readString();
      struct.setUuidIsSet(true);
      struct.url = iprot.readString();
      struct.setUrlIsSet(true);
      struct.timestamp = iprot.readI64();
      struct.setTimestampIsSet(true);
      BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.currentPlace = iprot.readString();
        struct.setCurrentPlaceIsSet(true);
      }
      if (incoming.get(1)) {
        struct.elapsedTime = iprot.readI64();
        struct.setElapsedTimeIsSet(true);
      }
    }
  }

}

