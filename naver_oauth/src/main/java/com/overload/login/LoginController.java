package com.overload.login;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.omg.PortableInterceptor.ForwardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.overload.application.Constants;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;
	
	@RequestMapping(value = Constants.ACT_LOGIN, method =RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String goLoginPage(HttpServletRequest request, HttpSession session){
		//This is a method for adding another OAuth.
		return Constants.VIEW_LOGIN;
	}
	
	
	@RequestMapping(value = Constants.NAVER_OAUTH, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public RedirectView naver_oauth(HttpServletRequest request, HttpSession session) {
		return new RedirectView(loginService.getNaverOauth(request, session));
	}
	
	@RequestMapping(value = Constants.NAVER_CALLBACK, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public ModelAndView naver_callback(HttpServletRequest request, HttpSession session, RedirectAttributes redirectAttributes) throws IOException{
		
		return loginService.getNaverCallback(request, session, redirectAttributes);
	}
	
	@RequestMapping(value = Constants.NAVER_INFO, method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public String naver_info(HttpServletRequest request, HttpSession session) throws IOException{
		
		return loginService.getNaverInfo(request, session);
	}
	
	@RequestMapping(value = Constants.ACT_LOGOUT, method = RequestMethod.GET, produces = "text/plain;charset=UTF8")
	public ModelAndView naver_logout(HttpServletRequest request, HttpSession session){		
		return loginService.getNaverLogout(request);
	}
	
	@RequestMapping(value = Constants.ACT_DELETE, method=RequestMethod.GET, produces="text/plain;charset=UTF-8")
	public ModelAndView naver_userDel(HttpServletRequest request, HttpSession session){
		return loginService.getNaverUserDel(request, session);
	}
}
