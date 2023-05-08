package net.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.project.db.Project;


public class UserDAO {
	private static DataSource ds;
	
	//생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.
	public UserDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		}catch(Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}
	}

	public int isId(String USER_ID, String USER_PASSWORD) {
		Connection con = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		int result=-1; //DB에 해당 id가 없습니다.
		try {
			con = ds.getConnection();
			
			String sql = "SELECT USER_ID, USER_PASSWORD FROM USER_INFO WHERE USER_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, USER_ID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(2).equals(USER_PASSWORD)) {
					result = 1; //아이디와 비밀번호 일치하는 경우
				}else {
					result = 0; //비밀번호가 일치하지 않는 경우
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if(pstmt != null)
					pstmt.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}try {
			if(con != null)
				con.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}

	public int isId(String USER_ID) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		try {
			con = ds.getConnection();
			
			String sql = "SELECT USER_ID FROM USER_INFO WHERE USER_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, USER_ID);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = 0;   //DB에 해당 id가 있습니다.
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if(pstmt != null)
					pstmt.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}try {
			if(con != null)
				con.close();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
		}

	public int insert(UserInfo m) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con = ds.getConnection();
			System.out.println("getConnection : insert()");
			
			pstmt = con.prepareStatement(
		               "insert into USER_INFO (USER_ID, USER_PASSWORD, USER_PASSWORD_CH, USER_EMAIL, USER_NAME,USER_IMG) "
		               +"values (?,?,?,?,?,?)");
			pstmt.setString(1, m.getUSER_ID());
			pstmt.setString(2, m.getUSER_PASSWORD());
			pstmt.setString(3, m.getUSER_PASSWORD_CH());
			pstmt.setString(4, m.getUSER_EMAIL());
			//pstmt.setInt(4, m.getUSER_CODE());
			pstmt.setString(5, m.getUSER_NAME());
			pstmt.setString(6, "profile.png");
			result = pstmt.executeUpdate();  //삽입 성공시 result=1
			
		}catch(java.sql.SQLIntegrityConstraintViolationException e) {
			result = -1;
			System.out.println("멤버 아이디 중복 에러입니다.");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
				if(pstmt != null)
					try{
						pstmt.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		if(con != null)
			try {
				con.close();
			}catch(SQLException ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}
	
	public UserInfo userinfo(String USER_ID) {
		UserInfo userinfo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			
			String sql = "SELECT * FROM USER_INFO WHERE USER_ID = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, USER_ID);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				userinfo = new UserInfo();
				userinfo.setUSER_ID(rs.getString("USER_ID"));
				userinfo.setUSER_PASSWORD(rs.getString("USER_PASSWORD"));
				userinfo.setUSER_EMAIL(rs.getString("USER_EMAIL"));
				userinfo.setUSER_CODE(rs.getInt("USER_CODE"));
				userinfo.setUSER_NAME(rs.getString("USER_NAME"));
				userinfo.setUSER_IMG(rs.getString("USER_IMG"));
				userinfo.setUSER_IS_ADMIN(rs.getInt("USER_IS_ADMIN"));
				userinfo.setUSER_DEPT(rs.getString("USER_DEPT"));
				userinfo.setUSER_JOB(rs.getString("USER_JOB"));
				userinfo.setUSER_FAX(rs.getString("USER_FAX"));
				userinfo.setUSER_INTRO(rs.getString("USER_INTRO"));
				userinfo.setUSER_CARD(rs.getString("USER_CARD"));
				userinfo.setUSER_STATE(rs.getString("USER_STATE"));
				//userinfo.setUSER_JOIN_DATE(rs.getDate("USER_JOIN_DATE"));
				userinfo.setUSER_PHONE(rs.getString("USER_PHONE"));
				
			}
				
		}catch(Exception se) {
			se.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if(pstmt != null)
					pstmt.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if(con != null)
					con.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return userinfo;
	}
	
	public List<MainBean> main() {
		List<MainBean> list = new ArrayList<MainBean>();
		MainBean mb = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			
			String sql = "SELECT * FROM PROJECT ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "PROJECT_NAME");
			rs = pstmt.executeQuery();
		
			while (rs.next()) {
				mb = new MainBean();
				mb.setProject_name(rs.getString("PROJECT_NAME"));
				mb.setProject_state(rs.getString("PROJECT_STATE"));
				mb.setProject_prog(rs.getInt("PROJECT_PROG"));
				mb.setProject_start(rs.getString("PROJECT_START"));
				mb.setProject_end(rs.getString("PROJECT_END"));
				mb.setProject_priority(rs.getString("PROJECT_PRIORITY"));
				mb.setProject_partici(rs.getInt("PROJECT_PARTICI"));
				mb.setProject_admin(rs.getString("PROJECT_ADMIN"));
				list.add(mb);
			}

			
		}catch(Exception se) {
			se.printStackTrace();
		}finally {
			try {
				if(rs != null)
					rs.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if(pstmt != null)
					pstmt.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			try {
				if(con != null)
					con.close();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return list;
	}
	
}