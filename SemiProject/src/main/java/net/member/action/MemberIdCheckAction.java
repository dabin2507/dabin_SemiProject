package net.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.UserDAO;

public class MemberIdCheckAction implements Action {
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		UserDAO dao = new UserDAO(); //db가서 확인해. result가 0인지 -1인지. 하고 찾아와서 result에 넣어 
		int result = dao.isId(request.getParameter("id"));
		response.getWriter().print(result);  //응답에 result담아. 응답으로 가면 돼하고 실어놓음.
		System.out.println(result);
		return null; 
	}
}