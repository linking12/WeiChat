package net.chat.constants;

import java.util.ArrayList;
import java.util.List;

import net.chat.domain.WxMsgType;

public class MessageTypeConstants {
	private static List<WxMsgType> messageTypeList = new ArrayList<WxMsgType>(5);

	static {
		messageTypeList.add(new WxMsgType(null, "text", null, null, "文本"));
		messageTypeList.add(new WxMsgType(null, "image", null, null, "图文"));
		messageTypeList.add(new WxMsgType(null, "voice", null, null, "声音"));
		messageTypeList.add(new WxMsgType(null, "video", null, null, "视频"));
	}

	public static List<WxMsgType> getMessageTypeList() {
		return messageTypeList;

	}
}
