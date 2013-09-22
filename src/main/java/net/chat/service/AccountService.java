package net.chat.service;

import net.chat.domain.WxAccount;
import net.chat.domain.WxMsgType;

import org.springframework.data.domain.Page;

public interface AccountService {

	/**
	 * 保存公共账户信息
	 * 
	 * @param account
	 */
	public void saveAccount(WxAccount account);

	/**
	 * 编辑公共账户信息
	 * 
	 * @param account
	 */

	public void editAccount(WxAccount account);

	/**
	 * 删除公共账户，也可停用公共账户
	 * 
	 * @TODO
	 * 
	 * @param accountId
	 */

	public void deleteAccount(Long accountId);

	/**
	 * 列出所有公共账户
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */

	public Page<WxAccount> listAllAcount(int pageNo, int pageSize);

	/**
	 * 根据公共账户ID获取公共账户信息
	 * 
	 * @param accountId
	 * @return
	 */

	public WxAccount findAcountById(Long accountId);

	/**
	 * 查询公共账户的的信息
	 * 
	 * @param accountId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */

	public Page<WxMsgType> queryAllMessageTypeInAccount(Long accountId,
			int pageNo, int pageSize);

}
