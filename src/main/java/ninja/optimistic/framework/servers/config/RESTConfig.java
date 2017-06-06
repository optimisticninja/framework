package ninja.optimistic.framework.servers.config;

import org.glassfish.jersey.server.ResourceConfig;

import ninja.optimistic.framework.shims.GsonJerseyShim;

public abstract class RESTConfig extends ResourceConfig {
	public RESTConfig(String apiPackage) {
		packages(apiPackage);
		register(GsonJerseyShim.class);
	}
}