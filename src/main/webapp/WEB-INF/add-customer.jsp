<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add customer</title>
</head>
<body>

<h1>Add customer</h1>
<form method="post" action="/customers/add">
    <label for="customerName">Name :</label>
    <input id="customerName" type="text" name="customerName">

    <label for="customerAddress">Address :</label>
    <input id="customerAddress" type="text" name="customerAddress">

    <label for="customerPostcode">Postcode :</label>
    <input id="customerPostcode" type="number" name="customerPostcode">

    <label for="customerCity">City :</label>
    <input id="customerCity" type="text" name="customerCity">

    <label for="customerPhoneNumber">Phone number :</label>
    <input id="customerPhoneNumber" type="text" name="customerPhoneNumber">

    <label for="customerEmail">Email :</label>
    <input id="customerEmail" type="text" name="customerEmail">

    <button>Add</button>
</form>

</body>
</html>
