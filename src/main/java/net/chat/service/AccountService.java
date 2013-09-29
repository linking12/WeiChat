package net.chat.service;

import java.util.List;

import net.chat.domain.WxAccount;
import net.chat.domain.WxMsgType;

import org.springframework.data.domain.Page;

public interface AccountService {

	/**
	 * ���湫���˻���Ϣ
	 * 
	 * @param account
	 */
	public void saveAccount(WxAccount account);

	/**
	 * �༭�����˻���Ϣ
	 * 
	 * @param account
	 */

	public void editAccount(WxAccount account);

	/**
	 * ɾ���˻���Ҳ��ͣ�ù����˻�
	 * 
	 * @TODO
	 * 
	 * @param accountId
	 */

	public void deleteAccount(Long accountId);

	/**
	 * �г����й����˻�
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */

	public Page<WxAccount> listAllAcount(int pageNo, int pageSize);

	/**
	 * ��ݹ����˻�ID��ȡ�����˻���Ϣ
	 * 
	 * @param accountId
	 * @return
	 */

	public WxAccount findAcountById(Long accountId);

	/**
	 * ��ѯ�����˻��ĵ���Ϣ
	 * 
	 * @param accountId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */

	public Page<WxMsgType> queryAllMessageTypeInAccount(Long accountId,
			int pageNo, int pageSize);

	public List<WxAccount> listAllAcount();
	
	public void updateAccount(Long id,String name,String note);
}
