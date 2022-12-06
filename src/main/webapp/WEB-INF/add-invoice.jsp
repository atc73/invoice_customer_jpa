<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add customer</title>
</head>
<body>

<h1>Add Invoice</h1>

<form method="post" action="/invoices/add">
  <label for="invoiceDate">Date :</label>
  <input id="invoiceDate" type="date" name="invoiceDate">

  <label for="invoiceTotal">Total :</label>
  <input id="invoiceTotal" type="number" step="0.01" name="invoiceTotal">

  <label for="customers">Choose a customer:</label>
  <select name="customers" id="customers">
    <option value="">--Please choose an option--</option>

    <c:forEach items="${customers}" var="customer">
      <option value="${customer.customerId}">${customer.name}</option>
    </c:forEach>

  </select>

  <button>Add</button>
</form>

</body>
</html>
