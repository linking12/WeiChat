/**
 * 
 */
package net.chat.formbean;

/**
 * @author Administrator
 * 
 */
public class SimpleBean {

	public SimpleBean(String key, String value) {
		this.key = key;
		this.value = value;
	}

	private String key;

	private String value;

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
