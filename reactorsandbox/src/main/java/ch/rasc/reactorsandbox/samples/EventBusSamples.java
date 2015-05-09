package ch.rasc.reactorsandbox.samples;

import static reactor.bus.selector.Selectors.$;
import reactor.Environment;
import reactor.bus.Event;
import reactor.bus.EventBus;

/**
 * @author Jon Brisbin
 * @author Stephane Maldini
 */
public class EventBusSamples {

	public static void main(String... args) {
		Environment env = new Environment();
		EventBus r = EventBus.config().env(env).dispatcher(Environment.SHARED).get();

		// Subscribe to topic "test"
		r.<Event<String>> on($("test"), ev -> System.out.println("hi " + ev.getData()));

		// Notify topic "test"
		r.notify("test", Event.wrap("Jon"));

		// Subscribe to topic "test2" and reply with value
		r.receive($("test2"), event -> "Jon");

		// Notify topic "test2" and reply to topic "test"
		r.send("test2", Event.wrap("test2").setReplyTo("test"));

		env.shutdown();
	}

}
