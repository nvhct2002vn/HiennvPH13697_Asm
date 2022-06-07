<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="text-center">
	<a href="/HiennvPH13697_Asm/admin/orders/create"
		class="btn btn-success">Create</a>
</div>
<table class="table">
	<thead>
		<tr>
			<th scope="col">User</th>
			<th scope="col">Created Date</th>
			<th scope="col">Address</th>
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
				<td>${ order.status == 0?"Chờ":order.status == 1?"Đã đặt hàng":order.status == 2?"Đã xác nhận":"Đã huỷ"}</td>
				<td><a class="btn btn-info"
					href="/HiennvPH13697_Asm/users/history-details/${ order.id }">Xem
						chi tiết</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>