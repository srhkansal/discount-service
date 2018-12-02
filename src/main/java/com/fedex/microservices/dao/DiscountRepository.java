package com.fedex.microservices.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fedex.microservices.beans.DiscountBean;

public interface DiscountRepository extends JpaRepository<DiscountBean, Long>{
	DiscountBean findByType(String type);
	DiscountBean findByIdAndType(Long id, String type);
}
