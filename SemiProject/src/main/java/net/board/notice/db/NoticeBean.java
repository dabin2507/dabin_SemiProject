package net.board.notice.db;

public class NoticeBean {
	private int		notice_num;			//글 번호
	private	String	user_id;			//작성자
	private	String	notice_subject;		//글 제목
	private	String	notice_content;		//글 내용
	private	String	notice_file;		//첨부될 파일명
	private int		notice_re_ref;		//답변 글 작성시 참조되는 글의 번호
	private int		notice_re_lev;		//답변 글의 깊이
	private int		notice_re_seq;		//답변 글의 순서
	private int		notice_readcount;	//글의 조회수
	private	String	notice_reg_date;	//작성일
	private int cnt;
	private	String	search_field;
	private	String	search_word;
	
	
	
	public String getSearch_field() {
		return search_field;
	}
	public void setSearch_field(String search_field) {
		this.search_field = search_field;
	}
	public String getSearch_word() {
		return search_word;
	}  
	public void setSearch_word(String search_word) {
		this.search_word = search_word;
	}	
	public int getNotice_num() {
		return notice_num;
	}
	public void setNotice_num(int notice_num) {
		this.notice_num = notice_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getNotice_subject() {
		return notice_subject;
	}
	public void setNotice_subject(String notice_subject) {
		this.notice_subject = notice_subject;
	}
	public String getNotice_content() {
		return notice_content;
	}
	public void setNotice_content(String notice_content) {
		this.notice_content = notice_content;
	}
	public String getNotice_file() {
		return notice_file;
	}
	public void setNotice_file(String notice_file) {
		this.notice_file = notice_file;
	}
	public int getNotice_re_ref() {
		return notice_re_ref;
	}
	public void setNotice_re_ref(int notice_re_ref) {
		this.notice_re_ref = notice_re_ref;
	}
	public int getNotice_re_lev() {
		return notice_re_lev;
	}
	public void setNotice_re_lev(int notice_re_lev) {
		this.notice_re_lev = notice_re_lev;
	}
	public int getNotice_re_seq() {
		return notice_re_seq;
	}
	public void setNotice_re_seq(int notice_re_seq) {
		this.notice_re_seq = notice_re_seq;
	}
	public int getNotice_readcount() {
		return notice_readcount;
	}
	public void setNotice_readcount(int notice_readcount) {
		this.notice_readcount = notice_readcount;
	}
	public String getNotice_reg_date() {
		return notice_reg_date;
	}
	public void setNotice_reg_date(String notice_reg_date) {
		this.notice_reg_date = notice_reg_date.substring(0,10);
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
}
