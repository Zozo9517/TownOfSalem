package com.gyilkososgame.beta.Controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyilkososgame.beta.Repositories.RoleRepo;
import com.gyilkososgame.beta.Repositories.UserRepo;
import com.gyilkososgame.beta.entities.Role;
import com.gyilkososgame.beta.entities.Users;
import com.gyilkososgame.beta.services.TokenSess;

@RestController
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
public class EndpointController {
	@Autowired
	private UserRepo repo;
	@Autowired
	private RoleRepo repo2;
	@Autowired
	private TokenSess ts;

	private static final Logger log = LoggerFactory.getLogger(EndpointController.class);

	@ResponseBody
	@ExceptionHandler(Exception.class)
	public String exceptionhandler(Exception e) {
		log.info("error", e);
		return e.getMessage();
	}

	@PostMapping("/index")
	public String postText(HttpServletResponse response, @RequestBody String user) throws Exception {
		System.out.println(user);

		Users u = new ObjectMapper().readValue(user, Users.class);
		System.out.println(u.getPw());
		repo.save(u);
		String sessjwt = ts.createToken(u.getName());
		Cookie sesstoken = new Cookie("sesstoken", sessjwt);
		response.addCookie(sesstoken);
		return "asdhello";
	}

	@PostMapping("/getLogin")
	public String getLogin(HttpServletResponse response, @RequestBody String user,
			@CookieValue(name = "sesstoken", required = false, defaultValue = "") String tokencookie)
			throws JsonParseException, JsonMappingException, IOException {
		List<Users> usrs = (List<Users>) repo.findAll();
		Users u = new ObjectMapper().readValue(user, Users.class);
		for (Users users : usrs) {
			if (users.getName().equals(u.getName())) {
				if (users.getPw().equals(u.getPw())) {
					String sessjwt = ts.createToken(u.getName());
					Cookie sesstoken = new Cookie("sesstoken", sessjwt);
					response.addCookie(sesstoken);
					return "Success";
				}
			} else
				return "Wrong usermane";
		}
		return "Wrong password";
	}

	@PostMapping("/setRole")
	public String setRole(HttpServletResponse response, 
			@RequestBody String user,
			@CookieValue(name = "sesstoken", required = false, defaultValue = "") String tokencookie)
			throws JsonParseException, JsonMappingException, IOException {
		
		Role r = new ObjectMapper().readValue(user, Role.class);
		repo2.save(r);
		return "Success";
	}
}
