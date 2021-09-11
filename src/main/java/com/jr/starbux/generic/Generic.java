package com.jr.starbux.generic;

import java.lang.reflect.InvocationTargetException;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Generic<T> {

	private Class<T> type;

	public Generic(Class<T> type) {
		this.type = type;
	}

	public T newInstance()  {
		try {
			return type.getDeclaredConstructor().newInstance();
		} catch (InstantiationException e) {
			log.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			log.error(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			log.error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			log.error(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			log.error(e.getMessage(), e);
		} catch (SecurityException e) {
			log.error(e.getMessage(), e);
		}
		return null;
	}

}
