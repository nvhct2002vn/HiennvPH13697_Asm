<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${ !empty  sessionScope.message }">
	<div class="alert alert-success">${ sessionScope.message }</div>
	<c:remove var="message" scope="session" />
</c:if>

<table class="table">
	<thead>
		<tr>
			<th scope="col">User</th>
			<th scope="col">Created Date</th>
			<th scope="col">Address</th>
			<th scope="col">Number Phone</th>
			<th scope="col">Status</th>
			<th scope="col" colspan="3">Thao tác</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="order" items="${ order }">
			<tr>
				<td>${ order.account.fullname }</td>
				<td>${ order.createDate }</td>
				<td>${ order.address }</td>
				<td>${ order.sdt }</td>
				<td>${ order.status == 0?"Đã huỷ đơn hàng":order.status == 1?"Đã đặt hàng":"Đã xác nhận"}</td>
				<td><a class="btn btn-info"
					href="/HiennvPH13697_Asm/users/history-details/${ order.id }">Xem
						chi tiết</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>