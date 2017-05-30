package ninja.optimistic.framework.model.response;

public abstract class Response<T> implements IResponse<T> {
	private boolean status;
	private T payload;
	
	protected Response(boolean status) {
		this.status = status;
	}
	
	@Override
	public T getPayload() {
		return this.payload;
	}

	@Override
	public void setPayload(T payload) {
		this.payload = payload;
	}
	
	@Override
	public boolean getStatus() {
		return this.status;
	}
	
	@Override
	public void setStatus(boolean success) {
		this.status = success;
	}
}
