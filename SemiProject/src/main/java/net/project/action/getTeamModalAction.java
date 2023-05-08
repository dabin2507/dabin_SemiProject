package net.project.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.project.db.ProjectDAO;
import net.project.db.Project_User;

public class getTeamModalAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProjectDAO pdao = new ProjectDAO();
		List<Project_User> list = new ArrayList<Project_User>();
		
		String a = request.getParameter("project_num");
		System.out.println(a);
			
		// 위에서 request로 담았던 것을 JsonObject에 담습니다.
//		JsonObject object = new JsonObject();
//		object.addProperty("page", page);
//		object.addProperty("maxpage", maxpage);
//		object.addProperty("startpage", startpage);
//		object.addProperty("endpage", endpage);
//		object.addProperty("listcount", listcount);
//		object.addProperty("limit", limit);
//
//		// JsonObject에 List 형식을 담을 수 있는 addProperty() 존재하지 않습니다.
//		// void com.google.gson.JsonObject.add(String property, JsonElement value) 메서드를
//		// 통해서 저장합니다.
//		// List형식을 JsonLelment로 바꾸어 주어야 object에 저장할 수 있습니다.
//
//		// List => JsonElement
//		System.out.println(object.toString());
//		JsonElement je = new Gson().toJsonTree(projectList);
//		System.out.println("projectList = " + je.toString());
//		object.add("projectList", je);
//
//		response.setContentType("application/json;charset=utf-8");
//		response.getWriter().print(object);
//		System.out.println(object.toString());
//		return null;
		
		return null;
		
	}

}
