package net.member.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.UserDAO;
import net.member.db.UserInfo;

public class MemberJoinProcessAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String USER_ID = request.getParameter("id");
		String USER_PASSWORD = request.getParameter("pass");
		String USER_PASSWORD_CH = request.getParameter("password_ch");
		String USER_EMAIL = request.getParameter("email");
		//int USER_CODE = Integer.parseInt(request.getParameter("USER_CODE"));
		String USER_NAME = request.getParameter("name");
		

		UserInfo m = new UserInfo();
		m.setUSER_ID(USER_ID);		m.setUSER_PASSWORD(USER_PASSWORD);
		m.setUSER_EMAIL(USER_EMAIL);			//m.setUSER_CODE(USER_CODE);	

		m.setUSER_NAME(USER_NAME);		m.setUSER_PASSWORD_CH(USER_PASSWORD_CH);

		
		UserDAO mdao = new UserDAO();
		int result = mdao.insert(m);
		
		if(result==0) {
			System.out.println("회원 가입 실패입니다.");
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			request.setAttribute("massage", "회원가입 실패입니다.");
			forward.setPath("error/error.jsp");
			return forward;
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		if(result == 1) { //삽입이 된 경우
			out.println("alert('회원 가입을 축하합니다. 다시 로그인 해주세요.');");
			out.println("location.href='login.net';");
		}else if(result == -1) {
			out.println("alert('아이디가 중복되었습니다. 다시 입력하세요');");
			//새로고침되어 이전에 입력한 데이터가 나타나지 않습니다.
			//out.println("location.href='join.net';);
			out.println("history.back();");  // 비밀번호를 제외한 다른 데이터는 유지 되어 있습니다. 비밀번호는 type이 pass라 지워짐.
		}
		out.println("</script>");
		out.close();
		return null;  //getWriter로 응답 보내서 리턴 null
	}
}