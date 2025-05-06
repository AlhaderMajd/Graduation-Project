// Create this in a new file: logging/annotation/LogExecutionTime.java
package com.example.Graduation.Project.logging.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)  // Can be used on methods only
@Retention(RetentionPolicy.RUNTIME)  // Available at runtime
public @interface LogExecutionTime {
    String value() default "";  // Optional description
}