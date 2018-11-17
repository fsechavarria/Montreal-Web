<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layouts/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Programas de Estudio</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <c:if test="${loggedUser.rol == 'Administrador' || loggedUser.rol == 'CEM'}">
                <div class="panel-heading">
                    <a href="<c:out value="/administracion/programas/create.htm" />">Agregar un nuevo programa</a>
                </div>
            </c:if>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Descripción</th>
                            <th>Centro Asignado</th>
                            <th>Fecha de Inicio</th>
                            <th>Fecha de Término</th>
                            <c:choose>
                                <c:when test="${loggedUser.rol == 'Alumno'}">
                                    <th><i class="fa fa-sign-in" aria-hidden="true"></i></th>
                                </c:when>
                                <c:when test="${loggedUser.rol == 'CEL'}">
                                    <th><i class="fa fa-sign-in" aria-hidden="true"></i></th>
                                </c:when>
                                <c:otherwise>
                                    <th><i class="fa fa-edit"></i></th>
                                    <th><i class="fa fa-trash"></i></th>
                                </c:otherwise>
                            </c:choose>
                            
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${lstProgramas}" var="programa" >
                            <tr>
                                <td><c:out value="${programa.nomb_programa}" /></td>
                                <td><c:out value="${programa.desc_programa}" /></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${programa.id_cel != null}">
                                            <c:out value="${programa.cel.nom_centro}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="Aún no se asigna ningún centro"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><fmt:formatDate type = "date" dateStyle="short"value = "${programa.fech_inicio}" /></td>
                                <td><fmt:formatDate type = "date" dateStyle="short" value = "${programa.fech_termino}" /></td>
                                <c:choose>
                                    <c:when test="${loggedUser.rol == 'Alumno'}">
                                        <td><a href="<c:out value="/administracion/postulacion.htm?id=${programa.id_programa}&postular=true" />">Postular</a></td>
                                    </c:when>
                                    <c:when test="${loggedUser.rol == 'CEL'}">
                                        <td><a href="<c:out value="/administracion/programas.htm?id=${programa.id_programa}&unirse=true" />">Unirse</a></td>
                                    </c:when>
                                    <c:otherwise>
                                        <td><a href="<c:out value="/administracion/programas.htm?id=${programa.id_programa}" />">Editar</a></td>
                                        <td><a href="<c:out value="/administracion/programas.htm?id=${programa.id_programa}&delete=true" />">Eliminar</a></td>
                                    </c:otherwise>
                                </c:choose>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <p class="text-danger"><c:out value="${errorMsg}" /> </p>
                <p class="text-success"><c:out value="${msg}" /> </p>
                <!-- /.table-responsive -->
                <c:if test="${loggedUser.rol == 'Alumno'}">
                    <div>
                        <p class="text-info">Solo se muestran los programas vigentes a los que aún no has postulado.</p>
                    </div>
                </c:if>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<hr/>
<c:if test="${loggedUser.rol == 'CEM' || loggedUser.rol == 'Administrador'}">
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h4>Programas finalizados</h4>
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable2">
                        <thead>
                            <tr>
                                <th>Nombre</th>
                                <th>Descripción</th>
                                <th>Centro Asignado</th>
                                <th>Fecha de Inicio</th>
                                <th>Fecha de Término</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${lstProgramasFinalizados}" var="programa" >
                                <tr>
                                    <td><c:out value="${programa.nomb_programa}" /></td>
                                    <td><c:out value="${programa.desc_programa}" /></td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${programa.id_cel != null}">
                                                <c:out value="${programa.cel.nom_centro}"/>
                                            </c:when>
                                            <c:otherwise>
                                                <c:out value="Aún no se asigna ningún centro"/>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td><fmt:formatDate type = "date" dateStyle="short"value = "${programa.fech_inicio}" /></td>
                                    <td><fmt:formatDate type = "date" dateStyle="short" value = "${programa.fech_termino}" /></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <!-- /.table-responsive -->
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-12 -->
    </div>
</c:if>        
<%@include file="../layouts/footer.jsp" %>

<script>
$(document).ready(function() {
    $('#dataTable').DataTable({
        responsive: true
    });
    
    $('#dataTable2').DataTable({
        responsive: true
    });
});
</script>