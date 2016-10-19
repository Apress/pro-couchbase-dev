require 'rubygems'
require 'couchbase'
client = Couchbase.connect("http://localhost:8091")
client.set("Client Type","Ruby")
