<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: qsymond
  Date: 11.10.2021
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>

<html>
<head>
    <title>Регистрация епта</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<h1>Авторизация</h1>
<form action="${pageContext.request.contextPath}/signIn" method="post">
    <div>
    <label for="login">Логин</label>
    <input type="text" id="login" name="login" placeholder="Логин">
    </div>
    <div>
    <label for="password">Пароль</label>
    <input type="password" id="password" name="password" placeholder="Пароль">
    </div>
    <div>
    <button type="submit">Войти</button>
    </div>
</form>
<a href="${pageContext.request.contextPath}/">На главную</a>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
