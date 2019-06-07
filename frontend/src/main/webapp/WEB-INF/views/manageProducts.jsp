<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="container">
	<div class="row">
	
	<c:if test="${not empty message}"> 
			
		<div class="col-8">
			<div class="alert alert-success alert-dismissible">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				${message}
			</div>
		
		</div>
	</c:if>
	
	
	
		<div class="md-offset-2 col-md-8">
			<div class="card">
				<div class="card-header">
					<h4>Product Management</h4>
				</div>

				<div class="card-body">
					<!-- FORM ELEMENT -->
					<sf:form class="card-body" modelAttribute="product" 
						action="${contextRoot}/manage/products" method="POST"
						enctype="multipart/form-data"
						>
						
						<div class="card-group row">
							<label class="control-lebel col-md-4" for="name">Enter
								product name</label>
							<div class="md-8">
								<sf:input type="text" path="name" id="name"
									placeholder="Product Name" class="form-control" /> 
								<sf:errors path="name" cssClass="help-block" element="em"/>
							</div>
						</div>

						<div class="card-group row">
							<label class="control-lebel col-md-4" for="brand">Enter
								brand name</label>
							<div class="md-8">
								<sf:input type="text" path="brand" id="brand"
									placeholder="Product Brand" class="form-control" /> 
								<sf:errors path="brand" cssClass="help-block" element="em"/>
							</div>
						</div>

						<div class="card-group row">
							<label class="control-lebel col-md-4" for="description">Product
								Description</label>
							<div class="md-8">
								<sf:textarea path="description" id="description" rows="4"
									placeholder="Write a description" class="form-control"/>
								<sf:errors path="description" cssClass="help-block" element="em"/>
							</div>
						</div>

						<div class="card-group row">
							<label class="control-lebel col-md-4" for="unitprice">Unit
								Price</label>
							<div class="md-8">
								<sf:input type="number" path="unitprice" id="unitprice"
									placeholder="Unit Price In &#8377" class="form-control"/>
								<sf:errors path="unitprice" cssClass="help-block" element="em"/> 
							</div>
						</div>

						<div class="card-group row">
							<label class="control-lebel col-md-4" for="unitPrice">Quantity
								Available</label>
							<div class="md-8">
								<sf:input type="number" path="quantity" id="quantity"
									placeholder="Quantity Available" class="form-control" />
							</div>
						</div>
						
						<div class="card-group row">
							<label class="control-lebel col-md-4" for="file">Select an image</label>
							<div class="md-8">
								<sf:input type="file" path="file" id="file"
									 class="form-control" />
								<sf:errors path="file" cssClass="help-block" element="em" />
							</div>
						</div>

						
						<div class="card-group row">
							<label class="control-lebel col-md-4" for="categoryId">Select
								Category</label>
							<div class="md-8">
								<sf:select path="categoryId" id="categoryId" class="form-control" 
								
								items="${categories}"
								itemLabel="name"
								itemValue="id"
								
								/>
								
							</div>
						</div>

						<div class="form-group">
							<div class="col-md-offset-4 md-8">
								<input type="submit" name="submit" id="submit" value="Sumit"
									class="btn btn-primary" />
									<!-- Hidden Fields -->
									<sf:hidden path="id"/>
									<sf:hidden path="code"/>
									<sf:hidden path="active"/>
									<sf:hidden path="supplierId"/>
									<sf:hidden path="purchases"/>
									<sf:hidden path="views"/>
							</div>
						</div>
					</sf:form>
				</div>
			</div>
		</div>
	</div>

</div>