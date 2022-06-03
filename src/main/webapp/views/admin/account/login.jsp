<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-md-5 col-12 m-auto">
		<form action="/HiennvPH13697_Asm/login" method="post">
			<div>
				<label>User name</label> <input class="form-control mb-3"
					type="text" name="username">
			</div>
			<div>
				<label>Password</label> <input class="form-control mb-3"
					type="password" name="password">
			</div>
			<div class="text-center">
				<button class="btn btn-success">Login now</button>
			</div>
		</form>
		
		<form:form action="/HiennvPH13697_Asm/login" method="post" modelAttribute="login">
		
		</form:form>
	</div>
</div>
