package net.chat.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import net.chat.constants.PageConstants;
import net.chat.domain.WxContent;
import net.chat.formbean.SimpleBean;
import net.chat.service.ContentService;
import net.chat.utils.AppContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;

	@RequestMapping("/init")
	public String init(
			@RequestParam(value = "msgType", required = false) String msgType,
			@RequestParam(value = "sortType", required = false) String sortType,
			@RequestParam(value = "page", defaultValue = "1") int pageNo,
			Model model) {
		if (StringUtils.isNotBlank(sortType))
			msgType = sortType;
		List<SimpleBean> contentTypes = PageConstants.buildContentTypesList();
		model.addAttribute("contentTypes", contentTypes);

		Page<WxContent> contents = contentService.findAllBaseMultimedia(
				msgType, pageNo);
		model.addAttribute("contents", contents);

		model.addAttribute("msgType", msgType);
		model.addAttribute("sortType", msgType);
		model.addAttribute("contentTypes", contentTypes);
		return PageConstants.PAGE_CONTENT_LIST;
	}

	@RequestMapping("/add")
	public String add(Model model) {
		List<SimpleBean> contentTypes = PageConstants.buildContentTypesList();
		model.addAttribute("contentTypes", contentTypes);
		model.addAttribute("wxContent", new WxContent());

		return PageConstants.PAGE_CONTENT_DETAIL_1;
	}

	@RequestMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		WxContent wxContent = contentService.findOne(id);
		model.addAttribute("wxContent", wxContent);
		model.addAttribute("contentTypes", PageConstants
				.buildContentTypesListByType(wxContent.getMsgType()));

		return PageConstants.PAGE_CONTENT_DETAIL_1;
	}

	@RequestMapping("/delete")
	public String delete(Model model) {
		return PageConstants.PAGE_CONTENT_LIST;
	}

	@RequestMapping("/submit")
	public String submit(
			@RequestParam(required = false) MultipartFile imageFile,
			@RequestParam(required = false) MultipartFile musicFile,
			@RequestParam(required = false) MultipartFile hqMusicFile,
			@RequestParam(required = false) MultipartFile videoFile,
			@RequestParam(required = false) MultipartFile hqVideoFile,
			WxContent content, HttpServletRequest req) throws IOException {
		String realpath = req.getSession().getServletContext()
				.getRealPath("/upload/");
		String contentRealPath = System.getProperty("ssweb.root");
		String msgType = content.getMsgType();
		if ("image".equals(msgType)) {
			String suffix = imageFile.getOriginalFilename().substring(
					imageFile.getOriginalFilename().lastIndexOf("."));
			String imageUrl = realpath + File.separator + UUID.randomUUID() + suffix;
			FileUtils.copyInputStreamToFile(imageFile.getInputStream(),
					new File(imageUrl));
			content.setPicUrl(StringUtils
					.replace(imageUrl, contentRealPath, ""));
		} else if ("voice".equals(msgType)) {
			String suffix = musicFile.getOriginalFilename().substring(
					musicFile.getOriginalFilename().lastIndexOf("."));
			String voiceUrl = realpath + File.separator + UUID.randomUUID() + suffix;
			FileUtils.copyInputStreamToFile(musicFile.getInputStream(),
					new File(voiceUrl));
			content.setMusicUrl(StringUtils.replace(voiceUrl, contentRealPath,
					""));
			if (null != hqMusicFile && !hqMusicFile.isEmpty()) {
				String suffix1 = hqMusicFile.getOriginalFilename().substring(
						hqMusicFile.getOriginalFilename().lastIndexOf("."));
				String hqvoiceUrl = realpath + File.separator + UUID.randomUUID()
						+ suffix1;
				FileUtils.copyInputStreamToFile(hqMusicFile.getInputStream(),
						new File(hqvoiceUrl));
				content.setHqmusicUrl(StringUtils.replace(hqvoiceUrl,
						contentRealPath, ""));
			}
		} else if ("video".equals(msgType)) {
			String suffix = videoFile.getOriginalFilename().substring(
					videoFile.getOriginalFilename().lastIndexOf("."));
			String videoUrl = realpath + File.separator + UUID.randomUUID() + suffix;
			FileUtils.copyInputStreamToFile(videoFile.getInputStream(),
					new File(videoUrl));
			content.setMusicUrl(StringUtils.replace(videoUrl, contentRealPath,
					""));
			if (null != hqVideoFile && !hqVideoFile.isEmpty()) {
				String suffix1 = hqVideoFile.getOriginalFilename().substring(
						hqVideoFile.getOriginalFilename().lastIndexOf("."));
				String hqvideoUrl = realpath + File.separator + UUID.randomUUID()
						+ suffix1;
				FileUtils.copyInputStreamToFile(hqVideoFile.getInputStream(),
						new File(hqvideoUrl));
				content.setHqmusicUrl(StringUtils.replace(hqvideoUrl,
						contentRealPath, ""));
			}
		}
		content.setUserId(AppContext.getUserId());
		contentService.save(content);
		return "redirect:/content/init";
	}

	@RequestMapping("/addContent/{msgType}")
	public String addContent(@PathVariable("msgType") String msgType,
			Model model) {
		model.addAttribute("contentTypes",
				PageConstants.buildContentTypesListByType(msgType));
		model.addAttribute("msgType", msgType);
		model.addAttribute("wxContent", new WxContent());
		return PageConstants.PAGE_CONTENT_DETAIL;
	}

	@RequestMapping("/saveContent")
	@ResponseBody
	public String saveContent(
			@RequestParam(required = false) MultipartFile imageFile,
			@RequestParam(required = false) MultipartFile musicFile,
			@RequestParam(required = false) MultipartFile hqMusicFile,
			@RequestParam(required = false) MultipartFile videoFile,
			@RequestParam(required = false) MultipartFile hqVideoFile,
			@Valid WxContent content, HttpServletRequest req) {
		try {
			this.submit(imageFile, musicFile, hqMusicFile, videoFile,
					hqVideoFile, content, req);
		} catch (IOException e) {
			return "0";
		}
		return content.getMsgType();

	}
}
