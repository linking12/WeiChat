/**
 * 
 */
package net.chat.service.mall.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.chat.dao.mall.WxMallCartDao;
import net.chat.dao.mall.WxMallDao;
import net.chat.dao.mall.WxMallExpressTypeDao;
import net.chat.dao.mall.WxMallOrderDao;
import net.chat.dao.mall.WxMallUserDao;
import net.chat.dao.mall.WxOrderProductDao;
import net.chat.dao.mall.WxPrdtCategoryDao;
import net.chat.dao.mall.WxPrdtSubCategoryDao;
import net.chat.dao.mall.WxProductCategoryDao;
import net.chat.dao.mall.WxProductDao;
import net.chat.dao.mall.WxProductPicDao;
import net.chat.dao.mall.WxProductPriceDao;
import net.chat.domain.mall.WxMall;
import net.chat.domain.mall.WxMallCart;
import net.chat.domain.mall.WxMallExpressType;
import net.chat.domain.mall.WxMallOrder;
import net.chat.domain.mall.WxMallUser;
import net.chat.domain.mall.WxOrderProduct;
import net.chat.domain.mall.WxPrdtCategory;
import net.chat.domain.mall.WxPrdtSubCategory;
import net.chat.domain.mall.WxProduct;
import net.chat.domain.mall.WxProductCategory;
import net.chat.domain.mall.WxProductPic;
import net.chat.domain.mall.WxProductPrice;
import net.chat.formbean.mall.WxCartForm;
import net.chat.formbean.mall.WxOrderForm;
import net.chat.formbean.mall.WxProductForm;
import net.chat.service.mall.MallService;
import net.chat.tenpay.util.MD5Util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @author bo.chen
 * 
 */
@Service("mallService")
public class MallServiceImpl implements MallService {

	@Autowired
	WxMallDao mallDao;

	@Autowired
	WxProductCategoryDao productCategoryDao;

	@Autowired
	WxPrdtSubCategoryDao prdtSubCategoryDao;

	@Autowired
	WxPrdtCategoryDao prdtCategoryDao;

	@Autowired
	WxProductDao productDao;

	@Autowired
	WxProductPriceDao priceDao;

	@Autowired
	WxProductPicDao picDao;

	@Autowired
	WxMallCartDao cartDao;

	@Autowired
	WxMallUserDao mallUserDao;

	@Autowired
	WxMallExpressTypeDao expressTypeDao;

	@Autowired
	WxMallOrderDao orderDao;

	@Autowired
	WxOrderProductDao orderProductDao;

	public WxMall findMallByAccountId(long accountId) {

		return mallDao.findMallByAccountId(accountId);
	}

	public List<WxProductCategory> findProductCategoryByMallId(long mallId) {

		return productCategoryDao.findProductCategoryByMallId(mallId);
	}

	public List<WxPrdtSubCategory> findSubCategoryByCategoryId(long categoryId) {

		return prdtSubCategoryDao.findSubCategoryByCategoryId(categoryId);
	}

	public WxProductCategory findWxProductCategoryById(long categoryId) {

		return productCategoryDao.findOne(categoryId);
	}

	@Override
	public List<WxProductForm> findPrdtListBySubCategoryId(long subCategoryId) {
		List<WxPrdtCategory> prdtList = prdtCategoryDao
				.findPrdtBySubCategoryId(subCategoryId);
		List<WxProductForm> formList = null;
		for (WxPrdtCategory prdt : prdtList) {

			WxProductForm form = this.findProductById(prdt.getProductId());
			if (null != form) {
				if (null == formList) {
					formList = new ArrayList<WxProductForm>();
				}
				formList.add(form);
			}
		}
		return formList;
	}

	@Override
	public WxProductForm findProductById(long productId) {
		WxProduct product = productDao.findProductById(productId);
		if (null != product) {
			WxProductForm form = new WxProductForm();
			form.setDescrpiton(product.getDescrpiton());
			form.setProductId(product.getId());
			form.setOriginalPrice(product.getProductPrice());
			form.setProductName(product.getProductName());
			WxProductPrice price = priceDao.findPriceByProductId(productId);
			if (null != price) {
				form.setExpenses(price.getExpenses());
				form.setSalePrice(price.getSalePrice());
			} else {
				form.setSalePrice(product.getProductPrice());
				form.setExpenses(new BigDecimal(5.00));
			}
			List<WxProductPic> piclist = picDao.findPicByProductId(productId);
			List<String> picList = null;
			for (WxProductPic pic : piclist) {
				if ("0".equals(pic.getFlag())) {
					form.setDefaultPic(pic.getPicUrl());
				}
				if (null == picList) {
					picList = new ArrayList<String>();
				}
				picList.add(pic.getPicUrl());
			}
			form.setPicUrl(picList);
			return form;
		}
		return null;

	}

	public void addToCart(WxMallCart wxMallCart) {
		WxMallCart oldCart = cartDao.findCartByUserIdAndProductId(
				wxMallCart.getId(), wxMallCart.getProductId());
		if (null != oldCart) {
			oldCart.setCount(oldCart.getCount() + wxMallCart.getCount());
			cartDao.save(oldCart);
		} else {
			wxMallCart.setCreateDate(new Date());
			cartDao.save(wxMallCart);
		}
	}

	public List<WxCartForm> findCartList(long userId) {
		List<WxCartForm> cartList = null;
		List<WxMallCart> wxcartList = cartDao.findCartByUserId(userId);
		for (WxMallCart cart : wxcartList) {
			if (null == cartList)
				cartList = new ArrayList<WxCartForm>();
			long productId = cart.getProductId();
			WxProductForm productForm = this.findProductById(productId);
			cartList.add(new WxCartForm(cart, productForm));

		}
		return cartList;
	}

	@Transactional
	public void deleteCartByProductId(long productId, long mallUserId) {

		cartDao.deleteCartByProductId(mallUserId, productId);
	}

	public WxMallUser dologin(WxMallUser mallUser) {
		WxMallUser _mallUser = mallUserDao.findByName(mallUser.getUserName());
		if (_mallUser == null)
			return null;
		if (_mallUser.getPassword().equalsIgnoreCase(
				MD5Util.MD5Encode(mallUser.getPassword(), null)))
			return _mallUser;
		else
			return null;
	}

	@Transactional
	public WxMallUser addMallUser(WxMallUser mallUser) {
		WxMallUser _mallUser = mallUserDao.findByName(mallUser.getUserName());
		if (_mallUser != null) {
			throw new IllegalArgumentException("User name:"
					+ _mallUser.getUserName() + " has exists.");
		}
		mallUser.setPassword(MD5Util.MD5Encode(mallUser.getPassword(), null));
		return mallUserDao.save(mallUser);
	}

	@Transactional
	public WxMallUser editMallUser(WxMallUser mallUser) {
		WxMallUser _mallUser = mallUserDao.findOne(mallUser.getId());
		if (_mallUser != null) {
			_mallUser.setAddress(mallUser.getAddress());
			_mallUser.setPassword(MD5Util.MD5Encode(mallUser.getPassword(),
					null));
			_mallUser.setPhoneNo(mallUser.getPhoneNo());
		}
		return mallUserDao.save(_mallUser);
	}

	public List<WxMallExpressType> findExpressTypeListByMall(long mallId) {

		return expressTypeDao.findExpressTypeListByMall(mallId);
	}

	public BigDecimal getExpressPriceById(long id) {
		WxMallExpressType express = expressTypeDao.findOne(id);
		if (null != express)
			return express.getPrice();
		else
			return BigDecimal.ZERO;
	}

	@Transactional
	public WxMallOrder addOrder(WxOrderForm orderForm) {
		WxMallOrder order = new WxMallOrder();
		BeanUtils.copyProperties(orderForm, order);
		order.setOrderNo(this.buildOrderNo(orderForm.getMallId(),
				orderForm.getUserId()));
		order = orderDao.save(order);
		BigDecimal salePrice = BigDecimal.ZERO;

		for (WxProductForm productForm : orderForm.getProductList()) {
			long productId = productForm.getProductId();
			long count = productForm.getStock();
			BigDecimal price = this.findProductById(productId).getSalePrice();
			salePrice = salePrice.add(price.multiply(new BigDecimal(count)));
			WxOrderProduct product = new WxOrderProduct();
			product.setOrderId(order.getId());
			product.setProductId(productId);
			product.setPrice(price);
			product.setCount(count);
			orderProductDao.save(product);
			cartDao.deleteCartByProductId(orderForm.getUserId(), productId);
		}
		order.setSalePrice(salePrice);
		order = orderDao.save(order);
		return order;
	}

	private String buildOrderNo(long mallId, long userId) {
		String orderNo = StringUtils.EMPTY;
		orderNo += mallId;
		orderNo += userId;
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		orderNo += sf.format(new Date());
		return orderNo;
	}

	public List<WxMallOrder> findOrderList(long mallId, long userId) {
		List<WxMallOrder> orderList = orderDao.findOrderList(mallId, userId);
		for (WxMallOrder order : orderList) {
			order.setSalePrice(order.getSalePrice().add(
					this.getExpressPriceById(order.getExpressType())));
		}
		return orderList;
	}

	public WxOrderForm findOrderByOrderId(long mallId, long userId, long orderId) {
		WxOrderForm form = new WxOrderForm();
		WxMallOrder order = orderDao.findOrder(mallId, userId, orderId);
		BeanUtils.copyProperties(order, form);
		List<WxOrderProduct> productList = orderProductDao
				.findOrderProductList(order.getId());
		if (!CollectionUtils.isEmpty(productList)) {
			List<WxProductForm> productformList = new ArrayList<WxProductForm>();
			for (WxOrderProduct o : productList) {
				WxProductForm productForm = this.findProductById(o
						.getProductId());
				productForm.setStock(o.getCount());
				productformList.add(productForm);
			}
			form.setProductList(productformList);
		}
		return form;
	}
}
