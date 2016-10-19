require 'rubygems'
require 'couchbase'
 conn = Couchbase.connect(:timeout => 10_000_000)
 conn.timeout = 1_0
 conn.set("connection timeout", "1_0")
