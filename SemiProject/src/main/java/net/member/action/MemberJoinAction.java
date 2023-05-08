package net.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberJoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward=new ActionForward();  
		forward.setRedirect(false);					//request가 계속 살아있어야 된다.=주소변경없이 JSP페이지로 이동=디스패쳐방식이다.
		forward.setPath("member/joinForm.jsp"); 	//이동할 곳
		return forward;								//이 정보를 담아서 리턴.\\
	}
}