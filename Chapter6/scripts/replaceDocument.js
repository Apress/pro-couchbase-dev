var couchbase = require('couchbase')
var cluster = new couchbase.Cluster('couchbase://localhost:8091');
 
var bucket =  cluster.openBucket('default');


var catalog_id = 'catalog';

  var catalog2 = 
{
"journal": "Oracle Magazine",
"publisher": "Oracle Publishing",
"edition": "November December 2013",
"title": "Engineering as a Service",
"author": "David A. Kelly",
};


bucket.replace(catalog_id, catalog2, {}, function(err, result) {
 console.log(result);
 });
 
 