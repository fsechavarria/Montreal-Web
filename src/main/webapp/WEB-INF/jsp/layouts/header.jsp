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
        <c:if test="${loggedUser != null}">
            <nav class="navbar navbar-expand-md navbar-dark bg-dark">
                <a class="navbar-brand" href="<c:url value="/home.htm"/>">Inicio <span class="sr-only">(current)</span></a>
                <div class="collapse navbar-collapse" id="navbarsExample04">
                    <ul class="navbar-nav mr-auto">
                        <c:choose>
                            <c:when test="${loggedUser.getRol() == 'Administrador'}">
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="https://example.com" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
                                    <div class="dropdown-menu" aria-labelledby="dropdown04">
                                      <a class="dropdown-item" href="#">Action</a>
                                      <a class="dropdown-item" href="#">Another action</a>
                                      <a class="dropdown-item" href="#">Something else here</a>
                                    </div>
                                </li>
                                <!-- Menu admin -->
                            </c:when>
                            <c:when test="${loggedUser.getRol() == 'CEM'}">
                                <li class="nav-item">
                                    <a class="nav-link" href="<c:url value="/administracion/programas.htm"/>">Programas de Estudio</a>
                                </li>
                            </c:when>
                            <c:when test="${loggedUser.getRol() == 'CEL'}">
                                <!-- Menu cel -->
                            </c:when>
                            <c:when test="${loggedUser.getRol() == 'Alumno'}">
                                <!-- Menu alumno -->
                            </c:when>
                            <c:when test="${loggedUser.getRol() == 'Familia'}">
                                <!-- Menu Familia -->
                            </c:when>
                        </c:choose>
                    </ul>
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle active" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><c:out value="${loggedUser.getNombre()}"/></a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                              <a class="dropdown-item" href="<c:url value="/logout.htm"/>"><i class="fas fa-sign-out-alt"></i> Cerrar Sesión</a>
                            </div>
                        </li>
                    </ul>
                </div>
            </nav>
        </c:if>