package com.fedex.microservices.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fedex.microservices.beans.DiscountBean;
import com.fedex.microservices.services.DiscountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import static org.mockito.ArgumentMatchers.anyLong;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DiscountController.class)
public class DiscountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private DiscountService discountService;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() throws Exception{ 
        DiscountBean discountBean =  discountBeanSupplier.get();


        when(discountService.findById(anyLong())).thenReturn(discountBean);
        this.mockMvc.perform(get("/discount-service/discounts/{id}", 1L)).andExpect(status().isOk());

        verify(discountService, times(1)).findById(1L);
        verifyNoMoreInteractions(discountService);
    }

    @Test
    public void testFindByType() throws Exception{
        DiscountBean discountBean =  discountBeanSupplier.get();
        when(discountService.findByType(anyString())).thenReturn(discountBean);
        this.mockMvc.perform(get("/discount-service/discounts/type/{type}", "iru")).andExpect(status().isOk());

        verify(discountService, times(1)).findByType("iru");
        verifyNoMoreInteractions(discountService);
    }

    @Test
    public void testFindByIdAndType() throws Exception{
        DiscountBean discountBean =  discountBeanSupplier.get();
        when(discountService.findByIdAndType(anyLong(), anyString())).thenReturn(discountBean);
        this.mockMvc.perform(get("/discount-service/discounts/{id}/type/{type}", 1L, "iru")).andExpect(status().isOk());

        verify(discountService, times(1)).findByIdAndType(1L,"iru");
        verifyNoMoreInteractions(discountService);
    }

    @Test
    public void testFindAll() throws Exception{
        DiscountBean discountBean =  discountBeanSupplier.get();
        List<DiscountBean> discountBeanList = new ArrayList<>();
        discountBeanList.add(discountBean);

        when(discountService.findAll()).thenReturn(discountBeanList);
        this.mockMvc.perform(get("/discount-service/discounts")).andExpect(status().isOk());

        verify(discountService, times(1)).findAll();
        verifyNoMoreInteractions(discountService);
    }

    @Test
    public void testCreateDiscount() throws Exception{
        DiscountBean discountBean =  discountBeanSupplier.get();
        String json = mapper.writeValueAsString(discountBean);

        this.mockMvc.perform(post("/discount-service/discounts")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)).andExpect(status().isOk());

        verify(discountService, times(1)).createDiscount(any(DiscountBean.class));
        verifyNoMoreInteractions(discountService);
    }

    @Test
    public void testDeleteDiscount() throws Exception{
        this.mockMvc.perform(delete("/discount-service/discounts/{id}", 1L)).andExpect(status().isOk());

        verify(discountService, times(1)).deleteDiscount(1L);
        verifyNoMoreInteractions(discountService);
    }

    Supplier<DiscountBean> discountBeanSupplier = () -> {
            DiscountBean discountBean = new DiscountBean();
        discountBean.setId(1L);
        discountBean.setStart_date("01/01/2018");
        discountBean.setEnd_date("12/31/2018");
        discountBean.setDescription("Promotion for IRU users");
        discountBean.setDisplay_name("iru_offer");
        discountBean.setType("iru");
        discountBean.setAmount(200);
        return discountBean;
    };
}
