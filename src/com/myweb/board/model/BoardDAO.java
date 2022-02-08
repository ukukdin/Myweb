package com.myweb.board.model;


	//싱글톤 형식
	import java.sql.Connection;
	
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.InitialContext;
	import javax.sql.DataSource;
	
	

	import com.myweb.util.jdbcUtil;





public class BoardDAO {

		//1. 싱글톤패턴을 이용한 클래스
		//스스로 객체를 생성하고 1개로 제한
		private static BoardDAO dao = new BoardDAO();

		//2. 생성자에 private를 붙입니다
		private BoardDAO() {

			try {
//				Class.forName("oracle.jdbc.driver.OracleDriver");
				InitialContext ctx =new InitialContext();
				ds =(DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		//3. 외부에서 객체생성을 요구할때 멤버변수 dao를 반환합니다.
		public static BoardDAO getInstance() {
			return dao;
		}

		////////////////////////////////////////////////////////
		//데이터베이스 연결주소
		String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
		String uid = "jsp";
		String upw = "jsp";

		
		DataSource ds;
		
	//글 등록매서드
		
		public void regist(String writer,String title,String content) {
			
			Connection conn =null;
			PreparedStatement pstmt =null;
			
			String sql="INSERT INTO BOARD(bno,writer,title,content) values(board_seq.nextval,?,?,?)";
		
				try {	
					conn=ds.getConnection();
					pstmt=conn.prepareStatement(sql);
					pstmt.setString(1, writer);
					pstmt.setString(2, title);
					pstmt.setString(3, content);
					pstmt.executeUpdate();
				} catch (Exception e) {
					e.printStackTrace();
					
				}finally {
					jdbcUtil.close(conn, pstmt, null);
				}
		
		}
		public ArrayList<BoardVO> getList(int pageNum,int amount){
			
			ArrayList<BoardVO> list=new ArrayList<>();//select한 결과를 담을 list
		
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;
			
			String sql="select*\r\n"
					+"from(select rownum as rn, \r\n"
					+"a.*\r\n"
					+"from(select *\r\n"
					+ "from board \r\n"
					+ "order by bno desc)a\r\n"
					+")\r\n"
					+"where rn> ? and rn <= ?";
			
			try {
				conn=ds.getConnection();
				
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1,(pageNum-1)*amount);
				pstmt.setInt(2,(pageNum*amount));
				rs=pstmt.executeQuery();
				while(rs.next()) {
					//1.컬럼값을 가지고 와서
				int bno=rs.getInt("bno");
				String writer=rs.getString("writer");
				String title=rs.getString("title");
				String content=	rs.getString("content");
				Timestamp regdate=rs.getTimestamp("regdate");
				int hit=rs.getInt("hit");
					//2.vo를 생성후 값 저장
					BoardVO vo = new BoardVO(bno,writer,title,content,regdate,hit);
					
					//리스트에 담아야합ㄴ디ㅏㅣ
					
					list.add(vo);
					
					
					
					
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				jdbcUtil.close(conn, pstmt, rs);
			}
			
			return list;
		}
		
		public int getTotal() {
			int result=0;
			Connection conn=null;
			PreparedStatement pstmt=null;
			ResultSet rs=null;

			String sql="select count(*) as total from board";
			
			
			try {
				conn=ds.getConnection();
				pstmt=conn.prepareStatement(sql);
				
				rs=pstmt.executeQuery();
				
				if(rs.next()) {
					result=rs.getInt("total");
				}
				
			} catch (Exception e) {
				e.printStackTrace();	
			}finally {
				jdbcUtil.close(conn, pstmt, rs);
			}
			
			return result;
		}

		public BoardVO getContent(String bno) {
		
		BoardVO vo=new BoardVO();
		
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		String sql="select*from board where bno=?";		
		
		try {
			conn=ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, bno);
	
			rs=pstmt.executeQuery();
			if(rs.next()) {
				
				vo.setBno(rs.getInt("bno"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getTimestamp("regdate"));
				vo.setHit(rs.getInt("hit"));
			
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			jdbcUtil.close(conn, pstmt, rs);
		}
		
		return vo;
		}
		public void update(String title, String bno,String content) {
			
			
			Connection conn=null;
			PreparedStatement pstmt=null;
			
			String sql="update board set title=?, content =? where bno=?"r;
			
			try {
				conn=ds.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, title);
				pstmt.setString(2, content);
				pstmt.setString(3, bno);
				
				
			
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				jdbcUtil.close(conn, pstmt, null);
			}
		}
		public void delete(String bno) {
			
			Connection conn=null;
			PreparedStatement pstmt=null;
			
			String sql="delete from board where bno=?";
			
			try {
				conn=ds.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, bno);
				
				pstmt.executeUpdate();
				
			} catch (Exception e) {
				
			}finally {
				jdbcUtil.close(conn, pstmt, null);
			}
		}
		public void Uphit(String bno) {
			
			Connection conn=null;
			PreparedStatement pstmt=null;
			String sql= "update board set Hit= Hit+1 where bno=?";
			
			try {
				conn=ds.getConnection();
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, bno);
				
				pstmt.executeUpdate();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				jdbcUtil.close(conn, pstmt, null);
			}
		}
		
}
