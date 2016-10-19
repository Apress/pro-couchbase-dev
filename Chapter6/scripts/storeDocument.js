var couchbase = require('couchbase')
var cluster = new couchbase.Cluster('couchbase://localhost:8091');
 
var bucket =  cluster.openBucket('default');


var catalog_id = 'catalog';

var catalog = {
  "journal": "Oracle Magazine",
  "publisher": "Oracle Publishing",
  "edition": "November-December 2013",
  "title": "Quintessential and Collaborative",
  "author": "Tom Haunert"
};

bucket.upsert(catalog_id, catalog, function(err, result) {
 console.log(result);
 });
 