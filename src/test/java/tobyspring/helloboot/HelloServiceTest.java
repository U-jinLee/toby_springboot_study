package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloServiceTest {

    @Test
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