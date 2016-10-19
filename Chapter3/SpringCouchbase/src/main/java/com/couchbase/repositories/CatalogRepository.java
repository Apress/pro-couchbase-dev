package com.couchbase.repositories;

import org.springframework.data.couchbase.repository.CouchbaseRepository;
import com.couchbase.model.Catalog;

public interface CatalogRepository extends CouchbaseRepository<Catalog, String> {
}
