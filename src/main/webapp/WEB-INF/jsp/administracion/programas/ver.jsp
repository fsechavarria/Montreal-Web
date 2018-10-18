<%@include file="../../layouts/header.jsp" %>

Nombre: <c:out value="${programa.getNomb_programa()}" /> <br/>
Descripción: <c:out value="${programa.getDesc_programa()}" /> <br/>
Fecha de Inicio: <fmt:formatDate type = "date" dateStyle="short" value = "${programa.getFech_inicio()}" /> <br/>
Fecha de Termino: <fmt:formatDate type = "date" dateStyle="short" value = "${programa.getFech_termino()}" /> <br/>
Cantidad Máxima de Alumnos: <c:out value="${programa.getCant_min_alumnos()}" /> <br/>
Cantidad Mínima de Alumnos: <c:out value="${programa.getCant_max_alumnos()}" /> <br/>

<%@include file="../../layouts/footer.jsp" %>