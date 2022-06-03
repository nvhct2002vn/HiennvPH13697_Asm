<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="text-center">
	<a href="/HiennvPH13697_Asm/admin/order-details/create"
		class="btn btn-success">Create</a>
</div>
<table class="table">
	<thead>
		<tr>
			<th scope="col">order id</th>
			<th scope="col">Products</th>
			<th scope="col">Price</th>
			<th scope="col">quantity</th>
			<th scope="col" colspan="2">Thao tác</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="orderdetails" items="${ orderdetails.content }">
			<tr>
				<td>${ orderdetails.order.id }- ${ orderdetails.order.account.fullname }</td>
				<td>${ orderdetails.product.category.name }${ khoangTrang }${ orderdetails.product.name }</td>
				<td>${ orderdetails.price }</td>
				<td>${ orderdetails.quantity }</td>
				<td><a class="btn btn-info"
					href="/HiennvPH13697_Asm/admin/order-details/edit/${ orderdetails.id }">Sửa</a></td>
				<td>
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-danger" data-bs-toggle="modal"
						data-bs-target="#exampleModal${ orderdetails.id }">Xóa</button> <!-- Modal -->
					<div class="modal fade" id="exampleModal${ orderdetails.id }"
						tabindex="-1" aria-labelledby="exampleModalLabel"
						aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Xóa sản
										phẩm</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">Bạn chắc chắn muốn xóa hoá đơn này
									hay không ?</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Close</button>
									<a class="btn btn-danger"
										href="/HiennvPH13697_Asm/admin/order-details/delete/${ orderdetails.id }">Xóa</a>
								</div>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<div class="d-flex justify-content-center">
	<ul class="pagination">
		<li class="page-item"><a class="page-link"
			href="/HiennvPH13697_Asm/admin/order-details/index"> First page </a></li>
		<li class="page-item"><a class="page-link"
			href="/HiennvPH13697_Asm/admin/order-details/index?page=${ orderdetails.number - 1 }">
				Previos page </a></li>
		<li class="page-item"><a class="page-link"
			href="/HiennvPH13697_Asm/admin/order-details/index?page=${ orderdetails.number + 1 }">
				Next page </a></li>
		<li class="page-item"><a class="page-link"
			href="/HiennvPH13697_Asm/admin/order-details/index?page=${ orderdetails.totalPages - 1 }">
				Last page </a></li>
	</ul>
</div>