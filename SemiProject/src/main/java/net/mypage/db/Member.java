package net.mypage.db;
import java.util.Date;


public class Member {
	private String user_id;
	private String user_password;
	private String user_email;
	private String user_name;
	private String user_img;
	private int user_is_admin;
	private String user_dept;
	private String user_job;
	private String user_fax;
	private String user_intro;
	private String user_card;
	private int user_state;
	private java.util.Date user_join_date;
	private String user_phone;

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getUser_email() {
		return user_email;
	}

	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_img() {
		return user_img;
	}

	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}

	public int getUser_is_admin() {
		return user_is_admin;
	}

	public void setUser_is_admin(int user_is_admin) {
		this.user_is_admin = user_is_admin;
	}

	public String getUser_dept() {
		return user_dept;
	}

	public void setUser_dept(String user_dept) {
		this.user_dept = user_dept;
	}

	public String getUser_job() {
		return user_job;
	}

	public void setUser_job(String user_job) {
		this.user_job = user_job;
	}

	public String getUser_fax() {
		return user_fax;
	}

	public void setUser_fax(String user_fax) {
		this.user_fax = user_fax;
	}

	public String getUser_intro() {
		return user_intro;
	}

	public void setUser_intro(String user_intro) {
		this.user_intro = user_intro;
	}

	public String getUser_card() {
		return user_card;
	}

	public void setUser_card(String user_card) {
		this.user_card = user_card;
	}

	public int getUser_state() {
		return user_state;
	}

	public void setUser_state(int user_state) {
		this.user_state = user_state;
	}

	public java.util.Date getUser_join_date() {
		return user_join_date;
	}

	public void setUser_join_date(Date date) {
		this.user_join_date = date;
	}

	public String getUser_phone() {
		return user_phone;
	}

	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}

}
