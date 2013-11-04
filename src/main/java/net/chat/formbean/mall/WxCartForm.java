/**
 * 
 */
package net.chat.formbean.mall;

import net.chat.domain.mall.WxMallCart;

/**
 * @author bo.chen
 * 
 */
public class WxCartForm {

	public WxCartForm(WxMallCart mallCart, WxProductForm productForm) {
		this.mallCart = mallCart;
		this.productForm = productForm;
	}

	private WxMallCart mallCart;

	private WxProductForm productForm;

	/**
	 * @return the mallCart
	 */
	public WxMallCart getMallCart() {
		return mallCart;
	}

	/**
	 * @param mallCart
	 *            the mallCart to set
	 */
	public void setMallCart(WxMallCart mallCart) {
		this.mallCart = mallCart;
	}

	/**
	 * @return the productForm
	 */
	public WxProductForm getProductForm() {
		return productForm;
	}

	/**
	 * @param productForm
	 *            the productForm to set
	 */
	public void setProductForm(WxProductForm productForm) {
		this.productForm = productForm;
	}

}
