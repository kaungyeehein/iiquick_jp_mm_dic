package com.iiquick.boot;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.TimerTask;

public class ScheduledTask extends TimerTask {
	
	public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss z");

	@Override
	public void run() {
		reportCurrentTime();
	}
	
	public void reportCurrentTime() {
		String url = "http://iiquick-boot.jpmmdictionary.cloudbees.net/GetOnline.do";
		dateFormat.setTimeZone(TimeZone.getTimeZone("GMT+0630"));		
		System.out.println("[" + dateFormat.format(new Date()) + "][" + url + "][" + sendPostRequest(url) + "]" );
	}
	
	public static int sendPostRequest(String url) {

	    int str = 0;

	    try {

	        URL requestUrl = new URL(url);
	        HttpURLConnection conn = (HttpURLConnection) requestUrl
	                .openConnection();
	        conn.setDoOutput(true);
	        conn.setRequestMethod("GET");
	        
	        str = conn.getResponseCode();

	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    } catch (MalformedURLException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return str;
	}
	
}
