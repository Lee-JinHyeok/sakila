package sakila.address.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.Address;
import sakila.address.model.AddressDao;
import sakila.customer.model.City;


@WebServlet("/address/insertAddress")
public class InsertAddress extends HttpServlet {
	private AddressDao addressDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");//응답 세팅
		addressDao = new AddressDao();
		
		int cityId = Integer.parseInt(request.getParameter("cityId"));
		//int countryId = Integer.parseInt(request.getParameter("countryId"));
		String address = request.getParameter("address");
		String address2 = request.getParameter("address2");
		String district = request.getParameter("district");
		String postalCode = request.getParameter("postalCode");
		String phone = request.getParameter("phone");
		
		Address a = new Address();
		
		a.setCity(new City());
		a.getCity().setCityId(cityId);
		a.setAddress(address);
		a.setAddress2(address2);
		a.setDistrict(district);
		a.setPostalCode(postalCode);
		a.setPhone(phone);

		addressDao.insertAddress(a);
		
		Gson gson = new Gson();
		String jsonList = gson.toJson(a);
		System.out.println(jsonList);
		response.getWriter().write(jsonList);	
	}

}
