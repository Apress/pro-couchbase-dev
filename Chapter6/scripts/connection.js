

var couchbase = require('couchbase')
var cluster = new couchbase.Cluster('couchbase://localhost:8091');
callback = function(err)
{
  if (err)
    console.log("Error in establishing connection with Couchbase Server bucket 'default': "+err);
else
  console.log("Connection with Couchbase Server bucket 'default'  established.");

}
var bucket =  cluster.openBucket('default',callback);

console.log("Client Version "+bucket.clientVersion);
console.log("Configuration throttle in msecs "+bucket.configThrottle);
console.log("Connection Timeout in msecs "+ bucket.connectionTimeout);
console.log("Node Connection Timeout msecs "+ bucket.nodeConnectionTimeout);
console.log("libcouchbase version "+bucket.lcbVersion);
console.log("Operation timeout in msecs "+bucket.operationTimeout);
console.log("View timeout in msecs "+bucket.viewTimeout);


 
 

 