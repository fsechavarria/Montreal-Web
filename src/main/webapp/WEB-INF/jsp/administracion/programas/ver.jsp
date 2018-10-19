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
                        <form role="form">
                            <div class="form-group">
                                <label>Nombre</label>
                                <input type="text" class="form-control" placeholder="Nombre" value="<c:out value="${programa.getNomb_programa()}" />">
                            </div>
                            <div class="form-group">
                                <label>Descripción</label>
                                <textarea class="form-control" rows="3" placeholder="Descripción"><c:out value="${programa.getDesc_programa()}" /></textarea>
                            </div>
                            <div class="form-group">
                                <label>CEL a cargo</label>
                                <input type="text" class="form-control" placeholder="CEL a cargo">
                            </div>
                            <div class="form-group">
                                <label>Fecha de Inicio</label>
                                <input type="date" class="form-control" placeholder="Fecha de Inicio" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${programa.getFech_inicio()}" />">
                            </div>
                            <div class="form-group">
                                <label>Fecha de Término</label>
                                <input type="date" class="form-control" placeholder="Fecha de Término" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${programa.getFech_termino()}" />">
                            </div>
                            <div class="form-group">
                                <label>Cantidad mínima de alumnos</label>
                                <input type="number" class="form-control" placeholder="Cantidad mínima de alumnos" value="<c:out value="${programa.getCant_min_alumnos()}" />">
                            </div>
                            <div class="form-group">
                                <label>Cantidad máxima de alumnos</label>
                                <input type="number" class="form-control" placeholder="Cantidad máxima de alumnos" value="<c:out value="${programa.getCant_max_alumnos()}" />">
                            </div>
                            <button type="submit" class="btn btn-default">Guardar</button>
                        </form>
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