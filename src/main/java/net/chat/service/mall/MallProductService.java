package net.chat.service.mall;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import net.chat.domain.mall.WxProduct;
import net.chat.domain.mall.WxProductPrice;
import net.chat.formbean.MallProductForm;

import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

public interface MallProductService {

	public Page<MallProductForm> findAllProductBySubcategory(
			final Long subcategoryId, int pageNo);

	public void updateProductPrice(long productId, BigDecimal price);

	public Long saveProduct(WxProduct product, List<Long> subcategoryIds,
			MultipartFile productDefaultPic) throws IOException;

	public Long editProduct(WxProduct product, List<Long> subcategoryIds,
			MultipartFile productDefaultPic) throws IOException;

	public void saveProductPrice(WxProductPrice productPrice);

	public void saveProductPic(File zipPicFile, long productId)
			throws IOException;

	public void setProductPicDefault(long productPicId);

}
