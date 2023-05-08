package net.mypage.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.member.db.UserInfo;

public class MypageDAO {
	private DataSource ds;

	public MypageDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB연결 실패 : " + ex);
		}
	}

	public UserInfo member_info(String user_id) {
		UserInfo m = new UserInfo();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select * from user_info user_id = ?");
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				m = new UserInfo();
				m.setUSER_ID(rs.getString("user_id"));
				m.setUSER_PASSWORD(rs.getString("user_password"));
				m.setUSER_DEPT(rs.getString("user_dept"));
				m.setUSER_JOB(rs.getString("user_job"));
				m.setUSER_EMAIL(rs.getString("user_email"));
				m.setUSER_NAME(rs.getString("user_name"));
				m.setUSER_FAX(rs.getString("user_fax"));
				m.setUSER_PHONE(rs.getString("user_phone"));
				m.setUSER_INTRO(rs.getString("user_intro"));
				m.setUSER_IMG(rs.getString("user_img"));
				m.setUSER_CARD(rs.getString("user_card"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("member_info() 에러 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return m;
	} // member_info end

	public int update(UserInfo m) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con = ds.getConnection();
			String sql = "update user_info "
					+ "	  set user_name=?, user_dept=?, user_job=?, user_email=?, user_fax=?, user_phone=?, user_intro=?, user_img=?, user_card=? "
					+ "	  where USER_ID=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, m.getUSER_NAME());
			pstmt.setString(2, m.getUSER_DEPT());
			pstmt.setString(3, m.getUSER_JOB());
			pstmt.setString(4, m.getUSER_EMAIL());
			pstmt.setString(5, m.getUSER_FAX());
			pstmt.setString(6, m.getUSER_PHONE());
			pstmt.setString(7, m.getUSER_INTRO());
			pstmt.setString(8, m.getUSER_IMG());
			pstmt.setString(9, m.getUSER_CARD());
			pstmt.setString(10, m.getUSER_ID());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("update() 오류");

		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return result;
	} // update end

	public int isId(String user_id, String user_password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1; // 패스워드가 다릅니다.
		try {
			con = ds.getConnection();

			String sql = "SELECT user_id, user_password from user_info where user_id = ? and user_password = ?";
			pstmt = con.prepareStatement(sql);

			pstmt.setString(1, user_id);
			pstmt.setString(2, user_password);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (rs.getString(2).equals(user_password)) {
					result = 1; // 아이디와 비밀번호 일치
				} else {
					result = 0; // 비밀번호가 일치하지 않는 경우.
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("isId() 오류");

		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return result;
	}

	public int passwordchange(String user_id, String user_password1) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con = ds.getConnection();
			String sql = "update user_info set user_password = ? WHERE USER_ID = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_password1);
			pstmt.setString(2, user_id);
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("passwordchange() 오류");
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return result;
	} // passwordchange end

}
