package net.chat.service.mall.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import net.chat.dao.mall.WxMallDao;
import net.chat.dao.mall.WxPrdtCategoryDao;
import net.chat.dao.mall.WxPrdtSubCategoryDao;
import net.chat.dao.mall.WxProductCategoryDao;
import net.chat.dao.mall.WxProductDao;
import net.chat.dao.mall.WxProductPicDao;
import net.chat.dao.mall.WxProductPriceDao;
import net.chat.domain.mall.WxPrdtCategory;
import net.chat.domain.mall.WxProduct;
import net.chat.domain.mall.WxProductPic;
import net.chat.domain.mall.WxProductPrice;
import net.chat.formbean.MallProductForm;
import net.chat.service.mall.MallProductService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service("mallProductService")
@Transactional
public class MallProductServiceImpl implements MallProductService {

	@Value("${ssweb.root}")
	protected File rootFolder;

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

	@Override
	public Page<MallProductForm> findAllProductBySubcategory(
			final Long subcategoryId, int pageNo) {

		Pageable pageable = new PageRequest(pageNo - 1, 5, new Sort(new Order(
				Direction.DESC, "createDate")));
		Specification<WxPrdtCategory> spec = new Specification<WxPrdtCategory>() {
			public Predicate toPredicate(Root<WxPrdtCategory> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb
						.equal(root.<Long> get("subCategoryId"), subcategoryId);
			}
		};

		Page<WxPrdtCategory> pagePrdtCategory = prdtCategoryDao.findAll(spec,
				pageable);
		List<WxPrdtCategory> prdtCategorys = pagePrdtCategory.getContent();
		List<MallProductForm> mallProductForms = new ArrayList<MallProductForm>();
		for (WxPrdtCategory prodtCategory : prdtCategorys) {
			WxProduct product = productDao
					.findOne(prodtCategory.getProductId());
			if (product != null) {
				MallProductForm _mallProductForm = new MallProductForm();
				WxProductPrice productPrice = priceDao
						.findPriceByProductId(prodtCategory.getProductId());
				WxProductPic productDefaultPic = picDao
						.findDefaultPicByProductId(prodtCategory.getProductId());
				List<WxProductPic> productPics = picDao
						.findPicByProductId(prodtCategory.getProductId());
				_mallProductForm.setDefaultMallProductPic(productDefaultPic);
				_mallProductForm.setMallProduct(product);
				_mallProductForm.setMallProductPrice(productPrice);
				_mallProductForm.setMallProductPic(productPics);
				_mallProductForm.setMallProductCategory(prodtCategory);
				mallProductForms.add(_mallProductForm);
			}
		}
		Page<MallProductForm> prodtFormPage = new PageImpl<MallProductForm>(
				mallProductForms, new PageRequest(pageNo - 1, 5, null),
				pagePrdtCategory.getTotalElements());
		return prodtFormPage;
	}

	@Override
	@Transactional
	public Long saveProduct(WxProduct product, List<Long> subcategoryIds,
			MultipartFile productDefaultPic) throws IOException {
		if (product.getId() == null) {
			product = productDao.save(product);
			for (Long subcategoryId : subcategoryIds) {
				WxPrdtCategory productCategory = new WxPrdtCategory();
				productCategory.setProductId(product.getId());
				productCategory.setSubCategoryId(subcategoryId);
				prdtCategoryDao.save(productCategory);
			}
			File slideTmpFolder = new File(rootFolder, "/mallimg/images/"
					+ product.getMallId());
			String suffix = productDefaultPic.getOriginalFilename().substring(
					productDefaultPic.getOriginalFilename().lastIndexOf("."));
			DateFormat format = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
			String imageUrl = format.format(new Date()) + UUID.randomUUID()
					+ suffix;
			FileUtils.copyInputStreamToFile(productDefaultPic.getInputStream(),
					new File(slideTmpFolder, imageUrl));
			WxProductPic pic = new WxProductPic();
			pic.setFlag("0");
			pic.setPicName(productDefaultPic.getName());
			pic.setPicUrl(File.separator + product.getMallId() + File.separator
					+ imageUrl);
			pic.setProductId(product.getId());
			picDao.save(pic);
		}
		return product.getId();
	}

	@Override
	public void saveProductPrice(WxProductPrice productPrice) {
		priceDao.save(productPrice);
	}

	@Override
	public void updateProductPrice(long productId, BigDecimal price) {
		priceDao.updatePrice(productId, price);
	}

	@Override
	public void saveProductPic(File zipPicFile, long productId)
			throws IOException {
		WxProduct product = productDao.findOne(productId);
		InputStream is = new FileInputStream(zipPicFile);
		ZipInputStream zipIn = new ZipInputStream(is);
		ZipEntry entry = null;
		File slideTmpFolder = new File(rootFolder, "/mallimg/images/"
				+ product.getMallId());
		List<WxProductPic> productPics = new ArrayList<WxProductPic>();
		while ((entry = zipIn.getNextEntry()) != null) {
			if (!entry.isDirectory()) {
				File f = new File(slideTmpFolder, entry.getName());
				if (!f.getParentFile().exists()) {
					f.getParentFile().mkdirs();
				}
				copyInputStreamToFile(zipIn, f);
				WxProductPic pic = new WxProductPic();
				pic.setFlag("1");
				pic.setPicName(f.getName());
				pic.setPicUrl(File.separator + f.getName());
				pic.setProductId(productId);
				productPics.add(pic);

			}
		}
		IOUtils.closeQuietly(zipIn);
		picDao.save(productPics);
	}

	public static void copyInputStreamToFile(InputStream source,
			File destination) throws IOException {
		try {
			FileOutputStream output = FileUtils.openOutputStream(destination);
			try {
				IOUtils.copy(source, output);
				output.close(); // don't swallow close Exception if copy
								// completes normally
			} finally {
				IOUtils.closeQuietly(output);
			}
		} finally {
		}
	}

	@Override
	public void setProductPicDefault(long productPicId) {
		picDao.setDaulftPic(productPicId);

	}

	@Override
	@Transactional
	public Long editProduct(WxProduct product, List<Long> subcategoryIds,
			MultipartFile productDefaultPic) throws IOException {
		WxProduct productEntity = productDao.findOne(product.getId());
		productEntity.setDescrpiton(product.getDescrpiton());
		productEntity.setEffectiveDate(product.getEffectiveDate());
		productEntity.setExpiryDate(product.getExpiryDate());
		productEntity.setProductName(product.getProductName());
		productEntity.setStock(product.getStock());
		productEntity.setProductPrice(product.getProductPrice());
		prdtCategoryDao.deleteByProductId(product.getId());
		for (Long subcategoryId : subcategoryIds) {
			WxPrdtCategory productCategory = new WxPrdtCategory();
			productCategory.setProductId(product.getId());
			productCategory.setSubCategoryId(subcategoryId);
			prdtCategoryDao.save(productCategory);
		}
		WxProductPic productDefaultPicEntity = picDao
				.findDefaultPicByProductId(product.getId());
		new File(rootFolder, "/mallimg/images"
				+ productDefaultPicEntity.getPicUrl()).delete();
		File slideTmpFolder = new File(rootFolder, "/mallimg/images/"
				+ product.getMallId());
		String suffix = productDefaultPic.getOriginalFilename().substring(
				productDefaultPic.getOriginalFilename().lastIndexOf("."));
		DateFormat format = new SimpleDateFormat("yyyyMMdd hh:mm:ss");
		String imageUrl = format.format(new Date()) + UUID.randomUUID()
				+ suffix;
		FileUtils.copyInputStreamToFile(productDefaultPic.getInputStream(),
				new File(slideTmpFolder, imageUrl));
		productDefaultPicEntity.setPicUrl(File.separator + product.getMallId()
				+ File.separator + imageUrl);

		return product.getId();
	}
}
