package onlineapp.frontend.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import onlineapp.backend.dao.CategoryDAO;
import onlineapp.backend.dao.ProductDAO;
import onlineapp.backend.dto.Category;
import onlineapp.backend.dto.Product;
import onlineapp.frontend.exception.ProductNotFoundException;

@Controller
public class pageController

{
	private static final Logger logger = LoggerFactory.getLogger(pageController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	
	@RequestMapping(value = { "/", "/home", "/index" })

	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Home");
		
		logger.info("Inside pageController index method - INFO");
		logger.debug("Inside pageController index method - DEBUG");
		
		//Passing the list of categories
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userClickHome", true);
		return mv;
	}
	


	@RequestMapping(value = {"/about"})

	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		mv.addObject("userClickAbout", true);
		return mv;
	}
	
	@RequestMapping(value = {"/contact"})

	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "Contact Us");
		mv.addObject("userClickContact", true);
		return mv;
	}
	
	//Methods to load all the products and based on category
	
	@RequestMapping(value = { "/show/all/products"})

	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "All Products");
		//Passing the list of categories
		mv.addObject("categories", categoryDAO.list());
		mv.addObject("userClickAllProducts", true);
		return mv;
	}
	
	
	//Methods to load all the products and based on category
	
	@RequestMapping(value = { "/show/category/{id}/products"})

	public ModelAndView showCategoryProducts(@PathVariable("id")int id) {
		ModelAndView mv = new ModelAndView("page");
		
		//CategoryDAO to fatch single category
		
		Category category = null;
		category = categoryDAO.get(id);
		mv.addObject("title", category.getName());
		
		//Passing the list of categories
		mv.addObject("categories", categoryDAO.list());
		
		//passing the single category
		mv.addObject("category", category);
		
		mv.addObject("userClickCategoryProducts", true);
		return mv;
	}
	
	//viewing a single product
	
	@RequestMapping(value= "/show/{id}/product")
	public ModelAndView showSingleProduct(@PathVariable int id) throws ProductNotFoundException
	{
		ModelAndView mv = new ModelAndView("page");
		
		Product product = productDAO.get(id);
		
		if(product==null) throw new ProductNotFoundException();
		
		//update the view count
		product.setViews(product.getViews()+1);
		productDAO.update(product);
		
		mv.addObject("title", product.getName());
		mv.addObject("product",product);
		
		mv.addObject("userClickShowProduct", true);
		
		return mv;
	}
	
	//having similar mapping to our flow id
	@RequestMapping(value = {"/register"})

	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("title", "About Us");
		return mv;
	}
	
	//Login
	@RequestMapping(value = {"/login"})

	public ModelAndView login(@RequestParam(name="error", required = false) String error,
								@RequestParam(name="logout", required = false) String logout) {
		ModelAndView mv = new ModelAndView("login");
		
		if(error  != null) {
			mv.addObject("message", "Invalid user name and password.");
		}
		
		if(logout != null) {
			mv.addObject("logout", "You successfully logout.");
		}
		
		mv.addObject("title", "Login");
		return mv;
	}
	
	
	//access denied page
		@RequestMapping(value = {"/access-denied"})

		public ModelAndView accessDeniedPage() {
			ModelAndView mv = new ModelAndView("error");
			mv.addObject("title", "403 - Access denied");
			mv.addObject("errorTitle", "Aha! Catch you.");
			mv.addObject("errorDescription", "You rae not authorized to view this page.");
			return mv;
		}
		
	//Logout option
		
	@RequestMapping(value="/perform/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
	

	/*
	 * @RequestMapping(value="/test") public ModelAndView
	 * test(@RequestParam(value="greeting", required=false) String greeting) {
	 * 
	 * if (greeting == null){ greeting = "hello there"; }
	 * 
	 * ModelAndView mv = new ModelAndView("page"); mv.addObject("greeting",
	 * greeting); return mv; }
	 * 
	 * @RequestMapping(value="/test/{greeting}") public ModelAndView
	 * test(@PathVariable("greeting") String greeting) {
	 * 
	 * if (greeting == null){ greeting = "hello there"; }
	 * 
	 * ModelAndView mv = new ModelAndView("page"); mv.addObject("greeting",
	 * greeting); return mv; }
	 * 
	 * 
	 */

}
