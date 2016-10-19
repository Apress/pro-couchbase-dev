require 'rubygems'
require 'couchbase'
   
client = Couchbase.connect("http://127.0.0.1:8091","default")

print client.delete("journal")
print client.delete("catalog2")
print client.delete("publisher","edition")

print client.delete("journal","publisher","edition",:quiet => true)

 


  