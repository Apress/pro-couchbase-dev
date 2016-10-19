package com.couchbase.service;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.couchbase.client.CouchbaseClient;
import com.couchbase.client.internal.HttpFuture;
import com.couchbase.client.protocol.views.DesignDocument;
import com.couchbase.client.protocol.views.ViewDesign;
import com.couchbase.config.SpringCouchbaseApplicationConfig;
import com.couchbase.model.Catalog;
import com.couchbase.repositories.CatalogRepository;

public class CatalogService {
	// @Autowired
	public static CatalogRepository repository;

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(
				SpringCouchbaseApplicationConfig.class);
		repository = context.getBean(CatalogRepository.class);

		List<URI> uris = new LinkedList<URI>();
		uris.add(URI.create("http://127.0.0.1:8091/pools"));
		CouchbaseClient couchbaseClient;
		try {
			couchbaseClient = new CouchbaseClient(uris, "default", "");
			DesignDocument designDoc = new DesignDocument("catalog");
			String viewName = "all";
			String mapFunction = "  function(doc,meta) {"
					+ "  if (meta.type == 'json') {"
					+ "  emit(doc.name, [doc.journal,doc.publisher,doc.edition,doc.title,doc.author]);"
					+ "  }" + "}";
			String reduceFunction = "function(key, values, rereduce) {"
					+ "if (rereduce) {" + "var result = 0;"
					+ "for (var i = 0; i < values.length; i++) {"
					+ "result += values[i];" + "}" + "return result;"
					+ "  } else {" + " return values.length;" + " }" + "}";
			ViewDesign viewDesign = new ViewDesign(viewName, mapFunction,
					reduceFunction);
			designDoc.getViews().add(viewDesign);
			// HttpFuture<java.lang.Boolean> httpFuture =
			// couchbaseClient.asyncCreateDesignDoc(designDoc);

		} catch (IOException e) {
			e.printStackTrace();
		}

		// countDocuments();
		// findAllDocuments();
		// findOneDocument();
		// findDocumentExists();
		// saveDocument();
		// saveDocuments();
		// deleteDocument();
		//deleteDocuments();
	}

	public static void countDocuments() {
		long count = repository.count();
		System.out.println("Number of catalogs: " + count);
	}

	public static void findAllDocuments() {
		Iterable<Catalog> iterable = repository.findAll();
		Iterator<Catalog> iter = iterable.iterator();
		while (iter.hasNext()) {
			Catalog catalog = iter.next();
			System.out.println("Journal: " + catalog.getJournal());
			System.out.println("Publisher: " + catalog.getPublisher());
			System.out.println("Edition: " + catalog.getEdition());
			System.out.println("Title: " + catalog.getTitle());
			System.out.println("Author: " + catalog.getAuthor());
		}

	}

	public static void findOneDocument() {
		Catalog catalog = repository
				.findOne("catalog:engineering-as-a-service");
		System.out.println("Journal: " + catalog.getJournal());
		System.out.println("Publisher: " + catalog.getPublisher());
		System.out.println("Edition: " + catalog.getEdition());
		System.out.println("Title: " + catalog.getTitle());
		System.out.println("Author: " + catalog.getAuthor());

	}

	public static void findDocumentExists() {

		boolean bool = repository
				.exists("catalog:quintessential-and-collaborative");
		System.out
				.println("Catalog with Id catalog:quintessential-and-collaborative exists: "
						+ bool);

	}

	public static void saveDocument() {

		Catalog catalog = new Catalog("Oracle Magazine", "Oracle Publishing",
				"11/12 2013", "Engineering as a Service", "Kelly, David");
		repository.save(catalog);

	}

	public static void saveDocuments() {

		ArrayList arrayList = new ArrayList();
		Catalog catalog1 = new Catalog("Oracle_Magazine", "Oracle_Publishing",
				"November-December_2013", "EngineeringasaService",
				"David_A._Kelly");
		Catalog catalog2 = new Catalog("Oracle_Magazine", "Oracle_Publishing",
				"11/12_2013", "Engineering_as_a_Service", "Kelly, David");
		arrayList.add(catalog1);
		arrayList.add(catalog2);
		repository.save(arrayList);

	}

	public static void deleteDocument() {
		// repository.delete("catalog:engineeringasaservice");

		Catalog catalog2 = new Catalog("Oracle_Magazine", "Oracle_Publishing",
				"11/12_2013", "Engineering_as_a_Service", "Kelly, David");
		repository.delete(catalog2);

	}

	public static void deleteDocuments() {
		ArrayList arrayList = new ArrayList();
		arrayList.add("catalog:engineering_as_a_service");
		arrayList.add("catalog:engineeringasaservice");
		// repository.delete(arrayList);
		repository.deleteAll();

	}

}
