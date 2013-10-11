package net.chat.service.impl;

import java.util.List;

import net.chat.dao.WxCmdDao;
import net.chat.domain.WxCmd;
import net.chat.service.CmdService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

@Service("cmdService")
public class CmdServiceImpl implements CmdService {

	@Autowired
	private WxCmdDao wxCmdDao;

	public WxCmd save(WxCmd cmd) {

		return wxCmdDao.save(cmd);
	}

	public void delete(Long cmdId) {
		wxCmdDao.delete(cmdId);
	}

	public Page<WxCmd> findCmdByAccountId(Long accountId, String condition,
			int pageNo) {
		int pageSize = 5;
		if (pageNo == 0)
			pageNo = 1;
		Pageable pageable = new PageRequest(pageNo - 1, pageSize, new Sort(
				new Order("id")));

		return wxCmdDao.findCmdByAccountId(accountId,
				StringUtils.isBlank(condition) ? "" : condition, pageable);
	}

	public List<WxCmd> findCmdByAccountId(Long accountId, String condition) {

		return wxCmdDao.findCmdByAccountId(accountId,
				StringUtils.isBlank(condition) ? "" : condition);
	}

	public WxCmd findOne(Long id) {

		return wxCmdDao.findOne(id);
	}
}
