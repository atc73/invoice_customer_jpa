<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add product</title>
</head>
<body>

<h1>Add product</h1>

<form method="post" action="/products/add">

    <label for="name">Name :</label>
    <input id="name" type="text" name="name">

    <label for="description">Description :</label>
    <input id="description" type="text" name="description">

    <label for="price">Price :</label>
    <input id="price" type="number" step="0.01" min="0" name="price">

    <label for="vat">Vat :</label>
    <input id="vat" type="number" step="0.01" min="0" name="vat">

    <button>Add</button>
</form>

</body>
</html>

