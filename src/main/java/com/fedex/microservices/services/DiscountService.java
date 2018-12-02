package com.fedex.microservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fedex.microservices.beans.DiscountBean;
import com.fedex.microservices.dao.DiscountRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountService { 

	@Autowired
	private DiscountRepository discountRepository;

    public DiscountBean findById(Long id){
    	if(id < 0 ) {
    		throw new IllegalArgumentException("Id should be a positive number.");
    	} else {
    		Optional<DiscountBean> discountBean = discountRepository.findById(id);
            if(discountBean.isPresent()){
                return discountBean.get();
            }	
    	}
        return null;
    }

    public DiscountBean findByType(String type){
        DiscountBean discountBean = discountRepository.findByType(type);
        return discountBean;
    }

    public DiscountBean findByIdAndType(Long id, String type){
        DiscountBean discountBean = discountRepository.findByIdAndType(id, type);
        return discountBean;
    }

    public List<DiscountBean> findAll(){
        return discountRepository.findAll();
    }

    public DiscountBean createDiscount(DiscountBean pricingBean){
        return discountRepository.save(pricingBean);
    }
}
