package net.chat.controllers;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import net.chat.constants.MessageTypeConstants;
import net.chat.constants.PageConstants;
import net.chat.domain.WxContent;
import net.chat.service.ContentService;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;

	@RequestMapping("/addContent")
	public String addText(Model model) {
		model.addAttribute("messageTypes",
				MessageTypeConstants.getMessageTypeMultimediaList());
		model.addAttribute("contentForm", new WxContent());
		return PageConstants.PAGE_CONTENT_DETAIL;
	}

	@RequestMapping("/saveContent")
	@ResponseBody
	public String saveContent(
			@RequestParam(required = false) MultipartFile imageFile,
			@RequestParam(required = false) MultipartFile musicFile,
			@RequestParam(required = false) MultipartFile hqMusicFile,
			@RequestParam(required = false) MultipartFile vidioFile,
			@RequestParam(required = false) MultipartFile hqVidioFile,
			@Valid WxContent content, HttpServletRequest req,
			HttpServletResponse response) {
		String realpath = req.getSession().getServletContext()
				.getRealPath("/upload/");
		String imageUrl = null;
		String musicUrl = null;
		String hqMusicUrl = null;
		String vidioUrl = null;
		String hqVidioUrl = null;

		try {
			if (imageFile != null && !imageFile.isEmpty()) {
				imageUrl = realpath + "/" + imageFile.getOriginalFilename();
				FileUtils.copyInputStreamToFile(imageFile.getInputStream(),
						new File(imageUrl));

			}
			if (musicFile != null && !musicFile.isEmpty()) {
				musicUrl = realpath + "/" + musicFile.getOriginalFilename();
				FileUtils.copyInputStreamToFile(musicFile.getInputStream(),
						new File(musicUrl));
			}
			if (hqMusicFile != null && !hqMusicFile.isEmpty()) {
				hqMusicUrl = realpath + "/" + hqMusicFile.getOriginalFilename();
				FileUtils.copyInputStreamToFile(hqMusicFile.getInputStream(),
						new File(hqMusicUrl));
			}
			if (vidioFile != null && !vidioFile.isEmpty()) {
				vidioUrl = realpath + "/" + vidioFile.getOriginalFilename();
				FileUtils.copyInputStreamToFile(vidioFile.getInputStream(),
						new File(vidioUrl));
			}
			if (hqVidioFile != null && !hqVidioFile.isEmpty()) {
				hqVidioUrl = realpath + "/" + hqVidioFile.getOriginalFilename();
				FileUtils.copyInputStreamToFile(hqVidioFile.getInputStream(),
						new File(hqVidioUrl));
			}
		} catch (IOException e) {
			return "0";
		}
		String contentRealPath = System.getProperty("ssweb.root");
		if (content.getMsgType().equals("image")) {
			content.setPicUrl(StringUtils
					.replace(imageUrl, contentRealPath, ""));
		}
		if (content.getMsgType().equals("voice")) {
			content.setMusicUrl(StringUtils.replace(musicUrl, contentRealPath,
					""));
			content.setHqmusicUrl(StringUtils.replace(hqMusicUrl,
					contentRealPath, ""));
		} else if (content.getMsgType().equals("video")) {
			content.setMusicUrl(StringUtils.replace(vidioUrl, contentRealPath,
					""));
			content.setHqmusicUrl(StringUtils.replace(hqVidioUrl,
					contentRealPath, ""));
		}
		contentService.save(content);
		return "1";

	}
}
