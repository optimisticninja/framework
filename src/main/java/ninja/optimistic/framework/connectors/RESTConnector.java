package ninja.optimistic.framework.connectors;

import java.net.URL;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;

public abstract class RESTConnector<I, O> extends Connector<I, O> implements IRESTConnector<I, O> {
	private static final Logger log = Logger.getLogger(RESTConnector.class.getName());

	private Class<O> responseType;
	private Client client;
	private ClientConfig clientConfig;
	private WebTarget webTarget;

	public RESTConnector(URL url, Class<O> responseType) {
		this.clientConfig = new ClientConfig();
		this.clientConfig.register(responseType);
		this.responseType = responseType;
		this.client = ClientBuilder.newClient(clientConfig);
		this.webTarget = client.target(url.toString());
	}

	@Override
	public O get(String path) {
		O response = null;
		WebTarget currentTarget = this.webTarget.path(path);
		Invocation.Builder invocationBuilder = currentTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response response1 = invocationBuilder.get();
		response = response1.readEntity(responseType);
		return response;
	}

	@Override
	public O post(String path, I request) {
		O response = null;
		log.info(path);
		WebTarget currentTarget = this.webTarget.path(path);
		Invocation.Builder invocationBuilder = currentTarget.request(MediaType.APPLICATION_JSON_TYPE);
		Response response1 = invocationBuilder.post(Entity.json(request));
	    response = (O) response1.readEntity(responseType);
	    return response;
	}
}
