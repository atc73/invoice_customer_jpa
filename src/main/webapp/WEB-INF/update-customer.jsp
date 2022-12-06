<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update customer</title>
</head>
<body>

<form method="post" action="/customers/update">

  <input type="hidden" name="customerId" value="${customer.customerId}">

  <label for="customerName">Name :</label>
  <input id="customerName" type="text" name="customerName" value="${customer.name}">

  <label for="customerAddress">Address :</label>
  <input id="customerAddress" type="text" name="customerAddress" value="${customer.address}">

  <label for="customerPostcode">Postcode :</label>
  <input id="customerPostcode" type="number" name="customerPostcode" value="${customer.postcode}">

  <label for="customerCity">City :</label>
  <input id="customerCity" type="text" name="customerCity" value="${customer.city}">

  <label for="customerPhoneNumber">Phone number :</label>
  <input id="customerPhoneNumber" type="text" name="customerPhoneNumber" value="${customer.phoneNumber}">

  <label for="customerEmail">Email :</label>
  <input id="customerEmail" type="text" name="customerEmail" value="${customer.email}">

  <button>Update</button>
</form>

</body>
</html>
