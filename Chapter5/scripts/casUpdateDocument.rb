require 'rubygems'
require 'couchbase'
   
client = Couchbase.connect("http://192.168.1.71:8091")
 
client.default_format = :document
    print  ver=   client.set("catalog", {"journal" => "Oracle Magazine"})
 
         client.cas("catalog") do |val|
               val["publisher"] = "Oracle Publishing"
 val["edition"] = "November December 2013"
 val["title"] = "Engineering as a Service"
 val["author"] = "David A. Kelly"
               val
         end

print client.get("catalog", :extended => true)

  
