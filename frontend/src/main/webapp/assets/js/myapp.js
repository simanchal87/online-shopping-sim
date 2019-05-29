$(function()
		{
	//Solving the Active Manu Problem for NavBar
			switch(manu)
			{
			case 'About Us': 
				$('#about').addClass('active');
				break;
			case 'Contact Us': 
				$('#contact').addClass('active');
				break;
			case 'Home': 
				$('#home').addClass('active');
				break;
				
			default : 
				$('#home').addClass('active');
				break;
			}
	
		})
