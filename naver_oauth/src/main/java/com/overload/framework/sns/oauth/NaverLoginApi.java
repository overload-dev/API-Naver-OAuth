package com.overload.framework.sns.oauth;

import com.github.scribejava.core.builder.api.DefaultApi20;

public class NaverLoginApi extends DefaultApi20 {
	protected NaverLoginApi() {
	}

	private static class InstanceHolder {
		private static final NaverLoginApi INSTANCE = new NaverLoginApi();
	}

	public static NaverLoginApi instance() {
		return InstanceHolder.INSTANCE;
	}

	@Override
	public String getAccessTokenEndpoint() {
		// TODO Auto-generated method stub
		// 접근 토큰 발급 요청
		return "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code";
	}

	@Override
	protected String getAuthorizationBaseUrl() {
		// TODO Auto-generated method stub
		// 네아로 로그인 인증 요청
		return "https://nid.naver.com/oauth2.0/authorize";
	}

}
