package org.zerock.w0929.dao;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

@Getter
public enum MyBatisUtil {
    INSTANCE;

    private SqlSessionFactory sqlSessionFactory;

    MyBatisUtil() {
        try{
            InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");

            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
