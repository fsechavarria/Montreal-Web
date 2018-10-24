<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../layouts/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Curso</h1>
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
                                <label for="selectPrograma">Alumno</label>
                                <input type="text" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label for="selectPrograma">Representante Familia Anfitriona</label>
                                <input type="text" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label for="selectPrograma">Programa de Estudio</label>
                                <input type="text" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label for="selectPrograma">Seguro Asociado</label>
                                <input type="text" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label for="selectPrograma">Fecha de Postulación</label>
                                <input type="date" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label for="selectPrograma">Fecha de Respuesta</label>
                                <input type="date" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label>Estado</label>
                                <input type="text" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label>Dinero de reserva para pasajes</label>
                                <input type="number" class="form-control" />
                            </div>
                            <div class="form-group">
                                <label class="checkbox-inline">
                                    <input type="checkbox" value="true">Aceptar
                                </label>
                                <label class="checkbox-inline">
                                    <input type="checkbox" value="false">Rechazar
                                </label>
                            </div>
                            <button type="submit" class="btn btn-default">Guardar</button>
                        </form>
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