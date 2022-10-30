package org.zerock.w0929.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w0929.dao.TodoDAO;
import org.zerock.w0929.dto.TodoDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(name = "TodoDeleteController", value = "/todo/delete")
public class TodoDeleteController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tno = request.getParameter("tno");

        log.info("delete tno : " + tno);

        TodoDTO todoDTO = TodoDTO.builder().tno(Integer.parseInt(tno)).build();

        try {
            Integer result = TodoDAO.INSTANCE.delete(todoDTO);
            log.info("delete result = " + result);

            request.setAttribute("result", result);

            response.sendRedirect("/todo/list?work=delete&result="+result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
