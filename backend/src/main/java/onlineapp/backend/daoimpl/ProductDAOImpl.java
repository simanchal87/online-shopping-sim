package onlineapp.backend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import onlineapp.backend.dao.ProductDAO;
import onlineapp.backend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	//Single
	@Override
	public Product get(int productid) {
		try {
			return sessionFactory.getCurrentSession().get(Product.class, Integer.valueOf(productid));
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

	//List
	@Override
	public List<Product> list() {
		
		return sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class).getResultList();
	}

	
	
	//Insert Data
	@Override
	public boolean add(Product product) {
		
		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
		
	}

	
	@Override
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean delete(Product product) {
		try {
			product.setActive(false);
			//Just called update method
			return this.update(product);
			
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Product> listActiveProductl() {
		String selectActiveProduct = "FROM Product WHERE active = :active";
		return sessionFactory.getCurrentSession()
				.createQuery(selectActiveProduct, Product.class)
				.setParameter("active", true).getResultList();
	}

	@Override
	public List<Product> listActiveProductByCategory(int categoryId) {
		String selectActiveProductByCategory = "FROM Product WHERE active = :active AND categoryId = :categoryId";
		return sessionFactory.getCurrentSession()
				.createQuery(selectActiveProductByCategory, Product.class)
				.setParameter("active", true)
				.setParameter("categoryId", categoryId)
				.getResultList();
	}

	@Override
	public List<Product> listLatestActiveProducts(int count) {
		return sessionFactory.getCurrentSession()
				.createQuery("FROM Product where active = :active OREDR BY id", Product.class)
				.setParameter("active", true)
				.setFirstResult(0)
				.setMaxResults(count)
				.getResultList();
	}

}
