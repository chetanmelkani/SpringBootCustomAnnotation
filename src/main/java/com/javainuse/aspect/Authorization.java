package com.javainuse.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = { ElementType.TYPE, ElementType.METHOD })
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Authorization
{
	public enum Type
	{
		REGULAR_USER,

		ADMIN
	}

	Type value() default Type.ADMIN;
}
