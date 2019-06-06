package onlineapp.backend.dao;

import java.util.List;

import onlineapp.backend.dto.Product;

public interface ProductDAO 
{
	Product get(int productid);
	
	List<Product> list();
	boolean add(Product product);
	boolean update(Product product);
	boolean delete (Product product);
	
	//Business Methods
	List<Product> listActiveProductl();
	List<Product> listActiveProductByCategory(int categoryId);
	List<Product> listLatestActiveProducts(int count);
	
	

}
