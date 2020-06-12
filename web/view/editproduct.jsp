<%--
  Created by IntelliJ IDEA.
  User: BKComputer
  Date: 6/12/2020
  Time: 10:35 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Product Form</title>
</head>
<body>
<h1>Edit Product</h1>
<p>
    <c:if test='${requestScope["message"]!=null}'>
        <span style="color:rebeccapurple;">${requestScope["message"]}</span>
    </c:if>
</p>
<p>
    <a href="./home">Back list product</a>
</p>
<form method="post">
    <fieldset>
        <legend>Product infomation</legend>
        <table>
            <tr>
                <td>Name</td>
                <td><input type="text" name="newName" value="${requestScope["product"].getName()}"></td>
            </tr>
            <tr>
                <td>Price</td>
                <td><input type="text" name="newPrice" value="${requestScope["product"].getPrice()}"></td>
            </tr>
            <tr>
                <td>Quantity</td>
                <td><input type="text" name="newQuantity" value="${requestScope["product"].getQuantity()}"></td>
            </tr>
            <tr>
                <td>Color</td>
                <td><input type="text" name="newColor" value="${requestScope["product"].getColor()}"></td>
            </tr>
            <tr>
                <td>Category</td>
                <td><input type="text" name="newCategory" value="${requestScope["product"].getCategory()}"></td>
            </tr>
            <tr>
                <td></td>
                <td><button type="submit">Save</button></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>

