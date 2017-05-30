package ninja.optimistic.framework.connectors;

public interface IRESTConnector<I, O> extends IConnector<I, O> {
	public O get(String path);
	public O post(String path, I request);
}
