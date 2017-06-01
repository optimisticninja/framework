package ninja.optimistic.framework.delegators;

import ninja.optimistic.framework.actors.IActor;

public interface IDelegator <I, O, A> {
	public void register(Class<?> input, IActor<I, O> actor);
	public O delegate(I input);
}
