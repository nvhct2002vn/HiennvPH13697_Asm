<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="text-center">
	<a href="/HiennvPH13697_Asm/admin/accounts/create"
		class="btn btn-success">Thêm</a>
</div>
<div class="row">
	<div class="mt-3 d-flex justify-content-end">
		<b>Sắp xếp theo:</b>
		<div class="btn-group" role="group"
			aria-label="Basic outlined example">
			<a href="/HiennvPH13697_Asm/admin/accounts/index?field=fullname"
				type="button" class="btn btn-outline-secondary">Họ tên</a> <a
				href="/HiennvPH13697_Asm/admin/accounts/index?field=username"
				type="button" class="btn btn-outline-secondary">Username</a> <a
				href="/HiennvPH13697_Asm/admin/accounts/index?field=email" type="button"
				class="btn btn-outline-secondary">Email</a> <a
				href="/HiennvPH13697_Asm/admin/accounts/index?field=activated"
				type="button" class="btn btn-outline-secondary">Activated</a> <a
				href="/HiennvPH13697_Asm/admin/accounts/index?field=admin" type="button"
				class="btn btn-outline-secondary">Admin?</a>
		</div>
	</div>
</div>
<table class="table">
	<thead>
		<tr>
			<th scope="col">Id</th>
			<th scope="col">Fullname</th>
			<th scope="col">Username</th>
			<th scope="col">Email</th>
			<th scope="col">Photo</th>
			<th scope="col">Role</th>
			<th scope="col" colspan="2">Action</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${ data.content }" var="lstAcc">
			<tr>
				<th scope="row">${ lstAcc.id }</th>
				<td>${ lstAcc.fullname }</td>
				<td>${ lstAcc.username }</td>
				<td>${ lstAcc.email }</td>
				<td><img style="width: 100px;" alt=""
					src="/HiennvPH13697_Asm/photoAccounts/${ lstAcc.photo }"></td>
				<td>${ lstAcc.activated	 == 0 ? "Not active":"Active" }</td>
				<td>${ lstAcc.admin	 == 0 ? "User":"Admin" }</td>
				<td><a
					href="/HiennvPH13697_Asm/admin/accounts/edit/${ lstAcc.id }"
					class="btn btn-primary">Sửa</a></td>
				<td>
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-danger" data-bs-toggle="modal"
						data-bs-target="#exampleModal${ lstAcc.id }">Xóa</button> <!-- Modal -->
					<div class="modal fade" id="exampleModal${ lstAcc.id }"
						tabindex="-1" aria-labelledby="exampleModalLabel"
						aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<h5 class="modal-title" id="exampleModalLabel">Xóa sản
										danh mục phẩm</h5>
									<button type="button" class="btn-close" data-bs-dismiss="modal"
										aria-label="Close"></button>
								</div>
								<div class="modal-body">Bạn chắc chắn muốn danh mục xóa
									sản phẩm này hay không ?</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-bs-dismiss="modal">Close</button>
									<a class="btn btn-danger"
										href="/HiennvPH13697_Asm/admin/accounts/delete/${ lstAcc.id }">Xóa</a>
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
			href="/HiennvPH13697_Asm/admin/accounts/index"> First page </a></li>
		<li class="page-item"><a class="page-link"
			href="/HiennvPH13697_Asm/admin/accounts/index?page=${ data.number - 1 }">
				Previos page </a></li>
		<li class="page-item"><a class="page-link"
			href="/HiennvPH13697_Asm/admin/accounts/index?page=${ data.number + 1 }">
				Next page </a></li>
		<li class="page-item"><a class="page-link"
			href="/HiennvPH13697_Asm/admin/accounts/index?page=${ data.totalPages - 1 }">
				Last page </a></li>
	</ul>
</div>