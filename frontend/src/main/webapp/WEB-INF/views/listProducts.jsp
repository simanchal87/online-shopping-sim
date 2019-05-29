<div calss="container">

	<div class="row">
		<!-- Would be display side bar -->
		<div calss="col-md-3">
			<%@include file="./shared/sidebar.jsp"%>
		</div>

		<!-- Would be display the actual products -->

		<div calss="col-md-9">
			<!-- Added breadcrumb component-->
			<div calss="row">
				<div calss="col-lg-12">
					<c:if test="${userClickAllProducts==true}">
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">All Products</li>

						</ol>
					</c:if>
					
					<c:if test="${userClickCategoryProducts==true}">
						<ol class="breadcrumb">
							<li><a href="${contextRoot}/home">Home</a></li>
							<li class="active">Category</li>
							<li class="active">${category.name}</li>

						</ol>
					</c:if>

				</div>

			</div>
		</div>

	</div>



</div>