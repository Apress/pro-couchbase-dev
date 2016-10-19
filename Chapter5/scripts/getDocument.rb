require 'rubygems'
require 'couchbase'
client = Couchbase.connect("http://127.0.0.1:8091")
print client.get("catalog2")

