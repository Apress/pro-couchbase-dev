package couchbase;

 
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import org.bson.Document;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class MigrateMongoDBToCouchbase {
	private static Document catalog;

	public static void main(String[] args) {

		MongoClient mongoClient = new MongoClient(
				Arrays.asList(new ServerAddress("localhost", 27017)));
		MongoDatabase db = mongoClient.getDatabase("local");
		MongoCollection<Document> coll = db.getCollection("catalog");
		catalog = coll.find().first();
		migrate();
		mongoClient.close();
	}

	private static void migrate() {
		Cluster cluster = CouchbaseCluster.create();
		Bucket defaultBucket = cluster.openBucket("default");

		Set<String> set = catalog.keySet();
		Iterator<String> iter = set.iterator();
		JsonObject catalogObj = JsonObject.empty();
		while (iter.hasNext()) {
			String columnName = iter.next().toString();
			String value = catalog.get(columnName.toString()).toString();
			catalogObj.put(columnName, value);
		}
		JsonDocument document = defaultBucket.insert(JsonDocument.create(
				"catalog", catalogObj));

		System.out.println("Set Succeeded");
	}

}
