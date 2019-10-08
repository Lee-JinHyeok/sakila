package sakila.customer.model;

public class City {
	private int cityId;
	private String city;
	private Country country;
	private String lastUpdate;
	public int getCityId() {
		return cityId;
	}
	public void setCityId(int cityId) {
		this.cityId = cityId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Country getCountry() {
		return country;
	}
	public void setCountry(Country country) {
		this.country = country;
	}
	public String getLastUpdate() {
		return lastUpdate;
	}
	public void setLastUpdate(String lastUpdate) {
		this.lastUpdate = lastUpdate;
	}
	@Override
	public String toString() {
		return "City [cityId=" + cityId + ", city=" + city + ", country=" + country + ", lastUpdate=" + lastUpdate
				+ "]";
	}
	
}
