<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../layouts/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Alumnos</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4>Programa: ${nomb_programa}</h4>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Apellido Paterno</th>
                            <th>Apellido Materno</th>
                            <th>Rut</th>
                            <th><i class="fa fa-edit"></i></th>
                            <th><i class="fa fa-eye"></i></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${lstAlumnos}" var="alumno" >
                            <tr>
                                <td><c:out value="${alumno.usuario.persona.nombre}"/></td>
                                <td><c:out value="${alumno.usuario.persona.app_paterno}" /></td>
                                <td><c:out value="${alumno.usuario.persona.app_materno}" /></td>
                                <td><c:out value="${alumno.usuario.persona.rut}" /></td>
                                <td><a href="<c:out value="/administracion/calificaciones/nueva.htm?id=${alumno.id_alumno}" />">Ingresar Nota</a></td>
                                <td><a href="<c:out value="/administracion/calificaciones/alumno.htm?id=${alumno.id_alumno}&nombre=${alumno.usuario.persona.nombre} ${alumno.usuario.persona.app_paterno} ${alumno.usuario.persona.app_materno}" />">Ver Notas</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <p class="text-danger"><c:out value="${errorMsg}" /> </p>
                <p class="text-success"><c:out value="${msg}" /> </p>
                <!-- /.table-responsive -->
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>

<%@include file="../../layouts/footer.jsp" %>

<script>
$(document).ready(function() {
    $('#dataTable').DataTable({
        responsive: true
    });
});
</script>