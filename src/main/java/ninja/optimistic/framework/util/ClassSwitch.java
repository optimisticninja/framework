package ninja.optimistic.framework.util;

import java.util.Optional;
import java.util.function.Consumer;

public class ClassSwitch {
	public static <T> void switchType(Object o, Consumer... a) {
	    for (Consumer consumer : a)
	        consumer.accept(o);
	}

	public static <T> Consumer caze(Class<T> cls, Consumer<T> c) {
	    return obj -> Optional.of(obj).filter(cls::isInstance).map(cls::cast).ifPresent(c);
	}    
}
