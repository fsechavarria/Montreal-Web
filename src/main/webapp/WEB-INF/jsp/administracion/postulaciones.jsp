<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../layouts/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">
            <c:choose>
                <c:when test="${loggedUser.rol == 'Administrador'}">
                    Postulaciones
                </c:when>
                <c:otherwise>
                    Mis Postulaciones
                </c:otherwise>
            </c:choose>
        </h1>
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <strong>Pendientes</strong>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable">
                    <thead>
                        <tr>
                            <th>Alumno</th>
                            <th>Representante Familia</th>
                            <th>Seguro</th>
                            <th>Programa de Estudio</th>
                            <th>Fecha de Postulación</th>
                            <c:choose>
                                <c:when test="${loggedUser.rol == 'Administrador'}">
                                    <th><i class="fa fa-check-circle-o"></i></th>
                                    <th><i class="fa fa-times"></i></th>
                                </c:when>
                                <c:otherwise>
                                    <th>Reserva de Dinero</th>
                                </c:otherwise>
                            </c:choose>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${lstPostulaciones}" var="postulacion" >
                            <tr>
                                <td><c:out value="${postulacion.alumno.persona.nombre}
                                       ${postulacion.alumno.persona.app_paterno}
                                       ${postulacion.alumno.persona.app_materno}" /></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${postulacion.id_familia != null}">
                                            <c:out value="${postulacion.familia.persona.nombre}
                                                    ${postulacion.familia.persona.app_paterno}
                                                    ${postulacion.familia.persona.app_materno}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="Aún no se escoge una familia"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${postulacion.id_seguro != null}">
                                            <c:out value="${postulacion.seguro.desc_seguro}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="Aún no se asigna un seguro"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><c:out value="${postulacion.programa.nomb_programa}"/></td>
                                <td><fmt:formatDate type = "date" dateStyle="short" value = "${postulacion.fech_postulacion}" /></td>
                                <td><fmt:formatNumber type="currency"  currencySymbol="$" value="${postulacion.reserva_dinero_pasajes}"/></td>
                                <c:if test="${loggedUser.rol == 'Administrador'}">
                                    <td><a href="<c:out value="/administracion/postulaciones.htm?id=${postulacion.id_postulacion}&accept=true" />">Aceptar</a></td>
                                    <td><a href="<c:out value="/administracion/postulaciones.htm?id=${postulacion.id_postulacion}&accept=false" />">Rechazar</a></td>
                                </c:if>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <p class="text-danger"><c:out value="${errorMsg}" /> </p>
                <p class="text-success"><c:out value="${msg}" /> </p>
                <!-- /.table-responsive -->
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<hr/>
<!-- /.row -->
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <strong>Respondidas</strong>
            </div>
            <!-- /.panel-heading -->
            <div class="panel-body">
                <table width="100%" class="table table-striped table-bordered table-hover" id="dataTable2">
                    <thead>
                        <tr>
                            <th>Alumno</th>
                            <th>Representante Familia</th>
                            <th>Seguro</th>
                            <th>Programa de Estudio</th>
                            <th>Reserva de Dinero</th>
                            <th>Fecha de Postulación</th>
                            <th>Fecha de Respuesta</th>
                            <th>Estado</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${lstPostulacionesFinalizadas}" var="postulacion" >
                            <tr>
                                <td><c:out value="${postulacion.alumno.persona.nombre}
                                       ${postulacion.alumno.persona.app_paterno}
                                       ${postulacion.alumno.persona.app_materno}" /></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${programa.id_familia != null}">
                                            <c:out value="${postulacion.familia.persona.nombre}
                                                    ${postulacion.familia.persona.app_paterno}
                                                    ${postulacion.familia.persona.app_materno}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="Aún no se escoge una familia"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${postulacion.id_seguro != null}">
                                            <c:out value="${postulacion.seguro.desc_seguro}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="Aún no se asigna un seguro"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td><c:out value="${postulacion.programa.nomb_programa}"/></td>
                                <td><fmt:formatNumber type="currency"  currencySymbol="$" value="${postulacion.reserva_dinero_pasajes}"/></td>
                                <td><fmt:formatDate type = "date" dateStyle="short" value = "${postulacion.fech_postulacion}" /></td>
                                <td><fmt:formatDate type = "date" dateStyle="short" value = "${postulacion.fech_respuesta}" /></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${postulacion.estado == 'A'}">
                                            <c:out value="Aprobada" />
                                        </c:when>
                                        <c:when test="${postulacion.estado == 'R'}">
                                            <c:out value="Rechazada" />
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="N/A" />
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <!-- /.table-responsive -->
            </div>
            <!-- /.panel-body -->
        </div>
        <!-- /.panel -->
    </div>
    <!-- /.col-lg-12 -->
</div>
                
<%@include file="../layouts/footer.jsp" %>

<script>
$(document).ready(function() {
    $('#dataTable').DataTable({
        responsive: true
    });
    
    $('#dataTable2').DataTable({
        responsive: true
    });
});
</script>