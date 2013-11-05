/**
 * 
 */
package net.chat.service.mall;

import java.util.List;

import net.chat.domain.mall.WxMall;
import net.chat.domain.mall.WxMallCart;
import net.chat.domain.mall.WxMallUser;
import net.chat.domain.mall.WxPrdtSubCategory;
import net.chat.domain.mall.WxProductCategory;
import net.chat.formbean.mall.WxCartForm;
import net.chat.formbean.mall.WxProductForm;
import net.chat.tenpay.util.MD5Util;

/**
 * @author bo.chen
 * 
 */
public interface MallService {

	WxMall findMallByAccountId(long accountId);

	List<WxProductCategory> findProductCategoryByMallId(long mallId);

	WxProductCategory findWxProductCategoryById(long categoryId);

	List<WxPrdtSubCategory> findSubCategoryByCategoryId(long categoryId);

	List<WxProductForm> findPrdtListBySubCategoryId(long subCategoryId);

	WxProductForm findProductById(long productId);

	void addToCart(WxMallCart wxMallCart);

	List<WxCartForm> findCartList(long userId);

	void deleteCartByProductId(long productId);

	public boolean dologin(WxMallUser mallUser);

	public void addMallUser(WxMallUser mallUser);

	public void editMallUser(WxMallUser mallUser);
}
