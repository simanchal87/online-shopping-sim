package onlineapp.frontend.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.stereotype.Component;

import onlineapp.backend.dao.UserDAO;
import onlineapp.backend.dto.Address;
import onlineapp.backend.dto.Cart;
import onlineapp.backend.dto.User;
import onlineapp.frontend.model.RegisterModel;

@Component
public class RegisterHandler {
	
	@Autowired
	private UserDAO userDAO;
	
	public RegisterModel init() {
		return new RegisterModel();
	}
	
	public void addUser(RegisterModel registerModel, User user) {
		
		registerModel.setUser(user);
	}
	
	public void addBilling(RegisterModel registerModel, Address billing)
	{
		registerModel.setBilling(billing);
	}
	
	public String validateUser(User user, MessageContext error) {
		String transitionvalue = "success";
		
		//checking the if the password match with the confirm password
		if(!(user.getPassword().equals(user.getConfirmPassword()))){
			error.addMessage(new MessageBuilder().error().source("confirmPassword")
					.defaultText("Password does not match with confirm password.").build());
			transitionvalue = "failure";
		}
		
		//checking the uniqueness of the e-mail id
		
		if(userDAO.getByEmail(user.getEmail())!= null) {
			error.addMessage(new MessageBuilder().error().source("email")
					.defaultText("The e-mail is is alreday used.").build());
			transitionvalue = "failure";
		}
		
		
		return transitionvalue;
	}
	
	
	public String saveAll(RegisterModel model) {
		String transitionvalue = "success";
		
		//fetch user
		 User user = model.getUser();
		 
		 if(user.getRole().equals("USER")) {
			 Cart cart = new Cart();
			 cart.setUser(user);
			 user.setCart(cart);
		 }
		//save the user in database
		  userDAO.addUser(user);
		  
		//fetch address
		  Address billing = model.getBilling();
		  billing.setUser(user);
		  billing.setBilling(true);
		  
		  userDAO.addAddress(billing);
		  
		
		return transitionvalue;
	}

}
