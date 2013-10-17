/**
 * 
 */
package net.chat.service.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.chat.dao.WxCustomMenuDao;
import net.chat.domain.WxCustomMenu;
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
}
