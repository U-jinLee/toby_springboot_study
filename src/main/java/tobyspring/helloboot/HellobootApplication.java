package tobyspring.helloboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@ComponentScan
public class HellobootApplication {
	public static void main(String[] args) {
		//Spring Container
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh();

				// Tomcat, Jetty .. 등을 추상화 해 놓음
				ServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
				WebServer webServer = serverFactory.getWebServer(servletContext -> {
					//servlet mapping -> controller로 전환
					servletContext.addServlet("dispatcherServlet",
									//자기 자신을 참고하기에 this를 입력
									new DispatcherServlet(this))
							//URL의 매핑
							.addMapping("/*");

				});
				//TomcatServlet Container 동작
				webServer.start();
			}
		};

		//Bean 등록::클래스 정보 넘기기
		applicationContext.register(HellobootApplication.class);
		applicationContext.refresh();
	}

}
