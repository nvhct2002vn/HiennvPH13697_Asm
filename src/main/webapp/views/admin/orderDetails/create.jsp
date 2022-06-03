<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-md-5 col-12 m-auto">
		<form:form action="/HiennvPH13697_Asm/admin/order-details/store"
			method="post" modelAttribute="ordCreate">
			<div>
				<label>Order User</label>
				<form:select class="form-select mb-3" path="order">
					<c:forEach var="order" items="${ listOrder }">
						<form:option value="${ order.id }">${ order.id } - ${ order.account.fullname }</form:option>
					</c:forEach>
				</form:select>
			</div>
			<div>
				<label>Product</label>
				<form:select class="form-select mb-3" path="product">
					<c:forEach var="prd" items="${ listPrd }">
						<form:option value="${ prd.id }">${ prd.category.name }${ khoangTrang }${ prd.name }</form:option>
					</c:forEach>
				</form:select>
			</div>
			<div class="text-center">
				<button class="btn btn-secondary">Create</button>
			</div>
		</form:form>
	</div>
</div>