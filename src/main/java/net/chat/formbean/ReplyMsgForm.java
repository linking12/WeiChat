package net.chat.formbean;

import java.util.List;

import net.chat.domain.WxMsgType;

public class ReplyMsgForm {

	private String accountId;
	
	private List<WxMsgType> msgTypes;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public List<WxMsgType> getMsgTypes() {
		return msgTypes;
	}

	public void setMsgTypes(List<WxMsgType> msgTypes) {
		this.msgTypes = msgTypes;
	}
	
	
}
