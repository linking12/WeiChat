package net.chat.service.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.chat.integration.service.InitThread;
import net.chat.integration.service.RespBuilder;
import net.chat.integration.vo.WXMessage;
import net.chat.integration.vo.WxCmd;
import net.chat.service.IntegrationService;
import net.chat.utils.DbUtil;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Service;

@Service("integrationService")
public class IntegrationServiceImpl implements IntegrationService,
		BeanFactoryPostProcessor {

	private DataSource dataSource;

	private String sql;
	private String content;
	public static HashMap<String, String> souces = new HashMap<String, String>();
	public static HashMap<String, String> games = new HashMap<String, String>();
	public static HashMap<String, String> acounts = new HashMap<String, String>();
	public static HashMap<String, String> urlseqs = new HashMap<String, String>();
	public static HashMap<String, WxCmd> autoreplay_cmd = new HashMap<String, WxCmd>();

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		action(request, response);
	}

	public void doProgram(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		WXMessage msg = (WXMessage) request.getAttribute("WX_MESSAGE");
		List<String> keyword = new ArrayList<String>();
		boolean isReg = false;
		String cnt = msg.getContent();
		String sql = "";
		String nickname = "";
		DbUtil db = new DbUtil(dataSource);
		if (cnt == null) {
			cnt = "";
		}
		cnt = cnt.trim();
		WxCmd cmd = IntegrationServiceImpl.autoreplay_cmd.get(msg.getReqUrl()
				+ cnt);
		IntegrationServiceImpl.logs(msg.getReqUrl() + cnt);
		IntegrationServiceImpl.logs(cmd + "");
		if (cnt.startsWith("激活") && cnt.length() > 2) {
			int i = 0;
			cnt = cnt.replaceAll(" ", "");
			sql = "update wx_account set programid='" + msg.getTouser()
					+ "' where name='" + cnt.substring(2) + "' and url='"
					+ msg.getReqUrl() + "' ";
			logs(sql);
			try {
				i = db.executeSql(sql);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				db.connClose();
			}
			if (i > 0) {
				out.println(RespBuilder.builderTextForReq(msg, cnt.substring(2)
						+ "激活成功！"));
			} else {
				out.println(RespBuilder.builderTextForReq(msg, cnt.substring(2)
						+ "激活失败！"));
			}

		} else if (msg != null && cmd != null) {
			String content = IntegrationServiceImpl.souces.get(cmd
					.getMessageid());
			IntegrationServiceImpl.logs("msg id" + cmd.getMessageid());
			content = RespBuilder.builderResp(msg, content);
			IntegrationServiceImpl.logs(content);
			out.println(content);

		} else {
			IntegrationServiceImpl.logs(cnt + "  is not set cmd ");
		}

	}

	public void doGet(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		String reqUrl = request.getRequestURI();
		String contextPath = request.getContextPath();
		reqUrl = StringUtils.substringAfter(reqUrl, contextPath);
		String codeString = validate(request, reqUrl);
		if (codeString != null) {
			resp.getWriter().print(codeString);
		}
	}

	private void action(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String reqUrl = request.getRequestURI();
		String contextPath = request.getContextPath();
		reqUrl = StringUtils.substringAfter(reqUrl, contextPath);	
		SAXBuilder sb = new SAXBuilder();
		Document doc = null;
		try {
			doc = sb.build(request.getInputStream());
		} catch (JDOMException e) {
			e.printStackTrace();
		}
		response.setContentType("text/xml; charset=utf8");
		Element root = doc.getRootElement();
		PrintWriter out = response.getWriter();

		WXMessage message = new WXMessage();
		if (doc != null) {
			String fromuser = root.getChild("FromUserName").getValue();
			message.setFromuser(fromuser);
			logs(fromuser);
			String touser = root.getChild("ToUserName").getValue();
			logs(touser);
			String type = root.getChild("MsgType").getValue();
			logs(type);
			message.setTouser(touser);
			message.setMsgtype(type);
			// 今日是否签到过
			sql = "";
			if ("event".equals(type)) {

				String etype = root.getChild("Event").getValue();
				logs(etype);
				if ("subscribe".equals(etype)) {

				}
				message.setEventkey(etype);

			} else if ("image".equals(type)) {
				String msgid = root.getChild("MsgId").getValue();
				String PicUrl = root.getChild("PicUrl").getValue();
				sql = "insert into wx_recevie_message_log (fromuser,touser,msgtype,msgid,PicUrl,dtime)values('";
				sql = sql + fromuser + "','";
				sql = sql + touser + "','";
				sql = sql + type + "','";
				sql = sql + msgid + "','";
				sql = sql + PicUrl + "',";
				sql = sql + "now())";
				message.setMsgid(msgid);
				message.setPicurl(PicUrl);

			} else if ("location".equals(type)) {
				String msgid = root.getChild("MsgId").getValue();
				String Location_X = root.getChild("Location_X").getValue();
				String Location_Y = root.getChild("Location_Y").getValue();
				String Scale = root.getChild("Scale").getValue();
				String Label = root.getChild("Label").getValue();

				sql = "insert into wx_recevie_message_log (fromuser,touser,msgtype,msgid,Location_X,Location_Y,Scale,Label,dtime)values('";
				sql = sql + fromuser + "','";
				sql = sql + touser + "','";
				sql = sql + type + "','";
				sql = sql + msgid + "','";
				sql = sql + Location_X + "','";
				sql = sql + Location_Y + "','";
				sql = sql + Scale + "','";
				sql = sql + Label + "',";
				sql = sql + "now())";
				message.setLocationx(Location_X);
				message.setLocationy(Location_Y);
				message.setLable(Label);
				message.setScale(Scale);
				message.setMsgid(msgid);
			} else if ("link".equals(type)) {
				String msgid = root.getChild("MsgId").getValue();
				// logs(msgid);
				String Title = root.getChild("Title").getValue();
				String Description = root.getChild("Description").getValue();
				String Url = root.getChild("Url").getValue();

				sql = "insert into wx_recevie_message_log (fromuser,touser,msgtype,msgid,Title,Description,Url,dtime)values('";
				sql = sql + fromuser + "','";
				sql = sql + touser + "','";
				sql = sql + type + "','";
				sql = sql + msgid + "','";
				sql = sql + Title + "','";
				sql = sql + Description + "','";
				sql = sql + Url + "',";
				sql = sql + "now())";
				message.setTitle(Title);
				message.setUrl(Url);
				message.setDescription(Description);
			} else if ("voice".equals(type)) {
				String msgid = root.getChild("MsgId").getValue();
				String MediaId = root.getChild("MediaId").getValue();
				String Recognition = root.getChild("Recognition").getValue();
				String Format = root.getChild("Format").getValue();

				sql = "insert into wx_recevie_message_log (fromuser,touser,msgtype,msgid,MediaId,Recognition,Format,dtime)values('";
				sql = sql + fromuser + "','";
				sql = sql + touser + "','";
				sql = sql + type + "','";
				sql = sql + msgid + "','";
				sql = sql + MediaId + "','";
				sql = sql + Recognition + "','";
				sql = sql + Format + "',";
				sql = sql + "now())";
				message.setMediaId(MediaId);
				message.setMsgid(msgid);
				message.setRecognition(Recognition);
				message.setFormat(Format);

			} else if ("video".equals(type)) {
				String msgid = root.getChild("MsgId").getValue();
				String MediaId = root.getChild("MediaId").getValue();
				String ThumbMediaId = root.getChild("ThumbMediaId").getValue();

				sql = "insert into wx_recevie_message_log (fromuser,touser,msgtype,msgid,mediaid,ThumbMediaId,dtime)values('";
				sql = sql + fromuser + "','";
				sql = sql + touser + "','";
				sql = sql + type + "','";
				sql = sql + msgid + "','";
				sql = sql + MediaId + "','";
				sql = sql + ThumbMediaId + "',";
				sql = sql + "now())";
				message.setMsgid(msgid);
				message.setMediaId(MediaId);
				message.setThumbMediaId(ThumbMediaId);

			} else if ("text".equals(type)) {
				String msgid = root.getChild("MsgId").getValue();
				content = root.getChild("Content").getValue();
				sql = "insert into wx_recevie_message_log (fromuser,touser,msgtype,msgid,content,dtime)values('";
				sql = sql + fromuser + "','";
				sql = sql + touser + "','";
				sql = sql + type + "','";
				sql = sql + msgid + "','";
				sql = sql + content + "',";
				sql = sql + "now())";
				message.setContent(content);
				message.setMsgid(msgid);
				logs(content);

			}
			if (!"".equals(sql)) {
				execut(sql);
			}
			String key = "";
			if ("event".equals(type)) {
				key = reqUrl + root.getChild("Event").getValue();
			} else {
				key = reqUrl + type;
			}
			message.setReqUrl(reqUrl);
			logs(key);

			if (IntegrationServiceImpl.acounts.containsKey(key)) {

				String msgid = IntegrationServiceImpl.acounts.get(key);

				// if (IntegrationServiceImpl.souces.containsKey(msgid)) {
				if (msgid.indexOf("program") == -1) {
					logs("direct msgid=" + msgid);
					String content = IntegrationServiceImpl.souces.get(msgid);
					content = content.replaceAll("from_user", fromuser);
					content = content.replaceAll("to_user", touser);

					out.print(content);
				} else {
					msgid = StringUtils.substringBefore(msgid, "_");
					logs("program id:" + msgid);
					String pathString = IntegrationServiceImpl.games.get(msgid);
					request.setAttribute("WX_MESSAGE", message);
					try {
						String p = "/program/" + "autoreply";
						logs("program path =" + p);
						request.getRequestDispatcher(p).include(request,
								response);
					} catch (ServletException e) {
						e.printStackTrace();
					}
				}
			} else {
				logs(key + "   No Setting！");
			}

		}
	}

	private String validate(HttpServletRequest request, String reqUrl) {

		// boolean flag = false;
		// String signature = request.getParameter("signature");
		// String timestamp = request.getParameter("timestamp");
		// String nonce = request.getParameter("nonce");
		String echostring = request.getParameter("echostr");
		System.out.println(echostring);
		return echostring;
		//
		// String token = IntegrationServiceImpl.urlseqs.get(reqUrl);//
		// "BZUVmaHfayRHDHJwXuYg";
		// IntegrationServiceImpl.logs("url=" + reqUrl);
		// IntegrationServiceImpl.logs("Token=" + token);
		// if (token != null) {
		// String[] strSet = new String[] { token, timestamp, nonce };
		// java.util.Arrays.sort(strSet);
		// String total = "";
		// for (String string : strSet) {
		// total = total + string;
		// }
		// // SHA-1加密实例
		// MessageDigest sha1 = null;
		// try {
		// sha1 = MessageDigest.getInstance("SHA-1");
		// } catch (NoSuchAlgorithmException e) {
		// e.printStackTrace();
		// }
		//
		// sha1.update(total.getBytes());
		// byte[] codedBytes = sha1.digest();
		// String codedString = new BigInteger(1, codedBytes).toString(16);
		// if (signature != null && signature.equals(codedString)) {
		// flag = true;
		// }
		// } else {
		// flag = false;
		// }
		// if (flag) {
		// if (echostring == null) {
		// echostring = "ok";
		// }
		// return echostring;
		// } else {
		// return null;
		// }
	}

	public void init() {

		Thread thread = new InitThread(dataSource);
		thread.start();

	}

	public void execut(String sql) {

		DbUtil db = new DbUtil(dataSource);

		try {
			db.excute(sql);
			db.commit();
		} catch (Exception e) {
			e.printStackTrace();
			logs("exception sql:" + sql);
		} finally {
			db.connClose();
		}
	}

	public static void logs(String str) {
		System.out.println(DateFormatUtils.format(new java.util.Date(),
				"yyyy-MM-dd HH:mm:ss") + "   " + str);
	}

	public void save(InputStream in) {
		FileOutputStream fos = null;
		int c = -1;
		try {
			fos = new FileOutputStream("out.xml");
			while ((c = in.read()) != -1) {
				fos.write(c);

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void postProcessBeanFactory(
			ConfigurableListableBeanFactory beanFactory) throws BeansException {
		DataSource obj = (DataSource) beanFactory.getBean("dataSource");
		this.dataSource = obj;
		init();

	}

}
