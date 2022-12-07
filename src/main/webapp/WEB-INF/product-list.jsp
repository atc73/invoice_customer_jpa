<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
  <title>Products</title>
</head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<h1>Products</h1>

<div>
  <a class="btn btn-success"
     href="${pageContext.request.contextPath}/products/add">Ajouter un produit</a>
</div>

<c:forEach items="${products}" var="product">

  <h3>${product.name}</h3>
  <ul>
    <li>${product.description}</li>
    <li>${product.price}</li>
    <li>${product.vat}</li>
  </ul>
</c:forEach>

</body>
</html>

</html>
