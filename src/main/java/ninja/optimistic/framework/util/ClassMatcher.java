package ninja.optimistic.framework.util;

import java.util.Set;

public class ClassMatcher {
	public static boolean isAnywhereInSet(Set<Class<?>> heirarchy, Class<?> clazz) {
		boolean ret = false;
		
		for (Class<?> c : heirarchy) {
			if (c.isAssignableFrom(clazz)) {
				ret = true;
				break;
			}
		}
		
		return ret;
	}
	
	public static boolean isInClassHeirarchy(Class<?> clazz1, Class<?> clazz2) {
		if (clazz1.isAssignableFrom(clazz2)) {
			return true;
		}
		
		return false;
	}
	
}
