package org.zerock.w0929.controller;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.zerock.w0929.dao.TodoDAO;
import org.zerock.w0929.dto.TodoDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Log4j2
@WebServlet(name = "TodoDetailController", value = "/todo/detail")
public class TodoDetailController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("TodoDetailController called.......");
        HttpSession session = request.getSession();
        String tno = request.getParameter("tno");
        String page = request.getParameter("page");
        String size = request.getParameter("size");

        if(page != null && size != null){
            session.setAttribute("page", page);
            session.setAttribute("size", size);
        }

        log.info("tno : " + tno);

        try {
            TodoDTO dto = TodoDTO.builder().tno(Integer.parseInt(tno)).build();

            log.info("dto : " + dto);

            TodoDTO todoDTO = TodoDAO.INSTANCE.detail(dto);

            request.setAttribute("todo", todoDTO);

            request.getRequestDispatcher("/WEB-INF/views/todo/detail.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
