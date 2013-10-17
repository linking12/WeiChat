package net.chat.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.chat.service.IntegrationService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IntegrationWX {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	private IntegrationService integrationService;

	@RequestMapping("/api/{seq}")
	@ResponseBody
	public void saveContent(@PathVariable("seq") String id,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getMethod().equals("GET")) {
			try {
				integrationService.doGet(request, response);
			} catch (ServletException e) {
				logger.info("WeiXin post message into our platform failed", e);

			} catch (IOException e) {
				logger.info("WeiXin post message into our platform failed", e);
			}

		} else {
			try {
				integrationService.doPost(request, response);
			} catch (ServletException e) {
				logger.info("WeiXin post message into our platform failed", e);

			} catch (IOException e) {
				logger.info("WeiXin post message into our platform failed", e);
			}
		}

	}

	@RequestMapping("/API/{seq}")
	@ResponseBody
	public void saveContent1(@PathVariable("seq") String id,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getMethod().equals("GET")) {
			try {
				integrationService.doGet(request, response);
			} catch (ServletException e) {
				logger.info("WeiXin post message into our platform failed", e);

			} catch (IOException e) {
				logger.info("WeiXin post message into our platform failed", e);
			}

		} else {
			try {
				integrationService.doPost(request, response);
			} catch (ServletException e) {
				logger.info("WeiXin post message into our platform failed", e);

			} catch (IOException e) {
				logger.info("WeiXin post message into our platform failed", e);
			}
		}

	}

	@RequestMapping("/program/autoreply")
	public void autoReply(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			integrationService.doProgram(request, response);
		} catch (ServletException e) {
			logger.info("WeiXin post message into our platform failed", e);

		} catch (IOException e) {
			logger.info("WeiXin post message into our platform failed", e);
		}
	}

}
