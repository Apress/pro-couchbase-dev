require 'rubygems'
require 'couchbase'
   
   
client = Couchbase.connect("http://127.0.0.1:8091")
 
client.add("journal","Oracle Magazine")

client.add("publisher","Oracle Publishing")

client.add("edition","November-December 2013")

client.add("title","Quintessential and Collaborative")

client.add("author","Tom Haunert")


client.add("catalog2","{'journal': 'Oracle Magazine','publisher': 'Oracle Publishing','edition': 'November December 2013','title': 'Engineering as a Service','author': 'David A. Kelly'}")

  