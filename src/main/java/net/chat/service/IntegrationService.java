package net.chat.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface IntegrationService {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException;

	public void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException;
}
