$(function() {
	//Solving the Active Manu Problem for NavBar
	switch (manu) {
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
	default:
		if (manu == "Home")
			break;
		$('#listproducts').addClass('active');
		$('#a_' + manu).addClass('active');
		break;
	}

	//Code for Jquery Data Table

	var $table = $('#productListTable');

	//Execute the below code only where we have this table

	var jsonUrl = '';
	if (window.categoryId == '') {
		jsonUrl = window.contextRoot + '/json/data/all/products';
	} else {

		jsonUrl = window.contextRoot + '/json/data/category/'
				+ window.categoryId + '/products';
	}

	if ($table.length) {
		//console.log('inside the table');
		$table
				.DataTable({
					lengthMenu : [ [ 3, 5, 10, -1 ],
							[ '3 Records', '5 Records', '10 Records', 'ALL' ] ],
					pageLength : 5,

					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},

					columns : [
							{
								data : 'code',
								bSortable : false,
								mRender : function(data, type, row) {
									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="dataTableImg"/>';
								}

							},

							{
								data : 'name'

							},

							{
								data : 'brand'

							},

							{
								data : 'unitprice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data
								}

							},

							{
								data : 'quantity',
								mRender : function(data, type, row) {
									if (data < 1) {
										return '<span style="color:red">Out of Stock.</span>';
									}
									return data;
								}

							},

							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/product" class="btn btn-primary"><snap calss="glyphicon glyphicon-eye-open"></snap> View</a> &#160;';

									if (row.quantity < 1) {
										str += '<a href="javascript:void(0)" class="btn btn-success disabled"><snap calss="glyphicon glyphicon-shopping-cart"></snap>Cart</a>';
									} else {
										str += '<a href="'
												+ window.contextRoot
												+ '/cart/add/'
												+ data
												+ '/product" class="btn btn-success"><snap calss="glyphicon glyphicon-shopping-cart"></snap> Cart</a>';
									}

									return str;
								}

							}

					]

				});
	}

	// dismissing the message alert after 3 sec
	var $alert = $('.alert');
	if ($alert.length) {
		setTimeout(function() {
			$alert.fadeOut('slow');
		}, 3000);
	}

	//--------------------------
	
	
	

	
	/*------------------------------
		Code for admin data table
	--------------------------------*/
	
	
	var $adminProductTable = $('#adminProductsTable');

	//Execute the below code only where we have this table

	
	if ($adminProductTable.length) {
		var jsonUrl = window.contextRoot+'/json/data/admin/all/products';
		//console.log('inside the table');
		$adminProductTable.DataTable({
			
					lengthMenu : [ [ 10, 30, 50, -1 ],
							[ '10 Records', '30 Records', '50 Records', 'ALL' ] ],
					pageLength : 30,

					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},

					columns : [
						
							{
								data: 'id'
							},
						
						
							{
								data : 'code',
								bSortable : false,
								mRender : function(data, type, row) {
									return '<img src="' + window.contextRoot
											+ '/resources/images/' + data
											+ '.jpg" class="adminDataTableImg"/>';
								}

							},

							{
								data : 'name'

							},

							{
								data : 'brand'

							},

							

							{
								data : 'quantity',
								mRender : function(data, type, row) {
									if (data < 1) {
										return '<span style="color:red">Out of Stock.</span>';
									}
									return data;
								}

							},
							
							{
								data : 'unitprice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data
								}

							},

							{
								data : 'active',
								bSortable: false,
								mRender: function(data, type, row){
									var str = '';
									str += '<label class="switch">';
									if(data)
										{
										str +=	'<input type="checkbox" checked="checked" value="'+row.id+'" />';
										} else{
										str +=	'<input type="checkbox" value="'+row.id+'" />';
										}
									
									str +=	'<div class="slider"></div> </label>';
									return str;
									
								}
								

							},
							
							{
								data: 'id',
								bSortable: false,
								mRender: function(data, type, row){
									var str = '';
									str+= '<a href="'+ window.contextRoot +'/manage/'+data+'/product" class="btn btn-warning"="btn btn-warning">';
									str+= '<span class="">Edit</span></a>';
									return str;
								}
							}

					],
					
					initComplete: function(){
						
						var api = this.api();
						
						/*code for confirmation message to 
							active and deactive product in admin page*/

						
						api.$('.switch input[type="checkbox"]')
						.on(
								'change',
								function() {

									var checkbox = $(this);
									var checked = checkbox.prop('checked');
									var dMsg = (checked) ? 'Do you want to activate the product?'
											: 'Do you want to deactivate the product?';
									var value = checkbox.prop('value');

									bootbox
											.confirm({
												size : 'medium',
												title : 'Product Activation and Deactivation',
												message : dMsg,
												callback : function(confirmed) {
													if (confirmed) {
														console.log(value);
														
														var activationUrl = window.contextRoot +  '/manage/product/'+value+'/activation';
														$.post(activationUrl, function(data){
															
															bootbox.alert({
																size : 'medium',
																title : 'information',
																message : data
															});
															
														});
														
														
													} 
													
													else {
														checkbox.prop('checked', !checked);
													}
												}
											});

								});
						
					}

				});
	}
	
	//-------------------
	
	// Client side validation code for category
	
	var $categoryForm = $('#categoryForm');
	
	if($categoryForm.length){
		$categoryForm.validate({
			
			rules : {
				name : {
					required: true,
					minlength: 2
				},
				
				description: {
					required: true
				},
				
				messages: {
					name : {
						required: 'Enter category name.',
						minlength: 'The category name should not be less than 2 characters.'
					},
					
					description: {
						required: 'Enter description for this category.'
					}
				},
				
				errorElement: 'em',
				errorPlacement: function(error, element){
					//add the class of help-block
					error.addClass('help-block');
					//add the error element after the input element
					error.insertAfter(element);
				}
			}
		});
	}
	
	/*-----------------------*/
	
	
});
