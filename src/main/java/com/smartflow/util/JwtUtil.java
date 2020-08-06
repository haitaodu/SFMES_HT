package com.smartflow.util;


import javax.crypto.SecretKey;

import com.smartflow.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;

public class JwtUtil {
	private static String SECRET = "TokenSecret";
	
	public static User parseToken(String token){
		try{
			Claims body = Jwts.parser().setSigningKey(SECRET)
							.parseClaimsJws(token).getBody();
			User user = new User();
			user.setId(Integer.parseInt(body.getSubject()));
			user.setUserName(body.get("userName").toString());
			user.setPassword(body.get("password").toString());
			return user;
		}catch(JwtException | ClassCastException e){
			return null;
		}
	}
	
	public static String generateToken(User user){
		Claims claims = Jwts.claims().setSubject(user.getId().toString());
		claims.put("userName", user.getUserName());
		claims.put("password", user.getPassword());
		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, SECRET).compact();
	}
	
	
	public static SecretKey getKey(){
		SecretKey key = MacProvider.generateKey(SignatureAlgorithm.HS512);
		return key;
	}
	
	public static void main(String[] args) {
		User user = new User();
		user.setId(21);
		user.setUserName("admin");
		user.setPassword("123456");
		System.out.println(user.toString());
		System.out.println(user.hashCode());
		String token = generateToken(user);
		System.out.println("Authorization : Bearer "+token);
		
		User u = parseToken(token);
		System.out.println(user.toString());
		System.out.println(user==u);
		System.out.println(user.equals(u));
	}
} 
