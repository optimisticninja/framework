package ninja.optimistic.framework.servers;

import org.eclipse.jetty.servlet.ServletHolder;

public interface IRESTServer extends Server {
	public void addServlet(ServletHolder servlet, String name);
}
