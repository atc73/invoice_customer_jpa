<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Invoice list</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<h1>Invoices</h1>

<div>
    <a class="btn btn-success"
       href="${pageContext.request.contextPath}/invoices/add">Ajouter une facture</a>
</div>

<c:forEach items="${invoices}" var="invoice">
    <ul>
        <li>${invoice.invoiceDate}</li>
        <li>${invoice.total}</li>
        <li>${invoice.customer.getName()}</li>
        <c:forEach items="${invoice.getAssociationList()}" var="obj">
            <li>${obj.getProduct().getName()} - ${obj.getProductQuantity()}</li>
        </c:forEach>
        <li style="list-style: none;">
            <form method="post" action="${pageContext.request.contextPath}/invoices/delete">
                <input type="hidden" value="${invoice.invoiceId}" name="idInvoice">
                <button>Delete</button>
            </form>
        </li>
        <li style="list-style: none;">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/invoices/update?id=${invoice.invoiceId}">Update</a>
        </li>
    </ul>
</c:forEach>
</body>
</html>
