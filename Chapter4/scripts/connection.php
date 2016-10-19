<?php
    $cluster = new CouchbaseCluster();
$bucket = $cluster->openBucket('default');

echo "Operation timeout (ms): ";  echo $bucket->operationTimeout;
  echo "<br/>\n";  
 echo "Configuration timeout (ms): ";  echo $bucket->configTimeout;
  echo "<br/>\n";
echo "Configuration Node timeout (ms) : ";  echo $bucket->configNodeTimeout;
  echo "<br/>\n";
echo "HTTP timeout (ms): ";  echo $bucket->httpTimeout;
  echo "<br/>\n";
echo "View timeout (ms): ";  echo $bucket->viewTimeout;
  echo "<br/>\n";


?>