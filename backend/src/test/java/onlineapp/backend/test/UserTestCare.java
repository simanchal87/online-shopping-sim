package onlineapp.backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import onlineapp.backend.dao.UserDAO;
import onlineapp.backend.dto.Address;
import onlineapp.backend.dto.Cart;
import onlineapp.backend.dto.User;

public class UserTestCare {
	
	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private static Address address;
	
	private User user = null;
	private Cart cart = null;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("onlineapp.backend");
		context.refresh();

		userDAO = (UserDAO) context.getBean("userDAO");

	}

	/*
	@Test
	public void TestAddAddress()
	{
		user = new User();
		user.setFirstName("Abc");
		user.setLastName("Zyz");
		user.setEmail("ab@gmail.com");
		user.setContactNumber("1234512345");
		user.setRole("USER");
		user.setPassword("123");
		
		assertEquals("FAiled to add user", true, userDAO.addUser(user));
		
		
		
		address = new Address();
		address.setAddressLineOne("Line One");
		address.setAddressLineTwo("Line 2");
		address.setCity("Mimbai");
		address.setState("Maharashtra");
		address.setCountry("India");
		address.setPostalCode("400093");
		address.setBilling(true);
		
		
		address.setUser(user);
		assertEquals("failed to add address", true, userDAO.addAddress(address));

		
		address = new Address();
		address.setAddressLineOne("Line One");
		address.setAddressLineTwo("Line 2");
		address.setCity("Delhi");
		address.setState("Delhi");
		address.setCountry("India");
		address.setPostalCode("100001");
		address.setShipping(true);
		
		address.setUser(user);
		assertEquals("failed to add address", true, userDAO.addAddress(address));

	}*/
	
	
	/*@Test
	public void testAddAddress() {
		
		user = userDAO.getByEmail("ab@gmail.com");
		
		address = new Address();
		address.setAddressLineOne("Line One HYD");
		address.setAddressLineTwo("Line 2 HYD");
		address.setCity("Hyderabad");
		address.setState("Telengana");
		address.setCountry("India");
		address.setPostalCode("500015");
		address.setShipping(true);
		
		address.setUser(user);
		assertEquals("failed to add address", true, userDAO.addAddress(address));
		
	}*/
	
	
	@Test
	public void testGetAddAddress() {
		
		user = userDAO.getByEmail("ab@gmail.com");
		
	
		
	
		assertEquals("failed to fatch list of shipping addresses", 2, userDAO.listShippingAddress(user).size());
		
		assertEquals("failed to fatch list of billing addresses", "Mimbai", userDAO.getBillingAddress(user).getCity());
		
	}
}
