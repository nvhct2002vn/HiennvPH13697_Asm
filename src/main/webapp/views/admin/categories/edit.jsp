<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="row">
	<div class="col-md-5 col-12 m-auto">
		<form:form method="post"
			action="/HiennvPH13697_Asm/admin/categories/update/${ categoryEdit.id }"
			modelAttribute="categoryEdit">
			<label for="id" class="form-label">Tên loại sản phẩm</label>
			<form:input class="form-control" path="name" name="name" />
			<form:errors path="name" class="badge text-danger"></form:errors>
			<hr>
			<div class="text-center">
				<button class="btn btn-secondary">Update</button>
			</div>
		</form:form>
	</div>
</div>
