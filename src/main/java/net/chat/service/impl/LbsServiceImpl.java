/**
 * 
 */
package net.chat.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chat.dao.WxAreaDao;
import net.chat.dao.WxCityDao;
import net.chat.dao.WxLbsDao;
import net.chat.dao.WxProvinceDao;
import net.chat.domain.WxArea;
import net.chat.domain.WxCity;
import net.chat.domain.WxLbs;
import net.chat.domain.WxProvince;
import net.chat.service.LbsService;

/**
 * @author bo.chen
 * 
 */
@Service("lbsService")
public class LbsServiceImpl implements LbsService {

	@Autowired
	WxLbsDao lbsDao;
	
	@Autowired
	WxProvinceDao provinceDao;
	
	@Autowired
	WxCityDao cityDao;
	
	@Autowired
	WxAreaDao areaDao;
	
	public WxLbs saveLbs(WxLbs WxLbs) {
		WxLbs.setCreateDate(new Date());
		return lbsDao.save(WxLbs);
	}

	public WxLbs findLbsByAccountId(long accountId) {

		return lbsDao.findByAccountId(accountId);
	}

	public List<WxProvince> findProvinceList() {
		
		return provinceDao.findAll();
	}

	public List<WxCity> findCityList(String parentCode) {
		
		return cityDao.findByParentCode(parentCode);
	}

	public List<WxArea> findAreaList(String parentCode) {
		
		return areaDao.findByParentCode(parentCode);
	}

}
