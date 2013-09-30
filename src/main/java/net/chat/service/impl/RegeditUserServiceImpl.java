package net.chat.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.chat.dao.RolesDao;
import net.chat.dao.UserDao;
import net.chat.dao.WxUserRegistDao;
import net.chat.domain.Roles;
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
	private RolesDao roleDao;

	@Autowired
	private WxUserRegistDao wxUserRegeditDao;

	public Long regeditUser(RegisterForm regeditUserForm) {
		if (StringUtils.isNotBlank(regeditUserForm.getName())) {
			User user = userDao.findByName("regeditUserForm.getName()");
			if (null != user) {
				throw new IllegalArgumentException("用户已存在！");
			}
		}
		if (!regeditUserForm.getSpassword1().equals(
				regeditUserForm.getSpassword2())) {
			throw new IllegalArgumentException(
					"密码和确认密码不一致！");
		}
		User userEntity = new User();
		userEntity.setAccount(regeditUserForm.getName());
		userEntity.setPassWord(regeditUserForm.getSpassword1());
		userEntity.setEnable(1l);
		Roles role = roleDao.findOne(2l);
		List<Roles> roleList = new ArrayList<Roles>(1);
		roleList.add(role);
		userEntity.setRoleList(roleList);
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
