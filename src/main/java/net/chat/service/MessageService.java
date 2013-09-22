package net.chat.service;

import net.chat.domain.WxMessage;

import org.springframework.data.domain.Page;

public interface MessageService {

	public Page<WxMessage> listALlMessageByAccountId(Long accountId,
			int pageNo, int pageSize);

	public void saveMessage(WxMessage message);

	public void editMessage(WxMessage message);

	public void delteMessage(Long messageId);

	public WxMessage findyMessageByMessageId(Long messageId);

}
