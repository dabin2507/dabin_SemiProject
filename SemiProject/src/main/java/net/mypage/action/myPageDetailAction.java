package net.mypage.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.Company;
import net.admin.db.CompanyDAO;
import net.member.db.UserInfo;
import net.mypage.db.Dept;
import net.mypage.db.Job;
import net.mypage.db.MypageDAO;

public class myPageDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		ActionForward forward = new ActionForward();
		String user_id = request.getParameter("id");

		MypageDAO mydao = new MypageDAO();
		CompanyDAO cdao = new CompanyDAO();

				UserInfo m = mydao.member_info(user_id);
				Company c = cdao.company_info(user_id);
				List<Dept> d = mydao.dept(c.getCompany_name());
				List<Job> j = mydao.job(c.getCompany_name());

				request.setAttribute("memberinfo", m);
				request.setAttribute("dept", d);
				request.setAttribute("job", j);
				forward.setRedirect(false);
				forward.setPath("mypage/mypage_Detail.jsp");
				return forward;
	}

}
