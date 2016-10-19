require 'rubygems'
require 'couchbase'
   
   
client = Couchbase.connect("http://192.168.1.71:8091")

client.run do
      client.get("catalog2") do |ret|
       print    ret.operation    
 print "\n" 
         print   ret.success?     
print "\n" 
print ret.value
print "\n" 
  print    ret.flags
print "\n" 
         print   ret.key          
print "\n" 
          print  ret.cas
      end
end
 