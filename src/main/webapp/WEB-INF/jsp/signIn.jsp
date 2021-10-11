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
        header {
            background: darkgray;
            text-align: center;
        }
        footer {
            color: beige;
            background: #092a75;
            text-align: center;
        }
        .signInForm {
            background: bisque;
            align-content: center;
        }
        html {
            position: relative;
            min-height: 100%;
        }
    </style>
</head>
<body>
<header>
    <h1>Авторизация</h1>
</header>
<div class="signInForm">
    <form action="Cinema_war_exploded/signIn" method="post">
        <div>
        <input type="text" value="Login">
        </div>
        <div>
        <input type="text" value="Password">
        </div>
        <div>
        <button type="submit">Войти</button>
        </div>
    </form>
</div>
<footer>
    <h3>
        <%SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");%>
        <%=formatter.format(new Date(System.currentTimeMillis()))%>
    </h3>
</footer>
</body>
</html>
