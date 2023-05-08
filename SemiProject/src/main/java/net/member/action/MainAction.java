package net.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ActionForward forward=new ActionForward();  
		forward.setRedirect(false);  //주소변경 없이 이동
		
		forward.setPath("main/main.jsp");  //이동할 곳=메인
		
		return forward;
	}

}

