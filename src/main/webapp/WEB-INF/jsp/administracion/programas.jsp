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
            <div class="panel-body">
                <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Descripción</th>
                            <th>CEL Asociado</th>
                            <th>Fecha de Inicio</th>
                            <th>Fecha de Término</th>
                            <th> # </th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${lstProgramas}" var="programa" >
                            <tr>
                                <td><c:out value="${programa.nomb_programa}" /></td>
                                <td><c:out value="${programa.desc_programa}" /></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${programa.id_cel}">
                                            <c:out value="${programa.id_cel}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="N/A"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><fmt:formatDate type = "date" dateStyle="short"value = "${programa.fech_inicio}" /></td>
                                <td><fmt:formatDate type = "date" dateStyle="short" value = "${programa.fech_termino}" /></td>
                                <td><a href="<c:out value="/administracion/programas.htm?id=${programa.id_programa}" />">Ver</a></td>
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

<%@include file="../layouts/footer.jsp" %>

<script>
$(document).ready(function() {
    $('#dataTable').DataTable({
        responsive: true
    });
});
</script>