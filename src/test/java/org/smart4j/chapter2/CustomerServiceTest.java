package org.smart4j.chapter2;

import org.junit.Test;
import org.smart4j.chapter2.dao.CustomerDAO;
import org.smart4j.chapter2.po.Customer;
import org.smart4j.chapter2.service.CustomerService;

import java.util.List;

/**
 * @author: gethin
 * @create: 2018-09-01 23:22
 * @description:
 **/
public class CustomerServiceTest {
	private CustomerService customerService = new CustomerService(new CustomerDAO());
	
	@Test
	public void getCustomerListTest() {
		List<Customer> list = customerService.getCustomerList();
		for (Customer customer : list) {
			System.out.println(customer);
		}
	}
	
	@Test
	public void getCustomerTest() {
		Customer customer = customerService.getCustomer(3);
		System.out.println(customer);
	}
	
	@Test
	public void insertCustomerTest(){
		Customer customer=new Customer();
		customer.setId(3);
		customer.setName("danny");
		customer.setEmail("78977@gmail.com");
		customer.setTelephone("14760928387");
		customer.setRemark("danny love swim");
		customerService.insertCustomer(customer);
	}
	
}
