<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../layouts/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Antecedente</h1>
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
                        <form:form role="form" modelAttribute="antecedente" enctype="multipart/form-data">
                            <c:if test="${loggedUser.rol == 'Administrador'}">
                                <div class="form-group">
                                <label>Rut Rep. Familia</label>
                                    <form:select id="selectFamilia" cssClass="form-control" path="id_familia">
                                        <c:forEach items="${lstFamilias}" var="familia">
                                            <form:option value="${familia.getId_familia()}">${familia.getPersona().getRut()}</form:option>
                                        </c:forEach>
                                    </form:select>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label>Cargar Archivo PDF</label>
                                <input type="file" name="file" id="file"/>
                            </div>
                            <div class="form-group">
                                <label>Descripción</label>
                                <form:textarea cssClass="form-control" path="desc_antecedente"></form:textarea>
                            </div>
                            <button type="submit" class="btn btn-default">Guardar</button>
                        </form:form>
                        </br>
                        <p class="text-info">Si la familia seleccionada ya posee un archivo, éste será reemplazado.</p>
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