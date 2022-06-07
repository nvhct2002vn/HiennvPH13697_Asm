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
		<p>Địa chỉ: ${ diaChi }</p>
	</div>
</c:if>

