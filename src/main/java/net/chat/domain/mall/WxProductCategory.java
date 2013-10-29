package net.chat.domain.mall;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 商场首页类别表 如:全部商品/潮流趋势/最新上架。。。
 * 
 * @author bo.chen
 * 
 */
@Entity
@Table(name = "wx_product_category")
public class WxProductCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6177152884606489031L;

	/**
	 * id 主键
	 */
	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;

	/**
	 * 商场id，关联wxmall表
	 */
	@Column(name = "mallid")
	private Long mallId;

	/**
	 * 类别名称
	 */
	@Column(name = "categoryname")
	private String categoryName;

	/**
	 * 类别名称
	 */
	@Column(name = "description")
	private String description;
	
	/**
	 * 样式
	 * 1：5：5
	 * 2: 6：4
	 * 3: 上图下字
	 */
	@Column(name = "style")
	private String style;
	
	/**
	 * 创建时间
	 */
	@Column(name = "createdate")
	private Date createDate = new Date();

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the mallId
	 */
	public Long getMallId() {
		return mallId;
	}

	/**
	 * @param mallId
	 *            the mallId to set
	 */
	public void setMallId(Long mallId) {
		this.mallId = mallId;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName
	 *            the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate
	 *            the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the style
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * @param style the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
	}

}
