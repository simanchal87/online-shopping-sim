package onlineapp.frontend.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import onlineapp.backend.dao.CategoryDAO;
import onlineapp.backend.dao.ProductDAO;
import onlineapp.backend.dto.Category;
import onlineapp.backend.dto.Product;

@Controller
@RequestMapping("/manage")
public class ManagementController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired 
	ProductDAO productDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

	@RequestMapping(value="/products", method=RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation", required=false) String operation)
	{
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		
		Product nProduct = new Product();
		
	
		//Set few of the fields
		nProduct.setSupplierId(1);
		nProduct.setActive(true);
		
		mv.addObject("product", nProduct);
		
		if(operation!=null) {
			if(operation.equals("product")) {
				mv.addObject("message", "Product submitted Successfully!");
			}
		}	
		
		return mv;
	}
	
	//Handeling product submition
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String handelProductSubmission(@ModelAttribute("product") Product mProduct)
	{
		logger.info(mProduct.toString());
		//create a new product record.
		productDAO.add(mProduct);
		
		return "redirect:/manage/products?operation=product";
	}
	
	
	//Returning categories for all the requests mapping
	@ModelAttribute("categories")
	public List<Category> getCategories()
	{
		return categoryDAO.list();
	}
	
}
