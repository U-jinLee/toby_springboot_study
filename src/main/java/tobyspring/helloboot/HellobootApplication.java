package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HellobootApplication {

	public static void main(String[] args) {
		// Tomcat, Jetty .. 등을 추상화 해 놓음
		ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {

			servletContext.addServlet("hello", new HttpServlet() {
						@Override
						protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
							//request 값 받기
							String name = req.getParameter("name");
							//Response 값 생성
							resp.setStatus(HttpStatus.OK.value());
							resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
							resp.getWriter().println("Hello Servlet " + name);
						}
					})
					//URL의 매핑
					.addMapping("/hello");

		});
		//TomcatServlet Container 동작
		webServer.start();
	}

}
