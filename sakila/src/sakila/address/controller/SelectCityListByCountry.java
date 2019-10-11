package sakila.address.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.CityDao;
import sakila.customer.model.City;

@WebServlet("/address/selectCityListByCountry")
	//address.html에서 country를 고르면 자동을 city목록이 나온다. 응답 호출 해주는 서블릿
public class SelectCityListByCountry extends HttpServlet {
	private CityDao cityDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");//응답 세팅
		int countryId = Integer.parseInt(request.getParameter("countryId"));
		System.out.println("Select City List By Country 넘어온 param countryId : "+countryId);
		
		cityDao = new CityDao();
		List<City> list = cityDao.selectCityListByCountry(countryId);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(list);
		
		response.getWriter().write(jsonStr);//응답
	}

}
