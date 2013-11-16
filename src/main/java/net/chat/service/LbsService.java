/**
 * 
 */
package net.chat.service;

import java.util.List;

import net.chat.domain.WxArea;
import net.chat.domain.WxCity;
import net.chat.domain.WxLbs;
import net.chat.domain.WxProvince;

/**
 * @author bo.chen
 *
 */
public interface LbsService {

	WxLbs saveLbs(WxLbs WxLbs);
	
	WxLbs findLbsByAccountId(long accountId);
	
	List<WxProvince> findProvinceList();
	
	List<WxCity> findCityList(String parentCode);
	
	List<WxArea> findAreaList(String parentCode);
}
