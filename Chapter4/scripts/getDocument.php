<?php
    $cluster = new CouchbaseCluster();
$bucket = $cluster->openBucket('default');

$arr = array('catalog', 'catalog2');
$res = $bucket->get($arr);
 var_dump($res["catalog"]);
?> 