package com.iiquick.action;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.ActionSupport;

import com.iiquick.dataaccess.AppDao;
import com.iiquick.dataaccess.ENDao;
import com.iiquick.dataaccess.JKDao;
import com.iiquick.dataaccess.JMDao;
import com.iiquick.dataaccess.MMDao;
import com.iiquick.domain.Result;

/**
 * @version 20140731
 * @author kaungyeehein@gmil.com
 * @copyright (c) 2013 jpmmdictionary.iiquick.cloudbees.net
 * @license GPL
 */
public class SearchAction extends ActionSupport {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String code = request.getQueryString();
		System.out.println(code);
		Map<String, String> lstParameter = new HashMap<String, String>();
		String url = URLDecoder.decode(code, "UTF-8");
		String res ="";
		System.out.println(url);
		String[] parameters = url.split("&");
		for (String str : parameters) {
			String[] s = str.split("=", 2);
			if (s.length == 2)
				lstParameter.put(s[0], s[1]);
		}
		int t=0;
		List<Result> lstENResult = new ArrayList<Result>();
		List<Result> lstJKResult = new ArrayList<Result>();
		List<Result> lstJMResult = new ArrayList<Result>();
		List<Result> lstMMResult = new ArrayList<Result>();
		AppDao appDao = (AppDao) getWebApplicationContext().getBean("appDao");
		ENDao endao = (ENDao) getWebApplicationContext().getBean("enDao");
		JKDao jkdao = (JKDao) getWebApplicationContext().getBean("jkDao");
		JMDao jmdao = (JMDao) getWebApplicationContext().getBean("jmDao");
		MMDao mmdao = (MMDao) getWebApplicationContext().getBean("mmDao");
		String[] tvar = new String[3];
		try {
			appDao.increaseSearchCount();
			if (lstParameter.get("sby").equals("jm")) {
				System.out.println("Search by jm:");
				lstENResult = endao.getENByJmword(lstParameter.get("input"));
				if(!lstENResult.isEmpty()){
					tvar[t] = "t3";
					t++;
				}
				lstJKResult = jkdao.getJKByJmword(lstParameter.get("input"));
				if(!lstJKResult.isEmpty()){
					tvar[t] = "t6";
					t++;
				}
				lstMMResult = mmdao.getMMByJmword(lstParameter.get("input"));
				if(!lstMMResult.isEmpty()){
					tvar[t] = "t2";
					t++;
				}
			} else if (lstParameter.get("sby").equals("jk")) {
				System.out.println("Search by jk:");
				lstENResult = endao.getENByJkword(lstParameter.get("input"));
				if(!lstENResult.isEmpty()){
					tvar[t] = "t5";
					t++;
				}
				lstJMResult = jmdao.getJMByJkword(lstParameter.get("input"));
				if(!lstJMResult.isEmpty()){
					tvar[t] = "t6";
					t++;
				}
				lstMMResult = mmdao.getMMByJkword(lstParameter.get("input"));
				if(!lstMMResult.isEmpty()){
					tvar[t] = "t4";
					t++;
				}
			} else if (lstParameter.get("sby").equals("en")) {
				System.out.println("Search by en:");
				String tempEn = lstParameter.get("input").toLowerCase();
				lstJKResult = jkdao.getJKByEnword(tempEn);
				if(!lstJKResult.isEmpty()){
					tvar[t] = "t5";
					t++;
				}
				lstJMResult = jmdao.getJMByEnword(tempEn);
				if(!lstJMResult.isEmpty()){
					tvar[t] = "t3";
					t++;
				}
				lstMMResult = mmdao.getMMByEnword(tempEn);
				if(!lstMMResult.isEmpty()){
					tvar[t] = "t1";
					t++;
				}
			} else if (lstParameter.get("sby").equals("mm")) {
				System.out.println("Search by mm:");
				lstENResult = endao.getENByMmword(lstParameter.get("input"));
				if(!lstENResult.isEmpty()){
					tvar[t] = "t1";
					t++;
				}
				lstJKResult = jkdao.getJKByMmword(lstParameter.get("input"));
				if(!lstJKResult.isEmpty()){
					tvar[t] = "t4";
					t++;
				}
				lstJMResult = jmdao.getJMByMmword(lstParameter.get("input"));
				if(!lstJMResult.isEmpty()){
					tvar[t] = "t2";
					t++;
				}
			}
		} catch (Exception e) {
			//e.printStackTrace();
			System.err.println("fail");
			res = "fail";
			response.getWriter().write(res);
			return null;
		}

		res+="<div class='panel panel-default'><div class='panel-heading'><span class='text-primary'>\u00A0 " + lstParameter.get("input") + " </span>၏အဓိပ္ပါယ်မှာ -</div><div class='panel-body'><div class='row'>";
		t=0;
		
		/*Table width logic*/
		int tmpWidth = 4;
		String colWidth = "";
		if(lstENResult.isEmpty()){
		    tmpWidth--;
		}
		if(lstJKResult.isEmpty()){
		    tmpWidth--;
		}
		if(lstJMResult.isEmpty()){
		    tmpWidth--;
		}
		if(lstMMResult.isEmpty()){
		    tmpWidth--;
		}
		if(tmpWidth == 3) {
		    colWidth = "col-xs-12 col-sm-12 col-md-4 col-lg-4";
		} else if(tmpWidth == 2) {
		    colWidth = "col-xs-12 col-sm-6 col-md-6 col-lg-6";
		} else {
		    colWidth = "col-xs-12 col-sm-12 col-md-12 col-lg-12";
		}
		
		if(!lstENResult.isEmpty()){
			res+="<div class='"+colWidth+"'><div class='panel panel-default'><div class='panel-heading'>အင်္ဂလိပ်ဘာသာ</div><table class='table table-striped table-hover table-bordered'><tbody>";
			for(int i=0; i<lstENResult.size();i++){
			    	res+="<tr><td class='col-xs-1 text-center'><span class='label label-success'>"+lstENResult.get(i).getWtype()+"</span></td><td class='col-xs-11'>"+lstENResult.get(i).getWord2()+"</td>"+
					"<td onclick='javascript:editDialog(\""+lstENResult.get(i).getWord2()+"\",\""+lstENResult.get(i).getId()+"\",\""+tvar[t]+"\",\""+lstENResult.get(i).getCorrect()+"\",\""+lstENResult.get(i).getIncorrect()+
					"\")' class='wordEdit text-primary'><span class='glyphicon glyphicon-pencil'></span><span class='sr-only'>Edit</span></td></tr>";
			}
			res+="</tbody>	</table></div></div>";
			t++;
		} 
		if(!lstJKResult.isEmpty()){
		    	res+="<div class='"+colWidth+"'><div class='panel panel-default'><div class='panel-heading'>ဂျပန်ဘာသာ (ခန်းကြီး)</div><table class='table table-striped table-hover table-bordered'><tbody>";
			for(int i=0; i<lstJKResult.size();i++){
			    	res+="<tr><td class='col-xs-1 text-center'><span class='label label-success'>"+lstJKResult.get(i).getWtype()+"</span></td><td class='col-xs-11'>"+lstJKResult.get(i).getWord2()+"</td>"+
					"<td onclick='javascript:editDialog(\""+lstJKResult.get(i).getWord2()+"\",\""+lstJKResult.get(i).getId()+"\",\""+tvar[t]+"\",\""+lstJKResult.get(i).getCorrect()+"\",\""+lstJKResult.get(i).getIncorrect()+
					"\")' class='wordEdit text-primary'><span class='glyphicon glyphicon-pencil'></span><span class='sr-only'>Edit</span></td></tr>";
			}
			res+="</tbody>	</table></div></div>";
			t++;
		} 
		if(!lstJMResult.isEmpty()){
		    	res+="<div class='"+colWidth+"'><div class='panel panel-default'><div class='panel-heading'>ဂျပန်ဘာသာ</div><table class='table table-striped table-hover table-bordered'><tbody>";
			for(int i=0; i<lstJMResult.size();i++){
			    	res+="<tr><td class='col-xs-1 text-center'><span class='label label-success'>"+lstJMResult.get(i).getWtype()+"</span></td><td class='col-xs-11'>"+lstJMResult.get(i).getWord2()+" ("+lstJMResult.get(i).getWord3()+")</td>"+
			    		"<td onclick='javascript:editDialog(\""+lstJMResult.get(i).getWord2()+"\",\""+lstJMResult.get(i).getId()+"\",\""+tvar[t]+"\",\""+lstJMResult.get(i).getCorrect()+"\",\""+lstJMResult.get(i).getIncorrect()+
					"\")' class='wordEdit text-primary'><span class='glyphicon glyphicon-pencil'></span><span class='sr-only'>Edit</span></td></tr>";
			}
			res+="</tbody>	</table></div></div>";
			t++;
		} 
		if(!lstMMResult.isEmpty()){
		    	res+="<div class='"+colWidth+"'><div class='panel panel-default'><div class='panel-heading'>မြန်မာဘာသာ</div><table class='table table-striped table-hover table-bordered'><tbody>";
			for(int i=0; i<lstMMResult.size();i++){
			    	res+="<tr><td class='col-xs-1 text-center'><span class='label label-success'>"+lstMMResult.get(i).getWtype()+"</span></td><td class='col-xs-11'>"+lstMMResult.get(i).getWord2()+"</td>"+
			    		"<td onclick='javascript:editDialog(\""+lstMMResult.get(i).getWord2()+"\",\""+lstMMResult.get(i).getId()+"\",\""+tvar[t]+"\",\""+lstMMResult.get(i).getCorrect()+"\",\""+lstMMResult.get(i).getIncorrect()+
					"\")' class='wordEdit text-primary'><span class='glyphicon glyphicon-pencil'></span><span class='sr-only'>Edit</span></td></tr>";
			}
			res+="</tbody>	</table></div></div>";
			t++;
		} 
		if(lstENResult.isEmpty() && lstJKResult.isEmpty() && lstJMResult.isEmpty() && lstMMResult.isEmpty()){
			res+="<p class='text-center'>အဘိဓာန်ထဲတွင် ရှာမတွေ့ပါ။</p>";
			System.out.println("Not found");
		}
		res+="</div></div></div>";
		response.getWriter().write(res);
		return null;
	}
}