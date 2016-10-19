package couchbase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.couchbase.client.java.AsyncBucket;
import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.view.DefaultView;
import com.couchbase.client.java.view.DesignDocument;
import com.couchbase.client.java.view.View;
import com.couchbase.client.java.view.ViewQuery;
import com.couchbase.client.java.view.ViewResult;
import com.couchbase.client.java.view.ViewRow;

public class CouchbaseJavaClient {

	private static Bucket jsonBucket;
	private static JsonDocument document;
	private static JsonObject catalogObj;
	private static JsonDocument catalog;
	private static Bucket defaultBucket;

	public static void main(String args[]) {
		Cluster cluster = CouchbaseCluster.create();
		// jsonBucket = cluster.openBucket("json");

		// AsyncBucket asyncBucket = jsonBucket.async();

		// List<String> nodes = Arrays.asList("192.168.1.71", "192.168.1.72");
		// cluster = CouchbaseCluster.create(nodes);

		defaultBucket = cluster.openBucket();

		createDocument();
		// updateDocument();
		// getDocument();
		// removeDocument();
		// queryView();

		// cluster.disconnect();
	}

	public static void createDocument() {

		catalogObj = JsonObject.empty().put("journal", "Oracle Magazine")
				.put("publisher", "Oracle Publishing")
				.put("edition", "March April 2013");

		document = defaultBucket.insert(JsonDocument.create("catalog",
				catalogObj));
		// document = jsonBucket.upsert(JsonDocument.create("catalog",
		// catalogObj));

	}

	public static void getDocument() {

		catalog = jsonBucket.get("catalog");
		System.out.println("Cas Value: " + catalog.cas());
		System.out.println("Catalog: " + catalog.content());

	}

	public static void updateDocument() {

		catalogObj = JsonObject.empty().put("journal", "Oracle Magazine")
				.put("publisher", "Oracle Publishing")
				.put("edition", "January February 2015")
				.put("section", "Technology");
		// jsonBucket.replace(JsonDocument.create("catalog",catalogObj));
		document = jsonBucket
				.upsert(JsonDocument.create("catalog", catalogObj));

	}

	public static void removeDocument() {

		document = jsonBucket.remove("catalog");
		System.out.println("Cas Value: " + document.cas());
		System.out.println("Catalog: " + document.content());

	}

	public static void queryView() {

		// Perform the ViewQuery
		ViewResult result = jsonBucket.query(ViewQuery.from("catalog",
				"catalog_view"));

		// Iterate through the returned ViewRows
		for (ViewRow row : result) {
			System.out.println(row);
		}

	}
}
