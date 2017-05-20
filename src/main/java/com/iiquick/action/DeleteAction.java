package com.iiquick.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.ActionSupport;

import com.iiquick.dataaccess.TableDao;

/**
 * @version 20140731
 * @author kaungyeehein@gmil.com
 * @copyright (c) 2013 jpmmdictionary.iiquick.cloudbees.net
 * @license GPL
 */
public class DeleteAction extends ActionSupport {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String table = request.getParameter("t");
		int id = Integer.parseInt(request.getParameter("id"));

		String result = "fail";
		 TableDao dao = (TableDao)getWebApplicationContext().getBean("tableDao");
		 try{
			 dao.deleteTable(table, id);
			 result = "success";
		 } catch (Exception e) {
			 e.printStackTrace();
			 result = "fail";
		 }
		if (result.equals("success")) {
			System.out.println("DeleteAction:success " +table+" "+id);
		} else {
			System.err.println("DeleteAction:fail");
		}
		response.getWriter().write(result);
		return null;
	}
}
