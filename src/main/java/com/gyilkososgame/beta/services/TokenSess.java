package com.gyilkososgame.beta.services;

import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class TokenSess {
	private final String secret = "titok";

	public String createToken(String sessid) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String token = JWT.create()
			        .withSubject(sessid)
			        .sign(algorithm);
			return token;
		} catch (JWTCreationException exception) {
			// Invalid Signing configuration / Couldn't convert Claims.
		}return "Failed to create JWT";
	}
	public void verifyToken(String token) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256(secret);
		    JWTVerifier verifier = JWT.require(algorithm)
		        .withIssuer("auth0")
		        .build(); //Reusable verifier instance
		    DecodedJWT jwt = verifier.verify(token);
		} catch (JWTVerificationException exception){
		    //Invalid signature/claims
		}
	}
	public String decodeToken(String token) {
		try {
		    DecodedJWT jwt = JWT.decode(token);
			return jwt.getSubject();
		} catch (JWTDecodeException exception){
		    //Invalid token
		}return "Failed to decode JWT..";
	
	}
}