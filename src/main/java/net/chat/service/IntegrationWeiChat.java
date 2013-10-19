package net.chat.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IntegrationWeiChat {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException;

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException;
}
