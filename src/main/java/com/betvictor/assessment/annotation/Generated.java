package com.betvictor.assessment.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
/**
 * Annotation to mark a class or  a method as generated to be ignored by jacoco test coverage.
 *
 * */
@Documented
@Retention(RUNTIME)
@Target({TYPE, METHOD})
public @interface Generated {}