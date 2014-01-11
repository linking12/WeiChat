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
import net.chat.domain.mall.WxProduct;
import net.chat.domain.mall.WxProductCategory;
import net.chat.formbean.mall.WxCartForm;
import net.chat.formbean.mall.WxOrderForm;
import net.chat.formbean.mall.WxProductForm;

import org.springframework.data.domain.Page;

/**
 * @author bo.chen
 * 
 */
public interface MallService {

	WxMall findMallByAccountId(long accountId);
	
	WxMall findMallById(long id);
	
	WxMall saveMall(WxMall wxMall);

	List<WxMall> findMallByUserId(long userId);

	List<WxProductCategory> findProductCategoryByMallId(long mallId);

	WxProductCategory findWxProductCategoryById(long categoryId);

	WxProductCategory save(WxProductCategory wxProductCategory);

	public Page<WxPrdtSubCategory> findSubCategoryByCategoryId(long categoryId,
			int pageNo);

	public List<WxPrdtSubCategory> findAllSubCategory();

	List<WxProductForm> findPrdtListBySubCategoryId(long subCategoryId);

	WxProductForm findProductById(long productId);

	void addToCart(WxMallCart wxMallCart);

	List<WxCartForm> findCartList(long userId);

	void deleteCartByProductId(long productId, long mallUserId);

	WxMallUser dologin(WxMallUser mallUser);

	WxMallUser findByMallUserId(long mallUserId);
	
	WxMallUser addMallUser(WxMallUser mallUser);

	WxMallUser editMallUser(WxMallUser mallUser);

	List<WxMallExpressType> findExpressTypeListByMall(long mallId);

	BigDecimal getExpressPriceById(long id);

	WxMallOrder addOrder(WxOrderForm orderForm);

	void payOrder(long userId, long orderId);
	
	List<WxMallOrder> findOrderList(long mallId, long userId);

	WxOrderForm findOrderByOrderId(long mallId, long userId, long orderId);

	public Page<WxPrdtSubCategory> findAllSubCategory(
			List<WxProductCategory> categorys, int pageNo);

	public WxPrdtSubCategory findPrdtSubCategoryBySubCategoryId(
			Long subCategoryId);

	public WxPrdtSubCategory save(WxPrdtSubCategory wxSubProductCategory);

	public WxPrdtSubCategory editSubCategory(
			WxPrdtSubCategory wxSubProductCategory);

	public void deleteSubCategory(Long subcategoryId);

	public WxProduct findProductByProductId(Long productId);

}
