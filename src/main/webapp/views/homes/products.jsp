<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="row">
	<div class="col-12 col-md-7 mt-3 title-img">
		<img class="img-fluid" alt=""
			src="/HiennvPH13697_Asm/photoProducts/${ prd.image }">
	</div>
	<div class="col-12 col-md-5 mt-3">
		<h4 class="fw-bold">${ prd.category.name }${ khoangTrang }${ prd.name }</h4>
		<p>Giá: ${ prd.price }</p>
		<div class="row text-center">
			<div class="col-9">
				<a href="/HiennvPH13697_Asm/addToCart/${ prd.id }"
					class="btn btn-outline-secondary">Thêm vào rỏ hàng</a>
			</div>
		</div>
	</div>
</div>