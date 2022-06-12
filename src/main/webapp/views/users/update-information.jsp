<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${ !empty  sessionScope.message }">
	<div class="alert alert-success">${ sessionScope.message }</div>
	<c:remove var="message" scope="session" />
</c:if>

<c:if test="${ !empty  sessionScope.erro }">
	<div class="alert alert-danger">${ sessionScope.erro }</div>
	<c:remove var="erro" scope="session" />
</c:if>

<div class="row">
	<div class="col-md-5 col-12 m-auto">
		<form:form method="post"
			action="/HiennvPH13697_Asm/update-information-store"
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

			<div class="text-center mt-3">
				<button class="btn btn-secondary">Update</button>
			</div>
		</form:form>
	</div>
</div>
