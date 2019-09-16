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
	 * DBMS�� ������ ��ȯ�ϴ� ��.
	 * @return ����� Ŀ�ؼ�
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException { 
		Connection con=null;
		//1. ����̹� �ε� 
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		//2. Connection ���
		String url="jdbc:oracle:thin:@211.63.89.132:1521:orcl";
//		String url="jdbc:oracle:thin:@211.63.89.133:1521:orcl";
		String id="pcbang";
		String pass="ezo";
		
		con=DriverManager.getConnection(url, id, pass);
		
		return con;
	}//getConnection
	
	/**
	 * ���̵�� ��й�ȣ�� �Է¹޾� admin_login ���̺��� ���̵�� ��й�ȣ�� �´´ٸ� �̸��� ��ȸ�ϴ� ��.
	 * 
	 * @param lv ���̵�� ����� ���� VO
	 * @return ���� - �̸�, ���� - empty
	 * @throws SQLException
	 */
	public String selectLogin(AdminLoginVO lv) throws SQLException { //boolean -> String
		String adminName="";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		//2. Ŀ�ؼ� ���
			con=getConnection();
		//3. ������ ������ü ���
			StringBuilder selectName=new StringBuilder();
			selectName
			.append("		select name		")
			.append("		from admin_account	")
			.append("		where id=? and pass=?	");
			
			pstmt=con.prepareStatement(selectName.toString());
			
		//4. ���ε� ������ �� �ֱ�
			pstmt.setString(1, lv.getId());
			pstmt.setString(2, lv.getPass());
		//5. ���� ���� �� ��� ���
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				adminName=rs.getString("name");
			}//end if
			
		}finally {
		//6. �������
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
	
	
	
	
