package org.zerock.w0929.dao;

import lombok.Getter;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

@Getter
public enum MyBatisUtil {
    INSTANCE;

    private SqlSessionFactory sqlSessionFactory;

    MyBatisUtil() {
        try {
            //Resources 추가할 때 패키지 ibatis 확인하기
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
