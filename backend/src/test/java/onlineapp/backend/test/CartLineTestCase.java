package onlineapp.backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import onlineapp.backend.dao.CartLineDAO;
import onlineapp.backend.dao.ProductDAO;
import onlineapp.backend.dao.UserDAO;
import onlineapp.backend.dto.Cart;
import onlineapp.backend.dto.CartLine;
import onlineapp.backend.dto.Product;
import onlineapp.backend.dto.User;

public class CartLineTestCase {
	
	private static AnnotationConfigApplicationContext context;
	
	private static CartLineDAO cartLineDAO;
	private static ProductDAO productDAO;
	private static UserDAO userDAO;
	
	
	private User user = null;
	private Cart cart = null;
	private Product product = null;
	private CartLine cartLine = null;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("onlineapp.backend");
		context.refresh();
		
		productDAO = (ProductDAO) context.getBean("productDAO");
		userDAO = (UserDAO) context.getBean("userDAO");
		cartLineDAO = (CartLineDAO) context.getBean("cartLineDAO");

	}
	
	
	@Test
	public void testAddNewCartLine() {
		// 1. get the user
		
		user = userDAO.getByEmail("u1@gmail.com");
		
		//2. fetch the cart
		
		cart = user.getCart();
		
		//3. get the product
		product = productDAO.get(1);
		
		//3. create new cart line
		cartLine = new CartLine();
		cartLine.setBuyingprice(product.getUnitprice());
		cartLine.setProductCount(cartLine.getProductCount()+1);
		cartLine.setTotal(cartLine.getProductCount() * product.getUnitprice());
		cartLine.setAvailable(true);
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
		
		assertEquals("Failed to add cartline", true, cartLineDAO.add(cartLine));
		
		//update the cart
		cart.setGrandTotal(cart.getGrandTotal()+cartLine.getTotal());
		cart.setCartLines(cart.getCartLines()+1);
		
		assertEquals("Failed to update cartline", true, cartLineDAO.updateCart(cart));
	}
	

}
