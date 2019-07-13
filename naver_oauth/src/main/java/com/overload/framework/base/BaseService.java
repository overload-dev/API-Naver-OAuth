package com.overload.framework.base;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseService {
	
	protected Logger log = LoggerFactory.getLogger(this.getClass());
	protected boolean isUsingLog = true; 
	
	protected void _logRequest(HttpServletRequest request){
		
		//log.info("["+form.getIfCount()+"]["+form.getLogID()+"][REQ][" +request.getRequestURI() + "?"+request.getQueryString()+"]["+request.getRemoteAddr()+"]");
		
		if(request.getQueryString() != null){
			log.info("[REQ][" +request.getRequestURI() + "?"+request.getQueryString()+"]["+request.getRemoteAddr()+"]");
		}else{
			log.info("[REQ][" +request.getRequestURI() + "?"+request.getQueryString()+"]["+request.getRemoteAddr()+"]");
		}
			
	}
	
	protected void _setUsingLog(boolean using){
		this.isUsingLog = using;
	}
}
