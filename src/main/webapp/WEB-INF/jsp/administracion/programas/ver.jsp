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
                                <label>Descripci�n</label>
                                <textarea class="form-control" rows="3" placeholder="Descripci�n"><c:out value="${programa.getDesc_programa()}" /></textarea>
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
                                <label>Fecha de Termino</label>
                                <input type="date" class="form-control" placeholder="Fecha de Termino" value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${programa.getFech_termino()}" />">
                            </div>
                            <div class="form-group">
                                <label>Cantidad m�nima de alumnos</label>
                                <input type="number" class="form-control" placeholder="Cantidad m�nima de alumnos" value="<c:out value="${programa.getCant_min_alumnos()}" />">
                            </div>
                            <div class="form-group">
                                <label>Cantidad m�xima de alumnos</label>
                                <input type="number" class="form-control" placeholder="Cantidad m�xima de alumnos" value="<c:out value="${programa.getCant_max_alumnos()}" />">
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