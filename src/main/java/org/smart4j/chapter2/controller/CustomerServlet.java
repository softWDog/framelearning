package org.smart4j.chapter2.controller;

import org.smart4j.chapter2.dao.CustomerDAO;
import org.smart4j.chapter2.po.Customer;
import org.smart4j.chapter2.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.undo.CannotUndoException;
import java.io.IOException;
import java.util.List;

/**
 * @author: gethin
 * @create: 2018-09-01 15:55
 * @description:
 **/
@WebServlet("/customer")
public class CustomerServlet extends HttpServlet {
	private CustomerService customerService;
	
	@Override
	public void init() throws ServletException {
		customerService = new CustomerService(new CustomerDAO());
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Customer> customerList = customerService.getCustomerList();
		req.setAttribute("customerList", customerList);
		req.getRequestDispatcher("/WEB-INF/jsp/customer.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
	}
}
