package net.project.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.project.db.Project;
import net.project.db.ProjectDAO;
import net.project.db.Project_User;

public class ProjectAddProcessAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
       
	  System.out.println("액션들어옴");
	  System.out.println(request.getParameter("project_name"));
	  System.out.println(request.getParameter("project_prog"));
	  System.out.println(request.getParameter("project_state"));

	  
      String project_name = request.getParameter("project_name");
      String project_state = request.getParameter("project_state");
      String project_start = request.getParameter("project_start");
      String project_end = request.getParameter("project_end");
      String project_priority = request.getParameter("project_priority");
      String project_admin = request.getParameter("project_admin");
    
      System.out.println(project_priority);
      System.out.println(project_start);
      
      Project p = new Project();
      p.setProject_name(project_name);          p.setProject_state(project_state);
      p.setProject_start(project_start);        p.setProject_end(project_end);
      p.setProject_priority(project_priority);  p.setProject_admin(project_admin); 
      

      ProjectDAO pdao = new ProjectDAO();
      int result = pdao.insert(p);
       
      ActionForward forward = new ActionForward();
      System.out.println(result);
      //result=0;
      if(result==0) {
         System.out.println("프로젝트 등록 실패입니다.");
         forward = new ActionForward();
         forward.setRedirect(false);
         request.setAttribute("message", "프로젝트 등록 실패입니다.");
         forward.setPath("error/error.jsp");
         return forward;
      }
      
      response.setContentType("text/html;charset=utf-8");
      PrintWriter out = response.getWriter();
      out.println("<script>");
      if (result == 2) {//삽입이 된 경우
         out.println("swal('프로젝트 생성 완료','지금 바로 관리해보세요','success' )");
        // out.println("location.href='ProjectAddProcess.po';");         
      } else if (result == -1) {
         out.println("alert('프로젝트명이 중복되었습니다. 다시 입력하세요');");
         //새로고침되어 이전에 입력한 데이터가 나타나지 않습니다.
         //out.println("location.href='join.net';");
         out.println("history.back();");//비밀번호를 제외한 다른 데이터는 유지 되어 있습니다.
      }
      out.println("</script>");
      out.close();
      
      forward.setRedirect(true);
      forward.setPath("ProjectList.po");
      return null;
   }
}