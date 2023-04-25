package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.jupiter.api.Assertions.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@UnitTest
@interface FastUnitTest { }

@Retention(RetentionPolicy.RUNTIME)
//Annotation type도 타겟으로 적용 가능
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Test
@interface UnitTest { }

class HelloServiceTest {

    @FastUnitTest
    void sayHello() {
        SimpleHelloService helloService = new SimpleHelloService();
        String result = helloService.sayHello("Spring");
        Assertions.assertThat(result).isEqualTo("Hello Spring");
    }

    @Test
    void helloDecorator() {
        String result = new HelloDecorator(name -> name).sayHello("Spring");
        Assertions.assertThat(result).isEqualTo("*Spring*");
    }
}