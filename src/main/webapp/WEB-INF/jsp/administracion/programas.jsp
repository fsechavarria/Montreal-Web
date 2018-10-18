<%@include file="../layouts/header.jsp" %>


<c:forEach items="${lstProgramas}" var="programa" >
    <c:out value="${programa.nomb_programa}" />
    <c:out value="${programa.desc_programa}" />
    <fmt:formatDate type = "date" dateStyle="short"value = "${programa.fech_inicio}" />
    <fmt:formatDate type = "date" dateStyle="short" value = "${programa.fech_termino}" />
    <a href="<c:out value="/administracion/programas.htm?id=${programa.id_programa}" />">Ver</a>
</c:forEach>

<%@include file="../layouts/footer.jsp" %>