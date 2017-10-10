<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="get" action="/accounts">
    <button type="submit" name="addAccount" value="add">Add account</button>
    <button type="submit" name="backToClients" value="back">Back To Clients</button>
    <button type="submit" name="backHome" value="back">Back Home</button>
</form>
<table>
    <input type="text" hidden name="clientId">
    <tr>
        <td>Account name</td>
        <td>Account password</td>
    </tr>
    <c:forEach items="${accountsList}" var="account">
        <tr>
            <th>${account.getName()}</th>
            <th>${account.getPassword()}</th>
            <form method="get" action="/accounts">
                <th>
                    <button type="submit" name="updateAccount" value="${account.getId()}">Update</button>
                </th>
                <th>
                    <button type="submit" name="deleteAccount" value="${account.getId()}">Delete</button>
                </th>
            </form>
        </tr>
    </c:forEach>
</table>
</body>
</html>
