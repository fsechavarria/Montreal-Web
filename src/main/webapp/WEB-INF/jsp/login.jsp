<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico">
        <script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/js/bootstrap.bundle.min.js" type="text/javascript"></script>
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css.map" type="text/css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/all.css" type="text/css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/resources/css/custom.css" type="text/css" rel="stylesheet">
        <title>Montreal - <c:out value="${title}"/></title>
    </head>
    <body>
        <div class="container mt-5">
            <div class="row">
              <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
                <div class="card card-signin my-5">
                  <div class="card-body">
                    <h5 class="card-title text-center">Iniciar Sesión</h5>
                    <form:form cssClass="form-signin" method="POST" modelAttribute="usuario">
                      <div class="form-label-group">
                        <form:input id="inputUsuario" path="usuario" cssClass="form-control" />
                        <label for="inputUsuario">Usuario</label>
                      </div>

                      <div class="form-label-group">
                        <form:password id="inputContrasena" path="contrasena" cssClass="form-control" />
                        <label for="inputContrasena">Contraseña</label>
                      </div>
                      <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Ingresar</button>
                    </form:form>
                  </div>
                  <p class="text-danger"><c:out value="${errorMsg}" /> </p>
                </div>
              </div>
            </div>
        </div>

<%@include file="./layouts/footer.jsp" %>