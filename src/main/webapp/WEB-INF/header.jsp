<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header>
  <a href="${pageContext.request.contextPath}/customers">Customers</a>
  <a href="${pageContext.request.contextPath}/customers/add">Add customer</a>
  <a href="${pageContext.request.contextPath}/invoices">Invoices</a>
  <a href="${pageContext.request.contextPath}/invoices/add">Add invoice</a>
  <a href="${pageContext.request.contextPath}/products">Products</a>
  <a href="${pageContext.request.contextPath}/products/add">Add product</a>
  <a href="${pageContext.request.contextPath}/login"></a>
  <a href="${pageContext.request.contextPath}/logout"></a>


  <c:choose>
    <c:when test="${not empty sessionScope.username}">
      <form method="post" action="${pageContext.request.contextPath}/logout">
        <input type="submit" value="Logout">
      </form>
    </c:when>

    <c:otherwise>
      <a href="${pageContext.request.contextPath}/login">Sign In</a>
    </c:otherwise>

  </c:choose>

</header>
