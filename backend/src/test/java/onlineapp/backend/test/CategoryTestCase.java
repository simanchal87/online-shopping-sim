package onlineapp.backend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import onlineapp.backend.dao.CategoryDAO;
import onlineapp.backend.dto.Category;

public class CategoryTestCase {
	public static AnnotationConfigApplicationContext context;
	public static CategoryDAO categoryDAO;
	private Category category;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("onlineapp.backend");
		context.refresh();

		categoryDAO = (CategoryDAO) context.getBean("categoryDAO");

	}

	@Test
	public void testCRUDCategory() {

		category = new Category();
		category.setName("Laptop");
		category.setDescription("Description for the Laptop");
		category.setImageURL("CAT_1.png");
		assertEquals("Successfully insert a category in table..", true,categoryDAO.add(category));
		
		category = new Category();
		category.setName("Television");
		category.setDescription("Description for the Television");
		category.setImageURL("CAT_2.png");
		assertEquals("Successfully insert a category in table..", true,categoryDAO.add(category));

		// Fatching and updating the category
		category = categoryDAO.get(2);
		category.setName("TV");
		assertEquals("Successfully updated single category in table..", true, categoryDAO.update(category));

		// Delete the category
		assertEquals("Successfully deleted single category in table..", true, categoryDAO.delete(category));
		
		// Fatching the list of  the category
		assertEquals("Successfully fatched the list of categories from the table..", 1, categoryDAO.list().size());
	}

	/*
	 * @Test public void testListCategory() {
	 * 
	 * assertEquals("Successfully fatched the list of categories from the table.."
	 * ,2, categoryDAO.list().size()); }
	 */

	/*
	 * @Test public void testDeleteCategory() { category = categoryDAO.get(4);
	 * assertEquals("Successfully deleted single category in table..", true,
	 * categoryDAO.delete(category)); }
	 */

	/*
	 * @Test public void testUpdateCategory() { category = categoryDAO.get(4);
	 * category.setName("TV");
	 * assertEquals("Successfully updated single category in table..", true,
	 * categoryDAO.update(category)); }
	 */

	/*
	 * @Test public void testGetCategory(){ category = categoryDAO.get(3);
	 * assertEquals("Successfully fatched single category from table..",
	 * "Television",category.getName()); }
	 */

	/*
	 * @Test public void testAddCategory() { category = new Category();
	 * 
	 * category.setName("Television");
	 * category.setDescription("Description for the television");
	 * category.setImageURL("CAT_1.png");
	 * 
	 * assertEquals("Successfully insert a category in table..",
	 * true,categoryDAO.add(category));
	 * 
	 * }
	 */

}
