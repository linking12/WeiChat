package net.chat.formbean;

import java.util.List;

import net.chat.domain.mall.WxPrdtCategory;
import net.chat.domain.mall.WxProduct;
import net.chat.domain.mall.WxProductPic;
import net.chat.domain.mall.WxProductPrice;

public class MallProductForm {

	private WxProduct mallProduct;

	private WxPrdtCategory mallProductCategory;

	private WxProductPic defaultMallProductPic;

	private List<WxProductPic> mallProductPic;

	private WxProductPrice mallProductPrice;

	public WxProduct getMallProduct() {
		return mallProduct;
	}

	public void setMallProduct(WxProduct mallProduct) {
		this.mallProduct = mallProduct;
	}

	public WxPrdtCategory getMallProductCategory() {
		return mallProductCategory;
	}

	public void setMallProductCategory(WxPrdtCategory mallProductCategory) {
		this.mallProductCategory = mallProductCategory;
	}

	public WxProductPrice getMallProductPrice() {
		return mallProductPrice;
	}

	public void setMallProductPrice(WxProductPrice mallProductPrice) {
		this.mallProductPrice = mallProductPrice;
	}

	public WxProductPic getDefaultMallProductPic() {
		return defaultMallProductPic;
	}

	public void setDefaultMallProductPic(WxProductPic defaultMallProductPic) {
		this.defaultMallProductPic = defaultMallProductPic;
	}

	public List<WxProductPic> getMallProductPic() {
		return mallProductPic;
	}

	public void setMallProductPic(List<WxProductPic> mallProductPic) {
		this.mallProductPic = mallProductPic;
	}

}
