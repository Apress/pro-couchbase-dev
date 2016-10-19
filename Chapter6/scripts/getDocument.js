 
var couchbase = require('couchbase')
var cluster = new couchbase.Cluster('couchbase://localhost:8091');
 
var bucket =  cluster.openBucket('default');

bucket.get('catalog', function(err, result) {
console.log(result);
});