package net.chat.service.mall;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

import net.chat.domain.mall.WxProduct;
import net.chat.domain.mall.WxProductPrice;
import net.chat.formbean.MallProductForm;

import org.springframework.data.domain.Page;

public interface MallProductService {

	public Page<MallProductForm> findAllProductBySubcategory(
			final Long subcategoryId, int pageNo);

	public void updateProductPrice(long productId, BigDecimal price);

	public void saveProduct(WxProduct product, long subcategoryId);

	public void saveProductPrice(WxProductPrice productPrice);

	public void saveProductPic(File zipPicFile, long productId)
			throws IOException;

	public void setProductPicDefault(long productPicId);

}
