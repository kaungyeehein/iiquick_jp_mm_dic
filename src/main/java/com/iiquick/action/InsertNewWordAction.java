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
 * @version 20140731
 * @author kaungyeehein@gmil.com
 * @copyright (c) 2013 jpmmdictionary.iiquick.cloudbees.net
 * @license GPL
 */
public class InsertNewWordAction extends ActionSupport {

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
		
		String resultReturn ="";
		System.out.println(url);
		String[] parameters = url.split("&");
		for (String str : parameters) {
			String[] s = str.split("=", 2);
			if (s.length == 2){
				lstParameter.put(s[0], s[1]);
				System.out.println(s[0]+":"+s[1]);
			}
		}
		
		char[] arrOperators = { ',', '၊', '、' };
	    String regex = "(" + new String(arrOperators).replaceAll("(.)", "\\\\$1|").replaceAll("\\|$", ")");
		String[] word1 = lstParameter.get("word1").split(regex);
		String[] word2 = lstParameter.get("word2").split(regex);
		String[] word3 = lstParameter.get("word3").split(regex);
		String table = lstParameter.get("table");
		short wtid = Short.parseShort(lstParameter.get("wtid"));
		// to get word1 list
		String space = "^\\s+|\\s+$|^\\u3000+|\\u3000+$";
		for(int i=0;i<word1.length;i++){
			word1[i] = word1[i].replaceAll(space, "");
			System.out.println("w1 output"+i+" : "+word1[i]);
		}
		// to get word2 list
		for(int i=0;i<word2.length;i++){
			word2[i] = word2[i].replaceAll(space, "");
			System.out.println("w2 output"+i+" : "+word2[i]);
		}
		// to get word3 list
		for(int i=0;i<word3.length;i++){
			word3[i] = word3[i].replaceAll(space, "");
			System.out.println("w3 output"+i+" : "+word3[i]);
		}
		int success = 0, exist = 0, fail = 0;
		List<Result> successResult = new ArrayList<Result>();
		List<Result> existResult = new ArrayList<Result>();
		if(table.equals("t7")){
			// loop for word1-word2, word1-word3
			for(int i=0;i<word1.length;i++){
				for(int j=0;j<word2.length;j++){
					Result result = new Result();
					table = "t2"; //mm-jm
					String s1 = insertTable(table, wtid, word1[i], word2[j]);
					if(s1.equals("success")){
						result.setWord1(word1[i]);
						result.setWord2(word2[j]);
						successResult.add(result);
						success++;
					}else if(s1.equals("exist")){
						result.setWord1(word1[i]);
						result.setWord2(word2[j]);
						existResult.add(result);
						exist++;
					}else{
						fail++;
					}
				}
				for(int j=0;j<word3.length;j++){
					Result result = new Result();
					table = "t4"; //mm-jk
					String s2 = insertTable(table, wtid, word1[i], word3[j]);
					if(s2.equals("success")){
						result.setWord1(word1[i]);
						result.setWord2(word3[j]);
						successResult.add(result);
						success++;
					}else if(s2.equals("exist")){
						result.setWord1(word1[i]);
						result.setWord2(word3[j]);
						existResult.add(result);
						exist++;
					}else{
						fail++;
					}
				}
			}
			//loop for word2-word3
			for(int i=0;i<word2.length;i++){
				for(int j=0;j<word3.length;j++){
					Result result = new Result();
					table = "t6"; //jm-jk
					String s = insertTable(table, wtid, word2[i], word3[j]);
					if(s.equals("success")){
						result.setWord1(word2[i]);
						result.setWord2(word3[j]);
						successResult.add(result);
						success++;
					}else if(s.equals("exist")){
						result.setWord1(word2[i]);
						result.setWord2(word3[j]);
						existResult.add(result);
						exist++;
					}else{
						fail++;
					}
				}
			}
		}else if(table.equals("t8")){
			// loop for word1-word2, word1-word3
			for(int i=0;i<word1.length;i++){
				for(int j=0;j<word2.length;j++){
					Result result = new Result();
					table = "t3"; //en-jm
					String s1 = insertTable(table, wtid, word1[i], word2[j]);
					if(s1.equals("success")){
						result.setWord1(word1[i]);
						result.setWord2(word2[j]);
						successResult.add(result);
						success++;
					}else if(s1.equals("exist")){
						result.setWord1(word1[i]);
						result.setWord2(word2[j]);
						existResult.add(result);
						exist++;
					}else{
						fail++;
					}
				}
				for(int j=0;j<word3.length;j++){
					Result result = new Result();
					table = "t5"; //en-jk
					String s2 = insertTable(table, wtid, word1[i], word3[j]);
					if(s2.equals("success")){
						result.setWord1(word1[i]);
						result.setWord2(word3[j]);
						successResult.add(result);
						success++;
					}else if(s2.equals("exist")){
						result.setWord1(word1[i]);
						result.setWord2(word3[j]);
						existResult.add(result);
						exist++;
					}else{
						fail++;
					}
				}
			}
			//loop for word2-word3
			for(int i=0;i<word2.length;i++){
				for(int j=0;j<word3.length;j++){
					Result result = new Result();
					table = "t6"; //jm-jk
					String s = insertTable(table, wtid, word2[i], word3[j]);
					if(s.equals("success")){
						result.setWord1(word2[i]);
						result.setWord2(word3[j]);
						successResult.add(result);
						success++;
					}else if(s.equals("exist")){
						result.setWord1(word2[i]);
						result.setWord2(word3[j]);
						existResult.add(result);
						exist++;
					}else{
						fail++;
					}
				}
			}
		}else{
			for(int i=0;i<word1.length;i++){
				for(int j=0;j<word2.length;j++){
					Result result = new Result();
					String s = insertTable(table, wtid, word1[i], word2[j]);
					if(s.equals("success")){
						result.setWord1(word1[i]);
						result.setWord2(word2[j]);
						successResult.add(result);
						success++;
					}else if(s.equals("exist")){
						result.setWord1(word1[i]);
						result.setWord2(word2[j]);
						existResult.add(result);
						exist++;
					}else{
						fail++;
					}
				}
			}
		}
		if(successResult != null && !successResult.isEmpty()) {
			resultReturn += "<div class='alert alert-success' role='alert'>ထည့်သွင်းမှု အောင်မြင်သည်<br/><br/><ul class='list-group'>";
			for(Result r : successResult) {
				resultReturn += "<li class='list-group-item'>" +  r.getWord1() + " = " + r.getWord2() + "</li>";
			}
			resultReturn += "</ul></div>";
		}
		if(existResult != null && !existResult.isEmpty()) {
			resultReturn += "<div class='alert alert-danger' role='alert'>ဝေါဟာရ ရှိပြီးသားဖြစ်သည်<br/><br/><ul class='list-group'>";
			for(Result r : existResult) {
				resultReturn += "<li class='list-group-item'>" +  r.getWord1() + " = " + r.getWord2() + " </li>";
			}
			resultReturn += "</ul></div>";
		}
		System.out.println("Success Count: "+success);
		System.out.println("Exist Count: "+exist);
		System.out.println("Fail Count: "+fail);
		System.out.println("Total Count: "+(success+exist+fail));
		response.getWriter().write(resultReturn);
		return null;
	}
	
	public String insertTable(String t, short wtid, String w1, String w2){
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
