package sakila.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import sakila.address.model.AddressDao;
import sakila.address.model.CityDao;
import sakila.address.model.CountryDao;
import sakila.business.model.PayMentDao;
import sakila.business.model.RentalDao;
import sakila.business.model.StaffDao;
import sakila.business.model.StoreDao;
import sakila.customer.model.CustomerDao;
import sakila.inventory.model.ActorDao;
import sakila.inventory.model.CategoryDao;
import sakila.inventory.model.FilmActorDao;
import sakila.inventory.model.FilmCategoryDao;
import sakila.inventory.model.FilmDao;
import sakila.inventory.model.FilmTextDao;
import sakila.inventory.model.InventoryDao;
import sakila.inventory.model.LanguageDao;


@WebServlet("/indexController")
public class IndexController extends HttpServlet {
	private CountryDao countryDao;
	private CityDao cityDao;
	private CustomerDao customerDao;
	private AddressDao addressDao;
	
	private PayMentDao payMentDao;
	private RentalDao rentalDao;
	private StaffDao staffDao;
	private StoreDao storeDao;
	
	private ActorDao actorDao;
	private CategoryDao categoryDao;
	private FilmActorDao filmActorDao;
	private FilmCategoryDao filmCategoryDao;
	private FilmDao filmDao;
	private FilmTextDao filmTextDao;
	private InventoryDao inventoryDao;
	private LanguageDao languageDao;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=UTF-8");
		
		//List<Integer> list = new ArrayList<Integer>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		//Customer Area
		countryDao = new CountryDao();
		int countryRow = countryDao.selectCountryCount();
		map.put("countryRow", countryRow);
		//list.add(countryRow); //autoboxing
		
		cityDao = new CityDao();
		int cityRow = cityDao.selectCityCount();
		map.put("cityRow",cityRow);
		
		customerDao = new CustomerDao();
		int customerRow = customerDao.selectCustomerCount();
		map.put("customerRow",customerRow);
		
		addressDao = new AddressDao();
		int addressRow = addressDao.selectAdderessCount();
		map.put("addressRow",addressRow);
		//busness Area
		payMentDao = new PayMentDao();
		int payMentRow = payMentDao.selectPayMentCount();
		map.put("payMentRow",payMentRow);
		
		rentalDao = new RentalDao();
		int rentalRow = rentalDao.selectRentalCount();
		map.put("rentalRow",rentalRow);
		
		staffDao = new StaffDao();
		int staffRow = staffDao.selectStaffCount();
		map.put("staffRow",staffRow);
		
		storeDao = new StoreDao();
		int storeRow = storeDao.selectStoreCount();
		map.put("storeRow",storeRow);
		// Inventory Area
		actorDao = new ActorDao();
		int actorRow = actorDao.selectActorCount();
		map.put("actorRow",actorRow);
		
		categoryDao = new CategoryDao();
		int categoryRow = categoryDao.selectCategoryCount();
		map.put("categoryRow",categoryRow);
		
		filmActorDao = new FilmActorDao();
		int filmActorRow = filmActorDao.selectFilmActorCount();
		map.put("filmActorRow",filmActorRow);
		
		filmCategoryDao = new FilmCategoryDao();
		int filmCategoryRow = filmCategoryDao.selectFilmCategoryCount();
		map.put("filmCategoryRow",filmCategoryRow);
		
		filmDao = new FilmDao();
		int filmRow = filmDao.selectFilmCount();
		map.put("filmRow",filmRow);
		
		filmTextDao = new FilmTextDao();
		int filmTextRow = filmTextDao.selectFilmTextCount();
		map.put("filmTextRow",filmTextRow);
		
		inventoryDao = new InventoryDao();
		int inventoryRow = inventoryDao.selectInventoryCount();
		map.put("inventoryRow",inventoryRow);
		
		languageDao = new LanguageDao();
		int languageRow = languageDao.selectLanguageCount();
		map.put("languageRow",languageRow);
		
		Gson gson = new Gson();
		String jsonStr = gson.toJson(map);
		
		response.getWriter().write(jsonStr);	//응답코드
	}
}










