<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../layouts/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Registrar CEM</h1>
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
                        <form:form role="form" modelAttribute="centro">
                            <div class="form-group">
                                <label>Usuario</label>
                                <form:input cssClass="form-control" path="usuario.usuario"/>
                                <span id="usuario" class="text-danger"></span>
                            </div>
                            <div class="form-group">
                                <label>Contraseña</label>
                                <form:password cssClass="form-control" path="usuario.contrasena"/>
                                <span id="contrasena1" class="text-danger"></span>
                            </div>
                            <div class="form-group">
                                <label>Confirmar Contraseña</label>
                                <input type="password" id="pw2" class="form-control" />
                                <span id="contrasena2" class="text-danger"></span>
                            </div>
                            <hr/>
                                <h3>Administrador del Centro</h3>
                            <hr/>
                            <div class="form-group">
                                <label>Nombre(s)</label>
                                <form:input cssClass="form-control" path="usuario.persona.nombre"/>
                                <span id="nombre" class="text-danger"></span>
                            </div>
                            <div class="form-group">
                                <label>Apellido Paterno</label>
                                <form:input cssClass="form-control" path="usuario.persona.app_paterno"/>
                                <span id="app_paterno" class="text-danger"></span>
                            </div>
                            <div class="form-group">
                                <label>Apellido Materno</label>
                                <form:input cssClass="form-control" path="usuario.persona.app_materno"/>
                                <span id="app_materno" class="text-danger"></span>
                            </div>
                            <div class="form-group">
                                <label>RUT</label>
                                <form:input cssClass="form-control" path="usuario.persona.rut"/>
                                <span id="rut" class="text-danger"></span>
                            </div>
                            <div class="form-group">
                                <label>Fecha de Nacimiento</label>
                                <form:input type="date" cssClass="form-control" path="usuario.persona.fech_nacimiento"/>
                                <span id="fecha_nacimiento" class="text-danger"></span>
                            </div>
                            <div class="form-group">
                                <label>Correo Electrónico</label>
                                <form:input type="email" cssClass="form-control" path="usuario.persona.contacto.desc_contacto"/>
                                <span id="email" class="text-danger"></span>
                            </div>
                            <hr/>
                                <h3>Datos del Centro</h3>
                            <hr/>
                            <div class="form-group">
                                <label>Nombre</label>
                                <form:input cssClass="form-control" path="nom_centro"/>
                                <span id="nombre_centro" class="text-danger"></span>
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
                                <span id="calle" class="text-danger"></span>
                            </div>
                            <div class="form-group">
                                <label>Numeración</label>
                                <form:input cssClass="form-control" path="usuario.persona.direccion.numeracion"/>
                                <span id="numeracion" class="text-danger"></span>
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
<script>
    $(document).ready(function(){
       registrar_centro(); 
    });
</script>
