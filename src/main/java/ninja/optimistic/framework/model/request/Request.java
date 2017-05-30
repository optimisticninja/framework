package ninja.optimistic.framework.model.request;

public abstract class Request<T> implements IRequest<T> {
	private T payload;
	
	@Override
	public T getPayload() {
		return this.payload;
	}
	
	@Override
	public void setPayload(T payload) {
		this.payload = payload;
	}
}
