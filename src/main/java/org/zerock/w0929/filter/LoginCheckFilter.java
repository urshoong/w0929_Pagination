package org.zerock.w0929.filter;

import lombok.extern.log4j.Log4j2;
import org.zerock.w0929.util.CookieUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;

@WebFilter(filterName = "LoginCheckFilter" , urlPatterns = {"/todo/*"} )
@Log4j2
public class LoginCheckFilter implements Filter {

    public void init(FilterConfig config) throws ServletException {
        log.info("init...........");
    }

    public void destroy() {
        log.info("destroy......");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        log.info("doFilter..................");
        log.info("doFilter..................");
        log.info("doFilter..................");
        log.info("doFilter..................");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;

        HttpSession session = req.getSession();

        String requestURL = req.getRequestURI();
        String queryString = req.getQueryString();

        if(requestURL.equals("/todo/list")){
            chain.doFilter(request,response);
            return;
        }

//        if(session == null){
//            res.sendRedirect("/login");
//            return;
//        }

        if(session.getAttribute("loginResult") == null){
            Cookie loginCookie = CookieUtil.findCookie(req, "loginResult");

            if(loginCookie == null){
                res.sendRedirect("/login");
                return;
            }

            String value = URLDecoder.decode(loginCookie.getValue(), "UTF-8");

            String wanted = requestURL + queryString;

            session.setAttribute("loginResult", value);

            session.setAttribute("wanted", wanted);

            res.sendRedirect("/login");
            return;
        }

        chain.doFilter(request, response);
    }
}