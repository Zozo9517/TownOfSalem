package com.gyilkososgame.beta.Controller;

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
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyilkososgame.beta.Repositories.UserRepo;
import com.gyilkososgame.beta.entities.Users;
import com.gyilkososgame.beta.services.TokenSess;

@RestController
@CrossOrigin(allowCredentials = "true", origins = "http://localhost:4200")
public class EndpointController {
	@Autowired
	private UserRepo repo;
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
	public String postText(HttpServletResponse response,
			@RequestBody String user) throws Exception {
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
	public String getLogin(@RequestBody String name,
			@CookieValue(name = "sesstoken", required = false, defaultValue = "") String tokencookie) throws Exception {
		System.out.println(name);
		System.out.println(repo.findAll());
		System.out.println("eso" + repo.findByName(name));
		try {
		System.out.println("asdasdas" + repo.findByName(name).getName());
		return "Success";
		}
		catch(Exception e){
			return "Failed";	
		}
	}
}
