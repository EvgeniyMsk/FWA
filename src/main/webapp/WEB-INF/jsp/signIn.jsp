<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %><%--
  Created by IntelliJ IDEA.
  User: qsymond
  Date: 11.10.2021
  Time: 18:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Регистрация епта</title>
    <style>
    </style>
</head>
<body>
<h1>Авторизация</h1>
<form action="/Cinema_war_exploded/signIn" method="post">
    <label for="login">Логин</label>
    <input type="text" id="login" name="login" placeholder="Логин">
    <label for="password">Пароль</label>
    <input type="password" id="password" name="password" placeholder="Пароль">
    <div>
    <button type="submit">Войти</button>
    </div>
</form>
<a href="/Cinema_war_exploded/">На главную</a>
</body>
</html>
