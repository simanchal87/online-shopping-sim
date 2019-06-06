package onlineapp.backend.dao;

import java.util.List;

import onlineapp.backend.dto.Category;

public interface CategoryDAO {
	
	
	
	
	//Single Category
	Category get (int id);
	
	//List of Category
	List<Category> list();
	
	//Adding a Category
	boolean add(Category category);
	
	boolean update(Category category);
	
	boolean delete(Category category);

}
	