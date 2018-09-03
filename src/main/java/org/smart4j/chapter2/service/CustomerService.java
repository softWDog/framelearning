package org.smart4j.chapter2.service;

import javafx.scene.chart.PieChart;
import org.smart4j.chapter2.dao.CustomerDAO;
import org.smart4j.chapter2.po.Customer;
import org.smart4j.chapter2.utils.DatabaseUtil;

import java.util.List;
import java.util.Map;

/**
 * @author: gethin
 * @create: 2018-09-01 15:57
 * @description:
 **/
public class CustomerService {
	private CustomerDAO customerDAO;
	
	public CustomerService(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
	
	public List<Customer> getCustomerList() {
		return customerDAO.getCustomerList();
	}
	
	public Customer getCustomer(long id) {
		return customerDAO.getCustomer(id);
	}
	
	public boolean updateCustomer(Customer customer) {
		return customerDAO.updateCustomer(customer);
	}
	
	public boolean insertCustomer(Customer customer) {
		return customerDAO.insertCustomer(customer);
	}
}
