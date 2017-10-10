<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/clients">
    <input type="text" name="clientName" value="${client.getName()}">
    <input type="text" name="clientPassword" value="${client.getPassword()}">
    <button type="submit" name="updateClient" value="${client.getId()}">Update</button>
</form>
</body>
</html>
