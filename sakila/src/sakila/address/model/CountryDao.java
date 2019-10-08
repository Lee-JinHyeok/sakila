package sakila.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.customer.model.Country;
import sakila.db.DBHelper;

public class CountryDao {
	public List<Country> selectCountryListAll(){
		List<Country> list = new ArrayList<Country>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT country_id, country FROM country ORDER BY country_id DESC";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Country c = new Country();
				c.setCountryId(rs.getInt("country_id"));
				c.setCountry(rs.getString("country"));
				list.add(c);
			}
		}	catch(Exception e) {
			e.printStackTrace();
		}	finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
		
	
	public int selectCountryCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT(*) FROM country";
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

	
	public List<Country> selectCountryList(int currentPage){
		List<Country> list = new ArrayList<Country>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		final int ROW_PER_PAGE = 10;//paging
		int beginRow = (currentPage - 1)*ROW_PER_PAGE;
		
		
		String sql = "SELECT country_id, country, last_update FROM country ORDER BY country_id DESC LIMIT ?, ?";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, ROW_PER_PAGE);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				Country c = new Country();
				c.setCountryId(rs.getInt("country_id"));
				c.setCountry(rs.getString("country"));
				c.setLastUpdate(rs.getString("last_update"));
				list.add(c);
			}
		}	catch(Exception e) {
			e.printStackTrace();
		}	finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
	
	public void insertCountry(Country Country) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO country(country, last_update) VALUES(?, now())";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1,  Country.getCountry());
			stmt.executeUpdate();
			
		}	catch(Exception e) {
			e.printStackTrace();
		}	finally {
			DBHelper.close(null, stmt, conn);
		}
	}
}
