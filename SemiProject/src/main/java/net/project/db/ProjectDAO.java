package net.project.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.swing.plaf.ProgressBarUI;

import net.member.db.MainBean;

public class ProjectDAO {
	private DataSource ds;

	
	//생성자에서 JNDI 리소스를 참조하여 Connection 객체를 얻어옵니다.



	public ProjectDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/OracleDB");

		}catch(Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
		}
   }
	




	public void close(PreparedStatement pstmt, Connection conn) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.setAutoCommit(true);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void all_close(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.setAutoCommit(true);
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int getListCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int x = 0;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select count(*) from project");
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				x = rs.getInt(1);
			}
		} catch (Exception ex) {
			ex.getStackTrace();
			System.out.println("getListCount() 에러 :" + ex);
		} finally {
			all_close(rs, pstmt, conn);
		}
		return x;
	}
	public int getDayCount(String date1, String date2) {
		int prog = 0;
		
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());
        try {
        	Date format1 = new SimpleDateFormat("yyyy-MM-dd").parse(date1);
    	    Date format2 = new SimpleDateFormat("yyyy-MM-dd").parse(date2);
    	    Date todayformat = new SimpleDateFormat("yyyy-MM-dd").parse(strToday);
    	    long diffSec = (format2.getTime() - format1.getTime()) / 1000; //초 차이
    	    long diffMin = (format2.getTime() - format1.getTime()) / 60000; //분 차이
    	    long diffHor = (format2.getTime() - format1.getTime()) / 3600000; //시 차이
    	    long all_Days_diff = diffSec / (24*60*60); //일자수 차이
    	   
    	    long today_diff_sec = (format2.getTime() - todayformat.getTime()) / 1000;
    	    long today_diff = today_diff_sec / (24*60*60);
    			        		
    	    double all_days = all_Days_diff;
    	    double today =  today_diff;
    	    prog = 100-(int)((today / all_days)*100 );
        } catch (Exception e) {
        	e.printStackTrace();
        }
	   
	   
	    
	    return prog;
	}
	public List<Project> getDeadLineProjects(int page, int limit){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String project_List_Sql = "SELECT PR.*,U.USER_IMG FROM ( SELECT * FROM (SELECT ROWNUM,P.* FROM (SELECT * FROM PROJECT ORDER BY PROJECT_END)P "
								+ "                WHERE ROWNUM <= ?) WHERE ROWNUM >= ? AND ROWNUM <= ? AND PROJECT_END < SYSDATE  )PR "
								+ "INNER JOIN USER_INFO U "
								+ "ON PR.PROJECT_ADMIN = U.USER_ID ";
		
		List<Project> list = new ArrayList<Project>();
		int startrow = (page -1 ) * limit + 1;
		int endrow = startrow + limit -1;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(project_List_Sql);
			pstmt.setInt(1, endrow);
			pstmt.setInt(2,startrow);
			pstmt.setInt(3, endrow);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Project pro = new Project();
				pro.setRow_num(rs.getInt(1));
				pro.setProject_num(rs.getInt(2));
				pro.setProject_name(rs.getString(3));
				pro.setProject_state(rs.getString(4));
				pro.setProject_prog(100);
				pro.setProject_start(rs.getString(6));
				pro.setProject_end(rs.getString(7));
				pro.setProject_priority(rs.getString(8));
				pro.setProject_admin(rs.getString("project_admin"));
				pro.setProject_bookmark(rs.getString(11));
				ArrayList<Project_User> user_parti = getParticipants(rs.getInt(2));
				pro.setProject_parti(user_parti);
				pro.setProject_admin_img(rs.getString("user_img"));
				
				list.add(pro);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getProjectList() : 에러발생" + ex);
		} finally {
			all_close(rs, pstmt, conn);
		}
		
		return list;

	}
	
	public List<Project> getProjectList(int page, int limit, String logingID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String id =  logingID;
		int isAdmin = getIsAdmin(id);
		String project_List_Sql = "";
		if(isAdmin == 1) {
			project_List_Sql = "SELECT PR.*, U.USER_IMG FROM ( SELECT * FROM "
					+ "    (SELECT ROWNUM,P.* FROM (SELECT * FROM PROJECT ORDER BY PROJECT_END)P "
					+ "     WHERE ROWNUM <= ?) "
					+ "WHERE ROWNUM >= ? "
					+ "AND ROWNUM <= ? "
					+ "AND PROJECT_END > SYSDATE "
					+ "OR PROJECT_END IS NULL )PR "
					+ "INNER JOIN USER_INFO U "
					+ "ON PR.PROJECT_ADMIN = U.USER_ID "
					+ " ORDER BY PROJECT_END";
		} else {
			project_List_Sql = "SELECT* FROM( SELECT PR.*, U.USER_IMG FROM ( SELECT * FROM "
					+ "    (SELECT ROWNUM,P.* FROM (SELECT * FROM PROJECT ORDER BY PROJECT_END)P "
					+ "     WHERE ROWNUM <= ?) "
					+ "WHERE ROWNUM >= ? "
					+ "AND ROWNUM <= ? "
					+ "AND PROJECT_END > SYSDATE "
					+ "OR PROJECT_END IS NULL )PR "
					+ "INNER JOIN USER_INFO U "
					+ "ON PR.PROJECT_ADMIN = U.USER_ID ORDER BY PROJECT_END ) WHERE PROJECT_NUM IN (SELECT DISTINCT(PROJECT_NUM) FROM PROJECT_USER WHERE USER_ID LIKE ?)";
					
		}
		
		
		
		List<Project> list = new ArrayList<Project>();
		List<Project> dead_list = new ArrayList<Project>();
		
		int startrow = (page -1 ) * limit + 1;
		int endrow = startrow + limit -1;
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(project_List_Sql);
			pstmt.setInt(1, endrow);
			pstmt.setInt(2,startrow);
			pstmt.setInt(3, endrow);
			if(isAdmin == 0 ) {
				pstmt.setString(4, id);
			}
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Project pro = new Project();
				pro.setRow_num(rs.getInt(1));
				pro.setProject_num(rs.getInt(2));
				pro.setProject_name(rs.getString(3));
				pro.setProject_state(rs.getString(4));
				int prog = 0;
				if(rs.getString(7) != null ) {
					String d1 = rs.getString(6);
					String d1s = d1.substring(0,10);
					String date1 = d1s;
				    String date2 = rs.getString(7).substring(0,10); //날짜2
				    prog = getDayCount(date1, date2);
					pro.setProject_prog(prog);
				} else {
					pro.setProject_prog(1);
				}
				
				pro.setProject_start(rs.getString(6));
				pro.setProject_end(rs.getString(7));
				pro.setProject_priority(rs.getString(8));
				pro.setProject_admin(rs.getString("project_admin"));
				pro.setProject_bookmark(rs.getString(11));
				
				ArrayList<Project_User> user_parti = getParticipants(rs.getInt(2));
				
			
				ArrayList<Project_User> modal = getParticipants(rs.getInt(2));
				int modalcount = getModalCount(rs.getInt(2));
				
				pro.setProject_parti(user_parti);
				pro.setParti_count(modalcount);
				pro.setProject_parti_forModal(modal);
				pro.setProject_admin_img(rs.getString("user_img"));
				list.add(pro);
				
			}
			dead_list = getDeadLineProjects(page,limit);
			list.addAll(dead_list);

		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getProjectList() : 에러발생" + ex);
		} finally {
			all_close(rs, pstmt, conn);
		}
		
		return list;
	}
	
	private int getIsAdmin(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int isAdmin = 0;
		
		String sql = "select USER_IS_ADMIN from user_info where user_id like ?";
	
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
		
			rs = pstmt.executeQuery();
			if(rs.next()) {
				isAdmin = rs.getInt(1);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("isAdmin() : 에러발생" + ex);
		} finally {
			all_close(rs, pstmt, conn);
		}
		
		return isAdmin;
	}





	private int getModalCount(int pro_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		
		String participants_Sql = "SELECT COUNT(*) FROM PROJECT_USER P "
								+ "INNER JOIN USER_INFO U "
								+ "ON P.USER_ID = U.USER_ID "
								+ "WHERE PROJECT_NUM = ? ";
	
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(participants_Sql);
			pstmt.setInt(1, pro_num);
		
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getProjectList() : 에러발생" + ex);
		} finally {
			all_close(rs, pstmt, conn);
		}
		
		return count;
	}





	private ArrayList<Project_User> getParticipants(int pro_num) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Project_User> list = null;
		
		String participants_Sql = "SELECT P.USER_ID,P.PROJECT_NUM , U.USER_NAME, U.USER_IMG FROM PROJECT_USER P "
								+ "INNER JOIN USER_INFO U "
								+ "ON P.USER_ID = U.USER_ID "
								+ "WHERE PROJECT_NUM = ? ";
	
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(participants_Sql);
			pstmt.setInt(1, pro_num);
		
			rs = pstmt.executeQuery();
			list = new ArrayList<Project_User>();
			while(rs.next()) {
				Project_User user = new Project_User();
				user.setUSER_ID(rs.getString(1));
				user.setPROJECT_NUM(rs.getInt(2));
				user.setUSER_NAME(rs.getString(3));
				user.setUSER_IMG(rs.getString(4));
				
				list.add(user);
			}
			
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("getProjectList() : 에러발생" + ex);
		} finally {
			all_close(rs, pstmt, conn);
		}
		
		return list;
	}





	public Project getDetailProject() {
		return null;
	}
	
	public int insert(Project p) {
		   //초기변수선언부
		        Connection con = null;
		        PreparedStatement pstmt = null;
		        int result=0;

		  try {
		     //db객체받아서
		     con = ds.getConnection();
		     //auto커밋 false
		     con.setAutoCommit(false);
		     
		     //1차 sql문생성
		     String sql =   "insert into PROJECT (PROJECT_NUM, PROJECT_NAME, PROJECT_STATE, PROJECT_ADMIN, "            
		              +  "                        PROJECT_START, PROJECT_END,PROJECT_priority) "
		              +  " values ( NVL((SELECT MAX(PROJECT_NUM)FROM PROJECT)+1,0 ), ?,?,?,?,?,?)";
		     //커넥션 객체 con 에 sql문을 넣어서 pstmt객체 생성 
		     pstmt = con.prepareStatement(sql);
		     
		     //sql 에 ? 부분 값 설정해주기
		     int state = Integer.parseInt(p.getProject_state());
		     String s_state= "";
		     if(state == 1) {
		    	 s_state = "진행중";
		     } else if(state == 0) {
		    	 s_state = "없음";
		     }
		     
		     pstmt.setString(1, p.getProject_name());
		     pstmt.setString(2, s_state);
		     pstmt.setString(3, p.getProject_admin());
		     pstmt.setString(4, p.getProject_start());
		     pstmt.setString(5, p.getProject_end());
		     pstmt.setString(6, p.getProject_priority());
		     
		     
		     //executeUpdate 메서드 반환값 result에 저장 executeUpdate함수는 변화가 일어난 row개수를 반환합니다 실패시 0 반환
		     //예를들어 1개행 insert : 1 / 5개행 insert : 5를반환 저희는 지금 프로젝트 1개를 생성하기때문에 1을 반환합니다
		     result = pstmt.executeUpdate(); //성공하면 result 1 실패면 0 인상태
		     //한번 실행한 pstmt객체는 close()해서 리소스 반환
		     pstmt.close();
		     
		     //2차 실행할 sql문 작성
		     sql = "   insert into PROJECT_USER  "
		             + "  values( (select nvl(max(PROJECT_NUM),0)+1 FROM PROJECT) "
		             + "        , ? ,  "
		             + "      ( SELECT MAX(ROWNUM)+3 FROM PROJECT_USER) ) ";
		     
		     //2차 sql문 실행해줄 pstmt객체 새로이 생성
		     pstmt = con.prepareStatement(sql);
		     
		     pstmt.setString(1, p.getProject_admin());
		     /*
		      *  sql 에 ? 부분 값 설정해주시면됩니다
		      * 
		      */
		     
		    //똑같이 result  에 executeUpdate 반환값 저장해주시고
		    //전에 실행한 executeUpdate 의 결과에 현재 executeUpdate 실행결과값을 더해줍니다
		    //위의 설명과 마찬가지로 일단은 프로젝트 최초 생성자 한명만 참여자 테이블에 들어가기 때문에 성공시 1개 행 insert 1을 반환합니다
		    result += pstmt.executeUpdate();
		    
		     
		        if (result == 2) {
		        //2면 둘다 실행이 잘된거니까 삽입완료 커밋하시면됩니다
		        //2가 아니면 0,1 둘다 둘중하나는 실패한상황이므로 맞춰서 에러메시지 추적하실수있게하세요!
		           System.out.println("insert 삽입 완료되었습니다.");
		           con.commit();
		        } else {
		        	System.out.println("삽입 실패");
		        }
		        
		        } catch (Exception e) {
		           e.printStackTrace();
		           
		           try {
		              con.rollback();
		           } catch (SQLException e1) {
		                 e1.printStackTrace();
		           }
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
		              } catch (Exception ex) {
		              ex.printStackTrace();
		              }
		           }
		           return result;      
		        }
	
		}

