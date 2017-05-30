package ninja.optimistic.framework.actors.delegators;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import ninja.optimistic.framework.actors.IActor;

public class Delegator<I, O, A> implements IDelegator<I, O, A> {
	private static final Logger log = Logger.getLogger(Delegator.class.getName());
	private Map<Class<?>, A> registrar;
	
	public Delegator() {
		this.registrar = new HashMap<>();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public O delegate(I input) {
		O ret = null;
		boolean registered = false;
		Iterator<Entry<Class<?>, A>> it = registrar.entrySet().iterator();
		
		while (it.hasNext()) {
			Map.Entry<Class<?>, A> pair = (Entry<Class<?>, A>) it.next();
			
			if (pair.getKey().isAssignableFrom(input.getClass())) {
				registered = true;
				ret = ((IActor<I, O>)pair.getValue()).act(input);
			}
		}
		
		if (!registered)
			log.info(input.getClass().getCanonicalName() + " was NOT previously registered.");
		
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void register(Class<?> input, IActor<I, O> actor) {
		this.registrar.put(input, (A) actor);
	}

	public Map<Class<?>, A> getRegistrar() {
		return this.registrar;
	}
}
