<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="trangChu">
	<div class="row mt-3">
		<article class="col-12 slide-show-img">
			<div id="carouselExampleControls" class="carousel slide"
				data-bs-ride="carousel" data-bs-interval="2000">
				<div class="carousel-inner">
					<div class="carousel-item active">
						<img
							src="https://file.vfo.vn/hinh/2015/12/meo-con-dang-yeu-lam-hinh-nen-may-tinh-4.jpg"
							alt="..." />
					</div>
					<div class="carousel-item">
						<img
							src="https://file.vfo.vn/hinh/2015/12/meo-con-dang-yeu-lam-hinh-nen-may-tinh-5.jpg"
							alt="..." />
					</div>
					<div class="carousel-item">
						<img
							src="https://file.vfo.vn/hinh/2015/12/meo-con-dang-yeu-lam-hinh-nen-may-tinh-6.jpg"
							alt="..." />
					</div>
				</div>
				<button class="carousel-control-prev" type="button"
					data-bs-target="#carouselExampleControls" data-bs-slide="prev">
					<span class="carousel-control-prev-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Previous</span>
				</button>
				<button class="carousel-control-next" type="button"
					data-bs-target="#carouselExampleControls" data-bs-slide="next">
					<span class="carousel-control-next-icon" aria-hidden="true"></span>
					<span class="visually-hidden">Next</span>
				</button>
			</div>
		</article>
	</div>
	<hr />
	<div class="row mt-3">
		<div class="text-center col-12 mb-3">
			<h1 class="border border-2 border-secondary px-2 d-inline fw-bold">
				Sản phẩm</h1>
		</div>
	</div>
	<hr />
	<div class="row product_border">
		<c:forEach var="prd" items="${ dsPrd }">
			<div class="col-12 col-md-3">
				<div class="container_overlay title-img">
					<a href="/HiennvPH13697_Asm/products/${ prd.id }"> <img
						src="/HiennvPH13697_Asm/photoProducts/${ prd.image }" alt="Avatar"
						class="image_product img-fluid" />
					</a>
				</div>
				<div class="text-center">
					<a class="product_name"
						href="/HiennvPH13697_Asm/product/${ prd.id }">${ prd.category.name }${ khoangTrang }${ prd.name }</a>
					<p class="product_price">${ prd.price }</p>
				</div>
			</div>
		</c:forEach>
	</div>
	<div class="text-center my-3">
		<button type="button" class="btn btn-outline-secondary">
			<a href="/HiennvPH13697_Asm/collections/allitems" class="text-black text-decoration-none">Xem Tất Cả</a>
		</button>
	</div>
</div>
