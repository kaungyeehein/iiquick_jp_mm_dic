package com.iiquick.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.ActionSupport;

import com.iiquick.dataaccess.AppDao;

/**
 * @version 20140731
 * @author kaungyeehein@gmil.com
 * @copyright (c) 2013 jpmmdictionary.iiquick.cloudbees.net
 * @license GPL
 */
public class GetSearchCountAction extends ActionSupport {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
				
		int searchCount = 0;
		try{
		    AppDao dao = (AppDao) getWebApplicationContext().getBean("appDao");
		    searchCount = dao.getSearchCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String res = "";
		if(searchCount > 0){
			int digit = String.valueOf(searchCount).length();
			for (int i=0; i<10-digit; i++) {
				res += "0";
			}
			res += String.valueOf(searchCount);
			System.out.println("GetSearchCountActionBy:" + res);
		}
		response.getWriter().write(res);
		return null;
	}	
}
