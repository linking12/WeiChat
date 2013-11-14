package net.chat.integration.vo;


public class ComplexButton extends Button {
	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Object[] sub_button) {
		Button[] sub_buttons = new Button[] {};
		for (int i = 0; i < sub_button.length; i++) {
			Button subButton = (Button) sub_button[i];
			sub_buttons[i] = subButton;
		}
		this.sub_button = sub_buttons;
	}
}
