package net.chat.util;

import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class HttpClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		// String content = "<xml>";
		// content = content + " <ToUserName><![CDATA[toUser]]></ToUserName>";
		// content = content
		// + " <FromUserName><![CDATA[fromUser]]></FromUserName>";
		// content = content + "<CreateTime>1348831860</CreateTime>";
		// content = content + "<MsgType><![CDATA[text]]></MsgType>";
		// content = content + "<Content><![CDATA[你好]]></Content>";
		// content = content + "<MsgId>1234567890123456</MsgId>";
		// content = content + "</xml>";
		// String content = "<xml>";
		// content = content + " <ToUserName><![CDATA[toUser]]></ToUserName>";
		// content = content
		// + " <FromUserName><![CDATA[fromUser]]></FromUserName>";
		// content = content + " <CreateTime>1348831860</CreateTime>";
		// content = content + " <MsgType><![CDATA[image]]></MsgType>";
		// content = content + " <PicUrl><![CDATA[this is a url]]></PicUrl>";
		// content = content + " <MsgId>1234567890123456</MsgId>";
		// content = content + " </xml>";

		// @SuppressWarnings("deprecation")
		// HttpClient httpclient = new DefaultHttpClient();
		// HttpPost httppost = new HttpPost(
		// "http://localhost:8080/WeiChat/API/JzfNgyGmEQKvqnaCBLNt");
		// StringEntity myEntity = new StringEntity(content, "UTF-8");
		// httppost.addHeader("Content-Type", "text/xml");
		// httppost.setEntity(myEntity);
		// HttpResponse response = httpclient.execute(httppost);
		// HttpEntity resEntity = response.getEntity();
		// InputStreamReader reader = new InputStreamReader(
		// resEntity.getContent(), "UTF-8");

		String content = "<xml>";
		content = content + " <ToUserName><![CDATA[toUser]]></ToUserName>";
		content = content
				+ " <FromUserName><![CDATA[fromUser]]></FromUserName>";
		content = content + "<CreateTime>1348831860</CreateTime>";
		content = content + "<MsgType><![CDATA[text]]></MsgType>";
		content = content + "<Content><![CDATA[你好]]></Content>";
		content = content + "<MsgId>1234567890123456</MsgId>";
		content = content + "</xml>";

		@SuppressWarnings("deprecation")
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(
				"http://localhost:8080/WeiChat/API/gzkUwLDfXADySiiCUvZZ");
		StringEntity myEntity = new StringEntity(content, "UTF-8");
		httppost.addHeader("Content-Type", "text/xml");
		httppost.setEntity(myEntity);
		HttpResponse response = httpclient.execute(httppost);
		HttpEntity resEntity = response.getEntity();
		InputStreamReader reader = new InputStreamReader(
				resEntity.getContent(), "ISO-8859-1");
		char[] buff = new char[1024];
		int length = 0;
		while ((length = reader.read(buff)) != -1) {
			System.out.println(new String(buff, 0, length));
		}
		httpclient.getConnectionManager().shutdown();

	}
}
