package net.member.db;

public class UserInfo {
	private String USER_ID;
	private String USER_PASSWORD;
	private String USER_EMAIL;
	private int USER_CODE;
	private String USER_NAME;
	private String USER_IMG;
	private int USER_IS_ADMIN;
	private String USER_DEPT;
	private String USER_JOB;
	private String USER_FAX;
	private String USER_INTRO;
	private String USER_CARD;
	private String USER_STATE;
	private String USER_JOIN_DATE;
	private String USER_PHONE;
	private String USER_PASSWORD_CH;
	private String USER_LASTTIME;

	public String getUSER_LASTTIME() {
		return USER_LASTTIME;
	}

	   public void setUSER_LASTTIME(String uSER_LASTTIME) {
		      if (uSER_LASTTIME != null) {
		         USER_LASTTIME = uSER_LASTTIME.substring(0, 10) + "<br>" + uSER_LASTTIME.substring(11, 19);
		      } else {
		         USER_LASTTIME = null;
		      }
		   }
	public String getUSER_PASSWORD_CH() {
		return USER_PASSWORD_CH;
	}

	public void setUSER_PASSWORD_CH(String uSER_PASSWORD_CH) {
		USER_PASSWORD_CH = uSER_PASSWORD_CH;
	}

	public String getUSER_ID() {
		return USER_ID;
	}

	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}

	public String getUSER_PASSWORD() {
		return USER_PASSWORD;
	}

	public void setUSER_PASSWORD(String uSER_PASSWORD) {
		USER_PASSWORD = uSER_PASSWORD;
	}

	public String getUSER_EMAIL() {
		return USER_EMAIL;
	}

	public void setUSER_EMAIL(String uSER_EMAIL) {
		USER_EMAIL = uSER_EMAIL;
	}

	public int getUSER_CODE() {
		return USER_CODE;
	}

	public void setUSER_CODE(int uSER_CODE) {
		USER_CODE = uSER_CODE;
	}

	public String getUSER_NAME() {
		return USER_NAME;
	}

	public void setUSER_NAME(String uSER_NAME) {
		USER_NAME = uSER_NAME;
	}

	public String getUSER_IMG() {
		return USER_IMG;
	}

	public void setUSER_IMG(String uSER_IMG) {
		USER_IMG = uSER_IMG;
	}

	public int getUSER_IS_ADMIN() {
		return USER_IS_ADMIN;
	}

	public void setUSER_IS_ADMIN(int uSER_IS_ADMIN) {
		USER_IS_ADMIN = uSER_IS_ADMIN;
	}

	public String getUSER_DEPT() {
		return USER_DEPT;
	}

	public void setUSER_DEPT(String uSER_DEPT) {
		USER_DEPT = uSER_DEPT;
	}

	public String getUSER_JOB() {
		return USER_JOB;
	}

	public void setUSER_JOB(String uSER_JOB) {
		USER_JOB = uSER_JOB;
	}

	public String getUSER_FAX() {
		return USER_FAX;
	}

	public void setUSER_FAX(String uSER_FAX) {
		USER_FAX = uSER_FAX;
	}

	public String getUSER_INTRO() {
		return USER_INTRO;
	}

	public void setUSER_INTRO(String uSER_INTRO) {
		USER_INTRO = uSER_INTRO;
	}

	public String getUSER_CARD() {
		return USER_CARD;
	}

	public void setUSER_CARD(String uSER_CARD) {
		USER_CARD = uSER_CARD;
	}

	public String getUSER_STATE() {
		return USER_STATE;
	}

	public void setUSER_STATE(String uSER_STATE) {
		USER_STATE = uSER_STATE;
	}

	public String getUSER_JOIN_DATE() {
		return USER_JOIN_DATE;
	}

	public void setUSER_JOIN_DATE(String uSER_JOIN_DATE) {
		USER_JOIN_DATE = uSER_JOIN_DATE.substring(0, 10);
	}

	public String getUSER_PHONE() {
		return USER_PHONE;
	}

	public void setUSER_PHONE(String uSER_PHONE) {
		USER_PHONE = uSER_PHONE;
	}

}