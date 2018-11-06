<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../layouts/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Usuario</h1>
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
                        <form:form role="form" modelAttribute="usuario">
                            <form:hidden path="id_usuario" />
                            <form:hidden path="persona.id_persona" />
                            <div class="form-group">
                                <label>Nombre</label>
                                <form:input cssClass="form-control" path="persona.nombre"/>
                            </div>
                            <div class="form-group">
                                <label>Apellido Paterno</label>
                                <form:input cssClass="form-control" path="persona.app_paterno"/>
                            </div>
                            <div class="form-group">
                                <label>Apellido Materno</label>
                                <form:input cssClass="form-control" path="persona.app_materno"/>
                            </div>
                            <div class="form-group">
                                <label>Rut</label>
                                <form:input cssClass="form-control" path="persona.rut"/>
                            </div>
                            <div class="form-group">
                                <label>Fecha de Nacimiento</label>
                                <form:input type="date" cssClass="form-control" path="persona.fech_nacimiento"/>
                            </div>
                            <div class="form-group">
                                <form:hidden path="persona.contacto.id_contacto" />
                                <label>Correo Electrónico</label>
                                <form:input type="text" cssClass="form-control" path="persona.contacto.desc_contacto"/>
                            </div>
                            <hr/>
                            <h3>Dirección</h3>
                            <hr/>
                            <form:hidden path="persona.direccion.id_direccion" />
                            <div class="form-group">
                                <label>Ciudad</label>
                                <form:select cssClass="form-control" path="persona.direccion.id_ciudad">
                                    <c:forEach items="${lstCiudad}" var="ciudad">
                                        <form:option value="${ciudad.getId_ciudad()}">${ciudad.getNombre()}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="form-group">
                                <label>Calle</label>
                                <form:input cssClass="form-control" path="persona.direccion.calle"/>
                            </div>
                            <div class="form-group">
                                <label>Numeración</label>
                                <form:input cssClass="form-control" path="persona.direccion.numeracion"/>
                            </div>
                            <div class="form-group">
                                <label>Departamento</label>
                                <form:input cssClass="form-control" path="persona.direccion.departamento"/>
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