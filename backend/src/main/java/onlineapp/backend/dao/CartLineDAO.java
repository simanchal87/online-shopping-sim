package onlineapp.backend.dao;

import java.util.List;

import onlineapp.backend.dto.Cart;
import onlineapp.backend.dto.CartLine;

public interface CartLineDAO {
	
	//common methods
	public CartLine get(int id);
	public boolean add(CartLine cartLine);
	public boolean update(CartLine cartLine);
	public boolean delete(CartLine cartLine);
	public List<CartLine> list (int cartId);
	
	//business methods which related to CartLine
	public List<CartLine> listAvailable (int cartId);
	public CartLine getByCartAndProduct(int cartId, int productId);
	boolean updateCart(Cart cart);

}
