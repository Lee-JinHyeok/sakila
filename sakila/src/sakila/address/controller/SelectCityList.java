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


@WebServlet("/address/selectCityList")
public class SelectCityList extends HttpServlet {
	private CityDao cityDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		cityDao = new CityDao();
		response.setContentType("application/json");

		System.out.println("currentPage : " + request.getParameter("currentPage"));

		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		List<City> list = cityDao.selectCityList(currentPage);		
		Gson gson = new Gson();
		String jsonList = gson.toJson(list);
		System.out.println(jsonList);
		response.getWriter().write(jsonList);
	}
}