<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico">
        <title>Montreal - <c:out value="${title}"/></title>
        <!-- Bootstrap Core CSS -->
        <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="${pageContext.request.contextPath}/resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="${pageContext.request.contextPath}/resources/dist/css/sb-admin-2.css" rel="stylesheet">

        <!-- Custom Fonts -->
        <link href="${pageContext.request.contextPath}/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        
    </head>
    <body>
    <fmt:requestEncoding value = "UTF-8" />
    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <div class="login-panel panel panel-default">
                    <div class="panel-heading">
                        <h3 class="panel-title">Iniciar Sesión</h3>
                    </div>
                    <div class="panel-body">
                        <form:form modelAttribute="usuario">
                            <fieldset>
                                <div class="form-group">
                                    <form:input cssClass="form-control" path="usuario" />
                                </div>
                                <div class="form-group">
                                    <form:password cssClass="form-control" path="contrasena" />
                                </div>
                                <button class="btn btn-lg btn-success btn-block">Ingresar</button>
                            </fieldset>
                        </form:form>
                        <hr>
                        <p class="text-center">Registrarse
                        <a href="<c:url value="/registrar-alumno.htm"/>">Como Alumno</a> | 
                        <a href="<c:url value="/registrar-familia.htm"/>">Como Familia</a></p>
                        <p class="text-center text-danger"><c:out value="${errorMsg}" /> </p>
                        <p class="text-center text-success"><c:out value="${msg}" /> </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </body>
</html>