package ninja.optimistic.framework.model.request;

public interface IRequest<T> {
	public T 	getPayload();
	public void	setPayload(T payload);
}
