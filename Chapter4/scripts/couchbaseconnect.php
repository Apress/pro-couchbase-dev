<?php
 
 $cluster = new CouchbaseCluster('couchbase://localhost:8091');
 $bucket = $cluster->openBucket('default');
$res = $bucket->get('catalog');
var_dump($res);
  
 ?>
