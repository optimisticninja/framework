package ninja.optimistic.framework.servers.config;

import org.glassfish.jersey.server.ResourceConfig;

import ninja.optimistic.framework.providers.GsonMessageBodyHandler;

public class RESTConfig extends ResourceConfig {
	public RESTConfig(String apiPackage) {
		packages(apiPackage);
		register(GsonMessageBodyHandler.class);
	}
}