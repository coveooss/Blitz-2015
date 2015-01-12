/**
 * Autogenerated by Thrift Compiler (0.9.2)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
#ifndef AwesomeService_H
#define AwesomeService_H

#include <thrift/TDispatchProcessor.h>
#include "awesome_types.h"



class AwesomeServiceIf {
 public:
  virtual ~AwesomeServiceIf() {}

  /**
   * Gets data from your service. The type and format of the requests are defined in the documentation.
   * 
   * 
   * @param request
   */
  virtual void getData(Response& _return, const Request& request) = 0;
  virtual void reset() = 0;
  virtual bool ping() = 0;
  virtual void handleMapReduceResult(const std::string& name, const std::string& data) = 0;
};

class AwesomeServiceIfFactory {
 public:
  typedef AwesomeServiceIf Handler;

  virtual ~AwesomeServiceIfFactory() {}

  virtual AwesomeServiceIf* getHandler(const ::apache::thrift::TConnectionInfo& connInfo) = 0;
  virtual void releaseHandler(AwesomeServiceIf* /* handler */) = 0;
};

class AwesomeServiceIfSingletonFactory : virtual public AwesomeServiceIfFactory {
 public:
  AwesomeServiceIfSingletonFactory(const boost::shared_ptr<AwesomeServiceIf>& iface) : iface_(iface) {}
  virtual ~AwesomeServiceIfSingletonFactory() {}

  virtual AwesomeServiceIf* getHandler(const ::apache::thrift::TConnectionInfo&) {
    return iface_.get();
  }
  virtual void releaseHandler(AwesomeServiceIf* /* handler */) {}

 protected:
  boost::shared_ptr<AwesomeServiceIf> iface_;
};

class AwesomeServiceNull : virtual public AwesomeServiceIf {
 public:
  virtual ~AwesomeServiceNull() {}
  void getData(Response& /* _return */, const Request& /* request */) {
    return;
  }
  void reset() {
    return;
  }
  bool ping() {
    bool _return = false;
    return _return;
  }
  void handleMapReduceResult(const std::string& /* name */, const std::string& /* data */) {
    return;
  }
};

typedef struct _AwesomeService_getData_args__isset {
  _AwesomeService_getData_args__isset() : request(false) {}
  bool request :1;
} _AwesomeService_getData_args__isset;

class AwesomeService_getData_args {
 public:

  static const char* ascii_fingerprint; // = "86EBE43E72A18EBE1565771CDC4D4CB9";
  static const uint8_t binary_fingerprint[16]; // = {0x86,0xEB,0xE4,0x3E,0x72,0xA1,0x8E,0xBE,0x15,0x65,0x77,0x1C,0xDC,0x4D,0x4C,0xB9};

  AwesomeService_getData_args(const AwesomeService_getData_args&);
  AwesomeService_getData_args& operator=(const AwesomeService_getData_args&);
  AwesomeService_getData_args() {
  }

  virtual ~AwesomeService_getData_args() throw();
  Request request;

  _AwesomeService_getData_args__isset __isset;

  void __set_request(const Request& val);

  bool operator == (const AwesomeService_getData_args & rhs) const
  {
    if (!(request == rhs.request))
      return false;
    return true;
  }
  bool operator != (const AwesomeService_getData_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const AwesomeService_getData_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

  friend std::ostream& operator<<(std::ostream& out, const AwesomeService_getData_args& obj);
};


class AwesomeService_getData_pargs {
 public:

  static const char* ascii_fingerprint; // = "86EBE43E72A18EBE1565771CDC4D4CB9";
  static const uint8_t binary_fingerprint[16]; // = {0x86,0xEB,0xE4,0x3E,0x72,0xA1,0x8E,0xBE,0x15,0x65,0x77,0x1C,0xDC,0x4D,0x4C,0xB9};


  virtual ~AwesomeService_getData_pargs() throw();
  const Request* request;

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

  friend std::ostream& operator<<(std::ostream& out, const AwesomeService_getData_pargs& obj);
};

typedef struct _AwesomeService_getData_result__isset {
  _AwesomeService_getData_result__isset() : success(false) {}
  bool success :1;
} _AwesomeService_getData_result__isset;

class AwesomeService_getData_result {
 public:

  static const char* ascii_fingerprint; // = "D92F6224DB9B7E8B9A35929DF5C09815";
  static const uint8_t binary_fingerprint[16]; // = {0xD9,0x2F,0x62,0x24,0xDB,0x9B,0x7E,0x8B,0x9A,0x35,0x92,0x9D,0xF5,0xC0,0x98,0x15};

  AwesomeService_getData_result(const AwesomeService_getData_result&);
  AwesomeService_getData_result& operator=(const AwesomeService_getData_result&);
  AwesomeService_getData_result() {
  }

  virtual ~AwesomeService_getData_result() throw();
  Response success;

  _AwesomeService_getData_result__isset __isset;

  void __set_success(const Response& val);

  bool operator == (const AwesomeService_getData_result & rhs) const
  {
    if (!(success == rhs.success))
      return false;
    return true;
  }
  bool operator != (const AwesomeService_getData_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const AwesomeService_getData_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

  friend std::ostream& operator<<(std::ostream& out, const AwesomeService_getData_result& obj);
};

typedef struct _AwesomeService_getData_presult__isset {
  _AwesomeService_getData_presult__isset() : success(false) {}
  bool success :1;
} _AwesomeService_getData_presult__isset;

class AwesomeService_getData_presult {
 public:

  static const char* ascii_fingerprint; // = "D92F6224DB9B7E8B9A35929DF5C09815";
  static const uint8_t binary_fingerprint[16]; // = {0xD9,0x2F,0x62,0x24,0xDB,0x9B,0x7E,0x8B,0x9A,0x35,0x92,0x9D,0xF5,0xC0,0x98,0x15};


  virtual ~AwesomeService_getData_presult() throw();
  Response* success;

  _AwesomeService_getData_presult__isset __isset;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

  friend std::ostream& operator<<(std::ostream& out, const AwesomeService_getData_presult& obj);
};


class AwesomeService_reset_args {
 public:

  static const char* ascii_fingerprint; // = "99914B932BD37A50B983C5E7C90AE93B";
  static const uint8_t binary_fingerprint[16]; // = {0x99,0x91,0x4B,0x93,0x2B,0xD3,0x7A,0x50,0xB9,0x83,0xC5,0xE7,0xC9,0x0A,0xE9,0x3B};

  AwesomeService_reset_args(const AwesomeService_reset_args&);
  AwesomeService_reset_args& operator=(const AwesomeService_reset_args&);
  AwesomeService_reset_args() {
  }

  virtual ~AwesomeService_reset_args() throw();

  bool operator == (const AwesomeService_reset_args & /* rhs */) const
  {
    return true;
  }
  bool operator != (const AwesomeService_reset_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const AwesomeService_reset_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

  friend std::ostream& operator<<(std::ostream& out, const AwesomeService_reset_args& obj);
};


class AwesomeService_reset_pargs {
 public:

  static const char* ascii_fingerprint; // = "99914B932BD37A50B983C5E7C90AE93B";
  static const uint8_t binary_fingerprint[16]; // = {0x99,0x91,0x4B,0x93,0x2B,0xD3,0x7A,0x50,0xB9,0x83,0xC5,0xE7,0xC9,0x0A,0xE9,0x3B};


  virtual ~AwesomeService_reset_pargs() throw();

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

  friend std::ostream& operator<<(std::ostream& out, const AwesomeService_reset_pargs& obj);
};


class AwesomeService_reset_result {
 public:

  static const char* ascii_fingerprint; // = "99914B932BD37A50B983C5E7C90AE93B";
  static const uint8_t binary_fingerprint[16]; // = {0x99,0x91,0x4B,0x93,0x2B,0xD3,0x7A,0x50,0xB9,0x83,0xC5,0xE7,0xC9,0x0A,0xE9,0x3B};

  AwesomeService_reset_result(const AwesomeService_reset_result&);
  AwesomeService_reset_result& operator=(const AwesomeService_reset_result&);
  AwesomeService_reset_result() {
  }

  virtual ~AwesomeService_reset_result() throw();

  bool operator == (const AwesomeService_reset_result & /* rhs */) const
  {
    return true;
  }
  bool operator != (const AwesomeService_reset_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const AwesomeService_reset_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

  friend std::ostream& operator<<(std::ostream& out, const AwesomeService_reset_result& obj);
};


class AwesomeService_reset_presult {
 public:

  static const char* ascii_fingerprint; // = "99914B932BD37A50B983C5E7C90AE93B";
  static const uint8_t binary_fingerprint[16]; // = {0x99,0x91,0x4B,0x93,0x2B,0xD3,0x7A,0x50,0xB9,0x83,0xC5,0xE7,0xC9,0x0A,0xE9,0x3B};


  virtual ~AwesomeService_reset_presult() throw();

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

  friend std::ostream& operator<<(std::ostream& out, const AwesomeService_reset_presult& obj);
};


class AwesomeService_ping_args {
 public:

  static const char* ascii_fingerprint; // = "99914B932BD37A50B983C5E7C90AE93B";
  static const uint8_t binary_fingerprint[16]; // = {0x99,0x91,0x4B,0x93,0x2B,0xD3,0x7A,0x50,0xB9,0x83,0xC5,0xE7,0xC9,0x0A,0xE9,0x3B};

  AwesomeService_ping_args(const AwesomeService_ping_args&);
  AwesomeService_ping_args& operator=(const AwesomeService_ping_args&);
  AwesomeService_ping_args() {
  }

  virtual ~AwesomeService_ping_args() throw();

  bool operator == (const AwesomeService_ping_args & /* rhs */) const
  {
    return true;
  }
  bool operator != (const AwesomeService_ping_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const AwesomeService_ping_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

  friend std::ostream& operator<<(std::ostream& out, const AwesomeService_ping_args& obj);
};


class AwesomeService_ping_pargs {
 public:

  static const char* ascii_fingerprint; // = "99914B932BD37A50B983C5E7C90AE93B";
  static const uint8_t binary_fingerprint[16]; // = {0x99,0x91,0x4B,0x93,0x2B,0xD3,0x7A,0x50,0xB9,0x83,0xC5,0xE7,0xC9,0x0A,0xE9,0x3B};


  virtual ~AwesomeService_ping_pargs() throw();

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

  friend std::ostream& operator<<(std::ostream& out, const AwesomeService_ping_pargs& obj);
};

typedef struct _AwesomeService_ping_result__isset {
  _AwesomeService_ping_result__isset() : success(false) {}
  bool success :1;
} _AwesomeService_ping_result__isset;

class AwesomeService_ping_result {
 public:

  static const char* ascii_fingerprint; // = "D9D3B4421B1F23CB4063C80B484E7909";
  static const uint8_t binary_fingerprint[16]; // = {0xD9,0xD3,0xB4,0x42,0x1B,0x1F,0x23,0xCB,0x40,0x63,0xC8,0x0B,0x48,0x4E,0x79,0x09};

  AwesomeService_ping_result(const AwesomeService_ping_result&);
  AwesomeService_ping_result& operator=(const AwesomeService_ping_result&);
  AwesomeService_ping_result() : success(0) {
  }

  virtual ~AwesomeService_ping_result() throw();
  bool success;

  _AwesomeService_ping_result__isset __isset;

  void __set_success(const bool val);

  bool operator == (const AwesomeService_ping_result & rhs) const
  {
    if (!(success == rhs.success))
      return false;
    return true;
  }
  bool operator != (const AwesomeService_ping_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const AwesomeService_ping_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

  friend std::ostream& operator<<(std::ostream& out, const AwesomeService_ping_result& obj);
};

typedef struct _AwesomeService_ping_presult__isset {
  _AwesomeService_ping_presult__isset() : success(false) {}
  bool success :1;
} _AwesomeService_ping_presult__isset;

class AwesomeService_ping_presult {
 public:

  static const char* ascii_fingerprint; // = "D9D3B4421B1F23CB4063C80B484E7909";
  static const uint8_t binary_fingerprint[16]; // = {0xD9,0xD3,0xB4,0x42,0x1B,0x1F,0x23,0xCB,0x40,0x63,0xC8,0x0B,0x48,0x4E,0x79,0x09};


  virtual ~AwesomeService_ping_presult() throw();
  bool* success;

  _AwesomeService_ping_presult__isset __isset;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

  friend std::ostream& operator<<(std::ostream& out, const AwesomeService_ping_presult& obj);
};

typedef struct _AwesomeService_handleMapReduceResult_args__isset {
  _AwesomeService_handleMapReduceResult_args__isset() : name(false), data(false) {}
  bool name :1;
  bool data :1;
} _AwesomeService_handleMapReduceResult_args__isset;

class AwesomeService_handleMapReduceResult_args {
 public:

  static const char* ascii_fingerprint; // = "07A9615F837F7D0A952B595DD3020972";
  static const uint8_t binary_fingerprint[16]; // = {0x07,0xA9,0x61,0x5F,0x83,0x7F,0x7D,0x0A,0x95,0x2B,0x59,0x5D,0xD3,0x02,0x09,0x72};

  AwesomeService_handleMapReduceResult_args(const AwesomeService_handleMapReduceResult_args&);
  AwesomeService_handleMapReduceResult_args& operator=(const AwesomeService_handleMapReduceResult_args&);
  AwesomeService_handleMapReduceResult_args() : name(), data() {
  }

  virtual ~AwesomeService_handleMapReduceResult_args() throw();
  std::string name;
  std::string data;

  _AwesomeService_handleMapReduceResult_args__isset __isset;

  void __set_name(const std::string& val);

  void __set_data(const std::string& val);

  bool operator == (const AwesomeService_handleMapReduceResult_args & rhs) const
  {
    if (!(name == rhs.name))
      return false;
    if (!(data == rhs.data))
      return false;
    return true;
  }
  bool operator != (const AwesomeService_handleMapReduceResult_args &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const AwesomeService_handleMapReduceResult_args & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

  friend std::ostream& operator<<(std::ostream& out, const AwesomeService_handleMapReduceResult_args& obj);
};


class AwesomeService_handleMapReduceResult_pargs {
 public:

  static const char* ascii_fingerprint; // = "07A9615F837F7D0A952B595DD3020972";
  static const uint8_t binary_fingerprint[16]; // = {0x07,0xA9,0x61,0x5F,0x83,0x7F,0x7D,0x0A,0x95,0x2B,0x59,0x5D,0xD3,0x02,0x09,0x72};


  virtual ~AwesomeService_handleMapReduceResult_pargs() throw();
  const std::string* name;
  const std::string* data;

  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

  friend std::ostream& operator<<(std::ostream& out, const AwesomeService_handleMapReduceResult_pargs& obj);
};


class AwesomeService_handleMapReduceResult_result {
 public:

  static const char* ascii_fingerprint; // = "99914B932BD37A50B983C5E7C90AE93B";
  static const uint8_t binary_fingerprint[16]; // = {0x99,0x91,0x4B,0x93,0x2B,0xD3,0x7A,0x50,0xB9,0x83,0xC5,0xE7,0xC9,0x0A,0xE9,0x3B};

  AwesomeService_handleMapReduceResult_result(const AwesomeService_handleMapReduceResult_result&);
  AwesomeService_handleMapReduceResult_result& operator=(const AwesomeService_handleMapReduceResult_result&);
  AwesomeService_handleMapReduceResult_result() {
  }

  virtual ~AwesomeService_handleMapReduceResult_result() throw();

  bool operator == (const AwesomeService_handleMapReduceResult_result & /* rhs */) const
  {
    return true;
  }
  bool operator != (const AwesomeService_handleMapReduceResult_result &rhs) const {
    return !(*this == rhs);
  }

  bool operator < (const AwesomeService_handleMapReduceResult_result & ) const;

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);
  uint32_t write(::apache::thrift::protocol::TProtocol* oprot) const;

  friend std::ostream& operator<<(std::ostream& out, const AwesomeService_handleMapReduceResult_result& obj);
};


class AwesomeService_handleMapReduceResult_presult {
 public:

  static const char* ascii_fingerprint; // = "99914B932BD37A50B983C5E7C90AE93B";
  static const uint8_t binary_fingerprint[16]; // = {0x99,0x91,0x4B,0x93,0x2B,0xD3,0x7A,0x50,0xB9,0x83,0xC5,0xE7,0xC9,0x0A,0xE9,0x3B};


  virtual ~AwesomeService_handleMapReduceResult_presult() throw();

  uint32_t read(::apache::thrift::protocol::TProtocol* iprot);

  friend std::ostream& operator<<(std::ostream& out, const AwesomeService_handleMapReduceResult_presult& obj);
};

class AwesomeServiceClient : virtual public AwesomeServiceIf {
 public:
  AwesomeServiceClient(boost::shared_ptr< ::apache::thrift::protocol::TProtocol> prot) {
    setProtocol(prot);
  }
  AwesomeServiceClient(boost::shared_ptr< ::apache::thrift::protocol::TProtocol> iprot, boost::shared_ptr< ::apache::thrift::protocol::TProtocol> oprot) {
    setProtocol(iprot,oprot);
  }
 private:
  void setProtocol(boost::shared_ptr< ::apache::thrift::protocol::TProtocol> prot) {
  setProtocol(prot,prot);
  }
  void setProtocol(boost::shared_ptr< ::apache::thrift::protocol::TProtocol> iprot, boost::shared_ptr< ::apache::thrift::protocol::TProtocol> oprot) {
    piprot_=iprot;
    poprot_=oprot;
    iprot_ = iprot.get();
    oprot_ = oprot.get();
  }
 public:
  boost::shared_ptr< ::apache::thrift::protocol::TProtocol> getInputProtocol() {
    return piprot_;
  }
  boost::shared_ptr< ::apache::thrift::protocol::TProtocol> getOutputProtocol() {
    return poprot_;
  }
  void getData(Response& _return, const Request& request);
  void send_getData(const Request& request);
  void recv_getData(Response& _return);
  void reset();
  void send_reset();
  void recv_reset();
  bool ping();
  void send_ping();
  bool recv_ping();
  void handleMapReduceResult(const std::string& name, const std::string& data);
  void send_handleMapReduceResult(const std::string& name, const std::string& data);
  void recv_handleMapReduceResult();
 protected:
  boost::shared_ptr< ::apache::thrift::protocol::TProtocol> piprot_;
  boost::shared_ptr< ::apache::thrift::protocol::TProtocol> poprot_;
  ::apache::thrift::protocol::TProtocol* iprot_;
  ::apache::thrift::protocol::TProtocol* oprot_;
};

class AwesomeServiceProcessor : public ::apache::thrift::TDispatchProcessor {
 protected:
  boost::shared_ptr<AwesomeServiceIf> iface_;
  virtual bool dispatchCall(::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, const std::string& fname, int32_t seqid, void* callContext);
 private:
  typedef  void (AwesomeServiceProcessor::*ProcessFunction)(int32_t, ::apache::thrift::protocol::TProtocol*, ::apache::thrift::protocol::TProtocol*, void*);
  typedef std::map<std::string, ProcessFunction> ProcessMap;
  ProcessMap processMap_;
  void process_getData(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
  void process_reset(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
  void process_ping(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
  void process_handleMapReduceResult(int32_t seqid, ::apache::thrift::protocol::TProtocol* iprot, ::apache::thrift::protocol::TProtocol* oprot, void* callContext);
 public:
  AwesomeServiceProcessor(boost::shared_ptr<AwesomeServiceIf> iface) :
    iface_(iface) {
    processMap_["getData"] = &AwesomeServiceProcessor::process_getData;
    processMap_["reset"] = &AwesomeServiceProcessor::process_reset;
    processMap_["ping"] = &AwesomeServiceProcessor::process_ping;
    processMap_["handleMapReduceResult"] = &AwesomeServiceProcessor::process_handleMapReduceResult;
  }

  virtual ~AwesomeServiceProcessor() {}
};

class AwesomeServiceProcessorFactory : public ::apache::thrift::TProcessorFactory {
 public:
  AwesomeServiceProcessorFactory(const ::boost::shared_ptr< AwesomeServiceIfFactory >& handlerFactory) :
      handlerFactory_(handlerFactory) {}

  ::boost::shared_ptr< ::apache::thrift::TProcessor > getProcessor(const ::apache::thrift::TConnectionInfo& connInfo);

 protected:
  ::boost::shared_ptr< AwesomeServiceIfFactory > handlerFactory_;
};

class AwesomeServiceMultiface : virtual public AwesomeServiceIf {
 public:
  AwesomeServiceMultiface(std::vector<boost::shared_ptr<AwesomeServiceIf> >& ifaces) : ifaces_(ifaces) {
  }
  virtual ~AwesomeServiceMultiface() {}
 protected:
  std::vector<boost::shared_ptr<AwesomeServiceIf> > ifaces_;
  AwesomeServiceMultiface() {}
  void add(boost::shared_ptr<AwesomeServiceIf> iface) {
    ifaces_.push_back(iface);
  }
 public:
  void getData(Response& _return, const Request& request) {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->getData(_return, request);
    }
    ifaces_[i]->getData(_return, request);
    return;
  }

  void reset() {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->reset();
    }
    ifaces_[i]->reset();
  }

  bool ping() {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->ping();
    }
    return ifaces_[i]->ping();
  }

  void handleMapReduceResult(const std::string& name, const std::string& data) {
    size_t sz = ifaces_.size();
    size_t i = 0;
    for (; i < (sz - 1); ++i) {
      ifaces_[i]->handleMapReduceResult(name, data);
    }
    ifaces_[i]->handleMapReduceResult(name, data);
  }

};



#endif
