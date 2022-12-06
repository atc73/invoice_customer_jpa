<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Customers</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<h1>Customers</h1>

<div>
  <a class="btn btn-success"
  href="${pageContext.request.contextPath}/customers/add">Ajouter un client</a>
</div>

  <c:forEach items="${customers}" var="customer">
    <ul>
      <li>${customer.name}</li>
      <li>${customer.address}</li>
      <li>${customer.postcode}</li>
      <li>${customer.city}</li>
      <li>${customer.phoneNumber}</li>
      <li>${customer.email}</li>
      <li style="list-style: none;">
        <form method="post" action="${pageContext.request.contextPath}/customers/delete">
          <input type="hidden" value="${customer.customerId}" name="idCustomer">
          <button>Delete</button>
        </form>
      </li>
      <li style="list-style: none;">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/customers/update?id=${customer.customerId}">Update</a>
      </li>
    </ul>
  </c:forEach>

</body>
</html>
