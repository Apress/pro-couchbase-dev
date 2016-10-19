require 'rubygems'
require 'couchbase'
   
   
client = Couchbase.connect("http://192.168.1.71:8091")
client.set("Client Type","Ruby")