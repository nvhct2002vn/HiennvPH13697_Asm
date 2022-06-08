<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<c:if test="${ !empty  sessionScope.message }">
	<div class="alert alert-success">${ sessionScope.message }</div>
	<c:remove var="message" scope="session" />
</c:if>
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
				<th>Thành tiền</th>
				<th colspan="1">Thao tác</th>
			</tr>
			<c:forEach var="lstCartdt" items="${ lstCartdt }">
				<tr>
					<td><img alt="Product" style="max-height: 100px"
						src="/HiennvPH13697_Asm/photoProducts/${ lstCartdt.product.image }"></td>
					<td>${ lstCartdt.product.category.name}${ khoangTrang }${ lstCartdt.product.name }</td>
					<td>${ lstCartdt.quantity }</td>
					<td>${ lstCartdt.price }</td>
					<td>
						<button type="button" class="btn btn-danger"
							data-bs-toggle="modal"
							data-bs-target="#exampleModal${ lstCartdt.id }">Xóa</button> <!-- Modal -->
						<div class="modal fade" id="exampleModal${ lstCartdt.id }"
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
											href="/HiennvPH13697_Asm/removePrdOnCart/${ lstCartdt.id }">Xóa</a>
									</div>
								</div>
							</div>
						</div>
					</td>
				</tr>
			</c:forEach>
		</table>
		<p>Tổng tiền: ${ tongTien }</p>
		<div class="text-center">
			<c:if test="${ trangThaiButton == 0 }">
				<form:form action="/HiennvPH13697_Asm/dathang/${ idOrder }"
					method="post" modelAttribute="entity">
					<label>Địa chỉ nhận hàng:</label>
					<form:input path="address" class="form-control mb-3" />
					<form:errors path="address" class="badge text-danger"></form:errors>
					<button class="btn btn-success">Đặt hàng</button>
				</form:form>
			</c:if>
			<c:if test="${ trangThaiButton == 1 }">
				<!-- Button trigger modal -->
				<button type="button" class="btn btn-outline-danger"
					data-bs-toggle="modal" data-bs-target="#exampleModal">Huỷ
					đơn hàng</button>
				<!-- Modal -->
				<div class="modal fade" id="exampleModal" tabindex="-1"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">Huỷ đơn hàng</h5>
								<button type="button" class="btn-close" data-bs-dismiss="modal"
									aria-label="Close"></button>
							</div>
							<div class="modal-body">Bạn chắc chắn muốn huỷ đơn hàng
								không ?</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-secondary"
									data-bs-dismiss="modal">Close</button>
								<a class="btn btn-danger"
									href="/HiennvPH13697_Asm/huydonhang/${ idOrder }">Huỷ</a>
							</div>
						</div>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</c:if>

