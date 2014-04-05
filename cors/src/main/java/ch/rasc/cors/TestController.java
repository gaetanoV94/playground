package ch.rasc.cors;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.ImmutableList;

@Controller
public class TestController {

	// @RequestMapping(value = "/users", method = RequestMethod.OPTIONS)
	// public void handleCorsPreFlight(HttpServletResponse response) {
	// System.out.println("preflight");
	// response.addHeader("Access-Control-Allow-Origin", "*");
	// response.addHeader("Access-Control-Allow-Methods", "POST");
	// response.addHeader("Access-Control-Allow-Headers", "Content-Type");
	// response.addHeader("Access-Control-Max-Age", "86400"); // seconds
	//
	// }

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	@ResponseBody
	public List<User> getUsers(@RequestBody String postData, HttpServletResponse response) {

		System.out.println(postData);

		// response.addHeader("Access-Control-Allow-Origin", "*");

		ImmutableList.Builder<User> builder = ImmutableList.builder();
		builder.add(new User(1, "admin"), new User(2, "user1"), new User(3, "user2"));
		return builder.build();
	}
}
