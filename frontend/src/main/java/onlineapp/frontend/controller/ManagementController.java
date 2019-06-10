package onlineapp.frontend.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import onlineapp.backend.dao.CategoryDAO;
import onlineapp.backend.dao.ProductDAO;
import onlineapp.backend.dto.Category;
import onlineapp.backend.dto.Product;
import onlineapp.frontend.util.FileUploadUtility;
import onlineapp.frontend.validator.ProductValidator;

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
			else if (operation.equals("category")){
				mv.addObject("message", "Category submitted Successfully!");
			}
		}	
		
		return mv;
	}
	
	@RequestMapping(value="/{id}/product", method=RequestMethod.GET)
		public ModelAndView showEditProducts(@PathVariable int id)
	{
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProducts", true);
		mv.addObject("title", "Manage Products");
		
		//fatch the product from database
		Product nProduct = productDAO.get(id);
				
		//set the product fatched from database
		mv.addObject("product", nProduct);
		
			
		return mv;
	}
	
	
	
	
	
	//Handeling product submition
	@RequestMapping(value="/products", method=RequestMethod.POST)
	public String handelProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult results, Model model, HttpServletRequest request)
	{
		
		//check image validator
		if(mProduct.getId()==0) {
			new ProductValidator().validate(mProduct, results);
		} else {
			if(mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct, results);
			}
		}
		
		
		
		//check if there is any error in product submission
		
		if(results.hasErrors())
		{	
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation failed for product submission.");
			return "page";
		}
		
		
		logger.info(mProduct.toString());
		
		
		if (mProduct.getId() ==0) {
			//create a new product record.
			productDAO.add(mProduct);
		} else {
			//update a new product record.
			productDAO.update(mProduct);
		}
		
		
		if(!mProduct.getFile().getOriginalFilename().equals(""))
		{
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());
		}
		
		return "redirect:/manage/products?operation=product";
	}
	
	@RequestMapping(value="/product/{id}/activation", method=RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int id) {
		//this is going to fatch the product from database.
		Product product = productDAO.get(id);
		boolean isActive = product.isActive();
		//Active or deactive based on the vale of active field
		product.setActive(!product.isActive());
		//Updating the status in database
		productDAO.update(product);
		
		return (isActive)?
				
				"You have successfully deactivated the product with id " + product.getId()
				:"You have successfully activated the product with id " + product.getId();
				
	}
	
	//to handel category submission
	@RequestMapping(value="/category", method=RequestMethod.POST)
	public String handelCategorySubmission(@ModelAttribute Category category)
	{
		//add the new category
		categoryDAO.add(category);
		return "redirect:/manage/products?operation=category";
	}
	

	
	//Returning categories for all the requests mapping
	@ModelAttribute("categories")
	public List<Category> getCategories()
	{
		return categoryDAO.list();
	}
	
	@ModelAttribute("category")
	public Category GetCategory() {
		return new Category();
	}
	
}
