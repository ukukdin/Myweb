package com.myweb.user.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.naming.InitialContext;
import javax.sql.DataSource;
import javax.websocket.SendResult;
import javax.xml.ws.Response;

import com.myweb.util.jdbcUtil;



public class UserDAO {

	//1. 싱글톤패턴을 이용한 클래스
	//스스로 객체를 생성하고 1개로 제한
	private static UserDAO dao = new UserDAO();

	//2. 생성자에 private를 붙입니다
	private UserDAO() {

		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			InitialContext ctx =new InitialContext();
			ds =(DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	//3. 외부에서 객체생성을 요구할때 멤버변수 dao를 반환합니다.
	public static UserDAO getInstance() {
		return dao;
	}

	////////////////////////////////////////////////////////
	//데이터베이스 연결주소
	String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
	String uid = "jsp";
	String upw = "jsp";

	
	DataSource ds;
	
	
	public int idCheck(String id) {
		int result=0;

		String sql="select from board where =id?";
						
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			//conn=DriverManager.getConnection(url,uid,upw);
			conn = ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs=pstmt.executeQuery();

			if(rs.next()) {//true
				result=1;//아이디가 있는경우
			}else {
				result=0;//아이디 중복이 없는경우
			}
		} catch (Exception e) {

		}finally {
			jdbcUtil.close(conn, pstmt, rs);
		}


		return result;
	}

	public int insert(UserVO vo) {
		int result=0;
		String sql="insert into users(id,pw,name,email,address)values(?,?,?,?,?)";

		Connection conn=null;
		PreparedStatement pstmt=null;

		try {
			//conn=DriverManager.getConnection(url,uid,upw);
			
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getAdress());
			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			jdbcUtil.close(conn, pstmt, null);
		}


		return result;
	}
	
	
	
	
	
	public UserVO login(String id, String pw) {
		UserVO vo = null;

		String sql = "Select *from users where id=? and pw=?";
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs =null;


		try {

			//conn=DriverManager.getConnection(url,uid,upw);
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs=pstmt.executeQuery();

			if(rs.next()) {
				String ids=rs.getString("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String address = rs.getString("address");
				//timestamp regdate =rs.gettimestampuser
				vo = new UserVO(ids, null, name, email, address, null);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			jdbcUtil.close(conn, pstmt, rs);
		}


		return vo;
	}
	public int update(UserVO vo) {
		int result = 0;
		
		String sql = "update users set pw = ?, name = ?, email = ?, address = ? where id = ?";
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			//conn = DriverManager.getConnection(url, uid, upw);
			conn=ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getPw() );
			pstmt.setString(2, vo.getName() );
			pstmt.setString(3, vo.getEmail() );
			pstmt.setString(4, vo.getAdress() );
			pstmt.setString(5, vo.getId() );
			
			result = pstmt.executeUpdate(); //성공시 1, 실패시 0
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close(conn, pstmt, null);
		}
				
		
		return result;
	}
public int delete(String id) {
	int result =0;
	String sql="delete from users where id=?";
	
	Connection conn=null;
	PreparedStatement pstmt=null;
	
	try {
		conn=ds.getConnection();
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, id);
		
		result=pstmt.executeUpdate();
		
	} catch (Exception e) {
		// TODO: handle exception
	}finally {
		jdbcUtil.close(conn, pstmt, null);
	}
	
	return result;
}
}
