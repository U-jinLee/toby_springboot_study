package tobyspring.helloboot;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE) // Class, Interface, Enum, Annotation
@Retention(RetentionPolicy.RUNTIME) //Runtime까지 유지
@Component
public @interface MyComponent {
}
