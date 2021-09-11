package com.jr.starbux.utils;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.jr.starbux.generic.Generic;

public class ConverterUtils<F, T> {

	private Class<F> fromType;
	private Class<T> toType;

	public ConverterUtils(Class<T> toType, Class<F> fromType) {
		this.toType = toType;
		this.fromType = fromType;
	}

	public Object copyProperties(F from, T to) {
		BeanUtils.copyProperties(from, to);
		return to;
	}

	public List<T> copyPropertiesFromList(List<F> from, List<T> to, Class cl) {
		BeanUtils.copyProperties(from, to);

		for (Object fronOne : from) {
			if (fronOne != null) {
				T toValue = new Generic<T>(this.toType).newInstance();
				BeanUtils.copyProperties(fronOne, toValue);
				to.add(toValue);
			}
		}

		return to;
	}

}
