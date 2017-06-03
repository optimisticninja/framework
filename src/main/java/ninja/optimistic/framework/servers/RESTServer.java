package ninja.optimistic.framework.servers;

import java.util.logging.Logger;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public abstract class RESTServer implements ninja.optimistic.framework.servers.Server, IRESTServer {
	private static final Logger log = Logger.getLogger(RESTServer.class.getName());
	private static final String JERSEY_PROVIDER = "jersey.config.server.provider.classnames";

	private org.eclipse.jetty.server.Server server;

	protected RESTServer(int port, Class<?> api, String path) {
		this.server = new org.eclipse.jetty.server.Server(port);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath(path);
		server.setHandler(context);
		ServletHolder servlet = context.addServlet(ServletContainer.class, "/*");
		servlet.setInitOrder(0);
		servlet.setInitParameter(JERSEY_PROVIDER, api.getCanonicalName());
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
