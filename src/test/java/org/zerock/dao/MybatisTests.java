package org.zerock.dao;

import lombok.Cleanup;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zerock.w0929.dao.MyBatisUtil;
import org.zerock.w0929.dao.TimeMapper;
import org.zerock.w0929.dto.PageRequestDTO;
import org.zerock.w0929.dto.TodoDTO;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
public class MyBatisTests {

    @Test
    public void test1(){
       log.info("test1..........");

       @Cleanup SqlSession session  = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession();

       log.info(session);

       String timeStr = session.selectOne("org.zerock.w0929.dao.TimeMapper.getTime");

       log.info(timeStr);
       session.close();
    }

    @Test
    public void test2(){
        log.info("test2..........");

        @Cleanup SqlSession session  = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession();

        log.info(session);

        TimeMapper mapper = session.getMapper(TimeMapper.class);

        String str = mapper.getTime();

        log.info(str);
        session.close();
    }

    @Test
    public void testSelectAll() {

        @Cleanup SqlSession session
                = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession(true);

        List<TodoDTO> dtoList = session.selectList("org.zerock.w0929.dao.TodoMapper.selectAll");

        dtoList.forEach(todoDTO -> log.info(todoDTO));
    }



    @Test
    public void testInsert() {

        TodoDTO dto = TodoDTO.builder()
                .title("MyBatis Tests")
                .memo("TEsts...")
                .dueDate(LocalDate.now()).build();

        SqlSession session
                = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession(true);

        session.insert("org.zerock.w0929.dao.TodoMapper.insert", dto);


    }


    @Test
    public void testSelectPage() {

        @Cleanup SqlSession session
                = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession(true);

        PageRequestDTO dto = PageRequestDTO.builder().page(2).size(50).build();

        List<TodoDTO> dtoList = session.selectList("org.zerock.w0929.dao.TodoMapper.selectPage", dto);

        dtoList.forEach(todoDTO -> log.info(todoDTO));
    }

}
