package onlineapp.backend.dao;

import java.util.List;

import onlineapp.backend.dto.Address;
import onlineapp.backend.dto.User;

public interface UserDAO {

	
	boolean addUser(User user);
	User getByEmail(String email);
	
	
	boolean addAddress(Address address);
	//Alternative way to do so to avoid mo query performe
	
	//Address getBillingAddress(int userId);
	//List <Address> listShippingAddress(int userId);
	
	
	Address getBillingAddress(User user);
	List <Address> listShippingAddress(User user);
	
	/*
	 * Moved it to CartLine DAO
	 * boolean updateCart(Cart cart);
	 * */
	
	
	
}
