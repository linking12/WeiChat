package net.chat.integration.vo;

public class Button {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Button) {
			Button button = (Button) obj;
			return (name.equals(button.name));
		}
		return super.equals(obj);
	}

	public int hashCode() {
		Button button = (Button) this;
		return button.name.hashCode();
	}

}
