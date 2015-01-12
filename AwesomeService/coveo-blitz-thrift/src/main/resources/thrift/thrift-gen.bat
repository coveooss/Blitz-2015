mkdir ..\..\java
mkdir ..\..\python
mkdir ..\..\cpp
mkdir ..\..\rb
mkdir ..\..\js_node
mkdir ..\..\js_browser
mkdir ..\..\csharp
mkdir ..\..\go

thrift-0.9.2.exe --gen java:hashcode -strict -r -out ..\..\java indexer\awesome.thrift
thrift-0.9.2.exe --gen py -strict -r -out ..\..\python indexer\awesome.thrift
thrift-0.9.2.exe --gen cpp -strict -r -out ..\..\cpp indexer\awesome.thrift
thrift-0.9.2.exe --gen rb -strict -r -out ..\..\rb indexer\awesome.thrift
thrift-0.9.2.exe --gen js:node -strict -r -out ..\..\js_node indexer\awesome.thrift
thrift-0.9.2.exe --gen js -strict -r -out ..\..\js_browser indexer\awesome.thrift
thrift-0.9.2.exe --gen csharp -strict -r -out ..\..\csharp indexer\awesome.thrift
thrift-0.9.2.exe --gen go -strict -r -out ..\..\go indexer\awesome.thrift
