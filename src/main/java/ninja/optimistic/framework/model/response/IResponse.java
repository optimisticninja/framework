package ninja.optimistic.framework.model.response;

public interface IResponse <T> {
	public T 		getPayload();
	public void 	setPayload(T payload);
	public boolean 	getStatus();
	public void 	setStatus(boolean success);
}
