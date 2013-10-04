package net.chat.formbean;

import java.util.ArrayList;
import java.util.List;

import net.chat.domain.WxMessage;

public class MultimediaMessageForm {

	private WxMessage message;

	private List<Long> selectContents = new ArrayList<Long>();

	public WxMessage getMessage() {
		return message;
	}

	public void setMessage(WxMessage message) {
		this.message = message;
	}

	public List<Long> getSelectContents() {
		return selectContents;
	}

	public void setSelectContents(List<Long> selectContents) {
		this.selectContents = selectContents;
	}

}
