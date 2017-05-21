package com.iiquick.action;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.ActionSupport;

import com.iiquick.dataaccess.WordDao;
import com.iiquick.domain.Word;

/**
 * @version 20140731
 * @author kaungyeehein@gmil.com
 * @copyright (c) 2013 jpmmdictionary.iiquick.cloudbees.net
 * @license GPL
 */
public class GetWordListAction extends ActionSupport {

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
		System.out.println("==="+lstParameter.get("sby")+"==="+lstParameter.get("input")+"===");
		
		List<Word> lstWord = null;
		try{
		    WordDao dao = (WordDao) getWebApplicationContext().getBean("wordDao");
		    if (lstParameter.get("sby").equals("en")) {
		    	System.out.println("GetWordListActionBy:en");
		    	lstWord = dao.getEnword(lstParameter.get("input").toLowerCase());
		    }else if(lstParameter.get("sby").equals("jm")) {
		    	System.out.println("GetWordListActionBy:jm");
		    	lstWord = dao.getJmword(lstParameter.get("input"));
		    }else if(lstParameter.get("sby").equals("jk")) {
		    	System.out.println("GetWordListActionBy:jk");
		    	lstWord = dao.getJkword(lstParameter.get("input"));
		    }else if(lstParameter.get("sby").equals("mm")) {
		    	System.out.println("GetWordListActionBy:mm");
		    	lstWord = dao.getMmword(lstParameter.get("input"));
		    }else{
		    	System.err.println("GetWordListActionBy:Error");
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		String res = "";
		if(lstWord != null && lstWord.size()!=0){
			System.out.println("GetWordListActionBy:"+lstWord.size());
			for( Word s : lstWord){
				res += s+",";
			}
		}
		response.getWriter().write(res);
		return null;
	}	
}
