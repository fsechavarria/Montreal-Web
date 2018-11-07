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
                        <form:form role="form" modelAttribute="cem">
                            <div class="form-group">
                                <label>Usuario</label>
                                <form:input cssClass="form-control" path="usuario.usuario"/>
                            </div>
                            <div class="form-group">
                                <label>Contraseña</label>
                                <form:password cssClass="form-control" path="usuario.contrasena"/>
                            </div>
                            <div class="form-group">
                                <label>Confirmar Contraseña</label>
                                <input type="password" id="confirm-password" class="form-control" />
                            </div>
                            <hr/>
                                <h3>Administrador del Centro</h3>
                            <hr/>
                            <div class="form-group">
                                <label>Nombre(s)</label>
                                <form:input cssClass="form-control" path="usuario.persona.nombre"/>
                            </div>
                            <div class="form-group">
                                <label>Apellido Paterno</label>
                                <form:input cssClass="form-control" path="usuario.persona.app_paterno"/>
                            </div>
                            <div class="form-group">
                                <label>Apellido Materno</label>
                                <form:input cssClass="form-control" path="usuario.persona.app_materno"/>
                            </div>
                            <div class="form-group">
                                <label>RUT</label>
                                <form:input cssClass="form-control" path="usuario.persona.rut"/>
                            </div>
                            <div class="form-group">
                                <label>Fecha de Nacimiento</label>
                                <form:input type="date" cssClass="form-control" path="usuario.persona.fech_nacimiento"/>
                            </div>
                            <div class="form-group">
                                <label>Correo Electrónico</label>
                                <form:input type="email" cssClass="form-control" path="usuario.persona.contacto.desc_contacto"/>
                            </div>
                            <hr/>
                                <h3>Datos del Centro</h3>
                            <hr/>
                            <div class="form-group">
                                <label>Nombre</label>
                                <form:input cssClass="form-control" path="nom_centro"/>
                            </div>
                            <div class="form-group">
                                <label>Ciudad</label>
                                <form:select cssClass="form-control" path="usuario.persona.direccion.id_ciudad">
                                    <c:forEach items="${lstCiudad}" var="ciudad">
                                        <form:option value="${ciudad.getId_ciudad()}">${ciudad.getNombre()}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="form-group">
                                <label>Calle</label>
                                <form:input cssClass="form-control" path="usuario.persona.direccion.calle"/>
                            </div>
                            <div class="form-group">
                                <label>Numeración</label>
                                <form:input cssClass="form-control" path="usuario.persona.direccion.numeracion"/>
                            </div>
                            <div class="form-group">
                                <label>Departamento</label>
                                <form:input cssClass="form-control" path="usuario.persona.direccion.departamento"/>
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
