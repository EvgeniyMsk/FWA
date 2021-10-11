package edu.school21.cinema.servlets;

import org.springframework.context.ApplicationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("")
public class MainCinemaServlet extends HttpServlet {
    private ApplicationContext springContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        springContext = (ApplicationContext) config.getServletContext().getAttribute("springContext");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/WEB-INF/html/index.html");
        dispatcher.forward(req, resp);
    }
}
