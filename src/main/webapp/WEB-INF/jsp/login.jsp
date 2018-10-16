<%@include file="./layouts/header.jsp" %>

<form:form method="POST" modelAttribute="usuario">
    <table>
        <tr>
            <td>Usuario</td>
            <td><form:input path="usuario" /></td>
            <td><form:errors path="usuario" /></td>
        </tr>
        <tr>
            <td>Contraseña</td>
            <td><form:password path="contrasena" /></td>
            <td><form:errors path="contrasena" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Ingresar" />
            </td>
        </tr>
    </table>
    <br />
    <p class="text-danger"><c:out value="${errorMsg}" /> </p>
</form:form>


<%@include file="./layouts/footer.jsp" %>