package com.tengmei.trade.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.Service;
import com.tengmei.trade.domain.Staff;
import com.tengmei.trade.domain.Store;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
	List<Staff> findByStoreAndDeleted(Store store, Boolean deleted);

	int countByStoreAndDeleted(Store store, Boolean deleted);

}
