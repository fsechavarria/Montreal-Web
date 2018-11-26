<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../layouts/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Nuevo Curso</h1>
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
                        <form:form modelAttribute="postulacion">
                            <div class="form-group">
                                <label>Familia Anfitriona</label>
                                <form:select cssClass="form-control" path="id_familia">
                                    <c:forEach items="${lstFamilias}" var="familia">
                                        <form:option value="${familia.getId_familia()}">
                                            Nombre: ${familia.getPersona().getNombre()} ${familia.getPersona().getApp_paterno()} ${familia.getPersona().getApp_materno()} |
                                            Integrantes: ${familia.getNum_integrantes()}
                                        </form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="form-group">
                                <label>Dinero de reserva para pasajes</label>
                                <form:input type="number" cssClass="form-control" path="reserva_dinero_pasajes"></form:input>
                                <span id="reserva" class="text-danger"></span>
                            </div>
                            <hr>
                            <h4>Seguro Asociado</h4>
                            <div class="form-group">
                                <label>Detalle</label>
                                <textarea class="form-control" disabled>${seguro.getDesc_seguro()}</textarea>
                            </div>
                            <button type="submit" class="btn btn-default">Postular</button>
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
       postulacion(); 
    });
</script>