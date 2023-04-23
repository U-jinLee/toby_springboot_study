package tobyspring.helloboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

}