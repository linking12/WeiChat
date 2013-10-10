package net.chat.utils;

public class DBException extends Exception {

	/**
     * 
     */
	public DBException() {

	}

	/**
	 * @param message
	 */
	public DBException(String message) {
		super(message);

	}

	/**
	 * @param cause
	 */
	public DBException(Throwable cause) {
		super(cause);
		// TODO 自动生成构造函数存根
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DBException(String message, Throwable cause) {
		super(message, cause);
		// TODO 自动生成构造函数存根
	}

}
