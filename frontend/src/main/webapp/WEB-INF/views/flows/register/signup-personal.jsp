<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@include file="../shared/flows-header.jsp"%>

<div class="container">

	<div class="row">

		<div class="col-md-6 col-md-offset-3">

			<div class="card card-primary">
				<div class="card card-header">
					<h4>Personal Sign Up</h4>
				</div>

				<div class="card-body">

					<sf:form class="card-body" method="POST" id="registerForm"
						modelAttribute="user">

						<div class="card-text">
							<label class="control-label col-md-4">First Name</label>
							<div class="col-md-8">
								<sf:input input="text" path="firstName" class="card-control"
									placeholder="First Name" />
								<sf:errors path="firstName" cssClass="help-block" element="em"/>
							</div>
						</div>
						<div class="card-text">
							<label class="control-label col-md-4">Last Name</label>
							<div class="col-md-8">
								<sf:input input="text" path="lastName" class="card-control"
									placeholder="Last Name" />
								<sf:errors path="lastName" cssClass="help-block" element="em"/>
									
							</div>
						</div>
						<div class="card-text">
							<label class="control-label col-md-4">E-Mail</label>
							<div class="col-md-8">
								<sf:input input="text" path="email" class="card-control"
									placeholder="abc@xyz.com" />
								<sf:errors path="email" cssClass="help-block" element="em"/>
							</div>
						</div>
						<div class="card-text">
							<label class="control-label col-md-4">Contact Number</label>
							<div class="col-md-8">
								<sf:input input="text" path="contactNumber" class="card-control"
									placeholder="9812312345" />
								<sf:errors path="contactNumber" cssClass="help-block" element="em"/>
							</div>
						</div>
						<div class="card-text">
							<label class="control-label col-md-4">Password</label>
							<div class="col-md-8">
								<sf:input type="password" path="password" class="card-control"
									placeholder="Password" />
								<sf:errors path="password" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="card-text">
							<label class="control-label col-md-4">Confirm Password</label>
							<div class="col-md-8">
								<sf:input type="password" path="confirmPassword" class="card-control"
									placeholder="Re-type your password here."/>
								<sf:errors path="confirmPassword" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						<div class="card-text">
							<label class="control-label col-md-4">Select Role</label>
							<div class="col-md-8">
							<label class="redion-inline">
								<sf:radiobutton path="role" value="USER" checked="checked"/>
							User</label>
							
							<label class="redion-inline">
								<sf:radiobutton path="role" value="SUPPLIER"/>
							Supplier</label>
							</div>
						</div>
						<div class="card-text">
							
							<div class="col-md-8">
								<button type="submit" class="btn btn-primary"
								name="_eventId_billing">Next - Billing</button>
								
							</div>
						</div>

					</sf:form>


				</div>

			</div>


		</div>

	</div>

</div>
<%@include file="../shared/flows-footer.jsp"%>
