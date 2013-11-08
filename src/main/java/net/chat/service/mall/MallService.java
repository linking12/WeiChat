/**
 * 
 */
package net.chat.service.mall;

import java.math.BigDecimal;
import java.util.List;

import net.chat.domain.mall.WxMall;
import net.chat.domain.mall.WxMallCart;
import net.chat.domain.mall.WxMallExpressType;
import net.chat.domain.mall.WxMallOrder;
import net.chat.domain.mall.WxMallUser;
import net.chat.domain.mall.WxPrdtSubCategory;
import net.chat.domain.mall.WxProductCategory;
import net.chat.formbean.mall.WxCartForm;
import net.chat.formbean.mall.WxOrderForm;
import net.chat.formbean.mall.WxProductForm;

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

	void deleteCartByProductId(long productId,long mallUserId);

	WxMallUser dologin(WxMallUser mallUser);

	WxMallUser addMallUser(WxMallUser mallUser);

	WxMallUser editMallUser(WxMallUser mallUser);
	
	List<WxMallExpressType> findExpressTypeListByMall(long mallId);
	
	BigDecimal getExpressPriceById(long id);
	
	WxMallOrder addOrder(WxOrderForm orderForm);
	
	List<WxMallOrder> findOrderList(long mallId,long userId);
	
	WxOrderForm findOrderByOrderId(long mallId,long userId,long orderId);
}
