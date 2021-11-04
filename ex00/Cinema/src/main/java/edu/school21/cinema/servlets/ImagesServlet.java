package edu.school21.cinema.servlets;

import edu.school21.cinema.models.User;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;

@WebServlet("/images/*")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class ImagesServlet extends HttpServlet {
    private ApplicationContext springContext;
    private String uploadPath;

    @Override
    public void init(ServletConfig config) throws ServletException {
        springContext = (ApplicationContext) config.getServletContext().getAttribute("springContext");
        uploadPath = springContext.getBean("uploadPath", String.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        User user = (User) req.getSession().getAttribute("user");
        String[] URLS = req.getRequestURI().split("/");
        String URI = URLS[URLS.length - 1];
        if (user == null)
            resp.sendError(HttpServletResponse.SC_FORBIDDEN);
        else
            try {
                byte[] fileContent = FileUtils.readFileToByteArray(new File(uploadPath + user.getId() + File.separator + URI));
                String encodedString = Base64.getEncoder().encodeToString(fileContent);
                req.setAttribute("image", encodedString);
                RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/jsp/image.jsp");
                dispatcher.forward(req, resp);
            } catch (Exception e) {
                resp.sendError(HttpServletResponse.SC_FORBIDDEN);
            }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        File imagesDir = new File(uploadPath + user.getId());
        if (!imagesDir.exists())
            imagesDir.mkdir();
        try {
            for (Part part : req.getParts())
                part.write(imagesDir + File.separator + part.getSubmittedFileName());
        }
        catch (Exception ignored)
        {

        }
        resp.sendRedirect("profile");
    }
}
