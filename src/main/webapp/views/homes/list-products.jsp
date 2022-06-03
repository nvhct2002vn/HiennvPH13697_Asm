<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<div class="my-3">
	<div class="btn-group">
		<a type="button" href="/HiennvPH13697_Asm/list-products"
			class="btn btn-secondary">Tất cả sản phẩm</a>
		<button type="button"
			class="btn btn-secondary dropdown-toggle dropdown-toggle-split"
			id="dropdownMenuReference" data-bs-toggle="dropdown"
			aria-expanded="false" data-bs-reference="parent">
			<span class="visually-hidden">Toggle Dropdown</span>
		</button>
		<ul class="dropdown-menu" aria-labelledby="dropdownMenuReference">
			<c:forEach var="dsCate" items="${ dsCate }">
				<li><a class="dropdown-item"
					href="/HiennvPH13697_Asm/locTheoDanhMuc/${ dsCate.id }">${ dsCate.name }</a></li>
			</c:forEach>
		</ul>
	</div>
</div>
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