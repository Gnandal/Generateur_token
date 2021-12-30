package com.gev.auth.token;

public class JwtProperties {
	
	public static final String SECRET = "gevdev@1234";
	public static final int EXPIRATION_TIME = 24*60*60;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
}
