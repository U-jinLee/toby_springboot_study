package tobyspring.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HelloControllerTest {

    @Test
    void helloApi() {
        String name = "Spring";
        String resultBody = "Hello " + name;
        //http localhost:8080/hello?name=Spring
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> result = testRestTemplate.getForEntity("http://localhost:8080/hello?name={name}", String.class, name);

        //status 200
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        //header{content-type} text/plain
        assertThat(result.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE))
                .startsWith(MediaType.TEXT_PLAIN_VALUE);
        //body Hello Spring
        assertThat(result.getBody()).isEqualTo(resultBody);
    }

    @Test
    void helloApi_fail() {
        String name = "Spring";
        String resultBody = "Hello " + name;
        //http localhost:8080/hello?name=Spring
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> result = testRestTemplate.getForEntity("http://localhost:8080/hello?name=", String.class);

        //status 200
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    void helloController() {
        HelloController helloController = new HelloController(name -> name);

        String result = helloController.hello("Test");
        assertThat(result).isEqualTo("Test");
    }
    @Test
    void helloController_fail() {
        HelloController helloController = new HelloController(name -> name);
        assertThatThrownBy(() -> {
            String result = helloController.hello(null);
        }).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> {
            String result = helloController.hello("");
        }).isInstanceOf(IllegalArgumentException.class);
    }
}