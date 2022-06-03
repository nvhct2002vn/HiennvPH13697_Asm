<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-md-5 col-12 m-auto">
		<form:form
			action="/HiennvPH13697_Asm/admin/products/update/${ prdEdit.id }"
			method="POST" modelAttribute="prdEdit" enctype="multipart/form-data">
			<div>
				<label>Categories</label>
				<form:select class="form-select mb-3" path="category"
					name="category">
					<c:forEach items="${ listCate }" var="cate">
						<form:option value="${ cate.id}">${ cate.name }</form:option>
					</c:forEach>
				</form:select>
			</div>
			<div>
				<label>Name</label>
				<form:input class="form-control mb-3" path="name" />
				<form:errors path="name" class="badge text-danger"></form:errors>
			</div>

			<div>
				<label>Image</label> <input type="file" class="form-control mb-3"
					name="multiImage" />
			</div>

			<div>
				<label>Price</label>
				<form:input class="form-control mb-3" path="price" />
				<form:errors path="price" class="badge text-danger"></form:errors>
			</div>
			<div>
				<label class="d-block">Available</label>
				<form:radiobutton value="1" path="available" label="Active" />
				<form:radiobutton value="0" path="available" label="Not active" />
			</div>

			<div class="text-center mt-3">
				<button class="btn btn-success">Update</button>
			</div>
		</form:form>
	</div>
</div>