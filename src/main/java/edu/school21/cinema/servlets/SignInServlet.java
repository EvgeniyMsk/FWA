package edu.school21.cinema.servlets;

import edu.school21.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private ApplicationContext springContext;

    @Override
    public void init(ServletConfig config) throws ServletException {
        springContext = (ApplicationContext) config.getServletContext().getAttribute("springContext");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/WEB-INF/jsp/signIn.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = springContext.getBean("userService", UserService.class);
        if (userService.signIn(req.getParameter("login"), req.getParameter("password"), req.getRemoteAddr()))
        {
            HttpSession session = req.getSession();
            session.setAttribute("user", userService.getProfile(req.getParameter("login")));
            session.setAttribute("auth", userService.getAuth(req.getParameter("login")));
            resp.sendRedirect("profile");
        } else resp.sendError(HttpServletResponse.SC_FORBIDDEN);
    }
}
