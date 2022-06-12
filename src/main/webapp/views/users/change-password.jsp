<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:if test="${ !empty  sessionScope.error }">
	<div class="alert alert-danger">${ sessionScope.error }</div>
	<c:remove var="error" scope="session" />
</c:if>
<c:if test="${ !empty  sessionScope.message }">
	<div class="alert alert-success">${ sessionScope.message }</div>
	<c:remove var="message" scope="session" />
</c:if>

<div class="row">
	<div class="col-md-5 col-12 m-auto">
		<form:form action="/HiennvPH13697_Asm/change-password-store"
			method="post" modelAttribute="entity">
			<div>
				<lable>Mật khẩu cũ:</lable>
				<form:password class="form-control" path="mkCu" name="mkCu" />
				<form:errors class="badge text-danger mb-3" path="mkCu" name="mkCu"></form:errors>
			</div>
			<div>
				<lable>Mật khẩu mới:</lable>
				<form:password class="form-control" path="mkMoi" name="mkMoi" />
				<form:errors class="badge text-danger mb-3" path="mkMoi"
					name="mkMoi"></form:errors>
			</div>
			<div>
				<lable>Nhập lại mật khẩu:</lable>
				<form:password class="form-control" path="mkMoi2" name="mkMoi2" />
				<form:errors class="badge text-danger mb-3" path="mkMoi2"
					name="mkMoi2"></form:errors>
			</div>
			<div class="text-center">
				<button class="btn btn-primary mt-3">Đổi mật khẩu</button>
			</div>
		</form:form>
	</div>
</div>
