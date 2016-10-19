 
var couchbase = require('couchbase')
var cluster = new couchbase.Cluster('couchbase://localhost:8091');
 
var bucket =  cluster.openBucket('default');
var ids = ['catalog', 'catalog2'];
bucket.getMulti(ids, function(err, results) {
console.log(results);
});