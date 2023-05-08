
package net.mypage.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
         pstmt = con.prepareStatement("select * from user_info where user_id = ?");
         System.out.println(user_id);
         pstmt.setString(1, user_id);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            m = new UserInfo();
            m.setUSER_ID(rs.getString("USER_ID"));
            m.setUSER_PASSWORD(rs.getString("USER_PASSWORD"));
            m.setUSER_DEPT(rs.getString("USER_DEPT"));
            m.setUSER_JOB(rs.getString("USER_JOB"));
            m.setUSER_EMAIL(rs.getString("USER_EMAIL"));
            m.setUSER_NAME(rs.getString("USER_NAME"));
            m.setUSER_FAX(rs.getString("USER_FAX"));
            m.setUSER_PHONE(rs.getString("USER_PHONE"));
            m.setUSER_INTRO(rs.getString("USER_INTRO"));
            m.setUSER_IMG(rs.getString("USER_IMG"));
            m.setUSER_CARD(rs.getString("USER_CARD"));
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
               + "     set user_name=?, user_dept=?, user_job=?, user_email=?, user_fax=?, user_phone=?, user_intro=?, user_img=?, user_card=? "
               + "     where USER_ID=?";
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

   public int update(String value, String change, String id) {
      Connection con = null;
      PreparedStatement pstmt = null;
      int result = 0;

      try {
         con = ds.getConnection();
         String sql = "update user_info " + " set " + value + " = ?" + "     where USER_ID=?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, change);
         pstmt.setString(2, id);
         result = pstmt.executeUpdate();
         System.out.println(result + " update() 결과");
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

   public List<Dept> dept(String com) {
      Connection con = null;
      PreparedStatement pstmt = null;
      List<Dept> list = new ArrayList<>();
      ResultSet rs = null;
      try {
         con = ds.getConnection();

         System.out.println(com);
         String sql = "SELECT * from company_dept where company_name = ? order by dept_name";
         pstmt = con.prepareStatement(sql);

         pstmt.setString(1, com);
         rs = pstmt.executeQuery();

         while (rs.next()) {
            Dept d = new Dept();
            d.setDept_name(rs.getString(1));
            d.setCompany_name(rs.getString(2));
            list.add(d);

         }
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("dept() 오류");
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
      return list;
   }

   public List<Job> job(String job) {
      Connection con = null;
      PreparedStatement pstmt = null;
      List<Job> list = new ArrayList<>();
      ResultSet rs = null;
      try {
         con = ds.getConnection();

         String sql = "SELECT * from company_job where company_name = ? order by job_name";
         pstmt = con.prepareStatement(sql);

         pstmt.setString(1, job);
         rs = pstmt.executeQuery();

         while (rs.next()) {
            Job j = new Job();
            j.setJob_name(rs.getString(1));
            j.setCompany_name(rs.getString(2));
            list.add(j);

         }
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("dept() 오류");
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
      return list;
   }

   public List<UserInfo> all_member_info() {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      List<UserInfo> list = new ArrayList<UserInfo>();
      try {
         con = ds.getConnection();

         String sql = "SELECT * from user_info order by user_state";
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();

         while (rs.next()) {
            UserInfo m = new UserInfo();
            m.setUSER_ID(rs.getString("user_id"));
            m.setUSER_PASSWORD(rs.getString("user_password"));
            m.setUSER_NAME(rs.getString("user_name"));
            m.setUSER_DEPT(rs.getString("user_dept"));
            m.setUSER_JOB(rs.getString("user_job"));
            m.setUSER_EMAIL(rs.getString("user_email"));
            m.setUSER_PHONE(rs.getString("user_phone"));
            m.setUSER_JOIN_DATE(rs.getString("user_join_date"));
            m.setUSER_STATE(rs.getString("user_state"));
            m.setUSER_IS_ADMIN(rs.getInt("user_is_admin"));
            m.setUSER_LASTTIME(rs.getString("user_lasttime"));

            list.add(m);
         }

      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("dept() 오류");
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
      return list;
   }

   public UserInfo isId(String user_id) {
      UserInfo m = new UserInfo();
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
         con = ds.getConnection();

         String sql = "SELECT * from user_info where user_id = ?";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, user_id);
         rs = pstmt.executeQuery();
         if (rs.next()) {
            m = new UserInfo();
            m.setUSER_ID(rs.getString("user_id"));
            m.setUSER_PASSWORD(rs.getString("user_password"));
            m.setUSER_JOIN_DATE(rs.getString("user_join_date"));
            m.setUSER_STATE(rs.getString("user_state"));
            m.setUSER_IS_ADMIN(rs.getInt("user_is_admin"));
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
      return m;
   }

   public void member_status(String user_id, String state) {
      Connection con = null;
      PreparedStatement pstmt = null;
      try {
         con = ds.getConnection();
         String sql = "update user_info set user_state = ? WHERE USER_ID = ?";

         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, state);
         pstmt.setString(2, user_id);
         pstmt.executeUpdate();
         System.out.println(state);
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("member_status() 오류");
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

   public List<UserInfo> normal_info() {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      List<UserInfo> normal = new ArrayList<UserInfo>();
      try {
         con = ds.getConnection();

         String sql = "SELECT * from user_info where user_state = 0";
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();

         while (rs.next()) {
            UserInfo m = new UserInfo();
            m.setUSER_ID(rs.getString("user_id"));
            m.setUSER_PASSWORD(rs.getString("user_password"));
            m.setUSER_NAME(rs.getString("user_name"));
            m.setUSER_DEPT(rs.getString("user_dept"));
            m.setUSER_JOB(rs.getString("user_job"));
            m.setUSER_EMAIL(rs.getString("user_email"));
            m.setUSER_PHONE(rs.getString("user_phone"));
            m.setUSER_JOIN_DATE(rs.getString("user_join_date"));
            m.setUSER_STATE(rs.getString("user_state"));
            m.setUSER_IS_ADMIN(rs.getInt("user_is_admin"));
            m.setUSER_LASTTIME(rs.getString("user_lasttime"));
            normal.add(m);
         }

      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("normal_info() 오류");
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
      return normal;
   }

   public List<UserInfo> wait_info() {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      List<UserInfo> wait = new ArrayList<UserInfo>();
      try {
         con = ds.getConnection();

         String sql = "SELECT * from user_info where user_state = 2";
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();

         while (rs.next()) {
            UserInfo m = new UserInfo();
            m.setUSER_ID(rs.getString("user_id"));
            m.setUSER_PASSWORD(rs.getString("user_password"));
            m.setUSER_NAME(rs.getString("user_name"));
            m.setUSER_DEPT(rs.getString("user_dept"));
            m.setUSER_JOB(rs.getString("user_job"));
            m.setUSER_EMAIL(rs.getString("user_email"));
            m.setUSER_PHONE(rs.getString("user_phone"));
            m.setUSER_JOIN_DATE(rs.getString("user_join_date"));
            m.setUSER_STATE(rs.getString("user_state"));
            m.setUSER_IS_ADMIN(rs.getInt("user_is_admin"));
            m.setUSER_LASTTIME(rs.getString("user_lasttime"));
            wait.add(m);
         }

      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("normal_info() 오류");
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
      return wait;
   }

   public List<UserInfo> stop_info() {
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      List<UserInfo> stop = new ArrayList<UserInfo>();
      try {
         con = ds.getConnection();

         String sql = "SELECT * from user_info where user_state = 1";
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();

         while (rs.next()) {
            UserInfo m = new UserInfo();
            m.setUSER_ID(rs.getString("user_id"));
            m.setUSER_PASSWORD(rs.getString("user_password"));
            m.setUSER_NAME(rs.getString("user_name"));
            m.setUSER_DEPT(rs.getString("user_dept"));
            m.setUSER_JOB(rs.getString("user_job"));
            m.setUSER_EMAIL(rs.getString("user_email"));
            m.setUSER_PHONE(rs.getString("user_phone"));
            m.setUSER_JOIN_DATE(rs.getString("user_join_date"));
            m.setUSER_STATE(rs.getString("user_state"));
            m.setUSER_IS_ADMIN(rs.getInt("user_is_admin"));
            m.setUSER_LASTTIME(rs.getString("user_lasttime"));
            stop.add(m);
         }

      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("normal_info() 오류");
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
      return stop;
   }

   public void admin_status(String user_id, String state) {
      Connection con = null;
      PreparedStatement pstmt = null;
      try {
         con = ds.getConnection();
         String sql = "update user_info set user_is_admin = ? WHERE USER_ID = ?";

         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, state);
         pstmt.setString(2, user_id);
         pstmt.executeUpdate();
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("admin_status() 오류");
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

}