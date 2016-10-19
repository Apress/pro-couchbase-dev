<?php
    $cluster = new CouchbaseCluster();
$bucket = $cluster->openBucket('default');
 
$bucket->insert('id1', 15);
$res=$bucket->counter('id1',5);
var_dump($res);

$bucket->insert('id2', 1);
$res= $bucket->counter('id2',-1);
var_dump($res);


$res=$bucket->counter('id3', 5, array('initial'=>10));
var_dump($res);


$res=$bucket->counter('id3', 5);
var_dump($res);
 

$bucket->insert('id4', -15);
$res=$bucket->counter('id4',5);
var_dump($res);

$bucket->insert('id5', 1);
$res=$bucket->counter('id5',-2);
var_dump($res);


?> 