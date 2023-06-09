package net.board.notice.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.notice.db.NoticeBean;
import net.board.notice.db.NoticeDAO;

public class NoticeModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		NoticeDAO noticedao = new NoticeDAO();
		NoticeBean noticedata = new NoticeBean();
		ActionForward forward =  new ActionForward();
		String realFolder = "";
		
		String saveFolder = "board/notice/boardupload";
		
		int fileSize = 5 * 1024 * 1024; // 업로드할 파일의 최대 사이즈 입니다.5MB
		
		// 실제 저장 경로를 지정합니다.
		ServletContext sc = request.getServletContext();
		realFolder = sc.getRealPath(saveFolder);
		System.out.println("realFolder=" + realFolder);
		boolean result = false;
		try {
			MultipartRequest multi =
					new MultipartRequest(request, realFolder, fileSize, "utf-8",
										 new DefaultFileRenamePolicy());
			
			int num = Integer.parseInt(multi.getParameter("notice_num"));
			
			// 비밀번호가 일치하는 경우 수정 내용을 설정합니다.
			// BoardBean 객체에 글 등록 폼에서 입력 받은 정보들을 저장합니다.
			noticedata.setNotice_num(num);
			noticedata.setNotice_subject(multi.getParameter("notice_subject"));
			noticedata.setNotice_content(multi.getParameter("notice_content"));
			
			String check = multi.getParameter("check");
			System.out.println("check=" + check);
			if (check != null) { //파일첨부를 변경하지 않으면
				noticedata.setNotice_file(check);
			} else {
				// 업로드된 파일의 시스템 상에 업로드된 실제 파일명을 얻어 옵니다.
				String filename = multi.getFilesystemName("notice_file");
				noticedata.setNotice_file(filename);
			}
			
			// DAO에서 수정 에서드 호출아여 수정합니다.
			result = noticedao.noticeModify(noticedata);
			
			// 수정에 실패한 경우
			if (result == false) {
				System.out.println("게시판 수정 실패");
				forward.setRedirect(false);
				request.setAttribute("message", "게시판 수정이 되지 않았습니다.");
				forward.setPath("error/error.jsp");
				return forward;
			}
			// 수정 성공의 경우
			System.out.println("게시판 수정 완료");
			
			forward. setRedirect(true);
			// 수정한 글 내용을 보여주기 위해 글 내용 보기 페이지로 이동아기위해 경로를 설정합니다.
			forward.setPath("NoticeDetailAction.bon?num=" + noticedata.getNotice_num());
			return forward;
		} catch (IOException e) {
			e.printStackTrace();
			forward.setPath("error/error.jsp");
			request.setAttribute("message", "게시판 업로드 중 실패입니다.");
			forward.setRedirect(false);
			return forward;
		}
	}

}
