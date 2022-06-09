<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:if test="${ empty  lstCartdt }">
	<div class="alert alert-danger text-center mt-3">
		<h4 class="text-danger">Giỏ hàng trống!</h4>
	</div>
</c:if>
<c:if test="${ !empty  lstCartdt }">
	<div class="table-responsive">
		<table class="table">
			<tr>
				<th>Hình ảnh</th>
				<th>Tên sản phẩm</th>
				<th>Số lượng</th>
				<th>Đơn giá</th>
				<!-- 				<th colspan="1">Thao tác</th> -->
			</tr>
			<c:forEach var="lstCartdt" items="${ lstCartdt }">
				<tr>
					<td><img alt="Product" style="max-height: 100px"
						src="/HiennvPH13697_Asm/photoProducts/${ lstCartdt.product.image }"></td>
					<td>${ lstCartdt.product.category.name}${ khoangTrang }${ lstCartdt.product.name }</td>
					<td>${ lstCartdt.quantity }</td>
					<td>${ lstCartdt.product.price }</td>
				</tr>
			</c:forEach>
		</table>
		<p>Tổng Tiền: ${ tongTien }</p>
		<c:if test="${ status == 1 }">
			<div class="text-center">
				<a href="/HiennvPH13697_Asm/admin/orders/xacNhanDonHang/${ idOrder }"
					class="btn btn-outline-success">Xác nhận đơn hàng</a>
			</div>
		</c:if>
	</div>
</c:if>

