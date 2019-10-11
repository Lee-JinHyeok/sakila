package sakila.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import sakila.customer.model.City;
import sakila.db.DBHelper;

public class AddressDao {
	//고객추가를 해야하는데 CustomerDao의 insertCustomer 함수가 같이 실행 된다.
	//오버로딩 customer과 address 같이 넣을때 사용 conn인자 추가
	public int insertAddress(Connection conn,Address Address)throws Exception {
		int addressId = 0;
		//Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO Address(address, address2, district, city_id, postal_code, phone, last_update) "
				+ "VALUES (?, ?, ?, ?, ?, ?, now())";
		
			//conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, Address.getAddress());
			stmt.setString(2, Address.getAddress2());
			stmt.setString(3, Address.getDistrict());
			stmt.setInt(4, Address.getCity().getCityId());
			stmt.setString(5, Address.getPhone());
			stmt.setString(6, Address.getPostalCode());
			
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				addressId = rs.getInt(1);
			}
		
		return addressId;
	}
	// 오버 로딩 순수 address만 넣을때 사용
	public void insertAddress(Address Address) {
		int addressId = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = "INSERT INTO Address(address, address2, district, city_id, postal_code, phone, last_update) "
				+ "VALUES (?, ?, ?, ?, ?, ?, now())";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setString(1, Address.getAddress());
			stmt.setString(2, Address.getAddress2());
			stmt.setString(3, Address.getDistrict());
			stmt.setInt(4, Address.getCity().getCityId());
			stmt.setString(5, Address.getPhone());
			stmt.setString(6, Address.getPostalCode());
		
			stmt.executeUpdate();
			rs = stmt.getGeneratedKeys();
			if(rs.next()) {
				addressId = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
         try {
	            
	            stmt.close();
	            conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }         
	      }
	}
	
	public List<Address> selectAddressList(int currentPage){
	      System.out.println("::: selectAddressList 실행 :::");
	      List<Address> list = new ArrayList<Address>();
	      Connection conn = null;
	      PreparedStatement stmt = null;
	      ResultSet rs = null;
	      int ROW_PER_PAGE = 10;
	      int beginRow = (currentPage -1)*ROW_PER_PAGE;
	      System.out.println("currnetPage: "+currentPage+" beginrow: "+beginRow);
	      String sql = "SELECT a.address_id, a.address, a.address2, c.city_id, a.district, a.postal_code, a.phone, a.last_update"
	            + " FROM address a INNER JOIN city c ON c.city_id = a.city_id order by a.address_id DESC limit ?, ?";
	      try {
	         conn = DBHelper.getConnection();
	         stmt = conn.prepareStatement(sql);
	         stmt.setInt(1, beginRow);
	         stmt.setInt(2, ROW_PER_PAGE);
	         rs = stmt.executeQuery();
	         while(rs.next()) {
	            Address a = new Address();
	            a.setAddressId(rs.getInt("a.address_id"));
	            a.setAddress(rs.getString("a.address"));
	            a.setAddress2(rs.getString("a.address2"));
	            a.setCity(new City());
	            a.getCity().setCityId(rs.getInt("c.city_id"));
	            a.setDistrict(rs.getString("a.district"));
	            a.setPostalCode(rs.getString("a.postal_code"));
	            a.setPhone(rs.getString("a.phone"));
	            a.setLastUpdate(rs.getString("a.last_update"));
	            list.add(a);
	         }         
	         
	      }catch (Exception e) {
	         e.printStackTrace();
	      }finally {
	         try {
	            
	            stmt.close();
	            conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }         
	      }      
	      return list;
	   }

	
	
	
	
	public int selectAdderessCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT(*) FROM address";
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
