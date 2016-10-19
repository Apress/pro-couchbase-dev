require 'rubygems'
require 'couchbase'
   
   
client = Couchbase.connect("http://localhost:8091")
 
client.set("journal","Oracle Magazine")

client.set("publisher","Oracle Publishing")

client.set("edition","November-December 2013")

client.set("title","Quintessential and Collaborative")

client.set("author","Tom Haunert")


client.set("catalog2","{'journal': 'Oracle Magazine','publisher': 'Oracle Publishing','edition': 'November December 2013','title': 'Engineering as a Service','author': 'David A. Kelly'}")

  