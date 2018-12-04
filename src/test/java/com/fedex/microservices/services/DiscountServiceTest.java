package com.fedex.microservices.services;

import com.fedex.microservices.beans.DiscountBean;
import com.fedex.microservices.dao.DiscountRepository;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = DiscountService.class) //specify which configuration files to use to load the ApplicationContext for your test.
public class DiscountServiceTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

    @MockBean
    private DiscountRepository discountRepository;

    @Autowired
    private DiscountService discountService;
    
    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testFindByIdReturnsDiscounts() { 
        DiscountBean discountBean = discountBeanSupplier.get();
        Optional<DiscountBean> discountBeanOptional = Optional.of(discountBean);

        when(discountRepository.findById(Mockito.anyLong())).thenReturn(discountBeanOptional);
        assertNotNull(discountService.findById(Mockito.anyLong()));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testFindByIdWithNegativeNumberInput() { 
        discountService.findById(-1L);
    }

    @Test
    public void testFindByIdWithNegativeNumberInputSecondMetod() { 
       thrown.expect(IllegalArgumentException.class);
       thrown.expectMessage("Id should be a positive number.");
       discountService.findById(-1L);
    }
    
    @Test
    public void testFindByIdForUnavailableDiscounts() {
        Optional<DiscountBean> discountBeanOptional = Optional.empty();

        when(discountRepository.findById(Mockito.anyLong())).thenReturn(discountBeanOptional);
        assertNull(discountService.findById(Mockito.anyLong()));
    }

    @Test
    public void testFindByTypeReturnsDiscounts() {
        DiscountBean discountBean = discountBeanSupplier.get();

        when(discountRepository.findByType(Mockito.anyString())).thenReturn(discountBean);
        assertNotNull(discountService.findByType(Mockito.anyString()));
    }

    @Test
    public void testFindByTypeForUnavailableDiscounts() {
        DiscountBean discountBean = null;
        when(discountRepository.findByType(Mockito.anyString())).thenReturn(discountBean);
        assertNull(discountService.findByType(Mockito.anyString()));
    }


    @Test
    public void testFindByIdAndTypeReturnsDiscounts() {
        DiscountBean discountBean = discountBeanSupplier.get();

        when(discountRepository.findByIdAndType(Mockito.anyLong(), Mockito.anyString())).thenReturn(discountBean);
        assertNotNull(discountService.findByIdAndType(Mockito.anyLong(), Mockito.anyString()));
    }

    @Test
    public void testFindByIdAndTypeForUnavailableDiscounts() {
        DiscountBean discountBean = null;
        when(discountRepository.findByIdAndType(Mockito.anyLong(), Mockito.anyString())).thenReturn(discountBean);
        assertNull(discountService.findByIdAndType(Mockito.anyLong(), Mockito.anyString()));
    }

    @Test
    public void testFindAllReturnsDiscounts() {
        DiscountBean discountBean = discountBeanSupplier.get();
        List<DiscountBean> discountBeanList = new ArrayList<>();
        discountBeanList.add(discountBean);

        when(discountRepository.findAll()).thenReturn(discountBeanList);
        assertNotNull(discountService.findAll());
        assertEquals(1, discountBeanList.size());
    }

    @Test
    public void testFindAllReturnsEmpty() {
        List<DiscountBean> discountBeanList = null;

        when(discountRepository.findAll()).thenReturn(discountBeanList);
        assertNull(discountService.findAll());
    }

    @Test
    public void testCreateDiscount() {
        DiscountBean discountBean = discountBeanSupplier.get();
        List<DiscountBean> discountBeanList = new ArrayList<>();
        discountBeanList.add(discountBean);

        when(discountRepository.save(discountBean)).thenReturn(discountBean);
        assertNotNull(discountService.createDiscount(discountBean));
    }
    
    Supplier<DiscountBean> discountBeanSupplier = () -> {
        DiscountBean discountBean = new DiscountBean();
        discountBean.setId(10L);
        discountBean.setStart_date("01/01/2018");
        discountBean.setEnd_date("12/31/2018");
        discountBean.setDescription("Promotion for IRU users");
        discountBean.setDisplay_name("iru_offer");
        discountBean.setType("iru");
        discountBean.setAmount(200);
        return discountBean;
    };
    
    @After
    public void postProcess(){
        // any post processing required after each test goes here
    }
}
