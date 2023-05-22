package dev.selena.commands;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)

public @interface Command {
    String name();
    String permission() default "";
    String[] aliases() default {};
    boolean playerOnly() default false;
   
}