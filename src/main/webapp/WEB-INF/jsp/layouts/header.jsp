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
                <a class="navbar-brand" href="<c:url value="/home.htm"/>">Inicio</a>
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
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
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
                                    <!-- <li class="nav-item dropdown">
                                        <a class="nav-link dropdown-toggle" href="https://example.com" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
                                        <div class="dropdown-menu" aria-labelledby="dropdown04">
                                          <a class="dropdown-item" href="#">Action</a>
                                          <a class="dropdown-item" href="#">Another action</a>
                                          <a class="dropdown-item" href="#">Something else here</a>
                                        </div>
                                    </li>-->
                                    <!-- Menu admin -->
                                </c:when>
                                <c:when test="${loggedUser.getRol() == 'CEM'}">
                                    <li>
                                        <a href="<c:url value="/administracion/programas.htm"/>"><i class="fa fa-book fa-fw"></i> Programas de Estudio</a>
                                    </li>
                                    <li>
                                        <a href="<c:url value="/administracion/cursos.htm"/>"><i class="fa fa-file-text fa-fw"></i> Cursos</a>
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
                        </c:if>
                        <li>
                            <a href="tables.html"><i class="fa fa-table fa-fw"></i> Tables</a>
                        </li>
                        <li>
                            <a href="forms.html"><i class="fa fa-edit fa-fw"></i> Forms</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-wrench fa-fw"></i> UI Elements<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="panels-wells.html">Panels and Wells</a>
                                </li>
                                <li>
                                    <a href="buttons.html">Buttons</a>
                                </li>
                                <li>
                                    <a href="notifications.html">Notifications</a>
                                </li>
                                <li>
                                    <a href="typography.html">Typography</a>
                                </li>
                                <li>
                                    <a href="icons.html"> Icons</a>
                                </li>
                                <li>
                                    <a href="grid.html">Grid</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-sitemap fa-fw"></i> Multi-Level Dropdown<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="#">Second Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Second Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Third Level <span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                    </ul>
                                    <!-- /.nav-third-level -->
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-files-o fa-fw"></i> Sample Pages<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="blank.html">Blank Page</a>
                                </li>
                                <li>
                                    <a href="login.html">Login Page</a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
        <div id="page-wrapper">