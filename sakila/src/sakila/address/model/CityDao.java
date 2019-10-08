package sakila.address.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import sakila.customer.model.City;
import sakila.customer.model.Country;
import sakila.db.DBHelper;

public class CityDao {
	public int selectCityCount() {
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String sql = "SELECT COUNT(*) FROM city";
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
	
	public List<City> selectCityList(int currentPage){
		List<City> list = new ArrayList<City>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		final int ROW_PER_PAGE = 10;//paging
		int beginRow = (currentPage - 1)*ROW_PER_PAGE;
		
		String sql = "SELECT ci.city_id, ci.city, ci.country_id, con.country FROM city ci INNER JOIN country con "
				+ "ON ci.country_id = con.country_id WHERE ci.country_id"
				+ " ORDER BY ci.city_id DESC LIMIT ?, ?";
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, ROW_PER_PAGE);
			
			rs = stmt.executeQuery();
			while(rs.next()) {
				City c = new City();
				c.setCountry(new Country());
				c.setCityId(rs.getInt("ci.city_id"));
				c.setCity(rs.getString("ci.city"));
				c.getCountry().setCountryId(rs.getInt("ci.country_id"));
				c.getCountry().setCountry(rs.getString("con.country"));
				list.add(c);
			}
		}	catch(Exception e) {
			e.printStackTrace();
		}	finally {
			DBHelper.close(rs, stmt, conn);
		}
		return list;
	}
	public void insertCity(City city) {
		Connection conn = null;
		PreparedStatement stmt = null;
		String sql = "INSERT INTO city(city, country_id, last_update) VALUES (?, ?, now())";
		
		try {
			conn = DBHelper.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, city.getCity());
			stmt.setInt(2, city.getCountry().getCountryId());
			
			stmt.executeUpdate();
			
		}	catch(Exception e) {
			e.printStackTrace();
		}	finally {
			DBHelper.close(null, stmt, conn);
		}
	}
}
