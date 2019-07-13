package com.overload.framework.sns.oauth;

public class SnsConstants {
	
	public static final String SESSION_STATE = "oauth_state";
	
	//Naver login api
	public static final String NAVER_CLIENT_ID = "Your Client ID";
	public static final String NAVER_CLIENT_SECRET = "Youre Secret Key";
	
	//local
	public static final String NAVER_CALLBACK_URL = "http://localhost:8081/login/naver_callback";
	public static final String NAVER_SERVICE_URL = "http://localhost:8081/home";
	
	//user info
	public final static String NAVER_PROFILE_API_URL = "https://openapi.naver.com/v1/nid/me";
	
	//user del
	public final static String NAVER_USER_DELETE_URL = "https://nid.naver.com/oauth2.0/token";
}
