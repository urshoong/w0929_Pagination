package org.zerock.w0929.controller;

import org.zerock.w0929.dao.TodoDAO;
import org.zerock.w0929.dto.TodoDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "TodoAddController", value = "/todo/add")
public class TodoAddController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/todo/add.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String memo = request.getParameter("memo");
        String dueDate = request.getParameter("dueDate");
        //String[] dates = dueDate.split("-");
        LocalDate date = LocalDate.parse(dueDate, DateTimeFormatter.ISO_DATE);
        TodoDTO todoDTO = TodoDTO.builder()
                .title(title)
                .memo(memo)
                //.dueDate(new Date((Integer.parseInt(dates[0]) - 1900), (Integer.parseInt(dates[1]) - 1), Integer.parseInt(dates[2])).toLocalDate())
                .dueDate(date)
                .build();

        Integer result = TodoDAO.INSTANCE.insert(todoDTO);

        response.sendRedirect("/todo/list?work=insert&result="+result);
    }
}
