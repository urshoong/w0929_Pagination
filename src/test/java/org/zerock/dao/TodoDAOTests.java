package org.zerock.dao;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.zerock.w0929.dao.TodoDAO;
import org.zerock.w0929.dto.PageRequestDTO;
import org.zerock.w0929.dto.TodoDTO;

import java.util.List;

@Log4j2
public class TodoDAOTests {

    @Test
    public void testList() {

        List<TodoDTO> list = TodoDAO.INSTANCE.getList(PageRequestDTO.builder().build());

        list.forEach(todoDTO -> log.info(todoDTO ));

    }

}
