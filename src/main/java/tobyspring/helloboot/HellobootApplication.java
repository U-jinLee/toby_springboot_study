package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
			//servlet mapping 방식
//			servletContext.addServlet("hello", new HttpServlet() {
//						@Override
//						protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//							//request 값 받기
//							String name = req.getParameter("name");
//							//Response 값 생성
//							resp.setStatus(HttpStatus.OK.value());
//							resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
//							resp.getWriter().println("Hello Servlet " + name);
//						}
//					})
//					//URL의 매핑
//					.addMapping("/hello");

			//servlet mapping -> controller로 전환
			servletContext.addServlet("frontcontroller", new HttpServlet() {
						@Override
						protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
							//매핑의 정보를 가지고 있는 컨트롤러
							if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())) {
								/***
								 * 웹 요청 정보를 받아서 인자값으로 넘겨주는 작업을 Binding이라고 한다.
								 * Request의 값을 받아서 Hello Controller의 인자값으로 넘겨주는 작업을 하고 있다.
								 */
								//HelloController
								HelloController helloController = new HelloController();
								//request 값 받기
								String name = req.getParameter("name");
								String result = helloController.hello(name);
								/***/

								//Response 값 생성
								resp.setStatus(HttpStatus.OK.value());
								resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
								resp.getWriter().println(result);
							} else if(req.getRequestURI().equals("/user")) {
								// 유저와 관련된 로직 처리
							} else {
								resp.setStatus(HttpStatus.NOT_FOUND.value());
							}
						}
					})
					//URL의 매핑
					.addMapping("/*");

		});
		//TomcatServlet Container 동작
		webServer.start();
	}

}
