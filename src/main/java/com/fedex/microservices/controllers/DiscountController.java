package com.fedex.microservices.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.fedex.microservices.beans.DiscountBean;
import com.fedex.microservices.services.DiscountService;

@RestController
public class DiscountController {

	@Autowired
	private DiscountService discountService;
	
	@GetMapping("/discount-service/discounts/{id}")
    public DiscountBean findById(@PathVariable Long id){
    	return discountService.findById(id);
    }

    @GetMapping("/discount-service/discounts/{id}/type/{type}")
    public DiscountBean findByIdAndType(@PathVariable Long id, @PathVariable String type){
        return discountService.findByIdAndType(id, type);
    }

    @GetMapping("/discount-service/discounts/type/{type}")
    public DiscountBean findByType(@PathVariable String type){
        return discountService.findByType(type);
    }

	@GetMapping("/discount-service/discounts")
    public List<DiscountBean> findAll(){
    	return discountService.findAll();
    }
	
    @PostMapping(name="/discount-service/discounts")
    public DiscountBean createDiscount(@RequestBody DiscountBean pricingBean){
        DiscountBean discountBean =  discountService.createDiscount(pricingBean);
        return discountBean;
    }
  }
