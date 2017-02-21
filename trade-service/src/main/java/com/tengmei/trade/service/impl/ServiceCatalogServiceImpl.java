package com.tengmei.trade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tengmei.trade.domain.ServiceCatalog;
import com.tengmei.trade.repository.ServiceCatalogRepository;
import com.tengmei.trade.service.ServiceCatalogService;

@Service
public class ServiceCatalogServiceImpl implements ServiceCatalogService {
	@Autowired
	private ServiceCatalogRepository serviceCatalogRepository;

	@Override
	public List<ServiceCatalog> findAll() {
		return serviceCatalogRepository.findAll();
	}

	@Override
	public ServiceCatalog findById(Long id) {
		return serviceCatalogRepository.findOne(id);
	}

}
