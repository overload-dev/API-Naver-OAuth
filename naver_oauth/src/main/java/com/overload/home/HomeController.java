package com.overload.home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.overload.application.Constants;
import com.overload.framework.base.BaseController;

@Controller
public class HomeController extends BaseController{

	@RequestMapping(value = Constants.ACT_HOME, method = RequestMethod.GET)
	public String goHome(HttpServletRequest request) {
		_log(request);
		return Constants.VIEW_HOME;
	}
}
