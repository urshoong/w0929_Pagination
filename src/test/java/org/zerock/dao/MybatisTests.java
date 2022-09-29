package org.zerock.dao;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;
import org.zerock.w0929.dao.MyBatisUtil;

@Log4j2
public class MybatisTests {

    @Test
    public void test1(){
        log.info("test1............");

        SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession();

        log.info(session);

        String timeStr = session.selectOne("org.zerock.w0929.dao.TimeMapper.getTime");

        log.info(timeStr);

        session.close();
    }

}
