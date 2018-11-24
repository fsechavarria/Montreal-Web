<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../layouts/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Nuevo Programa de Estudio</h1>
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
                        <form:form role="form" modelAttribute="nuevoPrograma">
                            <c:if test="${loggedUser.rol == 'Administrador'}">
                                <div class="form-group">
                                    <label>CEM</label>
                                    <form:select cssClass="form-control" path="id_cem">
                                        <c:forEach items="${lstCEM}" var="cem">
                                            <form:option value="${cem.getId_cem()}">${cem.getNom_centro()}</form:option>
                                        </c:forEach>
                                    </form:select>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label>Nombre</label>
                                <form:input cssClass="form-control" path="nomb_programa"/>
                                <form:errors cssClass="text-danger" path="nomb_programa" />
                            </div>
                            <div class="form-group">
                                <label>Descripción</label>
                                <form:textarea cssClass="form-control" rows="3" path="desc_programa"></form:textarea>
                                <form:errors cssClass="text-danger" path="desc_programa" />
                            </div>
                            <div class="form-group">
                                <label>Fecha de Inicio</label>
                                <form:input type="date" cssClass="form-control" path="fech_inicio"/>
                            </div>
                            <div class="form-group">
                                <label>Fecha de Término</label>
                                <form:input type="date" cssClass="form-control" path="fech_termino"/>
                            </div>
                            <div class="form-group">
                                <label>Cantidad mínima de alumnos</label>
                                <form:input type="number" cssClass="form-control" path="cant_min_alumnos"/>
                                <form:errors cssClass="text-danger" path="cant_min_alumnos" />
                            </div>
                            <div class="form-group">
                                <label>Cantidad máxima de alumnos</label>
                                <form:input type="number" cssClass="form-control" path="cant_max_alumnos"/>
                                <form:errors cssClass="text-danger" path="cant_max_alumnos" />
                            </div>
                            <button type="submit" class="btn btn-default">Publicar</button>
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