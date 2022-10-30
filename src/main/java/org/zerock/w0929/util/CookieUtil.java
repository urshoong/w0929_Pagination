package org.zerock.w0929.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtil {
    //자바에 클래스와 메소드를 선언할 떄 스태틱과 일반 메소드 선택의 기준
    //최대한 함수에 가까운 것들을 static으로 뺀다
    //static영역은 항상 class영역과 같이 묶어서 봐야한다.
    // ->  함수형 언어를 얼마나 이해하고 있는가?
    public static Cookie findCookie(HttpServletRequest request, String name){

        Cookie[] arr = request.getCookies();

        if(arr == null || arr.length == 0){
            return null;
        }

        Cookie result = null;

        for(Cookie ck : arr){
            if(ck.getName().equals(result)){
                result = ck;
                break;
            }
        }

        return result;
    }
}
