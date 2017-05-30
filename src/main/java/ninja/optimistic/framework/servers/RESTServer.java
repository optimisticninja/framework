package ninja.optimistic.framework.servers;

import java.util.logging.Logger;

import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public abstract class RESTServer implements Server, IRESTServer {
	private static final Logger log = Logger.getLogger(RESTServer.class.getName());
	private org.eclipse.jetty.server.Server server;

	protected RESTServer(int port) {
		this.server = new org.eclipse.jetty.server.Server(port);
		
	}

	@Override
	public void serve() {
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			log.info(e.getStackTrace().toString());
		} finally {
			server.destroy();
		}
	}
	
	@Override
	public void addServlet(ServletHolder servlet, String name) {
		ServletContextHandler context = new ServletContextHandler(this.server, name + "/*");
		context.addServlet(servlet, "/*");
	}
}
