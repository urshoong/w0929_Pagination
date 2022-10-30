package org.zerock.w0929.dao;

import lombok.Cleanup;
import org.apache.ibatis.session.SqlSession;
import org.zerock.w0929.dto.PageRequestDTO;
import org.zerock.w0929.dto.PageResponseDTO;
import org.zerock.w0929.dto.TodoDTO;

import java.util.List;

public enum TodoDAO {
    INSTANCE;

    public List<TodoDTO> getList(PageRequestDTO pageRequestDTO){
        @Cleanup SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession(true);

        List<TodoDTO> dtoList = session.selectList("org.zerock.w0929.dao.TodoMapper.getList",
                pageRequestDTO);

        return dtoList;
    }

    public int getTotal(PageRequestDTO pageRequestDTO){
        @Cleanup SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession(true);

        int total = session.selectOne("org.zerock.w0929.dao.TodoMapper.getTotal",
                pageRequestDTO);

        return total;
    }

    public TodoDTO detail(TodoDTO dto){
        @Cleanup SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession(true);

        TodoDTO todoDTO = session.selectOne("org.zerock.w0929.dao.TodoMapper.getDetail", dto);

        return todoDTO;
    }

    public Integer insert(TodoDTO dto){
        @Cleanup SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession(true);

        Integer result = session.insert("org.zerock.w0929.dao.TodoMapper.insert", dto);

        return result;
    }

    public Integer delete(TodoDTO dto){
        @Cleanup SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession(true);

        Integer result = session.delete("org.zerock.w0929.dao.TodoMapper.delete", dto);

        return result;
    }

    public Integer update(TodoDTO dto){
        @Cleanup SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession(true);

        Integer result = session.update("org.zerock.w0929.dao.TodoMapper.update", dto);

        return result;
    }
}














