package com.iiquick.action;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import javax.servlet.http.HttpSession;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class SessionCounter implements HttpSessionListener, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2335041117382573161L;
	private List<String> sessions = new ArrayList<String>();

	public SessionCounter() {
	}

	public void sessionCreated(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		sessions.add(session.getId());

		session.setAttribute("counter", this);
	}

	public void sessionDestroyed(HttpSessionEvent event) {
		HttpSession session = event.getSession();
		sessions.remove(session.getId());

		session.setAttribute("counter", this);
	}

	public int getActiveSessionNumber() {
		return sessions.size();
	}
}