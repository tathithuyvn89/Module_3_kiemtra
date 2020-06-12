<%--
  Created by IntelliJ IDEA.
  User: BKComputer
  Date: 6/12/2020
  Time: 9:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>Product list</h1>
<div>
    <form method="post" action="./home?action=search">
        <input type="text" name="search" placeholder=" Enter a name of product">
        <input type="hidden" name="address" value="/home">
        <button type="submit">Search</button>
    </form>
</div>
<a href="./home?action=create">Create new product</a>
<table border="3">
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Price</td>
        <td>Quantity</td>
        <td>Color</td>
        <td>Category</td>
        <td>Action</td>
    </tr>
    <c:forEach  items='${requestScope["products"]}' var="product">
        <tr>
            <td>${product.getId()}</td>
            <td>${product.getName()}</td>
            <td>${product.getPrice()}</td>
            <td>${product.getQuantity()}</td>
            <td>${product.getColor()}</td>
            <td>${product.getCategory()}</td>
            <td><a href="./home?action=edit&id=${product.getId()}">Edit</a></td>
            <td><a href="./home?action=delete&id=${product.getId()}">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
