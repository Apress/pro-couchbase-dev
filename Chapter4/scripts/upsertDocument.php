<?php
    $cluster = new CouchbaseCluster();
$bucket = $cluster->openBucket('default');

$arr = array('journal' => 'OracleMagazine', 'publisher' => 'OraclePublishing', 'edition' => '11/12  2013', 'title' => 'Quintessential and Collaborative', 'author' => 'Haunert, Tom');
$catalog2=json_encode($arr);
$res = $bucket->upsert('catalog2', $catalog2);
 var_dump($res);
?> 