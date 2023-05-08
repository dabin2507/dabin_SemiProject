package net.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.UserInfo;
import net.mypage.db.MypageDAO;

public class memberstatusProcess implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		MypageDAO mydao = new MypageDAO();
		String user_id = request.getParameter("user_id");
		String state = request.getParameter("state");
		mydao.member_status(user_id, state);

		List<UserInfo> list = null;
		List<UserInfo> normal = null;
		List<UserInfo> wait = null;
		List<UserInfo> stop = null;
		list = mydao.all_member_info();
		normal = mydao.normal_info();
		wait = mydao.wait_info();
		stop = mydao.stop_info();
		

		request.setAttribute("list", list);
		request.setAttribute("normal", normal);
		request.setAttribute("wait", wait);
		request.setAttribute("stop", stop);
		forward.setRedirect(false);
		forward.setPath("admin/members/membersinfo.jsp");
		return forward;

	}
}
