package net.chat.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/API")
public class IntegrationWX {
	@RequestMapping("/message")
	@ResponseBody
	public void saveContent(HttpServletRequest request,
			HttpServletResponse response) {

		/**
		 * <xml> 
		 * <ToUserName><![CDATA[toUser]]></ToUserName>
		 * <FromUserName><![CDATA[fromUser]]></FromUserName>
		 * <CreateTime>1348831860</CreateTime>
		 * <MsgType><![CDATA[text]]></MsgType> 
		 * <Content><![CDATA[this is atest]]></Content> 
		 * <MsgId>1234567890123456</MsgId> 
		 * </xml>
		 */
		SAXBuilder sb = new SAXBuilder();
		try {
			Document doc = sb.build(request.getInputStream());
			Element root = doc.getRootElement();
			String fromuser = root.getChild("FromUserName").getValue();

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
