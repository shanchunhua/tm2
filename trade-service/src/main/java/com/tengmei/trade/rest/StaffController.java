package com.tengmei.trade.rest;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tengmei.trade.domain.GenderType;
import com.tengmei.trade.domain.Staff;
import com.tengmei.trade.domain.WechatUser;
import com.tengmei.trade.service.StaffService;
import com.tengmei.trade.service.WechatUserService;

@RestController
@RequestMapping("/rest/staffs")
public class StaffController {
	@Autowired
	StaffService staffService;
	@Autowired
	WechatUserService wechatUserService;

	@RequestMapping(method = RequestMethod.POST)
	public RestResult<Staff> createStaff(MultipartHttpServletRequest request) throws ParseException {
		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		user = wechatUserService.findById(user.getId());
		Iterator<String> itr = request.getFileNames();
		if (itr.hasNext()) {
			MultipartFile mpf = request.getFile(itr.next());
			System.out.println(mpf.getOriginalFilename() + " uploaded!");
			try {
				FileUtils.copyInputStreamToFile(mpf.getInputStream(), new File("/home/sam/1.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Staff staff = new Staff();
		String id = request.getParameter("id");
		if (id != null) {
			staff = staffService.findById(Long.valueOf(id));
		}
		staff.setName(request.getParameter("name"));
		staff.setGender(GenderType.valueOf(request.getParameter("gender")));
		staff.setLevel(Integer.valueOf(request.getParameter("level")));
		staff.setUser(user);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		staff.setBirthday(format.parse(request.getParameter("birthday")));
		staff.setStore(user.getStore());
		staff.setDescription(request.getParameter("description"));
		staff = staffService.create(staff);

		RestResult<Staff> result = new RestResult<Staff>(staff);
		return result;
	}

	@RequestMapping(value = "/level/{level}", method = RequestMethod.GET)
	public RestResult<List<Staff>> list(@PathVariable Integer level, HttpServletRequest request) {

		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		user = wechatUserService.findById(user.getId());

		RestResult<List<Staff>> result = new RestResult<>();

		List<Staff> staffs = staffService.findByStore(user.getStore());
		Collections.sort(staffs, new Comparator<Staff>() {
			public int compare(Staff o1, Staff o2) {
				if ((o1.getLevel() == level && o2.getLevel() == level)
						|| (o1.getLevel() != level && o2.getLevel() != level)) {
					return o2.getId().intValue() - o1.getId().intValue();
				} else {
					if (o1.getLevel() == level) {
						return 1;
					} else {
						return -1;
					}
				}
			};
		});
		result.setData(staffs);
		return result;
	}

	/**
	 * 查询特定级别的staff
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public RestResult<List<Staff>> list(HttpServletRequest request) {

		WechatUser user = (WechatUser) request.getSession().getAttribute("user");
		user = wechatUserService.findById(user.getId());

		RestResult<List<Staff>> result = new RestResult<>();

		result.setData(staffService.findByStore(user.getStore()));
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public RestResult<Staff> load(@PathVariable Long id) {

		RestResult<Staff> result = new RestResult<Staff>();

		result.setData(staffService.findById(id));
		return result;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public RestResult<Staff> del(@PathVariable Long id) {

		RestResult<Staff> result = new RestResult<Staff>();
		staffService.delete(id);

		return result;
	}
}
