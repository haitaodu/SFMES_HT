package com.smartflow.util;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

public class MySessionContext {
//	public static MySessionContext instance;
	private static HashMap<String, Object> sessionMap = new HashMap<String, Object>();
	
	private static HashMap<String, HttpSession> userMap = new HashMap<String, HttpSession>();
//	public static MySessionContext getInstance() {
//		if(instance!=null){
//			instance = new MySessionContext();
//		}
//		return instance;
//	}
	
	public static synchronized void addSessionAndUser(HttpSession session,String userName){
		sessionMap.put(session.getId(), userName);
		userMap.put(userName, session);
	}

	public static synchronized void delUserBySessionId(String sessionId){
			//当前session销毁时删除当前session绑定的用户信息
			//同时删除当前session绑定的HttpSession
			userMap.remove(sessionMap.remove(sessionId));
	}
	
	public static synchronized void delSessionByUserName(String userName){
		//当前session销毁时删除当前session绑定的用户信息
		//同时删除当前session绑定的HttpSession
		sessionMap.remove(userMap.remove(userName).getId());
	}
	
	public static synchronized HttpSession getSession(String sessionId){
		if(sessionId == null){
			return null;
		}
		return (HttpSession) sessionMap.get(sessionId);
	}
	
	public static synchronized void repeatLoginHandle(String userName){
		//删除当前登录用户已绑定的HttpSession
		HttpSession session = userMap.remove(userName);
		if(session != null){
			//删除已登录的sessionId绑定的用户
			sessionMap.remove(session.getId());
		}
	}
	
	public static synchronized HttpSession getSessionByUserName(String userName){
		if(userMap.containsKey(userName)){//该用户登录过
			return userMap.get(userName);
		}
		return null;
	}
	
}
