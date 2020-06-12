<%--
  Created by IntelliJ IDEA.
  User: BKComputer
  Date: 6/12/2020
  Time: 11:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Deleting Product</title>
</head>
<body>
<h1>Delete Product</h1>
<p>
    <a href="./myproduct">Back to list product</a>
</p>
<form method="post">
    <h3>You are sure?</h3>
    <fieldset>
        <legend>Product infomation</legend>
        <table>
            <tr>
                <td>Product Name</td>
                <td>${requestScope["product"].getName()}</td>

            </tr>
            <tr>
                <td>Price</td>
                <td>${requestScope["product"].getPrice()}</td>

            </tr>
            <tr>
                <td>Quantity</td>
                <td>${requestScope["product"].getQuantity()}</td>
            </tr>
            <tr>
                <td>Color</td>
                <td>${requestScope["product"].getColor()}</td>
            </tr>
            <tr>
                <td>Category</td>
                <td>${requestScope["product"].getCategory()}</td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="DeleteProduct"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
