package com.tengmei.trade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengmei.trade.domain.Tag;
import com.tengmei.trade.repository.TagRepository;
import com.tengmei.trade.service.TagService;

@Service
public class TagServiceImpl implements TagService {
	@Autowired
	private TagRepository tagRepository;

	@Override
	public List<Tag> getAll() {
		return tagRepository.findAll();
	}

}
