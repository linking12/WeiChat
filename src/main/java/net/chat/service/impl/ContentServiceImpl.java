/**
 * 
 */
package net.chat.service.impl;

import java.util.List;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.chat.service.ContentService#save(net.chat.domain.WxContent)
	 */

	public WxContent save(WxContent wxContent) {

		return dao.save(wxContent);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.chat.service.ContentService#findOne(java.lang.Long)
	 */
	public WxContent findOne(Long id) {

		return dao.findOne(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.chat.service.ContentService#findByMessageId(java.lang.Long)
	 */
	public List<WxContent> findByMessageId(final Long messageId) {
		Specification<WxContent> spec = new Specification<WxContent>() {
			public Predicate toPredicate(Root<WxContent> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Long> get("messageId"), messageId);
			}

		};
		return dao.findAll(spec);

	}

	public void delete(Long id) {
		dao.delete(id);

	}

	public void deleteByMessageId(Long messageId) {
		List<WxContent> contents = dao.deleteByMessageId(messageId);
		dao.delete(contents);

	}

	public List<WxContent> findAllMultimedia(String msgType) {
		return dao.listAllMultimediaContent(msgType);
	}

	public Iterable<WxContent> findByContentIds(List<Long> contentIds) {
		return dao.findAll(contentIds);
	}

	public void saveContents(Iterable<WxContent> wxContent) {
		dao.save(wxContent);
	}

	public void delete(List<Long> ids) {
		for (Long id : ids)
			dao.delete(id);
	}
}
