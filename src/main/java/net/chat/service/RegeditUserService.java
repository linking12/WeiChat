package net.chat.service;

import net.chat.formbean.RegisterForm;

public interface RegeditUserService {

	public Long regeditUser(RegisterForm regeditUserForm);

	public void stopUser(Long userId);

	public void editUser(RegisterForm regeditUserForm);
}
