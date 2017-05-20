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

import com.iiquick.dataaccess.TableDao;
import com.iiquick.dataaccess.WordDao;
import com.iiquick.domain.Result;
import com.iiquick.domain.Table;

/**
 * @version 20140806
 * @author kaungyeehein@gmil.com
 * @copyright (c) 2013 jpmmdictionary.iiquick.cloudbees.net
 * @license GPL
 */
public class InsertWordAction extends ActionSupport {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.err.println("InsertWordAction");

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");

		String code = request.getQueryString();
		//System.out.println(code);
		Map<String, String> lstParameter = new HashMap<String, String>();
		String url = URLDecoder.decode(code, "UTF-8");
		
		String resultReturn ="";
		//System.out.println(url);
		String[] parameters = url.split("&");
		for (String str : parameters) {
			String[] s = str.split("=", 2);
			if (s.length == 2){
				lstParameter.put(s[0], s[1]);
				//System.out.println(s[0]+":"+s[1]);
			}
		}

		String space = "^\\s+|\\s+$|^\\u3000+|\\u3000+$";
		char[] arrOperators = { ',', '၊', '、' };
	    String regex = "(" + new String(arrOperators).replaceAll("(.)", "\\\\$1|").replaceAll("\\|$", ")");
		String[] wordEn = null, wordJk = null, wordJm = null, wordMm = null;
		short wtid = 0;
		try {
			wtid = Short.parseShort(lstParameter.get("wtId"));
		} catch (NumberFormatException e) {
			System.err.println("wtId is not valid id");
		}

		List<Result> successResult = new ArrayList<Result>();
		List<Result> existResult = new ArrayList<Result>();
		Result insertResult = new Result();
		String insertReturn = "";
		if( wtid != 0) {
			if(!lstParameter.get("wordEn").isEmpty()) {
				wordEn = lstParameter.get("wordEn").split(regex);
			}
			if(!lstParameter.get("wordJk").isEmpty()) {
				wordJk = lstParameter.get("wordJk").split(regex);
			}
			if(!lstParameter.get("wordJm").isEmpty()) {
				wordJm = lstParameter.get("wordJm").split(regex);
			}
			if(!lstParameter.get("wordMm").isEmpty()) {
				wordMm = lstParameter.get("wordMm").split(regex);
			}
			
			/**
			 * New word insert logic
			 * IF [EN] not Empty
			 * 		[EN] --> [JK]
			 * 		[EN] --> [JM]
			 * 		[EN] --> [MM]
			 * IF [JK] not Empty
			 * 		[JK] --> [JM]
			 * 		[JK] --> [MM]
			 * IF [JM] not Empty
			 * 		[JM] --> [MM]
			 */
			if(wordEn != null && wordEn.length > 0) { // EN not Empty
				for(int en = 0; en < wordEn.length; en++) {
					wordEn[en] = wordEn[en].replaceAll(space, "");
					
					if(wordJk != null && wordJk.length > 0) { // JK not Empty
						for(int jk = 0; jk < wordJk.length; jk++) {
							wordJk[jk] = wordJk[jk].replaceAll(space, "");
							/*EN = JK*/
							insertResult = new Result();
							insertResult.setWord1(wordEn[en]);
							insertResult.setWord2(wordJk[jk]);
							System.out.println(insertResult.getWord1()+"="+insertResult.getWord2());
							insertReturn = insertTable("t5", wtid, insertResult.getWord1(), insertResult.getWord2());
							if(insertReturn.equals("success")) {
								successResult.add(insertResult);
							} else if (insertReturn.equals("exist")) {
								existResult.add(insertResult);
							}
						}
					}
					
					if(wordJm != null && wordJm.length > 0) { // JM not Empty
						for(int jm = 0; jm < wordJm.length; jm++) {
							wordJm[jm] = wordJm[jm].replaceAll(space, "");
							/*EN = JM*/
							insertResult = new Result();
							insertResult.setWord1(wordEn[en]);
							insertResult.setWord2(wordJm[jm]);
							System.out.println(insertResult.getWord1()+"="+insertResult.getWord2());
							insertReturn = insertTable("t3", wtid, insertResult.getWord1(), insertResult.getWord2());
							if(insertReturn.equals("success")) {
								successResult.add(insertResult);
							} else if (insertReturn.equals("exist")) {
								existResult.add(insertResult);
							}
						}
					}
					
					if(wordMm != null && wordMm.length > 0) { // MM not Empty
						for(int mm = 0; mm < wordMm.length; mm++) {
							wordMm[mm] = wordMm[mm].replaceAll(space, "");
							/*MM = EN*/
							insertResult = new Result();
							insertResult.setWord1(wordMm[mm]);
							insertResult.setWord2(wordEn[en]);
							System.out.println(insertResult.getWord1()+"="+insertResult.getWord2());
							insertReturn = insertTable("t1", wtid, insertResult.getWord1(), insertResult.getWord2());
							if(insertReturn.equals("success")) {
								successResult.add(insertResult);
							} else if (insertReturn.equals("exist")) {
								existResult.add(insertResult);
							}
						}
					}
				}
			}
			if(wordJk != null && wordJk.length > 0) { // JK not Empty
				for(int jk = 0; jk < wordJk.length; jk++) {
					wordJk[jk] = wordJk[jk].replaceAll(space, "");
					
					if(wordJm != null && wordJm.length > 0) { // JM not Empty
						for(int jm = 0; jm < wordJm.length; jm++) {
							wordJm[jm] = wordJm[jm].replaceAll(space, "");
							/*JM = JK*/
							insertResult = new Result();
							insertResult.setWord1(wordJm[jm]);
							insertResult.setWord2(wordJk[jk]);
							System.out.println(insertResult.getWord1()+"="+insertResult.getWord2());
							insertReturn = insertTable("t6", wtid, insertResult.getWord1(), insertResult.getWord2());
							if(insertReturn.equals("success")) {
								successResult.add(insertResult);
							} else if (insertReturn.equals("exist")) {
								existResult.add(insertResult);
							}
						}
					}
					
					if(wordMm != null && wordMm.length > 0) { // MM not Empty
						for(int mm = 0; mm < wordMm.length; mm++) {
							wordMm[mm] = wordMm[mm].replaceAll(space, "");
							/*MM = JK*/
							insertResult = new Result();
							insertResult.setWord1(wordMm[mm]);
							insertResult.setWord2(wordJk[jk]);
							System.out.println(insertResult.getWord1()+"="+insertResult.getWord2());
							insertReturn = insertTable("t4", wtid, insertResult.getWord1(), insertResult.getWord2());
							if(insertReturn.equals("success")) {
								successResult.add(insertResult);
							} else if (insertReturn.equals("exist")) {
								existResult.add(insertResult);
							}
						}
					}
				}			
			}
			
			if(wordJm != null && wordJm.length > 0) { // JM not Empty
				for(int jm = 0; jm < wordJm.length; jm++) {
					wordJm[jm] = wordJm[jm].replaceAll(space, "");
					
					if(wordMm != null && wordMm.length > 0) { // MM not Empty
						for(int mm = 0; mm < wordMm.length; mm++) {
							wordMm[mm] = wordMm[mm].replaceAll(space, "");
							/*MM = JM*/
							insertResult = new Result();
							insertResult.setWord1(wordMm[mm]);
							insertResult.setWord2(wordJm[jm]);
							System.out.println(insertResult.getWord1()+"="+insertResult.getWord2());
							insertReturn = insertTable("t2", wtid, insertResult.getWord1(), insertResult.getWord2());
							if(insertReturn.equals("success")) {
								successResult.add(insertResult);
							} else if (insertReturn.equals("exist")) {
								existResult.add(insertResult);
							}
						}
					}
				}
			}
		}
		
		///////////////////////////////////////////////////////////////////////////
		
		if(successResult != null && !successResult.isEmpty()) {
			resultReturn += "<div class='alert alert-success' role='alert'>ထည့်သွင်းမှု အောင်မြင်သည်<br/><br/><ul class='list-group'>";
			for(Result r : successResult) {
				resultReturn += "<li class='list-group-item'>" +  r.getWord1() + " = " + r.getWord2() + "</li>";
			}
			resultReturn += "</ul></div>";
			System.err.println("InsertWordAction:success");
		}
		if(existResult != null && !existResult.isEmpty()) {
			resultReturn += "<div class='alert alert-danger' role='alert'>ဝေါဟာရ ရှိပြီးသားဖြစ်သည်<br/><br/><ul class='list-group'>";
			for(Result r : existResult) {
				resultReturn += "<li class='list-group-item'>" +  r.getWord1() + " = " + r.getWord2() + " </li>";
			}
			resultReturn += "</ul></div>";
			System.err.println("InsertWordAction:success");
		}
		if((successResult == null && existResult == null) || (successResult.isEmpty() && existResult.isEmpty())) {
			resultReturn += "<div class='alert alert-danger' role='alert'>ထည့်သွင်းမှု မအောင်မြင်ပါ</div>";
			System.err.println("InsertWordAction:fail");
		}
		
		response.getWriter().write(resultReturn);
		return null;
	}
	
	synchronized public String insertTable(String t, short wtid, String w1, String w2){
		String res = "";
		Table table = new Table();
		
		if (t.equals("t1")) {
			System.out.println("Insert >>> t1");
			table.setWtid(wtid);
			table.setWord1id(insertMM(w1));
			table.setWord2id(insertEN(w2));
			TableDao tabledao = (TableDao) getWebApplicationContext().getBean("tableDao");
			try {
				if(table.getWord1id() != 0 && table.getWord2id() != 0) {
					if(tabledao.checkT1(table)){
						tabledao.insertT1(table);
						System.out.println("Insert to t1:success");
						res = "success";
					}else{
						System.err.println("Insert to t1:Data is already exists");
						res = "exist";
					}
				}else{
					System.err.println("Insert to t1:fail");
					res = "fail";
				}
			}catch (Exception e) {
				System.err.println("Insert to t1:fail");
				res = "fail";
			}
		} else if(t.equals("t2")) {
			System.out.println("Insert >>> t2");
			table.setWtid(wtid);
			table.setWord1id(insertMM(w1));
			table.setWord2id(insertJM(w2));
			TableDao tabledao = (TableDao) getWebApplicationContext().getBean("tableDao");
			try {
				if(table.getWord1id() != 0 && table.getWord2id() != 0) {
					if(tabledao.checkT2(table)){
						tabledao.insertT2(table);
						System.out.println("Insert to t2:success");
						res = "success";
					}else{
						System.err.println("Insert to t2:Data is already exists");
						res = "exist";
					}
				}else{
					System.err.println("Insert to t2:fail");
					res = "fail";
				}
			}catch (Exception e) {
				//e.printStackTrace();
				System.err.println("Insert to t2:fail");
				res = "fail";
			}
		} else if(t.equals("t3")) {
			System.out.println("Insert >>> t3");
			table.setWtid(wtid);
			table.setWord1id(insertEN(w1));
			table.setWord2id(insertJM(w2));
			TableDao tabledao = (TableDao) getWebApplicationContext().getBean("tableDao");
			try {
				if(table.getWord1id() != 0 && table.getWord2id() != 0) {
					if(tabledao.checkT3(table)){
						tabledao.insertT3(table);
						System.out.println("Insert to t3:success");
						res = "success";
					}else{
						System.err.println("Insert to t3:Data is already exists");
						res = "exist";
					}
				}else{
					System.err.println("Insert to t3:fail");
					res = "fail";
				}
			}catch (Exception e) {
				System.err.println("Insert to t3:fail");
				res = "fail";
			}
		}else if(t.equals("t4")) {
			System.out.println("Insert >>> t4");
			table.setWtid(wtid);
			table.setWord1id(insertMM(w1));
			table.setWord2id(insertJK(w2));
			TableDao tabledao = (TableDao) getWebApplicationContext().getBean("tableDao");
			try {
				if(table.getWord1id() != 0 && table.getWord2id() != 0) {
					if(tabledao.checkT4(table)){
						tabledao.insertT4(table);
						System.out.println("Insert to t4:success");
						res = "success";
					}else{
						System.err.println("Insert to t4:Data is already exists");
						res = "exist";
					}
				}else{
					System.err.println("Insert to t4:fail");
					res = "fail";
				}
			}catch (Exception e) {
				System.err.println("Insert to t4:fail");
				res = "fail";
			}
		}else if(t.equals("t5")) {
			System.out.println("Insert >>> t5");
			table.setWtid(wtid);
			table.setWord1id(insertEN(w1));
			table.setWord2id(insertJK(w2));
			TableDao tabledao = (TableDao) getWebApplicationContext().getBean("tableDao");
			try {
				if(table.getWord1id() != 0 && table.getWord2id() != 0) {
					if(tabledao.checkT5(table)){
						tabledao.insertT5(table);
						System.out.println("Insert to t5:success");
						res = "success";
					}else{
						System.err.println("Insert to t5:Data is already exists");
						res = "exist";
					}
				}else{
					System.err.println("Insert to t5:fail");
					res = "fail";
				}
			}catch (Exception e) {
				System.err.println("Insert to t5:fail");
				res = "fail";
			}
		}else if(t.equals("t6")) {
			System.out.println("Insert >>> t6");
			table.setWtid(wtid);
			table.setWord1id(insertJM(w1));
			table.setWord2id(insertJK(w2));
			TableDao tabledao = (TableDao) getWebApplicationContext().getBean("tableDao");
			try {
				if(table.getWord1id() != 0 && table.getWord2id() != 0) {
					if(tabledao.checkT6(table)){
						tabledao.insertT6(table);
						System.out.println("Insert to t6:success");
						res = "success";
					}else{
						System.err.println("Insert to t6:Data is already exists");
						res = "exist";
					}
				}else{
					System.err.println("Insert to t6:fail");
					res = "fail";
				}
			}catch (Exception e) {
				System.err.println("Insert to t6:fail");
				res = "fail";
			}
		}else{
			res="fail";
		}
		return res;
	}
	
	synchronized private int insertEN(String enword){
		Integer enid = null;
		WordDao worddao = (WordDao) getWebApplicationContext().getBean("wordDao");
		try {
			enid = worddao.getEnidByWord(enword);
			if (enid == null){
				worddao.insertEN(enword);
				enid = worddao.getEnidByWord(enword);
			}
		}catch(Exception e){
			e.printStackTrace();
			enid = 0;
			System.err.println("insertEN fail");
		}
		return enid;
	}
	
	synchronized private int insertJK(String jkword){
		Integer jkid = null;
		WordDao worddao = (WordDao) getWebApplicationContext().getBean("wordDao");
		try {
			jkid = worddao.getJkidByWord(jkword);
			if (jkid == null){
				worddao.insertJK(jkword);
				jkid = worddao.getJkidByWord(jkword);
			}
		}catch(Exception e){
			e.printStackTrace();
			jkid = 0;
			System.out.println("insertJK fail");
		}
		return jkid;
	}
	
	synchronized private int insertJM(String jmword){
		Integer jmid = null;
		WordDao worddao = (WordDao) getWebApplicationContext().getBean("wordDao");
		try {
			jmid = worddao.getJmidByWord(jmword);
			if (jmid == null){
				worddao.insertJM(jmword);
				jmid = worddao.getJmidByWord(jmword);
			}
		}catch(Exception e){
			e.printStackTrace();
			jmid = 0;
			System.out.println("insertJM fail");
		}
		return jmid;
	}
	
	synchronized private int insertMM(String mmword){
		Integer mmid = null;
		WordDao worddao = (WordDao) getWebApplicationContext().getBean("wordDao");
		try {
			mmid = worddao.getMmidByWord(mmword);
			if (mmid == null){
				worddao.insertMM(mmword);
				mmid = worddao.getMmidByWord(mmword);
			}
		}catch(Exception e){
			e.printStackTrace();
			mmid = 0;
			System.out.println("insertMM fail");
		}
		return mmid;
	}
}
