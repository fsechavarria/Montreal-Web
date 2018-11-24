<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../layouts/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Nueva Calificación</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4>Alumno: ${nomb_alumno}</h4>
            </div>
            <div class="panel-body">
                <div class="row">
                    <!-- /.col-lg-6 (nested) -->
                    <div class="col-lg-6">
                        <form:form role="form" modelAttribute="calificacion">
                            <div class="form-group">
                                <label>Curso</label>
                                <form:select cssClass="form-control" path="id_curso">
                                    <c:forEach items="${cursos}" var="curso">
                                        <form:option value="${curso.getId_curso()}">${curso.getDesc_curso()}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="form-group">
                                <label>Nota</label>
                                <form:input type="number" min="1" max="7" step="0.1" cssClass="form-control" path="nota"/>
                            </div>
                            <button type="submit" class="btn btn-default">Ingresar</button>
                        </form:form>
                        <p class="text-danger"><c:out value="${errorMsg}" /> </p>
                    </div>
                    <!-- /.col-lg-6 (nested) -->
                </div>
                <!-- /.row (nested) -->
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<%@include file="../../layouts/footer.jsp" %>
