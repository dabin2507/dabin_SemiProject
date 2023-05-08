package net.project.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ProjectAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			ActionForward forward = new ActionForward();
			forward.setRedirect(false); //주소 변경없이 jsp 페이지의 내용을 보여줍니다.
			forward.setPath("project/Project_add.jsp");
			return forward;		
		}
	}