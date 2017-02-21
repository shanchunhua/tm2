package com.tengmei.trade.service;

import java.util.List;

import com.tengmei.trade.domain.Service;
import com.tengmei.trade.domain.ServiceCatalog;
import com.tengmei.trade.domain.Store;

public interface ServiceService {
	public Service create(Service service);

	public List<Service> findByStoreAndCatalog(Store store, ServiceCatalog catalog);
}
