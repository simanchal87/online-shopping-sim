package onlineapp.backend.dao;

import java.util.List;

import onlineapp.backend.dto.Category;

public interface CategoryDAO {
	
	List<Category> list();
	
	Category get (int id);

}
	