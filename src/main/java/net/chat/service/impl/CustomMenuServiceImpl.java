/**
 * 
 */
package net.chat.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.chat.dao.WxCustomMenuDao;
import net.chat.domain.WxCustomMenu;
import net.chat.integration.vo.Button;
import net.chat.integration.vo.ComplexButton;
import net.chat.integration.vo.KeyCommonButton;
import net.chat.integration.vo.Menu;
import net.chat.integration.vo.UrlCommonButton;
import net.chat.service.CustomMenuService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
			public Predicate toPredicate(Root<WxCustomMenu> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
		Menu menu = null;
		List<WxCustomMenu> parents = dao.findParentMenuByAccountId(accountId);
		if (!CollectionUtils.isEmpty(parents)) {
			List<Button> buttons = new ArrayList<Button>();
			menu = new Menu();
			for (WxCustomMenu p : parents) {
				List<WxCustomMenu> childs = dao.findChildMenuByAccountIdAndParentId(accountId, p.getId());
				if (!CollectionUtils.isEmpty(childs)) {// 存在子菜单
					ComplexButton complexButton = new ComplexButton();
					complexButton.setName(p.getName());
					List<Button> subButtons = new ArrayList<Button>();
					for (WxCustomMenu c : childs) {
						subButtons.add(this.buildButton(c));
					}
					complexButton.setSub_button(subButtons.toArray());
					buttons.add(complexButton);
				} else {
					buttons.add(this.buildButton(p));

				}
			}
			menu.setButton(buttons.toArray());
		}
//		String jsonMenu = JSONObject.fromObject(menu).toString();
//		System.out.println(jsonMenu);
		return menu;

		// List<WxCustomMenu> menus =
		// dao.findCommonButtonByAccountId(accountId);
		// Set<Button> menuButton = new HashSet<Button>();
		// Map<Long, Button> hasBuildParent = new HashMap<Long, Button>();
		// for (WxCustomMenu menu : menus) {
		// if (menu.getEventType().equals("message")) {
		// KeyCommonButton commonButton = new KeyCommonButton();
		// commonButton.setName(menu.getName());
		// commonButton
		// .setType(menu.getEventType().equals("message") ? "click"
		// : "view");
		// commonButton
		// .setKey(menu.getEventType().equals("message") ? menu
		// .getEventDesc() : null);
		// CreateCommonButton(menuButton, hasBuildParent, menu,
		// commonButton);
		//
		// } else {
		// UrlCommonButton commonButton = new UrlCommonButton();
		// commonButton.setName(menu.getName());
		// commonButton
		// .setType(menu.getEventType().equals("message") ? "click"
		// : "view");
		// commonButton
		// .setUrl(menu.getEventType().equals("message") ? null
		// : menu.getEventDesc());
		// CreateCommonButton(menuButton, hasBuildParent, menu,
		// commonButton);
		// }
		//
		// }
		//
		// Menu menu = new Menu();
		// menu.setButton(menuButton.toArray());
		// String jsonMenu = JSONObject.fromObject(menu).toString();
		// System.out.println(jsonMenu);
		// return menu;
	}

	private Button buildButton(WxCustomMenu menu) {
		if (menu.getEventType().equals("url")) {
			UrlCommonButton urlButton = new UrlCommonButton();
			urlButton.setName(menu.getName());
			urlButton.setType("view");
			urlButton.setUrl(menu.getEventDesc());
			return urlButton;
		} else {
			KeyCommonButton keyButton = new KeyCommonButton();
			keyButton.setName(menu.getName());
			keyButton.setType("click");
			keyButton.setKey(menu.getEventType());
			return keyButton;
		}
	}

	// private void CreateCommonButton(Set<Button> menuButton, Map<Long, Button>
	// hasBuildParent, WxCustomMenu menu, CommonButton commonButton) {
	// Long paraentId = menu.getParentId();
	// // 说明此菜单是二级菜单
	// if (paraentId != null) {
	// ComplexButton paraentButton;
	// if (hasBuildParent.get(paraentId) == null) {
	// paraentButton = new ComplexButton();
	// WxCustomMenu paraentMenu = dao.findOne(paraentId);
	// paraentButton.setName(paraentMenu.getName());
	// hasBuildParent.put(paraentId, paraentButton);
	// } else {
	// paraentButton = (ComplexButton) hasBuildParent.get(paraentId);
	// }
	// List<Button> subButtonList;
	// if (paraentButton.getSub_button() != null &&
	// paraentButton.getSub_button().length > 0) {
	// subButtonList = Arrays.asList(paraentButton.getSub_button());
	// subButtonList.add(commonButton);
	// } else {
	// subButtonList = new ArrayList<Button>();
	// subButtonList.add(commonButton);
	// }
	// paraentButton.setSub_button(subButtonList.toArray());
	// menuButton.add(paraentButton);
	// }// 说明此菜单式一级菜单
	// else {
	// menuButton.add(commonButton);
	// }
	// }

}
