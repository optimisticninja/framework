package ninja.optimistic.framework.connectors;

public abstract class Connector<I, O> implements IConnector<I, O> {
	@Override
	public O delegate(I input) {
		throw new UnsupportedOperationException();
	}
}
