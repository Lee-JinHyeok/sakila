package sakila.customer.model;

import sakila.address.model.Address;
import sakila.business.model.Store;

public class Customer {
   private int customerId;
   private Store store;
   private String firstName;
   private String lastName;
   private String email;
   private Address Address;
   private String active;
   private String createDate;
   private String lastUpdate;
   public int getCustomerId() {
      return customerId;
   }
   public void setCustomerId(int customerId) {
      this.customerId = customerId;
   }
	   public Store getStore() {
		return store;
	}
	public void setStore(Store store) {
		this.store = store;
	}
public String getFirstName() {
      return firstName;
   }
   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }
   public String getLastName() {
      return lastName;
   }
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
	
	   public Address getAddress() {
		return Address;
	}
	public void setAddress(Address address) {
		Address = address;
	}
	public String getActive() {
      return active;
   }
   public void setActive(String active) {
      this.active = active;
   }
   public String getCreateDate() {
      return createDate;
   }
   public void setCreateDate(String createDate) {
      this.createDate = createDate;
   }
   public String getLastUpdate() {
      return lastUpdate;
   }
   public void setLastUpdate(String lastUpdate) {
      this.lastUpdate = lastUpdate;
   }
@Override
public String toString() {
	return "Customer [customerId=" + customerId + ", store=" + store + ", firstName=" + firstName + ", lastName="
			+ lastName + ", email=" + email + ", address=" + Address + ", active=" + active + ", createDate="
			+ createDate + ", lastUpdate=" + lastUpdate + "]";
}

   
   


}