package com.onlythenaive.casestudy.slimchat.service.core.bootstrap;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Bootstrap component shortcut annotation.
 *
 * @author Ilia Gubarev
 */
@Component
@Lazy(false)
@ConditionalOnProperty(name = "bootstrap.enabled", havingValue = "true")
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BootstrapComponent {

}
