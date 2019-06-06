package onlineapp.backend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import onlineapp.backend.dao.CategoryDAO;
import onlineapp.backend.dto.Category;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionfactory;

	
	@Override
	public List<Category> list() {
		
		String selectActiveCategory = "FROM Category where active = :active";
		Query query = sessionfactory.getCurrentSession().createQuery(selectActiveCategory);
		query.setParameter("active", true);
		return query.getResultList();
	}

	// Getting single category based on ID
	@Override
	public Category get(int id) {

		return sessionfactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	@Override

	public boolean add(Category category) {

		try {

			sessionfactory.getCurrentSession().persist(category);
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(Category category) {
		try {

			sessionfactory.getCurrentSession().update(category);
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean delete(Category category) {
		try {
			
			category.setActive(false);

			sessionfactory.getCurrentSession().update(category);
			return true;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}

/*
 *
 * private static List<Category> categories = new ArrayList<>();
 * 
 * static {
 * 
 * // First categoty
 * 
 * Category category = new Category(); category.setId(1);
 * category.setName("Television");
 * category.setDescription("Description for the television");
 * category.setImageURL("CAT_1.png"); categories.add(category);
 * 
 * // Second categoty category = new Category(); category.setId(2);
 * category.setName("Mobile");
 * category.setDescription("Description for the Mobile");
 * category.setImageURL("CAT_2.png"); categories.add(category);
 * 
 * // Third categoty category = new Category(); category.setId(3);
 * category.setName("Laptop");
 * category.setDescription("Description for the Laptop");
 * category.setImageURL("CAT_3.png"); categories.add(category);
 * 
 * }
 * 
 * public Category get(int id) {
 * 
 * for (Category category : categories) { if (category.getId() == id) { return
 * category; } } return null; }
 */