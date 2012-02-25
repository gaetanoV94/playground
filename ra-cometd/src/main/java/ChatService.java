import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.cometd.bayeux.ChannelId;
import org.cometd.bayeux.server.Authorizer;
import org.cometd.bayeux.server.BayeuxServer;
import org.cometd.bayeux.server.ConfigurableServerChannel;
import org.cometd.bayeux.server.ServerMessage;
import org.cometd.bayeux.server.ServerSession;
import org.cometd.java.annotation.Configure;
import org.cometd.java.annotation.Listener;
import org.cometd.java.annotation.Service;
import org.cometd.server.authorizer.GrantAuthorizer;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
@Service
public final class ChatService {

	@Inject
	BayeuxServer server;

	@PostConstruct
	void init() {
		server.addListener(new BayeuxServer.SessionListener() {
			@Override
			public void sessionAdded(ServerSession session) {
				session.setAttribute("user", server.getContext().getHttpSessionAttribute("user"));
				server.getChannel("/chatroom").publish(session, "connected !", null);
			}

			@Override
			public void sessionRemoved(ServerSession session, boolean timedout) {
				server.getChannel("/chatroom").publish(session, "disconnected !", null);
				session.removeAttribute("user");
			}
		});
	}

	@Configure("/**")
	void any(ConfigurableServerChannel channel) {
		channel.addAuthorizer(GrantAuthorizer.GRANT_NONE);
	}

	@Configure("/chatroom")
	void configure(ConfigurableServerChannel channel) {
		channel.addAuthorizer(new Authorizer() {
			@Override
			public Result authorize(Operation operation, ChannelId channel, ServerSession session, ServerMessage message) {
				return session.getAttribute("user") != null ? Result.grant() : Result.deny("no user in session");
			}
		});
	}

	@Listener("/chatroom")
	void appendUser(ServerSession remote, ServerMessage.Mutable message) {
		message.setData("[" + remote.getAttribute("user") + "] " + message.getData());
	}

}
