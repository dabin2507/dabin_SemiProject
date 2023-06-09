package net.board.notice.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.board.notice.db.NoticeBean;
import net.board.notice.db.NoticeDAO;

public class NoticeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		NoticeDAO noticedao = new NoticeDAO();
		HttpSession session = request.getSession(); 
		String  id = (String)session.getAttribute("id");
		System.out.println(id);
		
		List<NoticeBean> noticelist = new ArrayList<NoticeBean>();
		
		
		int page = 1; //보여줄 page
		int limit = 10; //한 페이지에 보여줄 게시판 목록의 수
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			System.out.println("넘어온 페이지 =" + page);
			
			//추가
			if (request.getParameter("limit") != null) {
				limit = Integer.parseInt(request.getParameter("limit"));
			}
			System.out.println("넘어온 limit =" + limit);
		int listcount = 0;
		int index=-1;
		String search_word="";
		
		if ( request.getParameter("search_word") == null
				|| request.getParameter("search_word").equals("")) {
			
			// 총 리스트 수를 받아옵니다.
			listcount = noticedao.getListCount();
		
			// 리스트를 받아옵니다.
			noticelist = noticedao.getNoticeList(page, limit);
		} else { //검색을 클릭한 경우
			System.out.println("sf : " + request.getParameter("search_field"));
			index= Integer.parseInt(request.getParameter("search_field"));
			String[] search_field = new String[] {"notice_subject", "user_id"};
			search_word = request.getParameter("search_word");
			noticelist = noticedao.getNoticeList(search_field[index], search_word, page, limit);
			listcount = noticedao.getListCount(search_field[index], search_word);
		}
		System.out.println("넘어온 분류 =" + index);
		System.out.println("넘어온 검색어 =" + search_word);
		
		int maxpage = (listcount + limit - 1) / limit;
		System.out.println("총 페이지수 =" + maxpage);
		
		int startpage = ((page - 1) / 10) * 10 + 1;
		System.out.println("현재 페이지에 보여줄 시작 페이지 수 :" + startpage);		
		
		int endpage = startpage + 10 - 1;
		System.out.println("현재 페이지에 보여줄 마지막 페이지 수 :" + endpage);
		
		if (endpage > maxpage)
			endpage = maxpage;
		
		String state = request.getParameter("state");
		
		if (state == null) {
			System.out.println("state==null");
			request.setAttribute("page", page); // 현재 페이지 수
			request.setAttribute("maxpage", maxpage); // 최대 페이지 수
			
			// 현재 페이지에 표시할 첫 페이지 수
			request.setAttribute("startpage", startpage);
			
			// 현재 페이지에 표시할 끝 페이지 수
			request.setAttribute("endpage", endpage);
			
			request.setAttribute("listcount", listcount); // 총 글의 수
			
			// 해당 페이지의 글 목록을 갖고 있는 리스트
			request.setAttribute("noticelist", noticelist);
			request.setAttribute("limit", limit);
			request.setAttribute("search_field", index);
			request.setAttribute("search_word", search_word);
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			
			// 글 목록 페이지로 이동하기 위해 경로를 설정합니다.
			forward.setPath("board/notice/list.jsp");
			return forward;
			
		}else {
			System.out.println("state=ajax");
			
			//위에서 request로 담았던 것을 JsonObject 에 담습니다.
			JsonObject object = new JsonObject();
			object.addProperty("page", page); //{"page": 년수 page의 값} 형식으로 저장
			object.addProperty("maxpage", maxpage);
			object.addProperty("startpage", startpage);
			object.addProperty("endpage", endpage);
			object.addProperty("listcount", listcount);
			object.addProperty("limit", limit);
			object.addProperty("search_field", index);
			object.addProperty("search_word", search_word);
			
			//JsonObject에 List 형식을 담을 수 있느 addProperty() 존재하지 않습니다.
			//void com.google.gson.JsonObject.add(String property, JsonElement value)
			//메서드를 통해서 저장합니다.
			//List형식을 JsonElement로 바꾸어 주어야 object에 저장할 수 있습니다.
			//List => JsonElement
			JsonElement je = new Gson().toJsonTree(noticelist);
			System.out.println("noticelist="+je.toString());
			object.add("noticelist", je);
			
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(object);
			System.out.println(object.toString());
			return null;
		}//else end		
	}//execute end
}//class end
