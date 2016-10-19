require 'rubygems'
require 'couchbase'
   
   
client = Couchbase.connect("http://192.168.1.71:8091")
 
  cas=    client.set("catalog2","{'journal': 'Oracle Magazine','publisher': 'Oracle Publishing','edition': 'November December 2013','title': 'Engineering as a Service','author': 'David A. Kelly'}")  

print cas

       client.set("catalog2", "{'journal': 'Oracle Magazine','publisher': 'Oracle-Publishing','edition': 'November December 2013','title': 'Engineering as a Service','author': 'Kelly, A. David'}", :cas=>cas)