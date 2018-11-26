<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="../../layouts/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <h1 class="page-header">Antecedente</h1>
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
                        <form:form role="form" modelAttribute="antecedente">
                            <form:hidden path="id_antecedente" />
                            <div class="form-group">
                                <label>Descripción</label>
                                <form:textarea cssClass="form-control" path="desc_antecedente"></form:textarea>
                                <span id="descripcion" class="text-danger"></span>
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
       $('#antecedente').submit(function(event){
          event.preventDefault();
          var descripcion = document.getElementById('desc_antecedente');
          
          if (!validar_texto(descripcion)) {
              document.getElementById('descripcion').innerText = 'Debe ingresar una descripción de máximo 100 caracteres';
          } else {
              this.submit();
          }
       });
    });
</script>