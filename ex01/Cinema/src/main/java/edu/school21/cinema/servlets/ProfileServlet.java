package edu.school21.cinema.servlets;
import edu.school21.cinema.models.User;
import edu.school21.cinema.service.UserService;
import org.apache.commons.io.FileUtils;
import org.springframework.context.ApplicationContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;

@WebServlet("/profile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class ProfileServlet extends HttpServlet {
    private ApplicationContext springContext;
    private String uploadPath;

    @Override
    public void init(ServletConfig config) {
        springContext = (ApplicationContext) config.getServletContext().getAttribute("springContext");
        uploadPath = springContext.getBean("uploadPath", String.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        File imagesDir = new File(uploadPath + user.getId());
        if (!imagesDir.exists())
            imagesDir.mkdir();
        req.setAttribute("uploadPath", uploadPath + user.getId());
        File image = new File(uploadPath + user.getId());
        for (File file : image.listFiles())
            if (file.getName().contains("DS_Store"))
                file.delete();
        if ((image.listFiles().length != 0)) {
            File[] files = image.listFiles();
            Arrays.sort(files, (f1, f2) -> Long.valueOf(f1.lastModified()).compareTo(f2.lastModified()));
            byte[] fileContent = FileUtils.readFileToByteArray(files[files.length - 1]);
            String encodedString = Base64.getEncoder().encodeToString(fileContent);
            req.setAttribute("image", encodedString);
        }
        UserService userService = springContext.getBean("userService", UserService.class);
        session.setAttribute("auth", userService.getAuth(((User)session.getAttribute("user")).getLogin()));
        RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/jsp/profile.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        UserService userService = springContext.getBean("userService", UserService.class);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (req.getParameter("firstname").length() > 0)
            user.setFirstName(req.getParameter("firstname"));
        if (req.getParameter("lastname").length() > 0)
            user.setLastName(req.getParameter("lastname"));
        if (req.getParameter("phone").length() > 0)
            user.setPhoneNumber(req.getParameter("phone"));
        userService.updateProfile(user);
        resp.sendRedirect("profile");
    }
}
