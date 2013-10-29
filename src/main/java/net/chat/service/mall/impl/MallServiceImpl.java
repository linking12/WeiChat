/**
 * 
 */
package net.chat.service.mall.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.chat.dao.mall.WxMallDao;
import net.chat.dao.mall.WxPrdtCategoryDao;
import net.chat.dao.mall.WxPrdtSubCategoryDao;
import net.chat.dao.mall.WxProductCategoryDao;
import net.chat.dao.mall.WxProductDao;
import net.chat.dao.mall.WxProductPicDao;
import net.chat.dao.mall.WxProductPriceDao;
import net.chat.domain.mall.WxMall;
import net.chat.domain.mall.WxPrdtCategory;
import net.chat.domain.mall.WxPrdtSubCategory;
import net.chat.domain.mall.WxProduct;
import net.chat.domain.mall.WxProductCategory;
import net.chat.domain.mall.WxProductPic;
import net.chat.domain.mall.WxProductPrice;
import net.chat.formbean.mall.WxProductForm;
import net.chat.service.mall.MallService;

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
		List<WxPrdtCategory> prdtList = prdtCategoryDao.findPrdtBySubCategoryId(subCategoryId);
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

}
