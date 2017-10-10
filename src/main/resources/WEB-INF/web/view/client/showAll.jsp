<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="get" action="/clients">
    <th>
        <button type="submit" name="backHome" value="home">Home</button>
    </th>
</form>
<table>
    <tr>
        <td>Client name</td>
        <td>Client password</td>
    </tr>
    <c:forEach items="${clientsList}" var="client">
        <tr>
            <th>${client.getName()}</th>
            <th>${client.getPassword()}</th>
            <form method="get" action="/clients">
                <th>
                    <button type="submit" name="updateClient" value="${client.getId()}">Update</button>
                </th>
                <th>
                    <button type="submit" name="deleteClient" value="${client.getId()}">Delete</button>
                </th>
                <th>
                    <button type="submit" name="showAccounts" value="${client.getId()}">Show Accounts</button>
                </th>
            </form>
        </tr>
    </c:forEach>
</table>
</body>
</html>
