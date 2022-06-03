<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div class="row">
	<div class="col-md-5 col-12 m-auto">
		<form:form method="post"
			action="/HiennvPH13697_Asm/admin/accounts/store"
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
				<label class="mt-3 form-label">Password</label>
				<form:password class="form-control" path="password" name="password" />
				<form:errors path="password" class="badge text-danger"></form:errors>
			</div>

			<div>
				<label class="mt-3 form-label">Photo</label>
				<form:input type="file" class="form-control" path="multiImage"
					name="multiImage" />
				<%-- 				<form:errors path="multiImage" class="badge text-danger"></form:errors> --%>
			</div>

			<div>
				<label class="mt-3 form-label">Role</label>
				<form:select class="form-select" path="admin" name="admin">
					<form:option value="0">User</form:option>
					<form:option value="1">Admin</form:option>
				</form:select>
			</div>
			<div class="text-center mt-3">
				<button class="btn btn-secondary">Create</button>
			</div>
		</form:form>
	</div>
</div>