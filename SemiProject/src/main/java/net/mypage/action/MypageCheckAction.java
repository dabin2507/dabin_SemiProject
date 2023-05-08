package net.mypage.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.UserInfo;
import net.mypage.db.MypageDAO;

public class MypageCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("id");

		MypageDAO mydao = new MypageDAO();
		UserInfo m = mydao.member_info(user_id);
		
		forward.setRedirect(false);
		forward.setPath("mypage/mypagecheck.jsp");
		request.setAttribute("memberinfo", m);
		return forward;

	}
}