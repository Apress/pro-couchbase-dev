var couchbase = require('couchbase')
var cluster = new couchbase.Cluster('couchbase://localhost:8091');
 
var bucket =  cluster.openBucket('default');

var catalog_id = 'catalog2';
 
bucket.remove(catalog_id, {},function(err, result) {
console.log(result);
});
 