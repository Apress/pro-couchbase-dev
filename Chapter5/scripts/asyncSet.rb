require 'rubygems'
require 'couchbase'
   
   
client = Couchbase.connect("http://127.0.0.1:8091")

client.run do
      client.set("catalog2","{'journal': 'Oracle Magazine','publisher': 'Oracle Publishing','edition': 'November December 2013','title': 'Engineering as a Service','author': 'David A. Kelly'}") do |ret|
       print    ret.operation    
 print "\n" 
         print   ret.success?     
print "\n" 
         print   ret.key          
print "\n" 
          print  ret.cas
      end
end