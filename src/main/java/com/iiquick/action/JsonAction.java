package com.iiquick.action;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.ActionSupport;

import com.google.gson.Gson;
import com.iiquick.dataaccess.TableDao;
import com.iiquick.dataaccess.WTypeDao;
import com.iiquick.dataaccess.WordDao;
import com.iiquick.domain.Table;
import com.iiquick.domain.WType;
import com.iiquick.domain.Word;

public class JsonAction extends ActionSupport {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String code = request.getQueryString();
		Map<String, String> lstParameter = new HashMap<String, String>();
		String url = URLDecoder.decode(code, "UTF-8");
		String[] parameters = url.split("&");
		for (String str : parameters) {
			String[] s = str.split("=", 2);
			if (s.length == 2)
				lstParameter.put(s[0], s[1]);
		}
		
		String responseJson = "";
		List<Word> lstWord = null;
		List<WType> lstWType = null;
		List<Table> lstTable = null;
		try{
		    WordDao wDao = (WordDao) getWebApplicationContext().getBean("wordDao");
		    WTypeDao wtDao = (WTypeDao) getWebApplicationContext().getBean("wTypeDao");
		    TableDao tableDao = (TableDao) getWebApplicationContext().getBean("tableDao");
		    Gson gson = new Gson();
			if(lstParameter.get("q").equals("en")) {
				lstWord = wDao.getAllEnword();
				if(lstWord != null && !lstWord.isEmpty()) {
					responseJson = gson.toJson(lstWord);
				}
			}else if(lstParameter.get("q").equals("jk")) {
				lstWord = wDao.getAllJkword();
				if(lstWord != null && !lstWord.isEmpty()) {
					responseJson = gson.toJson(lstWord);
				}
			}else if(lstParameter.get("q").equals("jm")) {
				lstWord = wDao.getAllJmword();
				if(lstWord != null && !lstWord.isEmpty()) {
					responseJson = gson.toJson(lstWord);
				}
			}else if(lstParameter.get("q").equals("mm")) {
				lstWord = wDao.getAllMmword();
				if(lstWord != null && !lstWord.isEmpty()) {
					responseJson = gson.toJson(lstWord);
				}
			}else if(lstParameter.get("q").equals("wt")) {
				lstWType = wtDao.getAllWType();
				if(lstWType != null && !lstWType.isEmpty()) {
					responseJson = gson.toJson(lstWType);
				}
			}else if(lstParameter.get("q").equals("t1")) {
				lstTable = tableDao.getAllT1();
				if(lstTable != null && !lstTable.isEmpty()) {
					responseJson = gson.toJson(lstTable);
				}
			}else if(lstParameter.get("q").equals("t2")) {
				lstTable = tableDao.getAllT2();
				if(lstTable != null && !lstTable.isEmpty()) {
					responseJson = gson.toJson(lstTable);
				}
			}else if(lstParameter.get("q").equals("t3")) {
				lstTable = tableDao.getAllT3();
				if(lstTable != null && !lstTable.isEmpty()) {
					responseJson = gson.toJson(lstTable);
				}
			}else if(lstParameter.get("q").equals("t4")) {
				lstTable = tableDao.getAllT4();
				if(lstTable != null && !lstTable.isEmpty()) {
					responseJson = gson.toJson(lstTable);
				}
			}else if(lstParameter.get("q").equals("t5")) {
				lstTable = tableDao.getAllT5();
				if(lstTable != null && !lstTable.isEmpty()) {
					responseJson = gson.toJson(lstTable);
				}
			}else if(lstParameter.get("q").equals("t6")) {
				lstTable = tableDao.getAllT6();
				if(lstTable != null && !lstTable.isEmpty()) {
					responseJson = gson.toJson(lstTable);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(new Date()+" [q="+lstParameter.get("q")+"] "+responseJson);
		response.setContentType("application/json");
		response.getWriter().write(responseJson);
		return null;
	}
	
}
