<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/accounts">
    <input type="text" name="accountName" value="${account.getName()}">
    <input type="text" name="accountPassword" value="${account.getPassword()}">
    <button type="submit" name="updateAccount" value="${account.getId()}">Update</button>
</form>
</body>
</html>
