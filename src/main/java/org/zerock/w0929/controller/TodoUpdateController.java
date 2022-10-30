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
import java.sql.Date;

@Log4j2
@WebServlet(name = "TodoUpdateController", value = "/todo/update")
public class TodoUpdateController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tno = request.getParameter("tno");

        try {
            TodoDTO dto = TodoDTO.builder().tno(Integer.parseInt(tno)).build();

            TodoDTO todoDTO = TodoDAO.INSTANCE.detail(dto);

            request.setAttribute("todo", todoDTO);

            request.getRequestDispatcher("/WEB-INF/views/todo/update.jsp").forward(request, response);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tno = request.getParameter("tno");
        String title = request.getParameter("title");
        String memo = request.getParameter("memo");
        String dueDate = request.getParameter("dueDate");
        String complete = request.getParameter("complete");

        log.info("complete :::" + complete);

        String[] dates = dueDate.split("-");

        TodoDTO todoDTO = TodoDTO.builder()
                .tno(Integer.parseInt(tno))
                .title(title)
                .memo(memo)
                .dueDate(new Date((Integer.parseInt(dates[0]) - 1900), (Integer.parseInt(dates[1]) - 1), Integer.parseInt(dates[2])).toLocalDate())
                .complete(complete == null ? false : true)
                .build();

        try {
            Integer result = TodoDAO.INSTANCE.update(todoDTO);

            log.info("update result : " + result);

            response.sendRedirect("/todo/list?work=update&result="+result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
