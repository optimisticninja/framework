package ninja.optimistic.framework.actors;

import ninja.optimistic.framework.connectors.IConnector;

public interface IActor <I, O>
{
	public void register(Class<?> input, IConnector<I, O> connector);
	
	public O act(I input);
}
