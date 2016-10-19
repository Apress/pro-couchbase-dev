<?php
    $cluster = new CouchbaseCluster();
$bucket = $cluster->openBucket('default');

$arr = array('catalog2','id1','id2','id3','id4','id5');
$res = $bucket->remove($arr);
 var_dump($res);
?> 