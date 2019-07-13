package com.overload.framework.frame;

import javax.servlet.http.HttpServletRequest;

import com.overload.framework.base.BaseService;

public abstract class FrameService extends BaseService {

	protected boolean _isLogin(HttpServletRequest request) {
		FrameModel frameModel = (FrameModel) request.getSession().getAttribute(FrameConstants.FRAMEMODEL_KEY);
		if (frameModel != null && frameModel.getSns_user_id() != null) {
			return false;
		} else {
			return true;
		}
	}

}
