<%@include file="../shared/flows-header.jsp"%>

<div class="container">
	<div class="row">

		<div class="col-md-6">

			<div class="card card-primary">
				<div class="card card-header">
					<h4>Personal Details</h4>
			</div>

			<div class="card-body">
				<div class="text-center">
				
				<h4>${registerModel.user.firstName} ${registerModel.user.lastName}</h4>
				<h5>Email: ${registerModel.user.email}</h5>
				<h5>Contact Number: ${registerModel.user.contactNumber}</h5>
				<h5>Role: ${registerModel.user.role}</h5>
				
				</div>
				
			</div>
			
			<div class="card-footer">
			
				<a href="${flowExecutionUrl}&_eventId_personal" class="btn btn-primary">Edit</a>
			</div>

			</div>

		</div>

	</div>
	
	<div class="row">

		<div class="col-md-6">

			<div class="card card-primary">
				<div class="card card-header">
					<h4>Address Details</h4>
			</div>

			<div class="card-body">
			
			<div class="text-center">
				
				<h4>${registerModel.billing.addressLineOne} </h4>
				<h4>${registerModel.billing.addressLineTwo} </h4>
				<h4>${registerModel.billing.city} - ${registerModel.billing.postalCode}</h4>
				<h4>${registerModel.billing.state} - ${registerModel.billing.country}</h4>
				
				
				</div>
				
			</div>
			
			<div class="card-footer">
			
				<a href="${flowExecutionUrl}&_eventId_billing" class="btn btn-primary">Edit</a>
			</div>

			</div>

		</div>

	</div>
	
	
	<div class="row">

		<div class="col-sm-4 col-sm-offset-4">

			<div class="text-center">
				
				<a href="${flowExecutionUrl}&_eventId_submit" class="btn btn-primary">Confirm</a>

			</div>

		</div>

	</div>

</div>
<%@include file="../shared/flows-footer.jsp"%>





