package net.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String id = ""; //초기화하기 위함.
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(int i=0; i<cookies.length; i++) {
				if(cookies[i].getName().equals("id")) {
					id=cookies[i].getValue();
				}
			}
		}
		
		request.setAttribute("id", id); 			//전달되어온 리퀘스트에 셋어트리븉로 담음
		ActionForward forward=new ActionForward();  
		forward.setRedirect(false);					//request가 계속 살아있어야 된다.=주소변경없이 JSP페이지로 이동=디스패쳐방식이다.
		forward.setPath("member/loginForm.jsp"); 	//이동할 곳
		return forward;								//이 정보를 담아서 리턴.
	}
}