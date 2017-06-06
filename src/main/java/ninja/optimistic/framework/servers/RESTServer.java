package ninja.optimistic.framework.servers;

import java.util.logging.Logger;

import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.glassfish.jersey.servlet.ServletContainer;

public abstract class RESTServer implements ninja.optimistic.framework.servers.Server, IRESTServer {
	private static final Logger log = Logger.getLogger(RESTServer.class.getName());
	private static final String JERSEY_PROVIDER = "jersey.config.server.provider.classnames";

	private org.eclipse.jetty.server.Server server;

	protected RESTServer(int port, Class<?> api, String path) {
		// Server
		this.server = new org.eclipse.jetty.server.Server(port);

		// REST
		ServletContextHandler restHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		restHandler.setContextPath(path);
		WebAppContext context = new WebAppContext();
		context.setResourceBase("src/main/webapp");
		ServletHolder servlet = restHandler.addServlet(ServletContainer.class, "/rest/*");
		servlet.setInitOrder(0);
		servlet.setInitParameter(JERSEY_PROVIDER, api.getCanonicalName());

		// Web
		ResourceHandler webHandler = new ResourceHandler();
		webHandler.setDirectoriesListed(true);
		webHandler.setResourceBase("src/main/webapp");
		webHandler.setWelcomeFiles(new String[] { "index.html" });

		// Server
		HandlerCollection handlers = new HandlerCollection();
		handlers.addHandler(context);
		// handlers.addHandler(webHandler);
		handlers.addHandler(restHandler);
		server.setHandler(handlers);

	}

	@Override
	public void serve() {
		try {
			server.start();
		} catch (Exception e) {
			log.info(e.getStackTrace().toString());
		}
	}

	@Override
	public void addServlet(ServletHolder servlet, String name) {
		ServletContextHandler context = new ServletContextHandler(this.server, name + "/*");
		context.addServlet(servlet, "/*");
	}

	@Override
	public void close() {
		try {
			server.stop();
			server.destroy();
		} catch (InterruptedException e) {
			log.info(e.getStackTrace().toString());
		} catch (Exception e) {
			log.info(e.getStackTrace().toString());

		}
	}
}
