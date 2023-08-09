package com.team4.project;

public interface JWTUtil {
	public boolean verifyToken(String jwt_token);
	public Token createToken();
}
