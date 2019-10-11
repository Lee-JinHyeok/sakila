package sakila.address.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.Address;
import sakila.address.model.AddressDao;



@WebServlet("/address/selectAddressList")
public class SelectAddressList extends HttpServlet {
	private AddressDao addressDao;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		addressDao = new AddressDao();
		response.setContentType("application/json");

		System.out.println("select Address List 넘어온 currentPage : " + request.getParameter("currentPage"));

		int currentPage = Integer.parseInt(request.getParameter("currentPage"));


		List<Address> list = addressDao.selectAddressList(currentPage);		
		Gson gson = new Gson();
		String jsonList = gson.toJson(list);
		System.out.println(jsonList);
		response.getWriter().write(jsonList);		
		
	}

}
