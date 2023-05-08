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

public class NoticeAddAction implements Action {

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

      NoticeDAO noticedao      =   new NoticeDAO( );
      NoticeBean noticedata   =   new NoticeBean();
      ActionForward forward   =   new ActionForward();
      
      String realFolder="";
      
      //webapp아래에 목 폴더 생성하세요
      String saveFolder="board/notice/boardupload";
      
      int fileSize=5*1024*1024; //업로드할 파일이 최대 사이즈 입니다.5MB

      //실제 저장 경로를 지정합니다.
      ServletContext sc = request.getServletContext();
      realFolder = sc.getRealPath(saveFolder);
      System.out.println("realFolder= " + realFolder);
      boolean result=false;
      try{
         MultipartRequest multi= new MultipartRequest(
               request,
               realFolder,
               fileSize,
               "utf-8",
               new DefaultFileRenamePolicy());
         
         //BoardBean 객체에 글 능록 몸에서 입력 받은 정보들을 저장합니다.
         noticedata.setUser_id(multi.getParameter("user_id"));
         noticedata.setNotice_subject(multi.getParameter("notice_subject"));
         noticedata.setNotice_content(multi.getParameter("notice_content"));
         
         //시스템 상에 업로드된 실제 파일명을 얻어 옵니다.
         String filename=multi.getFilesystemName("notice_file");
         noticedata.setNotice_file(filename);
         
         //글 등록 처리를 위해 DAO의 boardInsert()메서드를 호출합니다.
         //글 등록 폼에서 입력한 정보가 저상되어 있는 noticedata객제를 전달합니다.
         result=noticedao.noticeInsert(noticedata);
         
         //글 등록에 실매할 경우 false를 반환합니다.
         if(result==false){
            System.out.println("게시판 등록 실패");
            forward.setPath("error/error.jsp");
            request.setAttribute("message", "게시판 등록 실패입니다.");
            forward.setRedirect(false);
            return forward;
         }
         System.out.println("게시판 등록 완료");
         
         //글 등록이 완료되면 글 목록을 보여주기 위해 "BoardList.bo"로 이동합니다.
         //Redirect여부를 true로 설정합니다.
         forward.setRedirect(true);
         forward.setPath("NoticeList.bon");//이동할 경로를 지정입니다.
         return forward;
      }catch(IOException ex) {
         ex.printStackTrace( );
         forward.setPath("error/error.jsp");
         request.setAttribute("message", "게시판 업로드 실패입니다.");
         forward.setRedirect(false);
         return forward;
      }//catch end
   }//execute end
}