/**
 * 
 */
package net.chat.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
	@Transactional
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
			Set<Button> buttons = new HashSet<Button>();
			menu = new Menu();
			for (WxCustomMenu p : parents) {
				List<WxCustomMenu> childs = dao
						.findChildMenuByAccountIdAndParentId(accountId,
								p.getId());
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
		String jsonMenu = JSONObject.fromObject(menu).toString();
		System.out.println(jsonMenu);
		return menu;

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
			keyButton.setKey(menu.getEventDesc());
			return keyButton;
		}
	}

}
