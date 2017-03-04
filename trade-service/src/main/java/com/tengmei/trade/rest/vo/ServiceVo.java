package com.tengmei.trade.rest.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.tengmei.trade.domain.Service;
import com.tengmei.trade.domain.ServiceCatalog;

public class ServiceVo {
	private String name;
	private Long cid;
	private BigDecimal level3Price;
	private BigDecimal level3Rate;
	private BigDecimal level4Price;
	private BigDecimal level4Rate;
	private BigDecimal level5Price;
	private BigDecimal level5Rate;

	public List<Service> getServices() {
		List<Service> services = new ArrayList<Service>();

		if (level3Price != null) {
			Service service = new Service();
			ServiceCatalog catalog = new ServiceCatalog();
			catalog.setId(cid);
			service.setName(name);
			service.setCatalog(catalog);
			service.setLevel(3);
			service.setPrice(level3Price);
			service.setCommissionRate(level3Rate);
			services.add(service);
		}
		if (level4Price != null) {
			Service service = new Service();
			ServiceCatalog catalog = new ServiceCatalog();
			catalog.setId(cid);
			service.setName(name);
			service.setCatalog(catalog);
			service.setLevel(4);
			service.setPrice(level4Price);
			service.setCommissionRate(level4Rate);
			services.add(service);
		}

		if (level5Price != null) {
			Service service = new Service();
			ServiceCatalog catalog = new ServiceCatalog();
			catalog.setId(cid);
			service.setName(name);
			service.setCatalog(catalog);
			service.setLevel(5);
			service.setPrice(level5Price);
			service.setCommissionRate(level5Rate);
			services.add(service);
		}

		return services;
	}

	public ServiceVo fromServices(List<Service> services) {
		ServiceVo vo = new ServiceVo();
		Service service = services.get(0);
		vo.setCid(service.getCatalog().getId());
		vo.setName(service.getName());
		for (Service item : services) {
			if (item.getLevel() == 3) {
				vo.setLevel3Price(item.getPrice());
				vo.setLevel3Rate(item.getCommissionRate());
			} else if (item.getLevel() == 4) {
				vo.setLevel4Price(item.getPrice());
				vo.setLevel4Rate(item.getCommissionRate());
			} else if (item.getLevel() == 5) {
				vo.setLevel5Price(item.getPrice());
				vo.setLevel5Rate(item.getCommissionRate());
			}
		}
		return vo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCid() {
		return cid;
	}

	public void setCid(Long cid) {
		this.cid = cid;
	}

	public BigDecimal getLevel3Price() {
		return level3Price;
	}

	public void setLevel3Price(BigDecimal level3Price) {
		this.level3Price = level3Price;
	}

	public BigDecimal getLevel3Rate() {
		return level3Rate;
	}

	public void setLevel3Rate(BigDecimal level3Rate) {
		this.level3Rate = level3Rate;
	}

	public BigDecimal getLevel4Price() {
		return level4Price;
	}

	public void setLevel4Price(BigDecimal level4Price) {
		this.level4Price = level4Price;
	}

	public BigDecimal getLevel4Rate() {
		return level4Rate;
	}

	public void setLevel4Rate(BigDecimal level4Rate) {
		this.level4Rate = level4Rate;
	}

	public BigDecimal getLevel5Price() {
		return level5Price;
	}

	public void setLevel5Price(BigDecimal level5Price) {
		this.level5Price = level5Price;
	}

	public BigDecimal getLevel5Rate() {
		return level5Rate;
	}

	public void setLevel5Rate(BigDecimal level5Rate) {
		this.level5Rate = level5Rate;
	}
}
