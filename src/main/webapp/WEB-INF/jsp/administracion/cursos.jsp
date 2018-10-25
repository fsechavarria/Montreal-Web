<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layouts/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Cursos</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <a href="<c:out value="/administracion/cursos/create.htm" />">Agregar un nuevo curso</a>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable">
                    <thead>
                        <tr>
                            <th>Programa Asociado</th>
                            <th>Descripción</th>
                            <th>Cupos</th>
                            <th><i class="fa fa-edit"></i></th>
                            <th><i class="fa fa-trash"></i></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${lstCursos}" var="curso" >
                            <tr>
                                <td>
                                    <c:choose>
                                        <c:when test="${curso.programa != null}">
                                            <c:out value="${curso.programa.nomb_programa}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="Aún no se asigna un programa"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><c:out value="${curso.desc_curso}" /></td>
                                <td><c:out value="${curso.cupos}" /></td>
                                <td><a href="<c:out value="/administracion/cursos.htm?id=${curso.id_curso}" />">Editar</a></td>
                                <td><a href="<c:out value="/administracion/cursos.htm?id=${curso.id_curso}&delete=true" />">Eliminar</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <p class="text-danger"><c:out value="${errorMsg}" /> </p>
                <p class="text-success"><c:out value="${msg}" /> </p>
                <!-- /.table-responsive -->
                <div>
                    <p class="text-info">Solo se muestran los cursos de los programas que están vigentes.</p>
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