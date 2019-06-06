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
			case 'Manage Products': 
				$('#manageProducts').addClass('active');
				break;
			default : 
				if (manu == "Home") break;
				$('#listproducts').addClass('active');
				$('#a_'+manu).addClass('active');
				break;
			}
	
			//Code for Jquery Data Table
					
			var $table = $('#productListTable');
			
			//Execute the below code only where we have this table
			
			var jsonUrl = '';
			if(window.categoryId =='') {
					jsonUrl = window.contextRoot + '/json/data/all/products';
				}
			else {
				
				jsonUrl = window.contextRoot + '/json/data/category/'+ window.categoryId +'/products';
			}
			
			if($table.length)
				{
				//console.log('inside the table');
					$table.DataTable({
						lengthMenu:[[3,5,10,-1], ['3 Records', '5 Records', '10 Records', 'ALL']],
						pageLength: 5,
						
						ajax:
							{
								url: jsonUrl,
								dataSrc: ''
							},
							
						columns:
							[
								{
									data: 'code',
									bSortable: false,
									mRender: function (data, type, row)
								{
										return '<img src="' +window.contextRoot+ '/resources/images/' +data+'.jpg" class="dataTableImg"/>';
								}
										
									
								},
								
								{
									data: 'name'
									
								},
								
								{
									data: 'brand'
									
								},
								
								{
									data: 'unitprice', mRender :
									function (data, type, row) {
										return '&#8377; ' + data
									}
									
								},
								
								{
									data: 'quantity',
									mRender: function(data, type, row)
									{
										if(data < 1)
											{
												return '<span style="color:red">Out of Stock.</span>';
											}
										return data;
									}
									
								},
								
								{
									data: 'id',
									bSortable: false,
									mRender : function (data, type, row) {
										var str = '';
										str += '<a href="' +window.contextRoot+ '/show/'+data+'/product" class="btn btn-primary"><snap calss="glyphicon glyphicon-eye-open"></snap> View</a> &#160;';
										
										if(row.quantity < 1)
											{
												str += '<a href="javascript:void(0)" class="btn btn-success disabled"><snap calss="glyphicon glyphicon-shopping-cart"></snap>Cart</a>';
											}
										else
											{
												str += '<a href="' +window.contextRoot+ '/cart/add/'+data+'/product" class="btn btn-success"><snap calss="glyphicon glyphicon-shopping-cart"></snap> Cart</a>';
											}
										
										return str;
									}
									
								}
								
							
							]
							
										
					});
				}
			
			
				// dismissing the message alert after 3 sec
			var $alert = $('.alert');
			if($alert.length){
				setTimeout(function() {
					$alert.fadeOut('slow');				
				}, 3000);
			}
			
			
		})
