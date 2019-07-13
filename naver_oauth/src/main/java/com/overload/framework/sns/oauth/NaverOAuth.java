package com.overload.framework.sns.oauth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.overload.framework.util.Util;

@Service
public class NaverOAuth {
	
	public static String SNS_USER_ID = "";
	public static String state = "";
	public static String code ="";
	public static String access_token ="";
	
	public String getAuthorizationUrl(HttpSession session){
		
		state = generateRandomState();
		setSession(session, state);
		
		String authorizationUrl;
		
		OAuth20Service oauthService = new ServiceBuilder()
				.apiKey(SnsConstants.NAVER_CLIENT_ID)
				.apiSecret(SnsConstants.NAVER_CLIENT_SECRET)
				.callback(SnsConstants.NAVER_CALLBACK_URL)
				.state(state)
				.build(NaverLoginApi.instance());
		
		authorizationUrl = oauthService.getAuthorizationUrl();
		
		return authorizationUrl;
	}
	
	public OAuth2AccessToken getAccessToken(HttpSession session) throws IOException{
		
		OAuth20Service oauthService = new ServiceBuilder()
				.apiKey(SnsConstants.NAVER_CLIENT_ID)
				.apiSecret(SnsConstants.NAVER_CLIENT_SECRET)
				.callback(SnsConstants.NAVER_CALLBACK_URL)
				.state(state)
				.build(NaverLoginApi.instance());
		
		//Scribe라이브러리의  AccessToken 기능을 통해 Access Token 요청
		OAuth2AccessToken accessToken = oauthService.getAccessToken(code);
		access_token = accessToken.getAccessToken();
		
		return accessToken;
	}
	
	@SuppressWarnings("rawtypes")
	public Map getUserInfo(OAuth2AccessToken accessToken) throws IOException{
		
		OAuth20Service oauthService = new ServiceBuilder()
				.apiKey(SnsConstants.NAVER_CLIENT_ID)
				.apiSecret(SnsConstants.NAVER_CLIENT_SECRET)
				.callback(SnsConstants.NAVER_CALLBACK_URL)
				.state(state)
				.build(NaverLoginApi.instance());
		
		//사용자 정보 요청 -----
		OAuthRequest oRequest = new OAuthRequest(Verb.GET, SnsConstants.NAVER_PROFILE_API_URL, oauthService);
		oauthService.signRequest(accessToken, oRequest);
		Response response = oRequest.send();
		String responseBody = response.getBody();
		Map map = new HashMap();
		map = Util.castMap(responseBody);
		
		return map;
	}
	
	public String getUserDel(){
		String userDelUrl = SnsConstants.NAVER_USER_DELETE_URL + "?" +
							"&client_id=" + SnsConstants.NAVER_CLIENT_ID +
							"&client_secret=" + SnsConstants.NAVER_CLIENT_SECRET +
							"&access_token=" + access_token + 
							"&grant_type=delete" +
							"&service_provider=NAVER";
		System.out.println(userDelUrl);
		try{
			URL url = new URL(userDelUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET"); //request method=GET
			conn.setConnectTimeout(3000); //connecting timeout 3c
			conn.setReadTimeout(3000); // read timeout 3s
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(),"UTF-8"));
			
			String inputLine;
			StringBuffer sb = new StringBuffer();
			while((inputLine = br.readLine()) != null){
				sb.append(inputLine + "\n");
			}

			br.close();
			
			String data = sb.toString();

			return data;
			
		}catch(IOException e){
			return null;
		}
		
	}
	
	private String generateRandomState(){
		return UUID.randomUUID().toString();
	}
	
	//�꽭�뀡 ���옣
	private void setSession(HttpSession session, String state){
		session.setAttribute(SnsConstants.SESSION_STATE, state);
	}
}
