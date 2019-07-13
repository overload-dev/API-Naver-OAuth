package com.overload.application;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ApplicationManager {

	private Logger log = LoggerFactory.getLogger(this.getClass());

	// server state log
	private String startTime = "";

	public void start() {
		SimpleDateFormat sd = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		startTime = sd.format(new Date());
		
		if (log.isInfoEnabled()) {
			log.info("====================================================");
			log.info("=	[Overload] API - Naver OAuth Program Start	=");
			log.info("====================================================");
			log.info("");
		}
	}

	public void stop() {

		if (log.isInfoEnabled()) {
			log.info("");
			log.info("====================================================");
			log.info("=	[Overload] API - Naver OAuth Program Stop	=");
			log.info("====================================================");
		}
	}

	public String getStartTime() {
		return startTime;
	}

}