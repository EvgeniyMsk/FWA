<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 01.11.2021
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Просмотр фото</title>
</head>
<body>
<img src="data:image/png;base64,<%=request.getAttribute("image")%>" style="height: 400px; width: 100%;">
</body>
</html>
