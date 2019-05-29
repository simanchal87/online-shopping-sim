package onlineapp.backend.daoimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import onlineapp.backend.dao.CategoryDAO;
import onlineapp.backend.dto.Category;

@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {

	private static List<Category> categories = new ArrayList<>();

	static {

		// First categoty

		Category category = new Category();
		category.setId(1);
		category.setName("Television");
		category.setDescription("Description for the television");
		category.setImageURL("CAT_1.png");
		categories.add(category);

		// Second categoty
		category = new Category();
		category.setId(2);
		category.setName("Mobile");
		category.setDescription("Description for the Mobile");
		category.setImageURL("CAT_2.png");
		categories.add(category);

		// Third categoty
		category = new Category();
		category.setId(3);
		category.setName("Laptop");
		category.setDescription("Description for the Laptop");
		category.setImageURL("CAT_3.png");
		categories.add(category);

	}

	@Override
	public List<Category> list() {
		// TODO Auto-generated method stub
		return categories;
	}

	@Override
	public Category get(int id) {
		
		for(Category category : categories)
		{
			if(category.getId() == id)
			{
				return category;
			}
		}
		return null;
	}

}
