package org.zerock.w0929.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w0929.util.CookieUtil;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello")
@Log4j2
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("HelloServlet called.....");
        response.setContentType("text/html");

        log.trace("t-----------------------");
        log.debug("d-----------------------");
        log.info("i-----------------------");
        log.warn("w-----------------------");
        log.error("e-----------------------");
        log.fatal("f-----------------------");

        try {
            Cookie loginCookie = CookieUtil.findCookie(request, "login");
            log.info(request.getCookies());
            log.info(loginCookie);

            if(loginCookie != null){
                log.info("-------Cookie-------");
                String value = loginCookie.getValue();
                log.info(value);
                value += "%"+value;

                loginCookie.setValue(value);

                response.addCookie(loginCookie);
            }

            request.getRequestDispatcher("/WEB-INF/views/hello.jsp").forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }


    }

    public void destroy() {
    }
}