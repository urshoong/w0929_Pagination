package org.zerock.w0929.controller;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

import java.io.*;
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
        response.setContentType("text/html");

        log.trace("t---------------");
        log.debug("d---------------");
        log.info("i---------------");
        log.warn("w---------------");
        log.error("e---------------");
        log.fatal("f---------------");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }
}