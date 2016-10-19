require 'rubygems'
require 'couchbase'
   
   
client = Couchbase.connect("http://192.168.1.71:8091")
 
print client.set("5",-5) 
 print "\n<br>";
print client.increment("5",1).class
print client.increment("5",4).class
 
 


  