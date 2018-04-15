package com.rianmusial.videoIntegretyVerifier.util;

import java.lang.reflect.Method;

import com.rianmusial.videoIntegretyVerifier.ImageComparer;

public abstract class ReflectionUtil {

	public static Method getMethod(String methodName, Class<?> c, Class<?>... classes) throws NoSuchMethodException {
		Method m = c.getDeclaredMethod(methodName, classes);
		m.setAccessible(true);
		return m;
	}

}
