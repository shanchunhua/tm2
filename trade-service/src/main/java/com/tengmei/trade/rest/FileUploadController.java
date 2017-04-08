package com.tengmei.trade.rest;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping("/rest/fileupload")
public class FileUploadController {
	@RequestMapping(method = RequestMethod.POST)
	public RestResult<String> upload(MultipartHttpServletRequest request) throws ParseException {
		String fileName = null;
		Iterator<String> itr = request.getFileNames();
		if (itr.hasNext()) {
			MultipartFile mpf = request.getFile(itr.next());
			System.out.println(mpf.getOriginalFilename() + " uploaded!");
			try {
				fileName = System.nanoTime() + ".png";
				FileUtils.copyInputStreamToFile(mpf.getInputStream(), new File("/home/sam/" + fileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
//		try {
//			Thread.sleep(30000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return new RestResult(fileName);
	}
}
