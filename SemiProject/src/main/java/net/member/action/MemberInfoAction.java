package net.member.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.UserDAO;
import net.member.db.UserInfo;

public class MemberInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		UserDAO udao = new UserDAO();
		UserInfo userinfo = new UserInfo();
		
		HttpSession session = request.getSession();
		String USER_ID = (String)session.getAttribute("USER_ID");
		userinfo = udao.userinfo(USER_ID);
		
		if(userinfo==null) {
			forward.setPath("error/error.jsp");
			forward.setRedirect(false);
			request.setAttribute("message", "아이디에 해당하는 정보가 없습니다.");
			return forward;
		}

		request.setAttribute("userinfo", userinfo);
		forward.setRedirect(false);
		
		forward.setPath("main/main.jsp");
		return forward;
		//이 서블릿은 사용안함
	}
}
