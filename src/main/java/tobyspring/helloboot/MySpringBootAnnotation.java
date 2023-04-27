package tobyspring.helloboot;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
//Class, Interface, Enum type에 적용 가능
@Target(ElementType.TYPE)
@Configuration
@ComponentScan
public @interface MySpringBootAnnotation {
}
