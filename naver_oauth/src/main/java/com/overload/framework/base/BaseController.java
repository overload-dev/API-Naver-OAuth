package com.overload.framework.base;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseController {
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	protected void _log(HttpServletRequest request){
		if(log.isDebugEnabled()){
			log.debug("===============================================");
			log.info("=						=");
			log.debug("=		Controller Start		=");
			log.info("=						=");
			log.debug("===============================================");
			log.debug("class = " + this.getClass().toString());
			log.debug("reqMap = " + request.getMethod() + " : " + request.getRequestURI());
		}
		
	}
}
