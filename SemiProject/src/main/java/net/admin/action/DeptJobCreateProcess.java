package net.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
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

public class DeptJobCreateProcess implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String user_id = (String) session.getAttribute("id");

		String company_name = request.getParameter("company_name");
		String value = request.getParameter("value");
		String change = request.getParameter(value);

		System.out.println(change);

		CompanyDAO dao = new CompanyDAO();
		int result = -1;

		if (value.equals("dept_name")) {
			result = dao.dept_create(value, change, company_name);

		} else if (value.equals("job_name")) {
			result = dao.job_create(value, change, company_name);
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (result == 0) {
			out.println("<script>");
			out.println("alert('중복 되었습니다.');");
			out.println("history.back()");
			out.println("</script>");
			return null;
		}

		CompanyDAO mydao = new CompanyDAO();
		MypageDAO mdao = new MypageDAO();

		Company m = mydao.company_info(user_id);
		List<Dept> d = mdao.dept(m.getCompany_name());
		List<Job> j = mdao.job(m.getCompany_name());

		ActionForward forward = new ActionForward();
		forward.setRedirect(true);
		request.setAttribute("companyinfo", m);
		request.setAttribute("dept", d);
		request.setAttribute("job", j);
		forward.setPath("companyinfo.com");
		return forward;
	}
}// execute end