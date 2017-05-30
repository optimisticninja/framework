package ninja.optimistic.framework.connectors;

public interface IConnector<I, O> {
	O delegate(I input);
}
