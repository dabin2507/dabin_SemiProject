package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.net")
public class MemberFrontController extends javax.servlet.http.HttpServlet{
	private static final long serialVersionUID = -9024603628813910853L;
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String requestURI = request.getRequestURI();
		System.out.println("RequestURI = " +requestURI);
		
		//getContextPath() : 컨텍스트 경로가반환
		//contextPath : "JspProject 가 반환
		String contextPath = request.getContextPath();
		System.out.println("contextPath = " + contextPath);
		
		
		//requestURI 에서 컨텍스트 경로 길이 값의 인덱스 위치의 문자부터 마지막 위치 문자까지 추출
		//command : /login.net 반환
		String command =  requestURI.substring(contextPath.length());
		System.out.println("command = " + command);
		
		ActionForward forward = null;
		Action action = null;
		
		switch(command) {
		case "/login.net":
			action = new MemberLoginAction();
			break;
		case "/join.net":
			action = new MemberJoinAction();
			break;
		case "/idcheck.net":
			action = new MemberIdCheckAction();
			break;
		case "/joinProcess.net":
			action = new MemberJoinProcessAction();
			break;
		case "/main.net":
			action = new MemberLoginProcessAction();
			break;
		case "/logout.net":
			action = new MemberLogoutAction();
			break;
	    case "/send.net":
	        action = new SendAction();
	        break;
	    case "/memberInfo.net":
	        action = new MemberInfoAction();
	        break;
	    case "/main2.net":
	    	action = new MainAction();
	    	break;
	
		} //switch and
		forward = action.execute(request, response); //반환형 포워드다.
		
		if(forward != null) {
			if(forward.isRedirect()) { //리다이렉트 됩니다.
				response.sendRedirect(forward.getPath());
			}else { //포워딩됩니다.
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request,	 response);
			}
		}
		
	}//doProcess() end
	
	//doProcess(request, response)메서드를 구현하여 요청이 get방식이든
	//post방식으로 전송되어 오던 같은 메서드에서 요청을 처리할 수 있도록 하였습니다.
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException{
		doProcess(request, response);  //doProcess에 넘김. 
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
			request.setCharacterEncoding("utf-8");
			doProcess(request, response);

	}
			
}
