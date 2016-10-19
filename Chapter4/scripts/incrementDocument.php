<?php
    $cluster = new CouchbaseCluster();
$bucket = $cluster->openBucket('default');

$arr = array('journal' => 'Oracle Magazine', 'publisher' => 'Oracle Publishing', 'edition' => 'November December  2013', 'title' => 'Quintessential and Collaborative', 'author' => 'Tom Haunert');
$catalog2=json_encode($arr);
$res = $bucket->insert('catalog2', $catalog2);
 var_dump($res);
?> 