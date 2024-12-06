package com.dollop.app.utility;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dollop.app.handler.ResourceNotFound;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Component
public class SecurityUtil {
	@Autowired
  private	HttpServletRequest httpServletRequest;
	
	
	

private static String secret = "sdfsklfjsfjskfjsklfjsklfjsklfjskdfwiouweroiwurnkrwriouwruoirwepoeriwrwri";
	
	private static SecretKey getSecretKey() {
	        byte[] secretKeyBytes = secret.getBytes(StandardCharsets.UTF_8);
	        return Keys.hmacShaKeyFor(secretKeyBytes);
	}
	
	private String generateToken(Map<String, Object> claims, String subject,Integer time) {
		return Jwts.builder().claims(claims).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() +TimeUnit.MINUTES.toHours(time)))
				.issuer("PRAYAG")
				.signWith(getSecretKey())
				.subject(subject)
				.compact();
	}


	public String generateToken(String subject,Integer time,String type) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("type", type);
		claims.put("email", subject);
		return generateToken(claims, subject,time);
	}
	
	public String extractToken() {
		String header = httpServletRequest.getHeader("Authorization");
		if(!header.startsWith("Bearer "))
			throw new ResourceNotFound(Constants.INVALID_TOKEN);
			
		String token = header.substring(7);
		
		
		System.err.println(header);
		return token;
	}

	public String getUsername(String token) {
		return getClaims(token).getSubject();

	}
	private Claims getClaims(String token) {
		return Jwts.parser().verifyWith(getSecretKey()).build().parseSignedClaims(token).getPayload();
	}
    
	public String getTokenType() {
		String extractToken = extractToken();
		Claims claims = getClaims(extractToken);
		if(claims!=null && claims.containsKey("type")) {
			return (String) claims.get("type");
		}
		return null;
	}
	
	public String getemail() {
		String extractToken = extractToken();
		Claims claims = getClaims(extractToken);
		if(claims!=null && claims.containsKey("email")) {
			return (String) claims.get("email");
		}
		return null;
	}
  
}
