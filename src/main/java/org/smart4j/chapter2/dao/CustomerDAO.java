package org.smart4j.chapter2.dao;

import org.smart4j.chapter2.po.Customer;
import org.smart4j.chapter2.utils.DatabaseUtil;

import java.util.List;
import java.util.Map;

/**
 * @author: gethin
 * @create: 2018-09-02 13:37
 * @description:
 **/
public class CustomerDAO {
	public List<Customer> getCustomerList() {
		String sql = "select * from customer";
		return DatabaseUtil.queryEntityList(Customer.class, sql);
	}
	
	public Customer getCustomer(long id) {
		String sql = "select * from customer where id=?";
		return DatabaseUtil.queryEntity(Customer.class, sql, id);
	}
	
	public boolean updateCustomer(Customer customer) {
		return DatabaseUtil.update(customer);
	}
	
	public boolean insertCustomer(Customer customer) {
		return DatabaseUtil.insert(customer);
	}
}
