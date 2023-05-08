package net.member.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SendAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String receiver = request.getParameter("receiver");
		
		int random = 0;
    	random = (int)Math.floor((Math.random()*(99999-10000+1)))+10000;
		
		
		Send send = new Send();
		send.send(receiver,random);
		
		response.getWriter().print(random); 
		
		return null;
	}

}
