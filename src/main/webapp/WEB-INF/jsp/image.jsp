<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 01.11.2021
  Time: 11:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <title>Просмотр фото</title>
</head>
<body>
<img src="data:image/png;base64,<%=request.getAttribute("image")%>" style="height: 30%; width: 30%;">
</body>
</html>
