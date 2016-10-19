require 'rubygems'
require 'couchbase'
   
   
client = Couchbase.connect("http://127.0.0.1:8091")
 
#client.set("2",2)

#client.set("catalog",1)
  
#client.set("2",-3)
client.set("2",-1)

#client.increment("1",2,:create=>true)
#client.decrement("2",3)
#client.increment("1",2)
#client.increment("2",2)
#print client.increment("3",2,:initial=>1,:create=>false, :extended=>true)


 
#client.increment("catalog",2)

 
 

#client.replace("catalog2","{'journal': 'Oracle Magazine','publisher': 'Oracle Publishing','edition': 'November December 2013','title': 'Quintessential #and Collaborative','author': 'Tom Haunert'}")

#client.append("catalog2",{"status"=>"archive"})
#client.prepend("catalog2",{"status"=>"archive"})

#client.default_format = :document
 #       client.set("catalog", {"journal" => "Oracle Magazine"})
 
 #       client.cas("catalog") do |val|
 #             val["publisher"] = "Oracle Publishing"
#val["edition"] = "November December 2013"
#val["title"] = "Engineering as a Service"
#val["author"] = "David A. Kelly"
     #         val
     #   end

 
    
 


  