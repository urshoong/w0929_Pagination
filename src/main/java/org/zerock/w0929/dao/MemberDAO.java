package org.zerock.w0929.dao;

import org.apache.ibatis.session.SqlSession;
import org.zerock.w0929.dto.MemberDTO;

public enum MemberDAO {
    INSTANCE;

    public MemberDTO login(MemberDTO dto){
        SqlSession session = MyBatisUtil.INSTANCE.getSqlSessionFactory().openSession(true);

        MemberDTO memberDTO = session.selectOne("org.zerock.w0929.dao.LoginMapper.getMember", dto);

        return memberDTO;
    }

}
