package admin.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import admin.VO.AdminLoginVO;

public class AdminDAO {
	
	private static AdminDAO aDAO;
	
	private AdminDAO() {
		
	}//AdminDAO
	
	public static AdminDAO getInstance() {
		if(aDAO==null) {
			aDAO=new AdminDAO();
			
		}//end if
		return aDAO;
	}//getInstance
	
	/**
	 * DBMS의 연결을 반환하는 일.
	 * @return 연결된 커넥션
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException { 
		Connection con=null;
		//1. 드라이버 로딩 
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		//2. Connection 얻기
		String url="jdbc:oracle:thin:@211.63.89.132:1521:orcl";
//		String url="jdbc:oracle:thin:@211.63.89.133:1521:orcl";
		String id="pcbang";
		String pass="ezo";
		
		con=DriverManager.getConnection(url, id, pass);
		
		return con;
	}//getConnection
	
	/**
	 * 아이디와 비밀번호를 입력받아 admin_login 테이블에서 아이디와 비밀번호가 맞는다면 이름을 조회하는 일.
	 * 
	 * @param lv 아이디와 비번을 가진 VO
	 * @return 성공 - 이름, 실패 - empty
	 * @throws SQLException
	 */
	public String selectLogin(AdminLoginVO lv) throws SQLException { //boolean -> String
		String adminName="";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		//2. 커넥션 얻기
			con=getConnection();
		//3. 쿼리문 생성객체 얻기
			StringBuilder selectName=new StringBuilder();
			selectName
			.append("		select name		")
			.append("		from admin_account	")
			.append("		where id=? and pass=?	");
			
			pstmt=con.prepareStatement(selectName.toString());
			
		//4. 바인드 변수에 값 넣기
			pstmt.setString(1, lv.getId());
			pstmt.setString(2, lv.getPass());
		//5. 쿼리 실행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				adminName=rs.getString("name");
			}//end if
			
		}finally {
		//6. 연결끊기
			if ( rs != null ) { rs.close(); } //end if
			if ( pstmt != null ) { pstmt.close(); } //end if
			if ( con != null ) { con.close(); } //end if
		}//end finally
		return adminName;
	}//selectLogin
	
	
//	public static void main(String[] args) {
//		AdminDAO ad=AdminDAO.getInstance();
//		try {
//			System.out.println(ad.selectLogin());  
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
	
}//class
	
	
	
	
