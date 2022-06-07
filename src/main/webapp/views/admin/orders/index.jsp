<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="text-center">
	<a href="/HiennvPH13697_Asm/admin/orders/create"
		class="btn btn-success">Create</a>
</div>

<div class="row">
	<div class="mt-3 d-flex justify-content-end">
		<b>Sắp xếp theo:</b>
		<div class="btn-group" role="group"
			aria-label="Basic outlined example">
			<a href="/HiennvPH13697_Asm/admin/orders/index?field=status"
				type="button" class="btn btn-outline-secondary">Status</a> <a
				href="/HiennvPH13697_Asm/admin/orders/index?field=username"
				type="button" class="btn btn-outline-secondary">User</a>
		</div>
	</div>
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
		<c:forEach var="order" items="${ order.content }">
			<tr>
				<td>${ order.account.fullname }</td>
				<td>${ order.createDate }</td>
				<td>${ order.address }</td>
				<td>${ order.status == 0?"Chờ":order.status == 1?"Đã đặt hàng":order.status == 2?"Đã xác nhận":"Đã huỷ"}</td>
				<!-- 				<td><a class="btn btn-info" -->
				<%-- 					href="/HiennvPH13697_Asm/admin/orders/edit/${ order.id }">Sửa</a></td> --%>
				<!-- 				<td> -->
				<!-- 					Button trigger modal -->
				<!-- 					<button type="button" class="btn btn-danger" data-bs-toggle="modal" -->
				<%-- 						data-bs-target="#exampleModal${ order.id }">Xóa</button> <!-- Modal --> --%>
				<%-- 					<div class="modal fade" id="exampleModal${ order.id }" --%>
				<!-- 						tabindex="-1" aria-labelledby="exampleModalLabel" -->
				<!-- 						aria-hidden="true"> -->
				<!-- 						<div class="modal-dialog"> -->
				<!-- 							<div class="modal-content"> -->
				<!-- 								<div class="modal-header"> -->
				<!-- 									<h5 class="modal-title" id="exampleModalLabel">Xóa sản -->
				<!-- 										phẩm</h5> -->
				<!-- 									<button type="button" class="btn-close" data-bs-dismiss="modal" -->
				<!-- 										aria-label="Close"></button> -->
				<!-- 								</div> -->
				<!-- 								<div class="modal-body">Bạn chắc chắn muốn xóa hoá đơn này -->
				<!-- 									hay không ?</div> -->
				<!-- 								<div class="modal-footer"> -->
				<!-- 									<button type="button" class="btn btn-secondary" -->
				<!-- 										data-bs-dismiss="modal">Close</button> -->
				<!-- 									<a class="btn btn-danger" -->
				<%-- 										href="/HiennvPH13697_Asm/admin/orders/delete/${ order.id }">Xóa</a> --%>
				<!-- 								</div> -->
				<!-- 							</div> -->
				<!-- 						</div> -->
				<!-- 					</div> -->
				<!-- 				</td> -->
				<td><a class="btn btn-info"
					href="/HiennvPH13697_Asm/admin/orders/order-details/${ order.id }">Xem
						chi tiết</a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="d-flex justify-content-center">
	<ul class="pagination">
		<li class="page-item"><a class="page-link"
			href="/HiennvPH13697_Asm/admin/orders/index"> First page </a></li>
		<li class="page-item"><a class="page-link"
			href="/HiennvPH13697_Asm/admin/orders/index?page=${ order.number - 1 }">
				Previos page </a></li>
		<li class="page-item"><a class="page-link"
			href="/HiennvPH13697_Asm/admin/orders/index?page=${ order.number + 1 }">
				Next page </a></li>
		<li class="page-item"><a class="page-link"
			href="/HiennvPH13697_Asm/admin/orders/index?page=${ order.totalPages - 1 }">
				Last page </a></li>
	</ul>
</div>
<div class="text-center">
	<a href="/HiennvPH13697_Asm/admin/order-details/index"
		class="btn btn-success">Order-details</a>
</div>