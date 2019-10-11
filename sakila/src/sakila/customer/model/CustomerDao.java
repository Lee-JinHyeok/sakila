package sakila.customer.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.address.model.Address;
import sakila.business.model.Store;
import sakila.db.DBHelper;

public class CustomerDao {
	//고객추가를 해야하는데 AddressDao의 insertAddress 함수가 같이 실행 된다.
	public void insertCustomer(Connection conn, Customer customer)throws Exception {
		//Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO customer"
				+ "(store_id, first_name, last_name, email, address_id, active, create_date, last_update) "
				+ "VALUES (?, ?, ?, ?, ?, 1, now(), now())";
		
		//conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, customer.getStore().getStoreId());
			stmt.setString(2, customer.getFirstName());
			stmt.setString(3, customer.getLastName());
			stmt.setString(4, customer.getEmail());
			stmt.setInt(5, customer.getAddress().getAddressId());
			stmt.executeUpdate();
			
		
	}
	public List<Customer> selectCustomerList(int currentPage){
		List<Customer> list = new ArrayList<Customer>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		final int ROW_PER_PAGE = 10;//paging
		int beginRow = (currentPage - 1)*ROW_PER_PAGE;
		// + 
		String sql = "SELECT c.customer_id, c.store_id, c.first_name, c.last_name, c.email, c.address_id, c.active, c.create_date, c.last_update "
				+ "FROM address a INNER JOIN customer c "
				+ "ON a.address_id = c.address_id "
				+ "WHERE c.address_id "
				+ "ORDER BY c.customer_id DESC LIMIT ?, ?";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, ROW_PER_PAGE);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Customer c = new Customer();
				c.setAddress(new Address());
				c.setStore(new Store());
				c.setCustomerId(rs.getInt("c.customer_id"));
				c.getStore().setStoreId(rs.getInt("c.store_id"));
				c.setFirstName(rs.getString("c.first_name"));
				c.setLastName(rs.getString("c.last_name"));
				c.setEmail(rs.getString("c.email"));
				c.getAddress().setAddressId(rs.getInt("c.address_id"));
				c.setActive(rs.getString("c.active"));
				c.setCreateDate(rs.getString("c.create_date"));
				c.setLastUpdate(rs.getString("c.last_update"));
				list.add(c);
			}
		}	catch(Exception e) {
			e.printStackTrace();
		}	finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
	
	
	
	public int selectCustomerCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT(*) FROM customer";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();

			if(rs.next()) {
				count = rs.getInt("count(*)");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			DBHelper.close(rs, stmt, conn);
		}
		return count;
	}
}
