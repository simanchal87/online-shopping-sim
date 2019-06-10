<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@include file="../shared/flows-header.jsp"%>

<div class="container">

	<div class="row">

		<div class="col-md-6 col-md-offset-3">

			<div class="card card-primary">
				<div class="card card-header">
					<h4>Sign Up Address</h4>
				</div>

				<div class="card-body">

					<sf:form class="card-body" method="POST" id="billingForm"
						modelAttribute="billing">

						<div class="card-text">
							<label class="control-label col-md-4">Address Line One</label>
							<div class="col-md-8">
								<sf:input input="text" path="addressLineOne" class="card-control"
									placeholder="Enter Address Line One" />
								<sf:errors path="addressLineOne" cssClass="help-block" element="em"/>
							</div>
						</div>
						<div class="card-text">
							<label class="control-label col-md-4">Address Line Two</label>
							<div class="col-md-8">
								<sf:input input="text" path="addressLineTwo" class="card-control"
									placeholder="Enter Address Line Two" />
								<sf:errors path="addressLineTwo" cssClass="help-block" element="em"/>
							</div>
						</div>
						<div class="card-text">
							<label class="control-label col-md-4">City</label>
							<div class="col-md-8">
								<sf:input input="text" path="city" class="card-control"
									placeholder="Enter City" />
								<sf:errors path="city" cssClass="help-block" element="em"/>
							</div>
						</div>
						<div class="card-text">
							<label class="control-label col-md-4">Postal Coder</label>
							<div class="col-md-8">
								<sf:input input="text" path="postalCode" class="card-control"
									placeholder="XXXXXX" />
								<sf:errors path="postalCode" cssClass="help-block" element="em"/>
							</div>
						</div>
						<div class="card-text">
							<label class="control-label col-md-4">State</label>
							<div class="col-md-8">
								<sf:input input="text" path="state" class="card-control"
									placeholder="ex. Odisha" />
								<sf:errors path="state" cssClass="help-block" element="em"/>
							</div>
						</div>
						<div class="card-text">
							<label class="control-label col-md-4">Country</label>
							<div class="col-md-8">
								<sf:input input="text" path="country" class="card-control"
									placeholder="ex. India" />
								<sf:errors path="country" cssClass="help-block" element="em"/>
							</div>
						</div>
						
						
						
						<div class="card-text">
							
							<div class="col-md-8">
								<button type="submit" class="btn btn-primary"
								name="_eventId_personal">Previous - Personal</button>
							</div>
							
							<div class="col-md-8">
								<button type="submit" class="btn btn-primary"
								name="_eventId_confirm">Next - Confirm</button>
							</div>
							
						</div>

					</sf:form>


				</div>

			</div>


		</div>

	</div>


</div>
<%@include file="../shared/flows-footer.jsp"%>
