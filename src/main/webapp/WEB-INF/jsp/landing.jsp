<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="./layouts/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <div class="panel-body">
            <div class="row">
                <!-- /.col-lg-6 (nested) -->
                <div class="col-lg-6">
                    <h4 class="text-danger"><c:out value="${errorMsg}" /> </h4>
                    <h4 class="text-success"><c:out value="${msg}" /> </h4>
                </div>
                <!-- /.col-lg-6 (nested) -->
            </div>
            <!-- /.row (nested) -->
        </div>
        <!-- /.panel-body -->
    </div>
    <!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<%@include file="./layouts/footer.jsp" %>