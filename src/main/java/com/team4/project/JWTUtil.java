package com.team4.project;

public interface JWTUtil {
	boolean verifyToken(String jwt_token);
	Token createToken();
}
