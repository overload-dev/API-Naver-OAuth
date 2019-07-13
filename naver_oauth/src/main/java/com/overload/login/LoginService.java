package com.overload.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.github.scribejava.core.model.OAuth2AccessToken;
import com.overload.application.Constants;
import com.overload.framework.frame.FrameConstants;
import com.overload.framework.frame.FrameModel;
import com.overload.framework.frame.FrameService;
import com.overload.framework.sns.oauth.NaverOAuth;

@Service
public class LoginService extends FrameService{
	
	@Autowired
	NaverOAuth naverOAuth;
	
	public String getNaverOauth(HttpServletRequest request, HttpSession session){
		
		String authorizationUrl;
		
		String state = UUID.randomUUID().toString();
		session.setAttribute("state", state);
		
		authorizationUrl = naverOAuth.getAuthorizationUrl(session);
		
		_logRequest(request);
		
		return authorizationUrl;
	}
	
	
	@SuppressWarnings("static-access")
	public ModelAndView getNaverCallback(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) throws IOException{
		ModelAndView mav = new ModelAndView();
		String view ="redirect:" + Constants.NAVER_INFO;
		
		Map<String, Object> model = new HashMap<String, Object>();
		
		naverOAuth.code = request.getParameter("code") == null ? "" : request.getParameter("code") ;
		naverOAuth.state = request.getParameter("state") == null ? "" : request.getParameter("state");
		
		OAuth2AccessToken accessToken = naverOAuth.getAccessToken(session);
		
		model.put("accessToken", accessToken);
		mav.setViewName(view);
		
		redirectAttributes.addFlashAttribute("model", model);
		
		_logRequest(request);
		
		return mav;
	}
	
	@SuppressWarnings({ "rawtypes", "static-access" })
	public String getNaverInfo(HttpServletRequest request, HttpSession session) throws IOException{
		String view = "redirect:" + Constants.ACT_HOME; //login before go to /home controller
		ModelAndView mav = new ModelAndView();
		
		Map setMap = RequestContextUtils.getInputFlashMap(request);
		
		Map modelMap = (Map)setMap.get("model");		
		OAuth2AccessToken accessToken = (OAuth2AccessToken) modelMap.get("accessToken");
		
		Map map = new HashMap();
		Map resultMap = new HashMap();
		
		map = naverOAuth.getUserInfo(accessToken);
		resultMap = (Map) map.get("response");
		
		naverOAuth.SNS_USER_ID = (String) resultMap.get("id");
		String sns_user_name = (String) resultMap.get("name");
		String sns_user_nickname = (String) resultMap.get("nickname");
		String sns_user_age = (String) resultMap.get("age");
		String sns_user_gender = (String) resultMap.get("gender");
		String sns_user_birthday = (String) resultMap.get("birthday");
		String sns_user_email = (String) resultMap.get("email");
		String sns_user_profile_image = (String) resultMap.get("profile_image");
		
		//---DB 등록 및 Model(bean)객체 지정
		FrameModel frameModel = new FrameModel();
		
		frameModel.setSns_user_id(naverOAuth.SNS_USER_ID);
		frameModel.setSns_user_nickname(sns_user_nickname);
		frameModel.setSns_user_name(sns_user_name);
		frameModel.setSns_user_age(sns_user_age);
		frameModel.setSns_user_gender(sns_user_gender);
		frameModel.setSns_user_birthday(sns_user_birthday);
		frameModel.setSns_user_email(sns_user_email);
		frameModel.setSns_user_profile_image(sns_user_profile_image);
		
		request.getSession().setAttribute(FrameConstants.FRAMEMODEL_KEY, frameModel);
		request.getSession().setAttribute(FrameConstants.RC_KEY, FrameConstants.RC_RESPONSE_OK);
		
		mav.setViewName(view);
		
		_logRequest(request);
		
		return view;
	}
	
	public ModelAndView getNaverLogout(HttpServletRequest request){
		String view = "redirect:/";
		ModelAndView mav = new ModelAndView();
		//naver login info session remove
		request.getSession().removeAttribute(FrameConstants.FRAMEMODEL_KEY);
		request.getSession().removeAttribute("state");
		mav.setViewName(view);
		
		_logRequest(request);
		
		return mav;
	}
	
	public ModelAndView getNaverUserDel(HttpServletRequest request, HttpSession session){
		String view = "redirect:" +Constants.ACT_HOME;
		ModelAndView mav = new ModelAndView();
		
		String userDelResult = naverOAuth.getUserDel();
		//naver login info session remove
		request.getSession().removeAttribute(FrameConstants.FRAMEMODEL_KEY);
		request.getSession().removeAttribute("state");
		System.out.println(userDelResult);
		
		mav.setViewName(view);
		
		_logRequest(request);
		
		return mav;
	}
}
