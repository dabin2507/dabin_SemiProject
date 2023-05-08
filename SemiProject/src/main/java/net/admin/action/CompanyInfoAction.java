package net.admin.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.admin.db.Company;
import net.admin.db.CompanyDAO;
import net.mypage.db.Dept;
import net.mypage.db.Job;
import net.mypage.db.MypageDAO;

public class CompanyInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("id");

		CompanyDAO mydao = new CompanyDAO();
		MypageDAO mdao = new MypageDAO();
		Company m = mydao.company_info(user_id);
		List<Dept> d = mdao.dept(m.getCompany_name());
		List<Job> j = mdao.job(m.getCompany_name());

		request.setAttribute("memberinfo", m);
		request.setAttribute("dept", d);
		request.setAttribute("job", j);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("admin/company/companyinfo.jsp");
		request.setAttribute("companyinfo", m);
		request.setAttribute("dept", d);
		request.setAttribute("job", j);
		return forward;
	}
}
