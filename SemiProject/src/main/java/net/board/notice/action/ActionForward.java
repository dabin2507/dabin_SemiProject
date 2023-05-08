package net.board.notice.action;

public class ActionForward {
	private boolean redirect=false;
	private String path = null;
	

	public ActionForward() {

	}

	public boolean isRedirect() {
		//프로퍼티 타입이 boolean일 경우  get대신 is를 앞에 붙일 수 있습니다
		return redirect;
	}

	public void setRedirect(boolean b) {
		this.redirect = b;
	}

	public void setPath(String string) {
		this.path = string;
	}

	public String getPath() {
		return path;
	}

}
