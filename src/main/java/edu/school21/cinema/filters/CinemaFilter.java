package edu.school21.cinema.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/profile")
public class CinemaFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        String requestURI = ((HttpServletRequest) servletRequest).getRequestURI();
        if (requestURI.startsWith("/signIn") || requestURI.startsWith("/signUp")) {
            if (session.getAttribute("user") != null) {
                ((HttpServletResponse)servletResponse).sendRedirect("/profile");
                return;
            }
        }
        else {
            if (session.getAttribute("user") == null) {
                ((HttpServletResponse) servletResponse).sendError(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
