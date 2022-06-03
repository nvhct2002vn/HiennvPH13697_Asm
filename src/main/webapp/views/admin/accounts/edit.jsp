<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="row">
	<div class="col-md-5 col-12 m-auto">
		<form:form method="post"
			action="/HiennvPH13697_Asm/admin/accounts/update/${ entity.id }"
			modelAttribute="entity" enctype="multipart/form-data">
			<div>
				<label class="mt-3 form-label">Fullname</label>
				<form:input class="form-control" path="fullname" name="fullname" />
				<form:errors path="fullname" class="badge text-danger"></form:errors>
			</div>

			<div>
				<label class="mt-3 form-label">Email</label>
				<form:input class="form-control" path="email" name="email" />
				<form:errors path="email" class="badge text-danger"></form:errors>
			</div>

			<div>
				<label class="mt-3 form-label">Username</label>
				<form:input class="form-control" path="username" name="username" />
				<form:errors path="username" class="badge text-danger"></form:errors>
			</div>

			<div>
				<label class="mt-3 form-label">Photo</label> <input
					class="form-control" type="file" name=multiImage />
			</div>

			<div>
				<label class="mt-3 form-label">Admin</label>
				<form:select class="form-select" path="admin">
					<form:option value="0">User</form:option>
					<form:option value="1">Admin</form:option>
				</form:select>
			</div>
			<div>
				<label class="mt-3 form-label">Activated</label>
				<form:select class="form-select" path="activated">
					<%-- 			 ${account.activated == 1?"selected":""} --%>
					<form:option value="0">Not Active</form:option>
					<form:option value="1">Active</form:option>
				</form:select>
			</div>
			<div class="text-center mt-3">
				<button class="btn btn-secondary">Update</button>
			</div>
		</form:form>
	</div>
</div>
