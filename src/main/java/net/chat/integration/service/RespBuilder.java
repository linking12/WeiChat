package net.chat.integration.service;

import java.util.Date;
import java.util.List;

import javax.servlet.jsp.JspWriter;

import net.chat.integration.vo.WXMessage;
import net.chat.integration.vo.WxContent;

public class RespBuilder {
	public static String buildText(String text) {
		String content = "";
		content = content + ("<xml>");

		content = content
				+ ("    <ToUserName><![CDATA[from_user]]></ToUserName>");

		content = content
				+ ("    <FromUserName><![CDATA[to_user]]></FromUserName>");

		content = content
				+ ("    <CreateTime>" + new Date().getTime() + "</CreateTime>");

		content = content + ("    <MsgType><![CDATA[text]]></MsgType>");

		content = content + ("    <Content><![CDATA[" + text + "]]></Content>");

		content = content + ("    <FuncFlag>0</FuncFlag>");

		content = content + ("</xml>");
		return content;
	}

	public static String builderTextForReq(WXMessage msg, String text) {

		String cnt = RespBuilder.buildText(text);

		cnt = cnt.replaceAll("from_user", msg.getFromuser());
		cnt = cnt.replaceAll("to_user", msg.getTouser());

		return cnt;
	}

	public static String builderResp(WXMessage msg, String text) {

		text = text.replaceAll("from_user", msg.getFromuser());
		text = text.replaceAll("to_user", msg.getTouser());

		return text;
	}

	public static String builderPicForReq(WXMessage msg, List<WxContent> cnts) {

		String cnt = RespBuilder.bulidPicText(cnts);

		cnt = cnt.replaceAll("from_user", msg.getFromuser());
		cnt = cnt.replaceAll("to_user", msg.getTouser());

		return cnt;
	}

	public static String buildMusic(String title, String discription,
			String musicurl, String hdurl) throws Exception {
		String content = "";
		content = content + ("<xml>");

		content = content
				+ ("    <ToUserName><![CDATA[from_user]]></ToUserName>");

		content = content
				+ ("    <FromUserName><![CDATA[to_user]]></FromUserName>");

		content = content
				+ ("    <CreateTime>" + new Date().getTime() + "</CreateTime>");

		content = content + ("    <MsgType><![CDATA[music]]></MsgType>");
		content = content + ("<Music>");
		content = content + ("<Title><![CDATA[" + title + "]]></Title> ");
		content = content
				+ ("<Description><![CDATA[" + discription + "]]></Description>");
		content = content
				+ ("<MusicUrl><![CDATA[" + musicurl + "]]></MusicUrl>");
		content = content
				+ ("<HQMusicUrl><![CDATA[" + hdurl + "]]></HQMusicUrl>");
		content = content + ("</Music>");

		content = content + ("    <FuncFlag>0</FuncFlag>");

		content = content + ("</xml>");
		return content;
	}

	public static String bulidPicText(List<WxContent> cnts) {
		String content = "";
		content = content + ("<xml>");
		content = content + ("<ToUserName><![CDATA[from_user]]></ToUserName>");
		content = content
				+ ("<FromUserName><![CDATA[to_user]]></FromUserName>");
		content = content
				+ (" <CreateTime>" + new Date().getTime() + "</CreateTime>");
		content = content + (" <MsgType><![CDATA[news]]></MsgType>");
		content = content
				+ (" <ArticleCount>" + cnts.size() + "</ArticleCount>");
		content = content + (" <Articles>");
		for (WxContent wx : cnts) {
			content = content + ("<item>");
			content = content
					+ ("<Title><![CDATA[" + wx.getTitle() + "]]></Title> ");
			content = content
					+ ("<Description><![CDATA[" + wx.getDescription() + "]]></Description>");
			content = content
					+ ("<PicUrl><![CDATA[http://yidia.cn/weixin/imgupload/"
							+ wx.getPicUrl() + "]]></PicUrl>");
			content = content + ("<Url><![CDATA[" + wx.getUrl() + "]]></Url>");
			content = content + (" </item>");
		}

		content = content + (" </Articles>");
		content = content + ("<FuncFlag>1</FuncFlag>");
		content = content + ("</xml>");
		return content;
	}

	public static void respSubText(JspWriter out, WXMessage msg, String content) {

	}
}
