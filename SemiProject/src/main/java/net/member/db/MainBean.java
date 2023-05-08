package net.member.db;

import java.util.ArrayList;

import net.project.db.Project_User;

public class MainBean {
	private int row_num;
	private int project_num;
	private String project_name;
	private String project_state;
	private int project_prog;
	private String project_start;
	private String project_end;
	private String project_priority;
	private int project_partici;
	private String project_admin;
	private String project_admin_img;
	private ArrayList<Project_User> project_parti;
	public String getProject_admin_img() {
		return project_admin_img;
		
	}
	
	public int getRow_num() {
		return row_num;
	}
	public void setRow_num(int row_num) {
		this.row_num = row_num;
	}
	public int getProject_num() {
		return project_num;
	}
	public void setProject_num(int project_num) {
		this.project_num = project_num;
	}
	public String getProject_name() {
		return project_name;
	}
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}
	public String getProject_state() {
		return project_state;
	}
	public void setProject_state(String project_state) {
		this.project_state = project_state;
	}
	public int getProject_prog() {
		return project_prog;
	}
	public void setProject_prog(int project_prog) {
		this.project_prog = project_prog;
	}
	public String getProject_start() {
		return project_start;
	}
	public void setProject_start(String project_start) {
		this.project_start = project_start;
	}
	public String getProject_end() {
		return project_end;
	}
	public void setProject_end(String project_end) {
		this.project_end = project_end;
	}
	public String getProject_priority() {
		return project_priority;
	}
	public void setProject_priority(String project_priority) {
		this.project_priority = project_priority;
	}
	public int getProject_partici() {
		return project_partici;
	}
	public void setProject_partici(int project_partici) {
		this.project_partici = project_partici;
	}
	public String getProject_admin() {
		return project_admin;
	}
	public void setProject_admin(String project_admin) {
		this.project_admin = project_admin;
	}
	public ArrayList<Project_User> getProject_parti() {
		return project_parti;
	}
	public void setProject_parti(ArrayList<Project_User> project_parti) {
		this.project_parti = project_parti;
	}
	public void setProject_admin_img(String project_admin_img) {
		this.project_admin_img = project_admin_img;
	}
}
