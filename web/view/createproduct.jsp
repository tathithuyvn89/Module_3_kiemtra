<%--
  Created by IntelliJ IDEA.
  User: BKComputer
  Date: 6/12/2020
  Time: 10:09 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Create new Product Form</h1>
<p>
    <c:if test='${requestScope["message"]!=null}'>
        <span  style="color: gold" class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<p><a href="./home">Back to list product</a></p>
<form method="post">
    <fieldset>
        <legend>Product infomation</legend>
        <table>
            <tr>
                <td>Name</td>
                <td><input type="text" name="productName"></td>
            </tr>
            <tr>
                <td>Price</td>
                <td><input type="text" name="productPrice"></td>
            </tr>
            <tr>
                <td>Quantity</td>
                <td><input type="text" name="productQuantity"></td>
            </tr>
            <tr>
                <td>Color</td>
                <td><input type="text" name="productColor"></td>
            </tr>
            <tr>
                <td>Category</td>
                <td><input type="text" name="productCategory"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Create new product"></td>
            </tr>
        </table>
    </fieldset>

</form>
</body>
</html>
