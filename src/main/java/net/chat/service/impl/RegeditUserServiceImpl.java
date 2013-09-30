package net.chat.service.impl;

import net.chat.dao.UserDao;
import net.chat.dao.WxUserRegistDao;
import net.chat.domain.User;
import net.chat.domain.WxUserRegist;
import net.chat.formbean.RegisterForm;
import net.chat.service.RegeditUserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("regeditUserService")
@Transactional
public class RegeditUserServiceImpl implements RegeditUserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private WxUserRegistDao wxUserRegeditDao;

	public Long regeditUser(RegisterForm regeditUserForm) {
		if (StringUtils.isNotBlank(regeditUserForm.getName())) {
			User user = userDao.findByName("regeditUserForm.getName()");
			if (null != user) {
				throw new IllegalArgumentException("User has exists.");
			}
		}
		if (!regeditUserForm.getSpassword1().equals(
				regeditUserForm.getSpassword2())) {
			throw new IllegalArgumentException(
					"Password1 is not mathed with Password2");
		}
		User userEntity = new User();
		userEntity.setAccount(regeditUserForm.getName());
		userEntity.setPassWord(regeditUserForm.getSpassword1());
		userEntity.setEnable(1l);
		userDao.save(userEntity);

		WxUserRegist wxUser = new WxUserRegist();
		BeanUtils.copyProperties(regeditUserForm, wxUser);
		wxUserRegeditDao.save(wxUser);

		return userEntity.getUserId();

	}

	public void stopUser(Long userId) {
		User user = userDao.findOne(userId);
		if (null == user) {
			throw new IllegalArgumentException("User is not exists.");
		}
		user.setEnable(0l);

	}

	public void editUser(RegisterForm regeditUserForm) {
		// User 信息不能修改，只能修改WxUserRegist的信息
		User user = userDao.findOne(regeditUserForm.getUserId());
		String name = user.getAccount();
		WxUserRegist wxUser = wxUserRegeditDao.findByName(name);
		BeanUtils.copyProperties(regeditUserForm, wxUser);

	}

}
