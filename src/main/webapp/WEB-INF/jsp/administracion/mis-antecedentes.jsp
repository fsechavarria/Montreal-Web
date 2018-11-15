<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layouts/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Mis Antecedentes</h1>
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
                            <th>Descripción</th>
                            <th><i class="fa fa-eye"></i></th>
                            <th><i class="fa fa-edit"></i></th>
                            <th><i class="fa fa-trash"></i></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${lstAntecedentes}" var="antecedente" >
                            <tr>
                                <td><c:out value="${antecedente.desc_antecedente}" /></td>
                                <td><a target="_blank" href="${antecedente.baseUrl}${antecedente.url_antecedente}">Ver</a></td>
                                <td><a href="<c:out value="/administracion/antecedentes.htm?id=${antecedente.id_antecedente}" />">Editar</a></td>
                                <td><a href="<c:out value="/administracion/antecedentes.htm?id=${antecedente.id_antecedente}&delete=true" />">Eliminar</a></td>
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
<%@include file="../layouts/footer.jsp" %>

<script>
$(document).ready(function() {
    $('#dataTable').DataTable({
        responsive: true
    });
});
</script>