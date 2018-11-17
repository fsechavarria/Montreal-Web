<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layouts/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Calificaciones</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <!-- /.panel-heading -->
            <div class="panel-body">
                <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable">
                    <thead>
                        <tr>
                            <th>Alumno</th>
                            <th>Programa de Estudio</th>
                            <th>Curso</th>
                            <th>Nota</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${lstCalificaciones}" var="calificacion" >
                            <tr>
                                <td>
                                    <c:out value="${calificacion.alumno.persona.nombre}"/>
                                    <c:out value="${calificacion.alumno.persona.app_paterno}"/>
                                    <c:out value="${calificacion.alumno.persona.app_materno}"/>
                                </td>
                                <td><c:out value="${calificacion.curso.programa.nomb_programa}" /></td>
                                <td><c:out value="${calificacion.curso.desc_curso}" /></td>
                                <td><c:out value="${calificacion.nota}" /></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <p class="text-danger"><c:out value="${errorMsg}" /> </p>
                <p class="text-success"><c:out value="${msg}" /> </p>
                <!-- /.table-responsive -->
                <div>
                    <p class="text-info">Solo se muestran las calificaciones de los cursos que están en programas vigentes.</p>
                </div>
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>

<%@include file="../layouts/footer.jsp" %>

<script>
$(document).ready(function() {
    $('#dataTable').DataTable({
        responsive: true
    });
});
</script>