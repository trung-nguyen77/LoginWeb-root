package com.chippo.LoginWeb.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DownloadFileController {

	@GetMapping("/download")
	public void download(HttpServletResponse response, 
			@RequestParam(name = "filename") String filename) {
		/// LUU FILE TREN SERVER
		String UPLOAD_LOCATION = "D:\\Anh";

		File file = new File(UPLOAD_LOCATION + File.separator + filename);
		try {
			response.setContentType("image/png");
			FileInputStream fileInputStream = new FileInputStream(file);
			IOUtils.copy(fileInputStream, response.getOutputStream());
		} catch (Exception e) {
			System.out.println("Loi " + e);
		}
	}
}
