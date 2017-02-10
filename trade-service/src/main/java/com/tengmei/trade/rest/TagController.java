package com.tengmei.trade.rest;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tengmei.trade.domain.Tag;
import com.tengmei.trade.service.TagService;

@RestController
@RequestMapping("/rest/tags")
public class TagController {
	@Autowired
	private TagService tagService;

	public RestResult<List<Tag>> getAll(HttpServletRequest request) {
		RestResult<List<Tag>> result = new RestResult<>();
		result.setData(tagService.getAll());
		return result;
	}
}
