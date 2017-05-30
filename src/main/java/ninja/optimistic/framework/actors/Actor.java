package ninja.optimistic.framework.actors;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import ninja.optimistic.framework.connectors.IConnector;

public abstract class Actor<I, O> implements IActor<I, O> {
	private Map<Class<?>, Class<?>> registrar;
	private Set<IConnector<I,O>> connectors;
	
	public Actor() {
		registrar = new HashMap<>();
		connectors = new HashSet<>();
	}
	
	@Override
	public void register(Class<?> input, IConnector<I, O> connector) {
		this.connectors.add(connector);
		this.registrar.put(input, connector.getClass());
	}

	public Map<Class<?>, Class<?>> getRegistrar() {
		return registrar;
	}

	public Set<IConnector<I, O>> getConnectors() {
		return connectors;
	}
}
