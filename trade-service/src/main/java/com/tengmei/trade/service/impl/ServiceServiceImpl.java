package com.tengmei.trade.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.tengmei.trade.domain.Service;
import com.tengmei.trade.domain.ServiceCatalog;
import com.tengmei.trade.domain.Store;
import com.tengmei.trade.repository.ServiceCatalogRepository;
import com.tengmei.trade.repository.ServiceRepository;
import com.tengmei.trade.service.ServiceService;

@org.springframework.stereotype.Service
@Transactional
public class ServiceServiceImpl implements ServiceService {
	@Autowired
	ServiceRepository serviceRepository;
	@Autowired
	ServiceCatalogRepository serviceCatalogRepository;

	@Override
	public Service create(Service service) {
		service.setCatalog(serviceCatalogRepository.findOne(service.getCatalog().getId()));
		return serviceRepository.save(service);
	}

	@Override
	public List<Service> findByStoreAndCatalog(Store store, ServiceCatalog catalog) {
		return serviceRepository.findByStoreAndCatalogAndDeleted(store, catalog, false);
	}

	@Override
	public Service findById(Long id) {
		return serviceRepository.findOne(id);
	}

	@Override
	public void delete(Long id) {
		Service service = serviceRepository.findOne(id);
		service.setDeleted(true);
		serviceRepository.save(service);
	}

}
