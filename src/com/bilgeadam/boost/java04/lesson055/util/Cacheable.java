package com.bilgeadam.boost.java04.lesson055.util;

import java.util.List;

public interface Cacheable<T> {
	T getEntry(long key);
	List<T> list();
	void invalidate();
	void initCache();
}
