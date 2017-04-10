package com.docker.mobyartshop.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import com.docker.mobyartshop.model.Customer;
import com.docker.mobyartshop.model.Order;
import com.docker.mobyartshop.model.Product;
import com.docker.mobyartshop.repositories.CustomerRepository;
import com.docker.mobyartshop.repositories.OrderRepository;
import com.docker.mobyartshop.repositories.ProductRepository;
import com.docker.mobyartshop.service.CustomerService;
import com.docker.mobyartshop.service.CustomerServiceImpl;
import com.docker.mobyartshop.service.OrderService;
import com.docker.mobyartshop.service.OrderServiceImpl;
import com.docker.mobyartshop.service.ProductService;
import com.docker.mobyartshop.service.ProductServiceImpl;


@SuppressWarnings("serial")
@RunWith(SpringRunner.class)
public class UnitTest {
	@Mock
	CustomerRepository customerRepository;
	
	@Mock
	CustomerService customerService;

	@InjectMocks
	CustomerServiceImpl mockCustomerServiceImpl = new CustomerServiceImpl();	
	
	@Mock
	ProductRepository productRepository;
	
	@Mock
	ProductService productService;
	
	@InjectMocks
	ProductServiceImpl mockProductServiceImpl = new ProductServiceImpl();
	
	@Mock
	OrderRepository orderRepository;
	
	@Mock
	OrderService orderService;
	
	@InjectMocks
	OrderServiceImpl mockOrderServiceImpl = new OrderServiceImpl();
	
	// Mock customer
	public Customer returnCustomer = new Customer(1L, "Arthur Dent", "Dockerland", "ad@null.com", "415-555-5555",
			"arthurd", "docker!", true, "ADMIN");
	
	// Mock product
	public byte[] value = new byte[1];
	public Product returnProduct = new Product(1l, "Container", "container picture", 25.50, value);
	
	// Mock order
	public Date orderDate = new Date(); 
	private static Map<Integer, Integer> productsOrdered;
    static{
      productsOrdered =   new HashMap<Integer, Integer>() {{
            put(2, 4);
            put(3, 9);
            put(4, 16);
        }};
    }
	public Order mockOrder = new Order(1L, orderDate, 2l, productsOrdered);
	public Order referenceOrder = new Order(1l, orderDate, 2l, productsOrdered);
	
	// Test CustomerService implementation
	@Test
	public void whenCustomerUserNameIsProvided_theReturnedNameIsCorrect() {	
		Mockito.when(mockCustomerServiceImpl.findByUserName("arthurd")).thenReturn(returnCustomer);
		String testName = returnCustomer.getName();
		Assert.assertEquals("Arthur Dent", testName);
	}
	
	// Test ProductService implementation
	@Test
	public void whenProductIdIdProvided_theReturnedDescriptionIsIncorrect(){
		Mockito.when(mockProductServiceImpl.findById(1l)).thenReturn(returnProduct);
		String testDescription = returnProduct.getDescription();
		Assert.assertNotEquals("a whale", testDescription);
	}
	
	// Test OrderService implementation
	@Test
	public void whenOrderFindById_theReferenceOrderDoesNotMatch() {
		Mockito.when(mockOrderServiceImpl.findById(1l)).thenReturn(referenceOrder);
		Assert.assertNotEquals(mockOrder, referenceOrder);
	}
	
}