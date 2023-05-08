package net.project.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProjectFrontController
 */
@WebServlet("*.po")
public class ProjectFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 	요청된 전체 URL중에서 포트번호 다음부터 마지막 문자열까지 반환
		 	contextPath 가 /JspProject인 경우
		 	http://localhost:8088/JspProject/login.net 으로 요청하면
		 	getRequsetURI 는 /JspProject/login.net를 반환
		 */
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
		case "/ProjectList.po" :
			action = new ProjectListAction();
			break;
		case "/ProjectAddProcess.po" :
			action = new ProjectAddProcessAction();
			break;
		case "/getTeamModal.po" :
			action = new getTeamModalAction();
			break;
		}
		
		forward = action.execute(request, response);
		System.out.println("forward : " + forward);
		
		if(forward != null ) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
				return;
			}
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doProcess(request, response);
	}

}
