package net.admin.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CompanyDAO {
	private DataSource ds;

	public CompanyDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");
		} catch (Exception ex) {
			System.out.println("DB연결 실패 : " + ex);
		}
	}

	public Company company_info(String user_id) {
		Company c = new Company();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select * from company_info where user_id = ?");
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				c = new Company();
				c.setCompany_name(rs.getString(1));
				c.setCompany_url(rs.getString(2));
				c.setCompany_logo(rs.getString(3));
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
		return c;
	} // company_info end

	public int create(String id, String value, String change) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con = ds.getConnection();

			// 원문글의 BOARD_RE_REF 필드는 자신의 글번호 입니다.
			String sql = "insert into COMPANY_INFO(" + value + ", user_id) values(?, ?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, change);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("데이터 삽입이 모두 완료되었습니다.");
			}
		} catch (Exception ex) {
			System.out.println("company_create() 에러 : " + ex);
			ex.printStackTrace();
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
	}

	public int dept_create(String value, String change, String company_name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = -1;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);

			String select_sql = "select * from company_dept where dept_name = ?";
			pstmt = con.prepareStatement(select_sql);
			pstmt.setString(1, change);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return 0;
			}
			pstmt.close();

			// 원문글의 BOARD_RE_REF 필드는 자신의 글번호 입니다.
			String sql = "insert into COMPANY_DEPT(" + value + ", COMPANY_NAME) values(?, ?)";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, change);
			pstmt.setString(2, company_name);
			result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("데이터 삽입이 모두 완료되었습니다.");
			}
		} catch (Exception ex) {
			System.out.println("dept_create() 에러 : " + ex);
			ex.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.setAutoCommit(true);
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return result;
	}

	public int job_create(String value, String change, String company_name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		try {
			con = ds.getConnection();

			con.setAutoCommit(false);

			String select_sql = "select * from company_job where job_name = ?";
			pstmt = con.prepareStatement(select_sql);
			pstmt.setString(1, change);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return 0;
			}
			pstmt.close();

			String sql = "insert into COMPANY_JOB(" + value + ", COMPANY_NAME) values(?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, change);
			pstmt.setString(2, company_name);
			result = pstmt.executeUpdate();
			if (result == 1) {
				System.out.println("데이터 삽입이 모두 완료되었습니다.");
			}
		} catch (Exception ex) {
			System.out.println("dept_create() 에러 : " + ex);
			ex.printStackTrace();
		} finally {
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			if (con != null)
				try {
					con.setAutoCommit(true);
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return result;
	}

	public int update(String value, String change, String id, String def) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;

		try {
			con = ds.getConnection();
			con.setAutoCommit(false);

			String update_sql = "update company_info set " + value + " = ? where USER_ID=?";
			pstmt = con.prepareStatement(update_sql);
			pstmt.setString(1, change);
			pstmt.setString(2, id);
			result = pstmt.executeUpdate();
			pstmt.close();

			if (value.equals("company_name")) {
				String dept_sql = "update company_dept set " + value + " = ? where company_name = ?";
				pstmt = con.prepareStatement(dept_sql);
				pstmt.setString(1, change);
				pstmt.setString(2, def);
				result = pstmt.executeUpdate();
				pstmt.close();

				String job_sql = "update company_job set " + value + " = ? where company_name = ?";
				pstmt = con.prepareStatement(job_sql);
				pstmt.setString(1, change);
				pstmt.setString(2, def);
				result = pstmt.executeUpdate();
				pstmt.close();
			}
			con.commit();

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
					con.setAutoCommit(true);
					con.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
		}
		return result;
	} // update end

	public boolean dept_update(String value, String change, String company, String def) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			con = ds.getConnection();

			if (value.equals("dept_name")) {
				sql = "update company_dept set dept_name = ? where dept_name = ? and company_name = ?";

			} else if (value.equals("job_name")) {
				sql = "update company_job set job_name = ? where job_name = ? and company_name = ?";
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, change);
			pstmt.setString(2, def);
			pstmt.setString(3, company);
			pstmt.executeUpdate();
			return true;
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
		return false;
	} // update end

	public void dept_delete(String value, String change, String company, String def) {
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);

			if (value.equals("dept_name")) {
				sql = "delete from company_dept where dept_name = ? and company_name = ?";

				String dept_sql = "update user_info set user_dept = '' where user_dept = ?";
				pstmt = con.prepareStatement(dept_sql);
				pstmt.setString(1, change);
				pstmt.executeUpdate();
				pstmt.close();

			} else if (value.equals("job_name")) {
				sql = "delete from company_job where job_name = ? and company_name = ?";
				String job_sql = "update user_info set user_job = '' where user_job = ?";
				pstmt = con.prepareStatement(job_sql);
				pstmt.setString(1, change);
				pstmt.executeUpdate();
				pstmt.close();
			}
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, def);
			pstmt.setString(2, company);
			pstmt.executeUpdate();
			con.commit();
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
	}

	public int update(String value, String change, String company_name) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			con = ds.getConnection();
			String update_sql = "update company_info set " + value + " = ? where company_name = ?";
			pstmt = con.prepareStatement(update_sql);
			pstmt.setString(1, change);
			pstmt.setString(2, company_name);
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

}
