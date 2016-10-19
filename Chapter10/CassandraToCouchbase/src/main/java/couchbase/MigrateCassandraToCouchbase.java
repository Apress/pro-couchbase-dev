package couchbase;

import java.net.URI;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;
import com.datastax.driver.core.ColumnDefinitions;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

public class MigrateCassandraToCouchbase {
	private static com.datastax.driver.core.Cluster cluster;
	private static Session session;

	public static void main(String[] argv) {

		cluster = com.datastax.driver.core.Cluster.builder()
				.addContactPoint("127.0.0.1").build();
		session = cluster.connect();

		migrate();
	}

	private static void migrate() {

		Cluster cluster = CouchbaseCluster.create();
		Bucket defaultBucket = cluster.openBucket("default");

		ResultSet results = session.execute("select * from datastax.catalog");
		int i = 0;
		for (Row row : results) {
			i = i + 1;
			JsonObject catalogObj = JsonObject.empty();
			ColumnDefinitions columnDefinitions = row.getColumnDefinitions();
			Iterator<ColumnDefinitions.Definition> iter = columnDefinitions
					.iterator();
			while (iter.hasNext()) {
				ColumnDefinitions.Definition column = iter.next();
				String columnName = column.getName();
				String value = row.getString(columnName);
				catalogObj.put(columnName, value);

				 
			}
			JsonDocument document = defaultBucket.insert(JsonDocument
					.create("catalog" + i, catalogObj));

			System.out.println("Set Succeeded");
		}

	}

}
