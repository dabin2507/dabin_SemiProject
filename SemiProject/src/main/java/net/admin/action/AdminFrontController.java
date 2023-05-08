package net.admin.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.com")
public class AdminFrontController extends javax.servlet.http.HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * 요청된 전체 URL중에서 포트 번호 다음 부터 마지막 문자열까지 반환됩니다. 예) contextPath가 "/JspProject"인 경우
		 * http://localhost:8088/JspProject/login.net로 요청하면 getRequestURI()는
		 * "/JspProject/login.net" 반환됩니다.
		 */
		String RequestURI = request.getRequestURI();
		System.out.println("RequestURI = " + RequestURI);

		// getContextPath() : 컨텍스트 경로가 반환됩니다.
		// contextPath는 "/JspProject"가 반환됩니다.
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);

		// RequestURI에서 컨텍스트 경로 길이 값의 인덱스 위치의 문자부터 마지막 위치 문자까지 추출합니다.
		// command는 "/login.net" 반환됩니다.
		String command = RequestURI.substring(contextPath.length());
		System.out.println("command = " + command);

		// 초기화
		ActionForward forward = null;
		Action action = null;

		switch (command) {
		case "/companyinfo.com":
			action = new CompanyInfoAction();
			break;
		case "/companyupdateProcess.com":
			action = new CompanyupdateProcess();
			break;
		case "/membersInfo.com":
			action = new MembersInfoAction();
			break;
		case "/deptJobUpdateProcess.com":
			action = new DeptJobUpdateProcess();
			break;
		case "/DeptJobCreateProcess.com":
			action = new DeptJobCreateProcess();
			break;
		case "/memberstatus.com":
			action = new memberstatusProcess();
			break;
		case "/adminright.com":
			action = new AdminRightProcess();
			break;
		case "/companyimgupdateprocess.com":
			action = new CompanyImgupdateProcess();
			break;

		} // switch end
		forward = action.execute(request, response);

		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);
	}
}
