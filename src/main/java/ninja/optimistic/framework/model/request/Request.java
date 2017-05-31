package ninja.optimistic.framework.model.request;

public abstract class Request<T> implements IRequest<T> {
	private boolean status;
	
	@Override
	public boolean getStatus() {
		return this.status;
	}
	
	@Override
	public void setStatus(boolean status) {
		this.status = status;
	}
}
