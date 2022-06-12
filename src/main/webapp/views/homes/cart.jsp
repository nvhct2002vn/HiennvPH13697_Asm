<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:if test="${ !empty  sessionScope.message }">
	<div class="alert alert-success">${ sessionScope.message }</div>
	<c:remove var="message" scope="session" />
</c:if>
<c:if test="${ empty  listCart }">
	<div class="alert alert-danger text-center mt-3">
		<h4 class="text-danger">Giỏ hàng trống!</h4>
	</div>
</c:if>
<c:if test="${ !empty  listCart }">
	<div class="table-responsive">
		<table class="table">
			<tr>
				<th>Hình ảnh</th>
				<th>Tên sản phẩm</th>
				<th>Số lượng</th>
				<th>Đơn Giá</th>
				<th>Thành tiền</th>
				<th colspan="1">Thao tác</th>
			</tr>
			<c:forEach var="cart" items="${ listCart }">
				<tr>
					<td><img alt="Product" style="max-height: 100px"
						src="/HiennvPH13697_Asm/photoProducts/${ cart.value.product.image }"></td>
					<td>${ cart.value.product.category.name}${ khoangTrang }${ cart.value.product.name }</td>
					<td>
						<form action="/HiennvPH13697_Asm/updateQuantity" method="post">
							<%-- 							<c:if test=""> --%>
							<input type="hidden" value="${ cart.key }" name="key">
							<%-- 							</c:if> --%>
							<input style="max-width: 80px" type="number"
								value="${ cart.value.quantity }" name="quantity">
						</form>
					</td>
					<td>${ cart.value.product.price }</td>
					<td>${ cart.value.product.price * cart.value.quantity}</td>
					<td>
						<button type="button" class="btn btn-danger"
							data-bs-toggle="modal"
							data-bs-target="#exampleModal${ cart.key }">Xóa</button>

						<div class="modal fade" id="exampleModal${ cart.key }"
							tabindex="-1" aria-labelledby="exampleModalLabel"
							aria-hidden="true">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">
										<h5 class="modal-title" id="exampleModalLabel">Xóa sản
											phẩm</h5>
										<button type="button" class="btn-close"
											data-bs-dismiss="modal" aria-label="Close"></button>
									</div>
									<div class="modal-body">Bạn chắc chắn muốn xóa sản phẩm
										này hay không ?</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-secondary"
											data-bs-dismiss="modal">Close</button>
										<a class="btn btn-danger"
											href="/HiennvPH13697_Asm/removePrdOnCart/${ cart.key }">Xóa</a>
									</div>
								</div>
							</div>
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
		<p>Tổng tiền: ${ tongTien }</p>
		<div class="row">
			<div class=" col-md-5 col-12  m-auto">
				<form action="/HiennvPH13697_Asm/dathang" method="get">
					<label>Địa chỉ:</label> <input class="form-control mb-3"
						type="text" name="address" required="required"> <label>Số
						điện thoại:</label> <input class="form-control mb-3" type="text"
						name="sdt" required="required">
					<div class="text-center">
						<button class="btn btn-success">Đặt hàng</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</c:if>

