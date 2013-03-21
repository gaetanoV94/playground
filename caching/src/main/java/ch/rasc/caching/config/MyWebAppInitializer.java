package ch.rasc.caching.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MyWebAppInitializer implements WebApplicationInitializer {

	@Override
	public void onStartup(ServletContext container) {
		try (AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
				GenericWebApplicationContext webApplicationContext = new GenericWebApplicationContext();) {
			rootContext.register(WebConfig.class);

			// Manage the lifecycle of the root application context
			container.addListener(new ContextLoaderListener(rootContext));

			// Register and map the dispatcher servlet
			ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher", new DispatcherServlet(
					webApplicationContext));
			dispatcher.setLoadOnStartup(1);
			dispatcher.addMapping("/action/*");
		}
	}
}