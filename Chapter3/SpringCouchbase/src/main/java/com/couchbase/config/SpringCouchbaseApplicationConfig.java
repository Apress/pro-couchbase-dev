package com.couchbase.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.couchbase.config.AbstractCouchbaseConfiguration;
import org.springframework.data.couchbase.repository.config.EnableCouchbaseRepositories;

import com.couchbase.client.CouchbaseClient;

import java.net.URI;
import java.util.Arrays;
 
import java.util.List;

@Configuration
@EnableCouchbaseRepositories("com.couchbase.repositories")
public class SpringCouchbaseApplicationConfig extends
		AbstractCouchbaseConfiguration {
	@Bean
	public CouchbaseClient couchbaseClient() throws Exception {
		return new CouchbaseClient(Arrays.asList(new URI(
				"http://127.0.0.1:8091/pools")), "default", "");
	}

	@Override
	protected List<String> bootstrapHosts() {

		return Arrays.asList(new String("http://127.0.0.1:8091/pools"));
	}

	@Override
	protected String getBucketName() {

		return "default";
	}

	@Override
	protected String getBucketPassword() {

		return "";
	}
}
