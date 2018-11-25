<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../layouts/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Programa de Estudio</h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-body">
                <div class="row">
                    <!-- /.col-lg-6 (nested) -->
                    <div class="col-lg-6">
                        <form:form role="form" path="programa" modelAttribute="programa">
                            <form:hidden path="id_programa" />
                            <form:hidden path="id_cem" />
                            <form:hidden path="id_cel" />
                            <div class="form-group">
                                <label>Nombre</label>
                                <form:input cssClass="form-control" path="nomb_programa"/>
                                <span id="nombre" class="text-danger"></span>
                            </div>
                            <div class="form-group">
                                <label>Descripción</label>
                                <form:textarea cssClass="form-control" rows="3" path="desc_programa"></form:textarea>
                                <span id="descripcion" class="text-danger"></span>
                            </div>
                            <div class="form-group">
                                <label>Centro Asignado</label>
                                <c:choose>
                                    <c:when test="${programa.getId_cel() != null}">
                                        <input disabled type="text" class="form-control" placeholder="CEL a cargo" value="<c:out value="${programa.getCel().getNom_centro()}"/>">
                                    </c:when>
                                    <c:otherwise>
                                        <input disabled type="text" class="form-control" placeholder="CEL a cargo" value="<c:out value="Aún no se asigna ningún centro"/>">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="form-group">
                                <label>Fecha de Inicio</label>
                                <form:input type="date" cssClass="form-control" path="fech_inicio"/>
                                <span id="fecha_inicio" class="text-danger"></span>
                            </div>
                            <div class="form-group">
                                <label>Fecha de Término</label>
                                <form:input type="date" cssClass="form-control" path="fech_termino"/>
                                <span id="fecha_termino" class="text-danger"></span>
                            </div>
                            <div class="form-group">
                                <label>Cantidad mínima de alumnos</label>
                                <form:input type="number" cssClass="form-control" path="cant_min_alumnos"/>
                                <span id="min_alumnos" class="text-danger"></span>
                            </div>
                            <div class="form-group">
                                <label>Cantidad máxima de alumnos</label>
                                <form:input type="number" cssClass="form-control" path="cant_max_alumnos"/>
                                <span id="max_alumnos" class="text-danger"></span>
                            </div>
                            <button type="submit" class="btn btn-default">Guardar</button>
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
<script>
    $(document).ready(function(){
        nuevo_programa();
    });
</script>