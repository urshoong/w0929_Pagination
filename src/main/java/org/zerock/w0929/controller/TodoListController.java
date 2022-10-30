package org.zerock.w0929.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w0929.dao.TodoDAO;
import org.zerock.w0929.dto.PageRequestDTO;
import org.zerock.w0929.dto.PageResponseDTO;
import org.zerock.w0929.dto.TodoDTO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Predicate;

@WebServlet(name = "TodoListController", value = "/todo/list")
@Log4j2
public class TodoListController extends HttpServlet {

    protected int getInt(String value, int defaultValue, Predicate<Integer> predicate){
        try {
            int intValue = Integer.parseInt(value);

            if(predicate != null) {
                boolean result = predicate.test(intValue);

                if (!result) {
                    return defaultValue;
                }
            }

            return intValue;
        }catch(Exception e){
            return defaultValue;
        }
    }

    protected LocalDate getLocalDate(String value, boolean isEnd){

        try {
            DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(value, DATEFORMATTER);
            return localDate;
        }catch (Exception e){
            if(isEnd){
                return LocalDate.of(2100, 12, 31);
            }else{
                return LocalDate.of(1970, 01, 01);
            }
        }
    }

    protected void dispatch(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/views/" +page+".jsp").forward(request,response);

    }
    protected void redirect(HttpServletResponse response, String queryString) throws ServletException, IOException {

        response.sendRedirect(queryString);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("TodoListController doGet called...");
        HttpSession session = request.getSession();
        String work = request.getParameter("work");
        String result = request.getParameter("result");

        int page = getInt(request.getParameter("page"), 1, (value) -> value < 0 ? false : true);
        int size = getInt(request.getParameter("size"), 10, (value) -> value < 10 ? false : value > 50 ? false : true);

        LocalDate from = getLocalDate(request.getParameter("from"), false);
        LocalDate to = getLocalDate(request.getParameter("to"), true);

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder()
                .from(from)
                .to(to)
                .page(page)
                .size(size)
                .build();

        log.info(pageRequestDTO);

        List<TodoDTO> dtoList = TodoDAO.INSTANCE.getList(pageRequestDTO);

        request.setAttribute("dtoList", dtoList);
        request.setAttribute("work", work);
        request.setAttribute("result", result);
        request.setAttribute("loginResult", session.getAttribute("loginResult"));

        PageResponseDTO responseDTO
                 = new PageResponseDTO(pageRequestDTO, TodoDAO.INSTANCE.getTotal(pageRequestDTO));

        request.setAttribute("pageDTO", responseDTO);

        dispatch(request,response, "todo/list");

    }

}
