//
// Autogenerated by Thrift Compiler (0.9.2)
//
// DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
//


OrderingType = {
  'ASCENDING' : 1,
  'DESCENDING' : 2
};
DataPoint = function(args) {
  this.dimensions = null;
  this.metrics = null;
  if (args) {
    if (args.dimensions !== undefined) {
      this.dimensions = args.dimensions;
    }
    if (args.metrics !== undefined) {
      this.metrics = args.metrics;
    }
  }
};
DataPoint.prototype = {};
DataPoint.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.MAP) {
        var _size0 = 0;
        var _rtmp34;
        this.dimensions = {};
        var _ktype1 = 0;
        var _vtype2 = 0;
        _rtmp34 = input.readMapBegin();
        _ktype1 = _rtmp34.ktype;
        _vtype2 = _rtmp34.vtype;
        _size0 = _rtmp34.size;
        for (var _i5 = 0; _i5 < _size0; ++_i5)
        {
          if (_i5 > 0 ) {
            if (input.rstack.length > input.rpos[input.rpos.length -1] + 1) {
              input.rstack.pop();
            }
          }
          var key6 = null;
          var val7 = null;
          key6 = input.readString().value;
          val7 = input.readString().value;
          this.dimensions[key6] = val7;
        }
        input.readMapEnd();
      } else {
        input.skip(ftype);
      }
      break;
      case 2:
      if (ftype == Thrift.Type.MAP) {
        var _size8 = 0;
        var _rtmp312;
        this.metrics = {};
        var _ktype9 = 0;
        var _vtype10 = 0;
        _rtmp312 = input.readMapBegin();
        _ktype9 = _rtmp312.ktype;
        _vtype10 = _rtmp312.vtype;
        _size8 = _rtmp312.size;
        for (var _i13 = 0; _i13 < _size8; ++_i13)
        {
          if (_i13 > 0 ) {
            if (input.rstack.length > input.rpos[input.rpos.length -1] + 1) {
              input.rstack.pop();
            }
          }
          var key14 = null;
          var val15 = null;
          key14 = input.readString().value;
          val15 = input.readI64().value;
          this.metrics[key14] = val15;
        }
        input.readMapEnd();
      } else {
        input.skip(ftype);
      }
      break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

DataPoint.prototype.write = function(output) {
  output.writeStructBegin('DataPoint');
  if (this.dimensions !== null && this.dimensions !== undefined) {
    output.writeFieldBegin('dimensions', Thrift.Type.MAP, 1);
    output.writeMapBegin(Thrift.Type.STRING, Thrift.Type.STRING, Thrift.objectLength(this.dimensions));
    for (var kiter16 in this.dimensions)
    {
      if (this.dimensions.hasOwnProperty(kiter16))
      {
        var viter17 = this.dimensions[kiter16];
        output.writeString(kiter16);
        output.writeString(viter17);
      }
    }
    output.writeMapEnd();
    output.writeFieldEnd();
  }
  if (this.metrics !== null && this.metrics !== undefined) {
    output.writeFieldBegin('metrics', Thrift.Type.MAP, 2);
    output.writeMapBegin(Thrift.Type.STRING, Thrift.Type.I64, Thrift.objectLength(this.metrics));
    for (var kiter18 in this.metrics)
    {
      if (this.metrics.hasOwnProperty(kiter18))
      {
        var viter19 = this.metrics[kiter18];
        output.writeString(kiter18);
        output.writeI64(viter19);
      }
    }
    output.writeMapEnd();
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

DimensionFilter = function(args) {
  this.dimension = null;
  this.value = null;
  if (args) {
    if (args.dimension !== undefined) {
      this.dimension = args.dimension;
    }
    if (args.value !== undefined) {
      this.value = args.value;
    }
  }
};
DimensionFilter.prototype = {};
DimensionFilter.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.STRING) {
        this.dimension = input.readString().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 2:
      if (ftype == Thrift.Type.STRING) {
        this.value = input.readString().value;
      } else {
        input.skip(ftype);
      }
      break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

DimensionFilter.prototype.write = function(output) {
  output.writeStructBegin('DimensionFilter');
  if (this.dimension !== null && this.dimension !== undefined) {
    output.writeFieldBegin('dimension', Thrift.Type.STRING, 1);
    output.writeString(this.dimension);
    output.writeFieldEnd();
  }
  if (this.value !== null && this.value !== undefined) {
    output.writeFieldBegin('value', Thrift.Type.STRING, 2);
    output.writeString(this.value);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

Response = function(args) {
  this.data = null;
  if (args) {
    if (args.data !== undefined) {
      this.data = args.data;
    }
  }
};
Response.prototype = {};
Response.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.LIST) {
        var _size20 = 0;
        var _rtmp324;
        this.data = [];
        var _etype23 = 0;
        _rtmp324 = input.readListBegin();
        _etype23 = _rtmp324.etype;
        _size20 = _rtmp324.size;
        for (var _i25 = 0; _i25 < _size20; ++_i25)
        {
          var elem26 = null;
          elem26 = new DataPoint();
          elem26.read(input);
          this.data.push(elem26);
        }
        input.readListEnd();
      } else {
        input.skip(ftype);
      }
      break;
      case 0:
        input.skip(ftype);
        break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

Response.prototype.write = function(output) {
  output.writeStructBegin('Response');
  if (this.data !== null && this.data !== undefined) {
    output.writeFieldBegin('data', Thrift.Type.LIST, 1);
    output.writeListBegin(Thrift.Type.STRUCT, this.data.length);
    for (var iter27 in this.data)
    {
      if (this.data.hasOwnProperty(iter27))
      {
        iter27 = this.data[iter27];
        iter27.write(output);
      }
    }
    output.writeListEnd();
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

Sort = function(args) {
  this.dimension = null;
  this.metric = null;
  this.ordering = null;
  if (args) {
    if (args.dimension !== undefined) {
      this.dimension = args.dimension;
    }
    if (args.metric !== undefined) {
      this.metric = args.metric;
    }
    if (args.ordering !== undefined) {
      this.ordering = args.ordering;
    }
  }
};
Sort.prototype = {};
Sort.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.STRING) {
        this.dimension = input.readString().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 2:
      if (ftype == Thrift.Type.STRING) {
        this.metric = input.readString().value;
      } else {
        input.skip(ftype);
      }
      break;
      case 3:
      if (ftype == Thrift.Type.I32) {
        this.ordering = input.readI32().value;
      } else {
        input.skip(ftype);
      }
      break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

Sort.prototype.write = function(output) {
  output.writeStructBegin('Sort');
  if (this.dimension !== null && this.dimension !== undefined) {
    output.writeFieldBegin('dimension', Thrift.Type.STRING, 1);
    output.writeString(this.dimension);
    output.writeFieldEnd();
  }
  if (this.metric !== null && this.metric !== undefined) {
    output.writeFieldBegin('metric', Thrift.Type.STRING, 2);
    output.writeString(this.metric);
    output.writeFieldEnd();
  }
  if (this.ordering !== null && this.ordering !== undefined) {
    output.writeFieldBegin('ordering', Thrift.Type.I32, 3);
    output.writeI32(this.ordering);
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

Request = function(args) {
  this.dimensions = null;
  this.metrics = null;
  this.filters = null;
  this.sorts = null;
  if (args) {
    if (args.dimensions !== undefined) {
      this.dimensions = args.dimensions;
    }
    if (args.metrics !== undefined) {
      this.metrics = args.metrics;
    }
    if (args.filters !== undefined) {
      this.filters = args.filters;
    }
    if (args.sorts !== undefined) {
      this.sorts = args.sorts;
    }
  }
};
Request.prototype = {};
Request.prototype.read = function(input) {
  input.readStructBegin();
  while (true)
  {
    var ret = input.readFieldBegin();
    var fname = ret.fname;
    var ftype = ret.ftype;
    var fid = ret.fid;
    if (ftype == Thrift.Type.STOP) {
      break;
    }
    switch (fid)
    {
      case 1:
      if (ftype == Thrift.Type.LIST) {
        var _size28 = 0;
        var _rtmp332;
        this.dimensions = [];
        var _etype31 = 0;
        _rtmp332 = input.readListBegin();
        _etype31 = _rtmp332.etype;
        _size28 = _rtmp332.size;
        for (var _i33 = 0; _i33 < _size28; ++_i33)
        {
          var elem34 = null;
          elem34 = input.readString().value;
          this.dimensions.push(elem34);
        }
        input.readListEnd();
      } else {
        input.skip(ftype);
      }
      break;
      case 2:
      if (ftype == Thrift.Type.LIST) {
        var _size35 = 0;
        var _rtmp339;
        this.metrics = [];
        var _etype38 = 0;
        _rtmp339 = input.readListBegin();
        _etype38 = _rtmp339.etype;
        _size35 = _rtmp339.size;
        for (var _i40 = 0; _i40 < _size35; ++_i40)
        {
          var elem41 = null;
          elem41 = input.readString().value;
          this.metrics.push(elem41);
        }
        input.readListEnd();
      } else {
        input.skip(ftype);
      }
      break;
      case 3:
      if (ftype == Thrift.Type.LIST) {
        var _size42 = 0;
        var _rtmp346;
        this.filters = [];
        var _etype45 = 0;
        _rtmp346 = input.readListBegin();
        _etype45 = _rtmp346.etype;
        _size42 = _rtmp346.size;
        for (var _i47 = 0; _i47 < _size42; ++_i47)
        {
          var elem48 = null;
          elem48 = new DimensionFilter();
          elem48.read(input);
          this.filters.push(elem48);
        }
        input.readListEnd();
      } else {
        input.skip(ftype);
      }
      break;
      case 4:
      if (ftype == Thrift.Type.LIST) {
        var _size49 = 0;
        var _rtmp353;
        this.sorts = [];
        var _etype52 = 0;
        _rtmp353 = input.readListBegin();
        _etype52 = _rtmp353.etype;
        _size49 = _rtmp353.size;
        for (var _i54 = 0; _i54 < _size49; ++_i54)
        {
          var elem55 = null;
          elem55 = new Sort();
          elem55.read(input);
          this.sorts.push(elem55);
        }
        input.readListEnd();
      } else {
        input.skip(ftype);
      }
      break;
      default:
        input.skip(ftype);
    }
    input.readFieldEnd();
  }
  input.readStructEnd();
  return;
};

Request.prototype.write = function(output) {
  output.writeStructBegin('Request');
  if (this.dimensions !== null && this.dimensions !== undefined) {
    output.writeFieldBegin('dimensions', Thrift.Type.LIST, 1);
    output.writeListBegin(Thrift.Type.STRING, this.dimensions.length);
    for (var iter56 in this.dimensions)
    {
      if (this.dimensions.hasOwnProperty(iter56))
      {
        iter56 = this.dimensions[iter56];
        output.writeString(iter56);
      }
    }
    output.writeListEnd();
    output.writeFieldEnd();
  }
  if (this.metrics !== null && this.metrics !== undefined) {
    output.writeFieldBegin('metrics', Thrift.Type.LIST, 2);
    output.writeListBegin(Thrift.Type.STRING, this.metrics.length);
    for (var iter57 in this.metrics)
    {
      if (this.metrics.hasOwnProperty(iter57))
      {
        iter57 = this.metrics[iter57];
        output.writeString(iter57);
      }
    }
    output.writeListEnd();
    output.writeFieldEnd();
  }
  if (this.filters !== null && this.filters !== undefined) {
    output.writeFieldBegin('filters', Thrift.Type.LIST, 3);
    output.writeListBegin(Thrift.Type.STRUCT, this.filters.length);
    for (var iter58 in this.filters)
    {
      if (this.filters.hasOwnProperty(iter58))
      {
        iter58 = this.filters[iter58];
        iter58.write(output);
      }
    }
    output.writeListEnd();
    output.writeFieldEnd();
  }
  if (this.sorts !== null && this.sorts !== undefined) {
    output.writeFieldBegin('sorts', Thrift.Type.LIST, 4);
    output.writeListBegin(Thrift.Type.STRUCT, this.sorts.length);
    for (var iter59 in this.sorts)
    {
      if (this.sorts.hasOwnProperty(iter59))
      {
        iter59 = this.sorts[iter59];
        iter59.write(output);
      }
    }
    output.writeListEnd();
    output.writeFieldEnd();
  }
  output.writeFieldStop();
  output.writeStructEnd();
  return;
};

