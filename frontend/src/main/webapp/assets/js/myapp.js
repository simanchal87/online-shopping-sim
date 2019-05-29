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
			case 'All Products': 
				$('#listproducts').addClass('active');
				break;
			default : 
				$('#listproducts').addClass('active');
				$('#a_'+manu).addClass('active');
				break;
			}
	
		})
