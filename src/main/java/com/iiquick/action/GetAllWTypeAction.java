package com.iiquick.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.ActionSupport;

import com.iiquick.dataaccess.WTypeDao;
import com.iiquick.dataaccess.WordDao;
import com.iiquick.domain.WType;

/**
 * @version 20140731
 * @author kaungyeehein@gmil.com
 * @copyright (c) 2013 jpmmdictionary.iiquick.cloudbees.net
 * @license GPL
 */
public class GetAllWTypeAction extends ActionSupport {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
	    List<WType> lstWType = null;
	    int countEn = 0, countJk = 0, countJm = 0, countMm = 0;
		try{
		    WTypeDao wtDao = (WTypeDao) getWebApplicationContext().getBean("wTypeDao");
		    WordDao wordDao = (WordDao) getWebApplicationContext().getBean("wordDao");
			lstWType = wtDao.getAllWType();
			countEn = wordDao.countEn();
			countJk = wordDao.countJk();
			countJm = wordDao.countJm();
			countMm = wordDao.countMm();
		} catch (Exception e) {
			//e.printStackTrace();
			lstWType = null;
		}
		if(lstWType != null){
			request.setAttribute("lstWType", lstWType);
			request.setAttribute("countEn", countEn);
			request.setAttribute("countJk", countJk);
			request.setAttribute("countJm", countJm);
			request.setAttribute("countMm", countMm);
			System.out.println("GetAllWTypeAction:success "+lstWType.size());
			return (mapping.findForward("success"));
		}else{
			System.err.println("GetAllWTypeAction:fail");
			return (mapping.findForward("fail"));
		}
	}
}
