package com.tengmei.trade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tengmei.trade.domain.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

}
