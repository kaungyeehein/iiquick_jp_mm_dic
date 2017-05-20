package com.iiquick.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.ActionSupport;


public class CheckUserAction extends ActionSupport {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		HttpSession session = request.getSession();
		SessionCounter counter = (SessionCounter) session.getAttribute("counter");
		if(counter != null){
			System.out.println("CheckUserAction:" + counter.getActiveSessionNumber());
			response.getWriter().write(""+counter.getActiveSessionNumber());
		}
		return null;
	}
	
}
