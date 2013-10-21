/**
 * 
 */
package net.chat.formbean;

import java.util.List;

import net.chat.domain.WxContent;
import net.chat.domain.WxMessage;

/**
 * @author bo.chen
 * 
 */
public class MessageForm {

	public MessageForm(WxMessage message, List<Long> selectIds, List<WxContent> contents) {
		this.message = message;
		this.selectIds = selectIds;
		this.contents = contents;
	}

	private WxMessage message;

	private List<Long> selectIds;

	private List<WxContent> contents;

	/**
	 * @return the message
	 */
	public WxMessage getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(WxMessage message) {
		this.message = message;
	}

	/**
	 * @return the selectIds
	 */
	public List<Long> getSelectIds() {
		return selectIds;
	}

	/**
	 * @param selectIds
	 *            the selectIds to set
	 */
	public void setSelectIds(List<Long> selectIds) {
		this.selectIds = selectIds;
	}

	/**
	 * @return the contents
	 */
	public List<WxContent> getContents() {
		return contents;
	}

	/**
	 * @param contents
	 *            the contents to set
	 */
	public void setContents(List<WxContent> contents) {
		this.contents = contents;
	}

}
