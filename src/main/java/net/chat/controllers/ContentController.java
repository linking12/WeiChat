package net.chat.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.chat.constants.MessageTypeConstants;
import net.chat.constants.PageConstants;
import net.chat.domain.WxContent;
import net.chat.formbean.SimpleBean;
import net.chat.service.ContentService;

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
	public String init(@RequestParam(value="msgType",required=false) String msgType ,@RequestParam(value = "pageNo", defaultValue = "1") int pageNo,Model model) {
		
		List<SimpleBean> contentTypes = PageConstants.buildContentTypesList();
		model.addAttribute("contentTypes", contentTypes);
		
		Page<WxContent> contents = contentService.findAllBaseMultimedia(msgType, pageNo);
		model.addAttribute("contents", contents);

		model.addAttribute("msgType", msgType);
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
	public String edit(@PathVariable("id") Long id,Model model) {
		WxContent wxContent=contentService.findOne(id);
		model.addAttribute("wxContent",wxContent);
		
		model.addAttribute("contentTypes", PageConstants.buildContentTypesListByType(wxContent.getMsgType()));
		
		return PageConstants.PAGE_CONTENT_DETAIL_1;
	}

	@RequestMapping("/delete")
	public String delete(Model model) {
		return PageConstants.PAGE_CONTENT_LIST;
	}

	@RequestMapping("/submit")
	public String submit(@RequestParam(required = false) MultipartFile imageFile, @RequestParam(required = false) MultipartFile musicFile, @RequestParam(required = false) MultipartFile hqMusicFile,
			@RequestParam(required = false) MultipartFile videoFile, @RequestParam(required = false) MultipartFile hqVideoFile, WxContent content, Model model, HttpServletRequest req)
			throws IOException {
		String realpath = req.getSession().getServletContext().getRealPath("/upload/");
		String contentRealPath = System.getProperty("ssweb.root");
		String msgType = content.getMsgType();
		if ("image".equals(msgType)) {
			String imageUrl = realpath + "/" + imageFile.getOriginalFilename();
			FileUtils.copyInputStreamToFile(imageFile.getInputStream(), new File(imageUrl));
			content.setPicUrl(StringUtils.replace(imageUrl, contentRealPath, ""));
		} else if ("voice".equals(msgType)) {
			String voiceUrl = realpath + "/" + musicFile.getOriginalFilename();
			FileUtils.copyInputStreamToFile(musicFile.getInputStream(), new File(voiceUrl));
			content.setMusicUrl(StringUtils.replace(voiceUrl, contentRealPath, ""));
			if (null != hqMusicFile&&!hqMusicFile.isEmpty()) {
				String hqvoiceUrl = realpath + "/" + hqMusicFile.getOriginalFilename();
				FileUtils.copyInputStreamToFile(hqMusicFile.getInputStream(), new File(hqvoiceUrl));
				content.setHqmusicUrl(StringUtils.replace(hqvoiceUrl, contentRealPath, ""));
			}
		} else if ("video".equals(msgType)) {
			String videoUrl = realpath + "/" + videoFile.getOriginalFilename();
			FileUtils.copyInputStreamToFile(videoFile.getInputStream(), new File(videoUrl));
			content.setMusicUrl(StringUtils.replace(videoUrl, contentRealPath, ""));
			if (null != hqVideoFile&&!hqVideoFile.isEmpty()) {
				String hqvideoUrl = realpath + "/" + hqVideoFile.getOriginalFilename();
				FileUtils.copyInputStreamToFile(hqVideoFile.getInputStream(), new File(hqvideoUrl));
				content.setHqmusicUrl(StringUtils.replace(hqvideoUrl, contentRealPath, ""));
			}
		}
	 contentService.save(content);
	 return "redirect:/content/init";
	}

	@RequestMapping("/addContent")
	public String addText(Model model) {
		model.addAttribute("messageTypes", MessageTypeConstants.getMessageTypeMultimediaList());
		model.addAttribute("contentForm", new WxContent());
		return PageConstants.PAGE_CONTENT_DETAIL;
	}

	@RequestMapping("/saveContent")
	@ResponseBody
	public String saveContent(@RequestParam(required = false) MultipartFile imageFile, @RequestParam(required = false) MultipartFile musicFile,
			@RequestParam(required = false) MultipartFile hqMusicFile, @RequestParam(required = false) MultipartFile vidioFile, @RequestParam(required = false) MultipartFile hqVidioFile,
			@Valid WxContent content, HttpServletRequest req, HttpServletResponse response) {
		String realpath = req.getSession().getServletContext().getRealPath("/upload/");
		String imageUrl = null;
		String musicUrl = null;
		String hqMusicUrl = null;
		String vidioUrl = null;
		String hqVidioUrl = null;

		try {
			if (imageFile != null && !imageFile.isEmpty()) {
				imageUrl = realpath + "/" + imageFile.getOriginalFilename();
				FileUtils.copyInputStreamToFile(imageFile.getInputStream(), new File(imageUrl));

			}
			if (musicFile != null && !musicFile.isEmpty()) {
				musicUrl = realpath + "/" + musicFile.getOriginalFilename();
				FileUtils.copyInputStreamToFile(musicFile.getInputStream(), new File(musicUrl));
			}
			if (hqMusicFile != null && !hqMusicFile.isEmpty()) {
				hqMusicUrl = realpath + "/" + hqMusicFile.getOriginalFilename();
				FileUtils.copyInputStreamToFile(hqMusicFile.getInputStream(), new File(hqMusicUrl));
			}
			if (vidioFile != null && !vidioFile.isEmpty()) {
				vidioUrl = realpath + "/" + vidioFile.getOriginalFilename();
				FileUtils.copyInputStreamToFile(vidioFile.getInputStream(), new File(vidioUrl));
			}
			if (hqVidioFile != null && !hqVidioFile.isEmpty()) {
				hqVidioUrl = realpath + "/" + hqVidioFile.getOriginalFilename();
				FileUtils.copyInputStreamToFile(hqVidioFile.getInputStream(), new File(hqVidioUrl));
			}
		} catch (IOException e) {
			return "0";
		}
		String contentRealPath = System.getProperty("ssweb.root");
		if (content.getMsgType().equals("image")) {
			content.setPicUrl(StringUtils.replace(imageUrl, contentRealPath, ""));
		}
		if (content.getMsgType().equals("voice")) {
			content.setMusicUrl(StringUtils.replace(musicUrl, contentRealPath, ""));
			content.setHqmusicUrl(StringUtils.replace(hqMusicUrl, contentRealPath, ""));
		} else if (content.getMsgType().equals("video")) {
			content.setMusicUrl(StringUtils.replace(vidioUrl, contentRealPath, ""));
			content.setHqmusicUrl(StringUtils.replace(hqVidioUrl, contentRealPath, ""));
		}
		contentService.save(content);
		return "1";

	}
}
