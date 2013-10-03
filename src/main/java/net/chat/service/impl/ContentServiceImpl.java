/**
 * 
 */
package net.chat.service.impl;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.chat.dao.WxContentDao;
import net.chat.domain.WxContent;
import net.chat.service.ContentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 *
 */
@Service("contentService")
public class ContentServiceImpl implements ContentService {

	@Autowired
	private WxContentDao dao;
	

	/* (non-Javadoc)
	 * @see net.chat.service.ContentService#save(net.chat.domain.WxContent)
	 */
	@Override
	public WxContent save(WxContent wxContent) {
		
		return dao.save(wxContent);
	}

	/* (non-Javadoc)
	 * @see net.chat.service.ContentService#findOne(java.lang.Long)
	 */
	@Override
	public WxContent findOne(Long id) {
		
		return dao.findOne(id);
	}

	/* (non-Javadoc)
	 * @see net.chat.service.ContentService#findByMessageId(java.lang.Long)
	 */
	@Override
	public WxContent findByMessageId(final Long messageId) {
		Specification<WxContent> spec = new Specification<WxContent>() {
			public Predicate toPredicate(Root<WxContent> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Long> get("messageId"), messageId);
			}

		};
		return dao.findOne(spec);
	
	}

	@Override
	public void delete(Long id) {
		dao.delete(id);
		
	}

	@Override
	public void deleteByMessageId(Long messageId) {
		dao.deleteByMessageId(messageId);
		
	}

}
