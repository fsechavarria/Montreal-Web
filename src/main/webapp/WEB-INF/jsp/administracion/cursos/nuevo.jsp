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
                        <form:form role="form" modelAttribute="curso">
                            <div class="form-group">
                                <label for="selectPrograma">Programas de Estudio</label>
                                <form:select id="selectPrograma" cssClass="form-control" path="id_programa">
                                    <c:forEach items="${lstPrograma}" var="programa">
                                        <form:option value="${programa.getId_programa()}">${programa.getNomb_programa()}</form:option>
                                    </c:forEach>
                                </form:select>
                            </div>
                            <div class="form-group">
                                <label>Descripción</label>
                                <form:input cssClass="form-control" path="desc_curso"/>
                                <form:errors cssClass="text-danger" path="desc_curso" />
                            </div>
                            <div class="form-group">
                                <label>Cupos</label>
                                <form:input type="number" min="1" cssClass="form-control" rows="3" path="cupos"></form:input>
                                <form:errors cssClass="text-danger" path="cupos" />
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