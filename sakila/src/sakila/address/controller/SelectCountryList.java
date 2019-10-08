package sakila.address.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.CountryDao;
import sakila.customer.model.Country;


@WebServlet("/address/selectCountryList")
public class SelectCountryList extends HttpServlet {
	private CountryDao countryDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		countryDao = new CountryDao();
		response.setContentType("application/json");

		System.out.println("currentPage : " + request.getParameter("currentPage"));

		int currentPage = 1;
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}

		List<Country> list = countryDao.selectCountryList(currentPage);		
		Gson gson = new Gson();
		String jsonList = gson.toJson(list);
		System.out.println(jsonList);
		response.getWriter().write(jsonList);
	}

}