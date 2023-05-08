package net.mypage.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.mypage.db.MypageDAO;

public class PasswordChangeProcess implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("id");
		String user_password = request.getParameter("user_password");
		MypageDAO mydao = new MypageDAO();
		int result = mydao.isId(user_id, user_password); // id, password 1차 확인

		if (result != 1) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('현재 비밀번호가 틀렸습니다.');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			return null;
		} else {
			String user_password1 = request.getParameter("user_password1");
			String user_password2 = request.getParameter("user_password2");

			if (!user_password1.equals(user_password2)) {
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('새로운 비밀번호가 서로 다릅니다.');");
				out.println("history.back();");
				out.println("</script>");
				out.close();
				return null;
			} else {
				if (user_password1.equals(user_password)) {
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('변경할 비밀번호가 현재 비밀번호와 동일합니다.');");
					out.println("history.back()");
					out.println("</script>");
					out.close();
					return null;

				}

				result = mydao.passwordchange(user_id, user_password1);
				if (result == 1) {
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('수정되었습니다.');");
					out.println("location.href='passwordchange.my'");
					out.println("</script>");
					out.close();
					return null;
				}
			}
		}
		return null;
	}

}
