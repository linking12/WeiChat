/**
 * 
 */
package net.chat.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.chat.dao.WxCustomMenuDao;
import net.chat.domain.WxCustomMenu;
import net.chat.integration.vo.Button;
import net.chat.integration.vo.CommonButton;
import net.chat.integration.vo.ComplexButton;
import net.chat.integration.vo.Menu;
import net.chat.service.CustomMenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author bo.chen <<<<<<< HEAD
 * 
 */
@Service("customMenuService")
public class CustomMenuServiceImpl implements CustomMenuService {

	@Autowired
	private WxCustomMenuDao dao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.chat.service.impl.CustomMenuService#findCustomMenuById(java.lang.
	 * Long)
	 */
	public WxCustomMenu findCustomMenuById(Long id) {

		return dao.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.chat.service.impl.CustomMenuService#findCustomMenuByAccountId(java
	 * .lang.Long)
	 */
	public List<WxCustomMenu> findCustomMenuByAccountId(final Long accountId) {

		Specification<WxCustomMenu> spec = new Specification<WxCustomMenu>() {
			public Predicate toPredicate(Root<WxCustomMenu> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Long> get("accountId"), accountId);
			}

		};
		return dao.findAll(spec);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.chat.service.impl.CustomMenuService#save(net.chat.domain.WxCustomMenu
	 * )
	 */
	public WxCustomMenu save(WxCustomMenu bean) {
		if (bean.getParentId() != null) {
			WxCustomMenu parentMenu = dao.findOne(bean.getParentId());
			parentMenu.setEventDesc(null);
			parentMenu.setEventType(null);
		}
		bean.setCreateDt(new Date());
		return dao.save(bean);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.chat.service.impl.CustomMenuService#delete(java.lang.Long)
	 */
	public void delete(Long id) {
		dao.delete(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.chat.service.impl.CustomMenuService#findParentMenuByAccountId(java
	 * .lang.Long)
	 */
	public List<WxCustomMenu> findParentMenuByAccountId(Long accountId) {

		return dao.findParentMenuByAccountId(accountId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.chat.service.impl.CustomMenuService#checkCustomMenu(net.chat.domain
	 * .WxCustomMenu)
	 */
	public String checkCustomMenu(WxCustomMenu bean) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * net.chat.service.impl.CustomMenuService#findChildMenuByAccountId(java
	 * .lang.Long)
	 */
	public List<WxCustomMenu> findChildMenuByAccountId(Long accountId) {
		return null;

	}

	@Override
	public Menu createMenu(Long accountId) {
		List<WxCustomMenu> menus = dao.findCommonButtonByAccountId(accountId);
		List<Button> menuButton = new ArrayList<Button>();
		Map<Long, Button> hasBuildParent = new HashMap<Long, Button>();
		for (WxCustomMenu menu : menus) {
			CommonButton commonButton = new CommonButton();
			commonButton.setName(menu.getName());
			commonButton.setKey(menu.getEventType().equals("message") ? menu
					.getEventDesc() : null);
			commonButton
					.setType(menu.getEventType().equals("message") ? "click"
							: "view");
			commonButton.setUrl(menu.getEventType().equals("message") ? null
					: menu.getEventDesc());
			Long paraentId = menu.getParentId();
			// 说明此菜单是二级菜单
			if (paraentId != null) {
				ComplexButton paraentButton;
				if (hasBuildParent.get(paraentId) == null) {
					paraentButton = new ComplexButton();
					hasBuildParent.put(paraentId, paraentButton);
				} else {
					paraentButton = (ComplexButton) hasBuildParent
							.get(paraentId);
				}
				WxCustomMenu paraentMenu = dao.findOne(paraentId);
				paraentButton.setName(paraentMenu.getName());
				List<Button> subButtonList;
				if (paraentButton.getSub_button() != null
						&& paraentButton.getSub_button().length > 0) {
					subButtonList = Arrays
							.asList(paraentButton.getSub_button());
					subButtonList.add(commonButton);
				} else {
					subButtonList = new ArrayList<Button>();
					subButtonList.add(commonButton);
				}
				paraentButton.setSub_button(subButtonList.toArray());
				menuButton.add(paraentButton);
			}// 说明此菜单式一级菜单
			else {
				menuButton.add(commonButton);
			}

		}

		/**
		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项<br>
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？<br>
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：<br>
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		menu.setButton(menuButton.toArray());
		return menu;
	}
}
