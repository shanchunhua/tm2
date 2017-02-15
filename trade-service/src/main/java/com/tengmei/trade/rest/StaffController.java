package com.tengmei.trade.rest;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.ws.rs.Path;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tengmei.trade.service.StaffService;

@RestController
@Path("/staffs")
public class StaffController {
	@Autowired
	StaffService staffService;

	@RequestMapping(method = RequestMethod.POST)
	public RestResult createStaff(MultipartHttpServletRequest request) {
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = request.getFile(itr.next());
		System.out.println(mpf.getOriginalFilename() + " uploaded!");
		try {
			FileUtils.copyInputStreamToFile(mpf.getInputStream(), new File("/home/sam/1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(request.getParameter("name"));
		// staffService.create(staff);

		RestResult result = new RestResult();
		return result;
	}

	
}
