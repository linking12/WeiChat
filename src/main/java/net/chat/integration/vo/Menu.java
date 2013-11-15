package net.chat.integration.vo;

public class Menu {

	private Button[] button;

	public Button[] getButton() {
		return button;
	}

	public void setButton(Object[] button) {
		Button[] _button = new Button[button.length];
		for (int i = 0; i < button.length; i++) {
			Button subButton = (Button) button[i];
			_button[i] = subButton;
		}
		this.button = _button;
	}

}
