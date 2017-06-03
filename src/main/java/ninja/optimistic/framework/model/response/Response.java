package ninja.optimistic.framework.model.response;

public abstract class Response implements IResponse {
	private boolean status;
	
	protected Response(boolean status) {
		this.status = status;
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
