<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/resources/img/favicon.ico">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">


        <!-- Bootstrap Core CSS -->
        <link href="${pageContext.request.contextPath}/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- MetisMenu CSS -->
        <link href="${pageContext.request.contextPath}/resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

        <!-- Custom CSS -->
        <link href="${pageContext.request.contextPath}/resources/dist/css/sb-admin-2.css" rel="stylesheet">

        <!-- DataTables CSS -->
        <link href="${pageContext.request.contextPath}/resources/vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">
        
        <!-- DataTables Responsive CSS -->
        <link href="${pageContext.request.contextPath}/resources/vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">
        
        <!-- Custom Fonts -->
        <link href="${pageContext.request.contextPath}/resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <title>Montreal - <c:out value="${title}"/></title>
    </head>
    <body>
        <fmt:requestEncoding value = "UTF-8" />
        <div id="wrapper">
        
        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Navegación</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" style="padding-top: 0px;" href="<c:url value="/home.htm"/>">
                    <img src="${pageContext.request.contextPath}/resources/img/Logo Montreal.png" alt="montreal1"/>
                </a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <div>
                        <span class="text-muted">
                            <c:out value="${loggedUser.getNombre()}" />
                        </span>
                    </div>
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="/mi-cuenta.htm"><i class="fa fa-user fa-fw"></i> Mi Cuenta</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="<c:url value="/logout.htm" />"><i class="fa fa-sign-out fa-fw"></i> Cerrar Sesión</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <c:if test="${loggedUser != null}">
                            <c:choose>
                                <c:when test="${loggedUser.getRol() == 'Administrador'}">
                                    <li>
                                        <a href="<c:url value="/administracion/programas.htm"/>"><i class="fa fa-book fa-fw"></i> Programas de Estudio</a>
                                    </li>
                                    <li>
                                        <a href="<c:url value="/administracion/cursos.htm"/>"><i class="fa fa-file-text fa-fw"></i> Cursos</a>
                                    </li>
                                    <li>
                                        <a href="<c:url value="/administracion/postulaciones.htm"/>"><i class="fa fa-file fa-fw"></i> Postulaciones</a>
                                    </li>
                                    <li>
                                        <a href="#"><i class="fa fa-users" aria-hidden="true"></i> Usuarios<span class="fa arrow"></span></a>
                                        <ul class="nav nav-second-level">
                                            <li>
                                                <a href="<c:url value="/administracion/usuarios.htm"/>">Ver Usuarios</a>
                                            </li>
                                            <li>
                                                <a href="<c:url value="/administracion/cel/nuevo.htm"/>">Registrar CEL</a>
                                            </li>
                                            <li>
                                                <a href="<c:url value="/administracion/cem/nuevo.htm"/>">Registrar CEM</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li>
                                        <a href="#"><i class="fa fa-file-pdf-o" aria-hidden="true"></i> Antecedentes<span class="fa arrow"></span></a>
                                        <ul class="nav nav-second-level">
                                            <li>
                                                <a href="<c:url value="/administracion/antecedentes.htm"/>">Ver Antecedentes</a>
                                            </li>
                                            <li>
                                                <a href="<c:url value="/administracion/antecedentes/nuevo.htm"/>">Cargar Antecedentes</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li>
                                        <a href="<c:url value="#"/>"><i class="fa fa-certificate" aria-hidden="true"></i> Certificados</a>
                                    </li>
                                    <!-- Menu administrador -->
                                </c:when>
                                <c:when test="${loggedUser.getRol() == 'CEM'}">
                                    <li>
                                        <a href="<c:url value="/administracion/programas.htm"/>"><i class="fa fa-book fa-fw"></i> Programas de Estudio</a>
                                    </li>
                                    <li>
                                        <a href="<c:url value="/administracion/cursos.htm"/>"><i class="fa fa-file-text fa-fw"></i> Cursos</a>
                                    </li>
                                    <li>
                                        <a href="<c:url value="#"/>"><i class="fa fa-graduation-cap" aria-hidden="true"></i> Calificaciones</a>
                                    </li>
                                    <li>
                                        <a href="<c:url value="#"/>"><i class="fa fa-file-pdf-o" aria-hidden="true"></i> Antecedentes</a>
                                    </li>
                                    <li>
                                        <a href="<c:url value="#"/>"><i class="fa fa-certificate" aria-hidden="true"></i> Certificados</a>
                                    </li>
                                    <!-- Menu cem -->
                                </c:when>
                                <c:when test="${loggedUser.getRol() == 'CEL'}">
                                    <li>
                                        <a href="#"><i class="fa fa-book fa-fw"></i> Programas de Estudio<span class="fa arrow"></span></a>
                                        <ul class="nav nav-second-level">
                                            <li>
                                                <a href="<c:url value="/administracion/mis-programas.htm"/>">Mis Programas</a>
                                            </li>
                                            <li>
                                                <a href="<c:url value="/administracion/programas.htm"/>">Ver Programas</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <li>
                                        <a href="<c:url value="#"/>"><i class="fa fa-graduation-cap" aria-hidden="true"></i> Registrar Notas</a>
                                    </li>
                                    <!-- Menu cel -->
                                </c:when>
                                <c:when test="${loggedUser.getRol() == 'Alumno'}">
                                    <li>
                                        <a href="<c:url value="/administracion/programas.htm"/>"><i class="fa fa-book fa-fw"></i> Programas de Estudio</a>
                                    </li>
                                    <li>
                                        <a href="<c:url value="/administracion/postulaciones.htm"/>"><i class="fa fa-file fa-fw"></i> Mis Postulaciones</a>
                                    </li>
                                    <li>
                                        <a href="<c:url value="#"/>"><i class="fa fa-graduation-cap" aria-hidden="true"></i> Mis Calificaciones</a>
                                    </li>
                                    <li>
                                        <a href="<c:url value="#"/>"><i class="fa fa-certificate" aria-hidden="true"></i> Mis Certificados</a>
                                    </li>
                                    <!-- Menu alumno -->
                                </c:when>
                                <c:when test="${loggedUser.getRol() == 'Familia'}">
                                    <li>
                                        <a href="<c:url value="#"/>"><i class="fa fa-file-pdf-o" aria-hidden="true"></i> Antecedentes<span class="fa arrow"></span></a>
                                        <ul class="nav nav-second-level">
                                            <li>
                                                <a href="<c:url value="#"/>">Mis antecedentes</a>
                                            </li>
                                            <li>
                                                <a href="<c:url value="#"/>">Cargar Antecedentes</a>
                                            </li>
                                        </ul>
                                    </li>
                                    <!-- Menu Familia -->
                                </c:when>
                            </c:choose>
                        </c:if>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
        <div id="page-wrapper">