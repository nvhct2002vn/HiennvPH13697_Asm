<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${ !empty  sessionScope.error }">
	<div class="alert alert-danger">${ sessionScope.error }</div>
	<c:remove var="error" scope="session" />
</c:if>

<div class="row">
	<div class="col-md-5 col-12 m-auto">
		<form:form action="/HiennvPH13697_Asm/login" method="post"
			modelAttribute="login">
<!-- 			<div> -->
<!-- 				<label>User name</label> <input class="form-control mb-3" -->
<!-- 					type="text" name="username"> -->
<%-- 				<form:errors path="username" class="badge text-danger"></form:errors> --%>
<!-- 			</div> -->
			<div>
				<label>email</label> <input class="form-control mb-3"
					type="text" name="email">
				<form:errors path="email" class="badge text-danger"></form:errors>
			</div>
			<div>
				<label>Password</label> <input class="form-control mb-3"
					type="password" name="password">
				<form:errors path="password" class="badge text-danger"></form:errors>
			</div>
			<div class="text-center">
				<button class="btn btn-success">Login now</button>
			</div>
		</form:form>
	</div>
</div>
