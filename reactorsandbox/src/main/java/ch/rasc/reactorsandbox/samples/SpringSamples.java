package ch.rasc.reactorsandbox.samples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import reactor.Environment;
import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.spring.context.annotation.Selector;
import reactor.spring.context.config.EnableReactor;

/**
 * @author Jon Brisbin
 */
@EnableAutoConfiguration
public class SpringSamples implements CommandLineRunner {

	@Autowired
	private Environment env;
	@Autowired
	private TestService service;

	@Override
	public void run(String... args) throws Exception {
		this.service.test();

		this.env.shutdown();
	}

	public static void main(String... args) {
		SpringApplication.run(SpringSamples.class, args);
	}

	@Configuration
	@EnableReactor
	@ComponentScan
	public static class ReactorConfiguration {

		@Bean
		public EventBus reactor(Environment env) {
			return EventBus.config().env(env).dispatcher(Environment.SHARED).get();
		}

		@Bean
		public Logger log() {
			return LoggerFactory.getLogger(SpringSamples.class);
		}

	}

	@Component
	public static class AnnotatedHandler {
		@Autowired
		private Logger log;
		@Autowired
		public EventBus reactor;

		@Selector("test.topic")
		public void onTestTopic(String s) {
			this.log.info("onTestTopic: {}", s);
		}
	}

	@Service
	public static class TestService {
		@Autowired
		private Logger log;
		@Autowired
		private EventBus reactor;

		public void test() {
			this.log.info("Testing service...");
			this.reactor.notify("test.topic", Event.wrap("Hello World!"));
		}
	}

}
