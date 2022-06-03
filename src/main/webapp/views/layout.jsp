<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Category</title>
<link rel="icon" href="/HiennvPH13697_Asm/logo/logo.png" />
<link rel="stylesheet" href="/HiennvPH13697_Asm/css/bootstrap.min.css" />
<link rel="stylesheet" href="/HiennvPH13697_Asm/codeCss/style.css" />
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css" />
</head>
<body>
	<div class="container">
		<header class="my-3 text-center">
			<a href="/HiennvPH13697_Asm"><img
				src="/HiennvPH13697_Asm/logo/logo.png"
				style="width: 64px; height: 64px" class="my-3" alt="" /></a>
			<p class="fw-bold">Bài Assignment Java 5 của Nguyễn Viết Hiên</p>
		</header>

		<nav
			class="navbar navbar-expand-lg navbar-light bg-light sticky-top fw-bold">
			<div class="container-fluid">
				<ul class="navbar-nav">
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="/HiennvPH13697_Asm">Trang chủ</a></li>
				</ul>
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown"
					aria-controls="navbarNavDropdown" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				<div class="collapse navbar-collapse row px-3"
					id="navbarNavDropdown">
					<ul class="navbar-nav col-12 col-md-12 col-lg-10">
						<li class="nav-item"><a class="nav-link" href="#">Giới
								thiệu</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/HiennvPH13697_Asm/collections/allitems">Sản phẩm</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Liên hệ</a></li>
						<li class="nav-item"><a class="nav-link" href="#">Hỏi đáp</a></li>
					</ul>
					<ul
						class="navbar-nav col-12 col-md-12 col-lg-2 justify-content-end p-0">
						<li class="nav-item"><a class="nav-link"
							href="/HiennvPH13697_Asm/cart"> <i class="bi bi-bag"></i>
						</a></li>
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle" href="#"
							id="navbarDropdownMenuLink" role="button"
							data-bs-toggle="dropdown" aria-expanded="false"> <i
								class="bi bi-person-circle"></i> <c:if
									test="${ !empty userLogin}">
									${ userLogin.fullname }
								</c:if> <c:if test="${ empty userLogin}">
									Tài khoản
								</c:if>

						</a>
							<ul class="dropdown-menu"
								aria-labelledby="navbarDropdownMenuLink">
								<c:if test="${ empty userLogin}">
									<li><a href="/HiennvPH13697_Asm/login-form"
										class="dropdown-item">Đăng nhập</a></li>
									<li><a href="/HiennvPH13697_Asm/accounts/register"
										class="dropdown-item">Đăng ký</a></li>
								</c:if>
								<c:if test="${ !empty userLogin}">
									<c:if test="${ userLogin.admin == 1 }">
										<li><a href="/HiennvPH13697_Asm/admin/accounts/index"
											class="dropdown-item">Quản lý người dùng</a></li>
										<li><a href="/HiennvPH13697_Asm/admin/products/index"
											class="dropdown-item">Quản lý sản phẩm</a></li>
										<li><a href="/HiennvPH13697_Asm/admin/categories/index"
											class="dropdown-item">Quản lý loại sản phẩm</a></li>
										<li><a href="/HiennvPH13697_Asm/admin/orders/index"
											class="dropdown-item">Quản lý đơn hàng</a></li>
									</c:if>
									<li><a href="#" class="dropdown-item"
										data-bs-toggle="modal" data-bs-target="#doiMatKhau">Đổi
											mật khẩu</a></li>
									<li><a class="dropdown-item" href="#">Cập nhật tài
											khoản</a></li>
									<li><a class="dropdown-item"
										href="/HiennvPH13697_Asm/logout">Đăng xuất</a></li>
								</c:if>
<!-- 								<li><a class="dropdown-item" -->
<!-- 									href="/HiennvPH13697_Asm/history">Lịch sử</a></li> -->
							</ul></li>
					</ul>
				</div>
			</div>
		</nav>
		<div class="row p-5">
			<jsp:include page="${view}"></jsp:include>
		</div>
		<button type="button"
			class="btn btn-outline-secondary btn-floating btn-lg text-center"
			id="btn-back-to-top">
			<i class="bi bi-arrow-up"></i>
		</button>
		<div class="sticky-bottom">
			<footer class="text-center bg-secondary text-white p-3 mt-3">
				<p class="m-0">@2022 - design by Nguyễn Viết Hiên - Java 5</p>
			</footer>
		</div>
	</div>
	<script src="/HiennvPH13697_Asm/codeJs/linkJs.js"></script>
	<script src="/HiennvPH13697_Asm/js/jquery.min.js"></script>
	<script src="/HiennvPH13697_Asm/js/popper.min.js"></script>
	<script src="/HiennvPH13697_Asm/js/bootstrap.min.js"></script>
</body>
</html>