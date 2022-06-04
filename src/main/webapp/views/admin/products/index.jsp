<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="text-center">
	<a href="/HiennvPH13697_Asm/admin/products/create"
		class="btn btn-success">Create</a>
</div>

<div class="row">
	<div class="mt-3 d-flex justify-content-end">
		<b>Sắp xếp theo:</b>
		<div class="btn-group" role="group"
			aria-label="Basic outlined example">
			<a href="/HiennvPH13697_Asm/admin/products/index?field=name"
				type="button" class="btn btn-outline-secondary">Tên</a> <a
				href="/HiennvPH13697_Asm/admin/products/index?field=price"
				type="button" class="btn btn-outline-secondary">Price</a> <a
				href="/HiennvPH13697_Asm/admin/products/index?field=available"
				type="button" class="btn btn-outline-secondary">Available</a>
		</div>
	</div>
</div>

<table class="table table-responsive">
	<thead>
		<tr>
			<th scope="col">Category</th>
			<th scope="col">Name</th>
			<th scope="col">Image</th>
			<th scope="col">Price</th>
			<th scope="col">Created Date</th>
			<th scope="col">Available</th>
			<th scope="col" colspan="2">Thao Tác</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${ prd.content }" var="prd">
			<tr>
				<td>${ prd.category.name }</td>
				<td>${ prd.name }</td>

				<td><img style="width: 100px"
					src="/HiennvPH13697_Asm/photoProducts/${ prd.image }" /></td>
				<td>${ prd.price }</td>
				<td>${ prd.createdDate }</td>
				<td>${ prd.available == 1 ?"Active":"Not active" }</td>
				<td><a class="btn btn-info"
					href="/HiennvPH13697_Asm/admin/products/edit/${ prd.id }">Sửa</a></td>
				<td>
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-danger" data-bs-toggle="modal"
						data-bs-target="#exampleModal${ prd.id }">Xóa</button> <!-- Modal -->
					<div class="modal fade" id="exampleModal${ prd.id }" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Xóa sản
										phẩm</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">Bạn chắc chắn muốn xóa sản phẩm
									này hay không ?</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Close</button>
									<a class="btn btn-danger"
										href="/HiennvPH13697_Asm/admin/products/delete/${ prd.id }">Xóa</a>
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
			href="/HiennvPH13697_Asm/admin/products/index"> First page </a></li>
		<li class="page-item"><a class="page-link"
			href="/HiennvPH13697_Asm/admin/products/index?page=${ prd.number - 1 }">
				Previos page </a></li>
		<li class="page-item"><a class="page-link"
			href="/HiennvPH13697_Asm/admin/products/index?page=${ prd.number + 1 }">
				Next page </a></li>
		<li class="page-item"><a class="page-link"
			href="/HiennvPH13697_Asm/admin/products/index?page=${ prd.totalPages - 1 }">
				Last page </a></li>
	</ul>
</div>