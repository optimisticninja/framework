package ninja.optimistic.framework.model.request;

public interface IRequest<T> {
	public boolean getStatus();
	public void setStatus(boolean status);
}
