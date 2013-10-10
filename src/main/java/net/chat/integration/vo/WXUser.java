package net.chat.integration.vo;

public class WXUser {
	private long id;
	private String nickname;
	private String browserseq;
	private String address;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getBrowserseq() {
		return browserseq;
	}

	public void setBrowserseq(String browserseq) {
		this.browserseq = browserseq;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
