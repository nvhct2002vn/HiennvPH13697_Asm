<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
	<div class="text-center">
		<a class="btn btn-success"
			href="/HiennvPH13697_Asm/admin/categories/create">Create</a>
	</div>
	<table class="table text-center table-responsive">
		<tr>
			<th>Name</th>
			<th colspan="2">Thao tác</th>
		</tr>
		<c:forEach var="cate" items="${cate.content}">
			<tr>
				<td>${cate.name}</td>
				<td><a class="btn btn-info"
					href="/HiennvPH13697_Asm/admin/categories/edit/${cate.id}">Edit</a></td>
				<td>
					<!-- Button trigger modal -->
					<button type="button" class="btn btn-danger" data-bs-toggle="modal"
						data-bs-target="#exampleModal${ cate.id }">Xóa</button> <!-- Modal -->
					<div class="modal fade" id="exampleModal${ cate.id }" tabindex="-1"
						aria-labelledby="exampleModalLabel" aria-hidden="true">
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
										href="/HiennvPH13697_Asm/admin/categories/delete/${cate.id}">Xóa</a>
								</div>
							</div>
						</div>
					</div>
				</td>
			</tr>
		</c:forEach>
	</table>
	<div class="d-flex justify-content-center">
		<ul class="pagination">
			<li class="page-item"><a class="page-link"
				href="/HiennvPH13697_Asm/admin/categories/index"> First page </a></li>
			<li class="page-item"><a class="page-link"
				href="/HiennvPH13697_Asm/admin/categories/index?page=${ cate.number - 1 }">
					Previos page </a></li>
			<li class="page-item"><a class="page-link"
				href="/HiennvPH13697_Asm/admin/categories/index?page=${ cate.number + 1 }">
					Next page </a></li>
			<li class="page-item"><a class="page-link"
				href="/HiennvPH13697_Asm/admin/categories/index?page=${ cate.totalPages - 1 }">
					Last page </a></li>
		</ul>
	</div>