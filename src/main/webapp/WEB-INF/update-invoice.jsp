<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update invoice</title>
</head>
<body>

<h1>Update Invoice</h1>

<form method="post" action="/invoices/update">

  <input type="hidden" name="invoiceId" value="${invoice.invoiceId}">

  <label for="invoiceDate">Date :</label>
  <input id="invoiceDate" type="date" name="invoiceDate" value="${invoice.invoiceDate}">


  <label for="customers">Choose a customer:</label>
  <select name="customers" id="customers" value="${invoice.customer.customerId}">
    <c:forEach items="${customers}" var="customer">
      <c:choose>
        <c:when test="${invoice.customer.customerId == customer.customerId}">
          <option value="${customer.customerId}" selected>${customer.name}</option>
        </c:when>
        <c:otherwise>
          <option value="${customer.customerId}">${customer.name}</option>
        </c:otherwise>
      </c:choose>
    </c:forEach>

  </select>

  <button>Update</button>
</form>

</body>
</html>
