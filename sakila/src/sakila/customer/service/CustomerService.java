package sakila.customer.service;

import java.sql.Connection;
import java.sql.SQLException;

import sakila.address.model.Address;
import sakila.address.model.AddressDao;
import sakila.customer.model.Customer;
import sakila.customer.model.CustomerDao;
import sakila.db.DBHelper;

public class CustomerService {
	private AddressDao addressDao;
	private CustomerDao customerDao;
	public void insertCustomer(Address address, Customer customer) {
		
		Connection conn = null;
	
		try {
			conn = DBHelper.getConnection();
			conn.setAutoCommit(false);
			addressDao = new AddressDao();
			int addressId = addressDao.insertAddress(conn ,address);
			
			customerDao = new CustomerDao();
			customer.setAddress(new Address()); 
			
			customer.getAddress().setAddressId(addressId);
			customerDao.insertCustomer(conn, customer);
			conn.commit();
			
		}	catch(Exception e) {
			try {conn.rollback();}	catch(SQLException e1) {}
			e.printStackTrace();
		}	finally	{
			DBHelper.close(null, null, conn);
		}
		
	}
}
