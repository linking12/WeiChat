package net.chat.service;

import java.util.List;

import net.chat.domain.WxAccount;
import net.chat.domain.WxMsgType;

import org.springframework.data.domain.Page;

public interface AccountService {

	public void saveAccount(WxAccount account);

	public void editAccount(WxAccount account);

	public void deleteAccount(Long accountId);

	public Page<WxAccount> listAllAcount(int pageNo, int pageSize);

	public WxAccount findAcountById(Long accountId);

	public Page<WxMsgType> queryAllMessageTypeInAccount(Long accountId,
			int pageNo, int pageSize);

	public List<WxAccount> findAll();
}
