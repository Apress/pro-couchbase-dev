package com.couchbase.core;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.couchbase.core.BucketCallback;
import org.springframework.data.couchbase.core.CouchbaseOperations;
import com.couchbase.model.Catalog;
import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.internal.HttpFuture;
import com.couchbase.client.protocol.views.DesignDocument;
import com.couchbase.client.protocol.views.Query;
import com.couchbase.client.protocol.views.Stale;
import com.couchbase.client.protocol.views.View;
import com.couchbase.client.protocol.views.ViewDesign;
import com.couchbase.client.protocol.views.ViewRow;
import com.couchbase.config.SpringCouchbaseApplicationConfig;

public class App {
	static CouchbaseOperations ops;
	static Catalog catalog1;
	static Catalog catalog2;
	static ArrayList arrayList;
	static Query query;
	static String viewName;

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				SpringCouchbaseApplicationConfig.class);
		ops = context.getBean("couchbaseTemplate", CouchbaseOperations.class);
		catalog1 = new Catalog("Oracle Magazine", "Oracle Publishing",
				"November-December 2013", "Engineering as a Service",
				"David A. Kelly");
		catalog2 = new Catalog("Oracle Magazine", "Oracle Publishing",
				"November-December 2013", "Quintessential and Collaborative",
				"Tom Haunert");
		arrayList = new ArrayList();
		  saveDocument();
		// saveDocuments();
		// removeDocument();
		// removeDocuments();
		// insertDocument();
		// insertDocuments();
		// documentExists();
		// findDocumentById();
		// findDocumentByView();
		// queryDocumentView();
		// updateDocument();
		// updateDocuments();
		//bucketCallback();
	}

	public static void saveDocument() {
		ops.save(catalog1);
		ops.save(catalog2);
		System.out.println("Catalog ID : " + catalog1.getId());
                System.out.println("Catalog ID : " + catalog2.getId());
	}

	public static void saveDocuments() {
		arrayList.add(catalog1);
		arrayList.add(catalog2);
		ops.save(arrayList);
	}

	public static void removeDocument() {
		ops.remove(catalog1);
	}

	public static void removeDocuments() {
		arrayList.add(catalog1);
		arrayList.add(catalog2);
		ops.remove(arrayList);
	}

	public static void insertDocument() {
		ops.insert(catalog1);
	}

	public static void insertDocuments() {
		arrayList.add(catalog1);
		arrayList.add(catalog2);
		ops.insert(arrayList);
	}

	public static void documentExists() {
		System.out.println("catalog:engineering-as-a-service ID exists: "
				+ ops.exists("catalog:engineering-as-a-service"));

		System.out.println("catalog:quintessential-and-collaborative ID exists: "
						+ ops.exists("catalog:quintessential-and-collaborative"));
	}

	public static void findDocumentById() {
		Catalog catalog = ops.findById("catalog:engineering-as-a-service",
				Catalog.class);
		System.out.println("Journal : " + catalog.getJournal());
		System.out.println("Publisher : " + catalog.getPublisher());
		System.out.println("Edition : " + catalog.getEdition());
		System.out.println("Title : " + catalog.getTitle());
		System.out.println("Author : " + catalog.getAuthor());
	}

	public static void findDocumentByView() {
		List<URI> uris = new LinkedList<URI>();
		uris.add(URI.create("http://127.0.0.1:8091/pools"));
		CouchbaseClient couchbaseClient;
		try {
			couchbaseClient = new CouchbaseClient(uris, "default", "");
			DesignDocument designDoc = new DesignDocument("JSONDocument");
			viewName = "by_name";
			String mapFunction = "  function(doc,meta) {\n"
					+ "  if (meta.type == 'json') {\n"
					+ "  emit(doc.name, [doc.journal,doc.publisher,doc.edition,doc.title,doc.author]);\n"
					+ "  }\n" + "}";
			ViewDesign viewDesign = new ViewDesign(viewName, mapFunction);
			designDoc.getViews().add(viewDesign);
			HttpFuture<java.lang.Boolean> httpFuture = couchbaseClient
					.asyncCreateDesignDoc(designDoc);
			View view = couchbaseClient.getView("JSONDocument", "by_name");
			query = new Query();
			query.setIncludeDocs(true).setLimit(20);
			query.setStale(Stale.FALSE);
			List<Catalog> catalogList = ops.findByView("JSONDocument",
					viewName, query, Catalog.class);
			Iterator<Catalog> iter = catalogList.iterator();
			while (iter.hasNext()) {
				Catalog catalog = iter.next();
				System.out.println("Journal : " + catalog.getJournal());
				System.out.println("Publisher : " + catalog.getPublisher());
				System.out.println("Edition : " + catalog.getEdition());
				System.out.println("Title : " + catalog.getTitle());
				System.out.println("Author : " + catalog.getAuthor());
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void queryDocumentView() {
		com.couchbase.client.protocol.views.ViewResponse viewResponse = ops
				.queryView("JSONDocument", "by_name", query);
		for (ViewRow row : viewResponse) {
			System.out.println("Id " + row.getId());
			System.out.println("Key " + row.getKey());
			System.out.println("Value " + row.getValue());
		}
	}

	public static void updateDocument() {
		catalog1 = new Catalog("Oracle Magazine", "Oracle Publishing",
				"11/12 2013", "Engineering as a Service", "Kelly, David A.");
		ops.update(catalog1);
	}

	public static void updateDocuments() {
		Catalog catalog1 = new Catalog("Oracle Magazine", "Oracle Publishing",
				"November December 2013", "Engineering as a Service",
				"David Kelly");
		Catalog catalog2 = new Catalog("Oracle Magazine", "Oracle Publishing",
				"11/12 2013", "Quintessential and Collaborative",
				"Haunert, Tom");
		arrayList = new ArrayList();
		arrayList.add(catalog1);
		arrayList.add(catalog2);
		ops.update(arrayList);

	}

	public static void bucketCallback() {
		Catalog catalog = ops.execute(new BucketCallback<Catalog>() {
			public Catalog doInBucket() throws TimeoutException,
					ExecutionException, InterruptedException {
				Catalog catalog1 = new Catalog("Oracle_Magazine",
						"Oracle_Publishing", "11_12_2013",
						"Engineering_as_a_Service", "Kelly_David");
				ops.save(catalog1);
				return catalog1;
			}
		});
		System.out.println("Journal : " + catalog.getJournal());
		System.out.println("Publisher : " + catalog.getPublisher());
		System.out.println("Edition : " + catalog.getEdition());
		System.out.println("Title : " + catalog.getTitle());
		System.out.println("Author : " + catalog.getAuthor());
	}
}
