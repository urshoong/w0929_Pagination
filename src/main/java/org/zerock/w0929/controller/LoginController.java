package org.zerock.w0929.controller;

import lombok.extern.log4j.Log4j2;
import org.zerock.w0929.dao.MemberDAO;
import org.zerock.w0929.dto.MemberDTO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "LoginController", value = "/login")
@Log4j2
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("login get.........");

        request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request,response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        log.info("login post...");

        String mid = request.getParameter("mid");
        String mpw = request.getParameter("mpw");

        //자동로그인확인
        String rememberMe = request.getParameter("remember-me") == null ? "" : request.getParameter("remember-me");

        log.info(mid);
        log.info(mpw);

        MemberDTO dto = MemberDTO.builder().mid(mid).mpw(mpw).build();

        MemberDTO memberDTO = MemberDAO.INSTANCE.login(dto);

        //로그인 처리
        if(mid.equals(memberDTO.getMid()) && mpw.equals(memberDTO.getMpw())){
            HttpSession session = request.getSession();
            // 로그인 성공 - 사용자의 정보를 담는다.
            session.setAttribute("loginResult", "success");

            if (rememberMe.equals(("on"))) {
                // 쿠키를 저장할때 인코딩을 해서 저장을 해야 한다, 공백은 안됨. - UTF-8
                Cookie loginCookie = new Cookie("loginResult", URLEncoder.encode("Hong Gil Dong","UTF-8"));
                loginCookie.setPath("/");
                loginCookie.setMaxAge(60*60*24*7);

                response.addCookie(loginCookie);
            }

            log.info("wanted :::: " + session.getAttribute("wanted"));

            if(session.getAttribute("wanted") != null){
                response.sendRedirect((String)session.getAttribute("wanted"));
                session.removeAttribute("wanted");

            }else {
                response.sendRedirect("/hello");
            }

            /**
            Cookie loginCookie = new Cookie("login","HELLO");

            //만료 시간 지정(유통 기한 - 초단위)
            //쿠키에 만료시간을 설정하면 클라이언트의 파일시스템으로 저장된다.
            // └ 그렇지 않으면 메모리상에서만 존재하게 되어 브라우저를 종료하고 다시 키면 없어진다.
            //시간을 설정하지 않으면 GTC기준으로 시간이 자동으로 설정된다
            loginCookie.setMaxAge(1000 * 60);
            //경로 지정

            //응답을 해줄 때 쿠키를 같이 전달해야 한다
            response.addCookie(loginCookie);

            //쿠키는 request안에 있다.
            //getCookies() 만 존재하기 때문에 사용자 함수를 만들어서 사용하는것이 좋다
            request.getRequestDispatcher("/WEB-INF/views/member/login.jsp").forward(request, response);
            */

            return;
        }

        log.info("login failed...");
        response.sendRedirect("/login?error=fail");



    }
}
