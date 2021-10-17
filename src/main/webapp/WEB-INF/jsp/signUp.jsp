<%--
  Created by IntelliJ IDEA.
  User: qsymond
  Date: 11.10.2021
  Time: 19:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Регистрация</h1>
<form action="/Cinema_war_exploded/signUp" method="post">
    <label for="login">Логин</label>
    <input type="text" id="login" name="login" placeholder="Логин">
    <label for="password">Пароль</label>
    <input type="password" id="password" name="password" placeholder="Пароль">
    <div>
        <button type="submit">Зарегистрироватья</button>
    </div>
</form>
<a href="/Cinema_war_exploded/">На главную</a>
</body>
</html>
