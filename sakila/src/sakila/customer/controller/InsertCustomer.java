package sakila.customer.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sakila.address.model.Address;
import sakila.business.model.Store;
import sakila.customer.model.City;
import sakila.customer.model.Customer;
import sakila.customer.service.CustomerService;


@WebServlet("/customer/insertCustomer")
public class InsertCustomer extends HttpServlet {
	
	private CustomerService customerService;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		customerService = new CustomerService();
		
		// address request.getParameter("")
		Address a = new Address();
		int cityId = Integer.parseInt(request.getParameter("cityId"));
		System.out.println("Customer InsertCustomer cityId : " + cityId);
		String address = request.getParameter("address");
		System.out.println("Customer InsertCustomer address : " + address);
		String address2 = request.getParameter("address2");
		System.out.println("Customer InsertCustomer address2 : " + address2);
		String district = request.getParameter("district");
		System.out.println("Customer InsertCustomer district : " + district);
		String postalCode = request.getParameter("postalCode");
		System.out.println("Customer InsertCustomer postalCode : " + postalCode);
		String phone = request.getParameter("phone");
		System.out.println("Customer InsertCustomer phone : " + phone);
		
		a.setCity(new City());
		a.getCity().setCityId(cityId);
		a.setAddress(address);
		a.setAddress2(address2);
		a.setDistrict(district);
		a.setPostalCode(postalCode);
		a.setPhone(phone);
		
		
		
		// customer request.getParameter("")
		Customer c = new Customer();
		int storeId = Integer.parseInt(request.getParameter("storeId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String emailName = request.getParameter("emailName");
		//int cityId = Integer.parseInt(request.getParameter("cityId"));
		
		c.setStore(new Store());
		c.getStore().setStoreId(storeId);
		c.setFirstName(firstName);
		c.setLastName(lastName);
		c.setEmail(emailName);
		
		
		
		customerService.insertCustomer(a, c);

	}

}

