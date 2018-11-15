<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="./layouts/header.jsp" %>

<div class="row">
    <div class="col-lg-12">
        <div class="panel-body">
            <div class="row">
                <!-- /.col-lg-6 (nested) -->
                <div class="col-lg-6">
                    <p class="text-center">
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam vel tortor aliquam, scelerisque erat vel, cursus dui. Fusce pretium ante quis diam egestas tristique. Nullam faucibus tempor urna et dapibus. Vivamus est nunc, lacinia a venenatis eget, sodales at erat. Aenean dictum iaculis leo. Suspendisse aliquet quam sit amet libero porttitor, vitae dignissim metus tempus. Suspendisse quis nulla sagittis, iaculis eros id, volutpat leo. Mauris commodo felis eros, et dignissim justo viverra a. Fusce tincidunt elementum sagittis. Mauris sit amet ante ornare, dapibus lectus nec, facilisis libero. Nulla nec nisi imperdiet, commodo ligula nec, elementum metus. Duis aliquam varius urna, non blandit ante vestibulum vitae.
                    </p>
                </div>
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