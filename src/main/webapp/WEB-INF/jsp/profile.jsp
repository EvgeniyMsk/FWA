<%@ page import="java.util.ArrayList" %>
<%@ page import="edu.school21.cinema.models.AuthHistory" %>
<%@ page import="edu.school21.cinema.models.User" %><%--
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-4">
            <img src="https://cdn1.ozone.ru/s3/multimedia-j/6021227203.jpg" style="height: 300px; width: 200px">
            <br>
            <button type="button">Обновить фото</button>
        </div>
        <div class="col-4">
            <% User user = (User) request.getSession().getAttribute("user");%>
            <table class="table table-bordered">
                <th>
                    Логин
                </th>
                <th>
                    Имя
                </th>
                <th>
                    Фамилия
                </th>
                <th>
                    Телефон
                </th>
                <tbody>
                <td>
                    <%=user.getLogin()%>
                </td>
                <td>
                    <%=user.getFirstName()%>
                </td>
                <td>
                    <%=user.getLastName()%>
                </td>
                <td>
                    <%=user.getPhoneNumber()%>
                </td>
                </tbody>
            </table>
            <form action="/Cinema_war_exploded/profile" method="post">
                <label for="firstname">Имя</label>
                <input type="text" id="firstname" name="firstname" placeholder="Имя">
                <br>
                <label for="lastname">Фамилия</label>
                <input type="text" id="lastname" name="lastname" placeholder="Фамилия">
                <br>
                <label for="phone">Телефон</label>
                <input type="text" id="phone" name="phone" placeholder="Телефон">
                <br>
                <div>
                    <button type="submit">Обновить</button>
                </div>
            </form>
        </div>
    </div>
    <div class="row">
        <%ArrayList<AuthHistory> authHistories = (ArrayList<AuthHistory>)request.getSession().getAttribute("auth");
            String operation = "";
            for (AuthHistory authHistory : authHistories)
            {
                if (authHistory.getType().equals("sign_up"))
                {
                    operation = "Регистрация";
                }
                else
                {
                    operation = "Вход";
                }
                out.println("<div>" + operation + "</div>");
                out.println("<div>" + authHistory.getTime() + "</div>");
                out.println("<div>" + authHistory.getAddress() + "</div>");
            }
        %>
        <form action="/Cinema_war_exploded/logout" method="post">
            <button type="submit">Выйти</button>
        </form>
    </div>

</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>
