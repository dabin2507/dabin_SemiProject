package net.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MemberLogoutAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();   //로그아웃은 세션을 invalidate해야함.
		session.invalidate();
		forward.setRedirect(true);		//주소가 바뀌므로 리다이렉트. 주소가 바뀌면 true
		forward.setPath("login.net");	//로그아웃하면 로그인 페이지로 이동
		return forward;
	}
}
