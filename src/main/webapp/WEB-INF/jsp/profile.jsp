<%--
  Created by IntelliJ IDEA.
  User: qsymond
  Date: 16.10.2021
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Профиль пользователя</title>
</head>
<body>
<%=request.getSession().getAttribute("user")%>
</body>
</html>
