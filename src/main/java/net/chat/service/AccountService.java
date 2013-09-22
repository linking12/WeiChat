package net.chat.service;

import java.util.List;

import net.chat.domain.WxAccount;
import net.chat.domain.WxMsgType;

public interface AccountService {

	public void saveAccount(WxAccount account);

	public void editAccount(WxAccount account);

	public void deleteAccount(Long accountId);

	public List<WxAccount> listAllAcount(int pageNo, int pageSize);

	public WxAccount findAcountById(Long accountId);

	public List<WxMsgType> queryAllMessageTypeInAccount(Long accountId,
			int pageNo, int pageSize);

}
