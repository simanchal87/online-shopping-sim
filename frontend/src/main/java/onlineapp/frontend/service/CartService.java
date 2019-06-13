package onlineapp.frontend.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onlineapp.backend.dao.CartLineDAO;
import onlineapp.backend.dao.ProductDAO;
import onlineapp.backend.dto.Cart;
import onlineapp.backend.dto.CartLine;
import onlineapp.backend.dto.Product;
import onlineapp.frontend.model.UserModel;

@Service("cartService")
public class CartService {
	
	@Autowired
	private CartLineDAO cartLineDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Autowired
	private HttpSession session;
	
	//get the cart from the user who is logged in.
	private Cart getCart() {
		return ((UserModel)session.getAttribute("userModel")).getCart();
	}
	
	//retruns the entire cart line
	public List<CartLine> getCartLines(){
		return cartLineDAO.list(this.getCart().getId());
	}
	

	public String manageCartLine(int cartLineId, int count) {
		
		//fetch the cartline 
		
		CartLine cartLine = cartLineDAO.get(cartLineId);
		
		if(cartLine == null) {
			return "result=error";
		}else {
			
			Product product = cartLine.getProduct();
			double oldTotal = cartLine.getTotal();
			
			//checking if the product quantity is available
			if(product.getQuantity() <= count) {
				
				return "result=unavailable";
			}
			
			
			
			
			cartLine.setProductCount(count);
			cartLine.setBuyingprice(product.getUnitprice());
			cartLine.setTotal(product.getUnitprice() * count);
			
			cartLineDAO.update(cartLine);
			
			Cart cart = this.getCart();cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
			
			cartLineDAO.updateCart(cart);
			
			return "result=updated";
		}
		
	}

	public String deleteCartLine(int cartLineId) {
		
		CartLine cartLine = cartLineDAO.get(cartLineId);
		
		if(cartLine == null) {
			return "result=error";
		}else {
			
			Cart cart = this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() - 1);
			cartLineDAO.updateCart(cart);
			
			cartLineDAO.delete(cartLine);
			
			return "result=deleted";
		}
		
	}

	public String addCartLine(int productId) {
		
		String response = null;
		
		Cart cart = this.getCart();
		CartLine cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), productId);
		
		if(cartLine == null) {
			
			//add a new cartline
			cartLine = new CartLine();
			Product product = productDAO.get(productId);
			
			//fetch the product
			cartLine.setCartId(cart.getId());
			cartLine.setProduct(product);
			cartLine.setBuyingprice(product.getUnitprice());
			cartLine.setProductCount(1);
			cartLine.setTotal(product.getUnitprice());
			cartLine.setAvailable(true);
			
			
			//updating cart
			cartLineDAO.add(cartLine);
			cart.setCartLines(cart.getCartLines()+1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			
			cartLineDAO.updateCart(cart);
			
			response = "result=added";
		} else {
			
			// check if the cartline has reached the maximum count
			if(cartLine.getProductCount() <3) {
				//update the product count for that cartline
				response = this.manageCartLine(cartLine.getId(), cartLine.getProductCount()+1);
			} else {
				response = "result=maximum";
			}
			
		}
		
		
		
		
		
		
		
		return response;
	}
	

}
