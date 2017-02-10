package com.tengmei.trade.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class Card extends BaseEntity {

	private CardType type;

	private String name;

	@ManyToOne
	@JoinColumn(name = "store_id")
	private Store store;

	@Column(precision = 10, scale = 2)
	private BigDecimal price;

	private Boolean deleted = false;

	// only for times card
	private Integer times;

	@Column(precision = 10, scale = 2)
	private BigDecimal discount;
}
