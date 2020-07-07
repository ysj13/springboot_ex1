package net.boot.web;

import javax.servlet.http.HttpSession;

import net.boot.domain.User;

public class HttpSessionUtils {
	public static final String USER_SESSION_KEY = "sessionedUser";
	
	public static boolean isLoginUser(HttpSession httpSession) {
		Object sessionedUser = httpSession.getAttribute(USER_SESSION_KEY);
		if(sessionedUser == null) {
			return false;
		}
		return true;
	}
	
	public static User getUserFromSession(HttpSession httpSession) {
		if(!isLoginUser(httpSession)) {
			return null; 
		}
		
		return (User)httpSession.getAttribute(USER_SESSION_KEY);
	}
}
