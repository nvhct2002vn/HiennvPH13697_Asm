<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-md-5 col-12 m-auto">
		<form:form action="/HiennvPH13697_Asm/admin/orders/store"
			method="post" modelAttribute="orderCreate">
			<div>
				<label>User</label>
				<form:select class="form-select mb-3" path="account">
					<c:forEach var="acc" items="${ listAcc }">
						<form:option value="${ acc.id }">${ acc.fullname }</form:option>
					</c:forEach>
				</form:select>
			</div>
			<div>
				<label>Address</label>
				<form:input class="form-control mb-3" path="address" />
			</div>
			<div class="text-center">
				<button class="btn btn-secondary">Create</button>
			</div>
		</form:form>
	</div>
</div>