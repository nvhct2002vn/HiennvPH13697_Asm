<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:if test="${ !empty  lstCartdt }">
	<div class="table-responsive">
		<table class="table">
			<tr>
				<th>Hình ảnh</th>
				<th>Tên sản phẩm</th>
				<th>Số lượng</th>
				<th>Đơn giá</th>
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
		<p>Tổng Tiền: ${ tongTienDetails }</p>
		<p>Địa chỉ đặt hàng: ${diaChi}</p>
	</div>
</c:if>
<c:if test="${ status == 1 }">
	<div class="text-center">
		<a class="btn btn-info"
			href="/HiennvPH13697_Asm/users/huydonhang/${ idOrder }">Huỷ đơn
			hàng</a>
	</div>
</c:if>
<c:if test="${ status == 0 }">
	<div class="text-center">
		<a class="btn btn-info"
			href="/HiennvPH13697_Asm/users/datlaidonhang/${ idOrder }">Đặt lại đơn hàng</a>
	</div>
</c:if>
