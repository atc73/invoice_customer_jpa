<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add invoice</title>
</head>
<body>

<h1>Add Invoice</h1>

<form method="post" action="/invoices/add">
  <label for="invoiceDate">Date :</label>
  <input id="invoiceDate" type="date" name="invoiceDate">

  <label for="customers">Choose a customer:</label>
  <select name="customers" id="customers">
    <option value="">--Please choose an option--</option>

    <c:forEach items="${customers}" var="customer">
      <option value="${customer.customerId}">${customer.name}</option>
    </c:forEach>

  </select>

  <div>
    <c:forEach items="${products}" var="product">

      <label for="product">${product.name} :</label>
      <input id="product" type="checkbox" value="${product.productId}" name="checkedProducts">

      <label for="product">Quantity :</label>
      <input type="number" name="quantity">

    </c:forEach>
  </div>

  <button>Add</button>
</form>

</body>
</html>
