package admin.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.DAO.CalcPCDAO;
import admin.VO.CalcPCReciptVO;
import admin.VO.CalcPCVO;


public class CalcPCDAO {
	
	private static CalcPCDAO pcDAO ;
	
	private CalcPCDAO() {
		
		
	} // CalcPCDAO
	
	public static CalcPCDAO getInstance() {
		if (pcDAO == null) {
			pcDAO = new CalcPCDAO() ;
		} // end if
	return pcDAO ;
	} // getInstance
	
	
	/**
	 * DBMS�� ������ ��ȯ�ϴ� ��
	 * 
	 * @return ����� Ŀ�ؼ�
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		Connection conn = null ;
		// 1. ����̹� �ε� (ojdbc8.jar
		try {
			Class.forName("oracle.jdbc.OracleDriver") ;
			
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} // end catch
		
		// 2. Connection ���
		String url = "jdbc:oracle:thin:@211.63.89.132:1521:orcl" ;
		String id = "pcbang" ;
		String pass = "ezo" ;
		
		conn = DriverManager.getConnection(url, id, pass) ;
		
		return conn ;
	} // getConnection
	
	public List<CalcPCVO> selectCalcPC() throws SQLException {
		List<CalcPCVO> list = new ArrayList<CalcPCVO>() ;
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		// 2. Connection ���
		conn = getConnection() ;
		

		try {
		// 3. ������ ���� ��ü ��� : lunch ���̺��� �̸�, �ڵ�, ����, �Է����� ���� �ֱٿ� �Էµ� �� ���� ��ȸ
		StringBuilder selectCalcPC = new StringBuilder() ;
		selectCalcPC
		//select pc_num, pc_code, id, use_time, use_fee from pc_history
		.append("	select pc_num, pc_code, id, use_time, use_fee	")
		.append("	from pc_history	") ;
		
		pstmt = conn.prepareStatement(selectCalcPC.toString()) ;
		
		// 4. bind ������ �� ����
		// ����
		
		// 5. ���� ���� �� ��� ���
		rs = pstmt.executeQuery() ;
		CalcPCVO cv = null ;
		while( rs.next() ) {
			//pc_num, pc_code, id, use_time, use_fee
			cv = new CalcPCVO(rs.getString("pc_code"),rs.getString("id"), rs.getString("use_time"), rs.getInt("pc_num"), rs.getInt("use_fee")) ;
			list.add(cv) ;	// ��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
		} // end while
		
		} finally {
		// 6. ���� ����
			if ( rs != null) { rs.close(); } // end if
			if ( pstmt != null) { pstmt.close(); } // end if
			if ( conn != null) { conn.close(); } // end if
			
		} // end finally
				
		return list ;
	} // selectCalcPC
	
	public List<CalcPCReciptVO> selectCalcPCRecipt() throws SQLException {
		List<CalcPCReciptVO> list = new ArrayList<CalcPCReciptVO>() ;
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		// 2. Connection ���
		conn = getConnection() ;
		
		
		try {
			// 3. ������ ���� ��ü ��� 
			StringBuilder selectCalcPCRecipt = new StringBuilder() ;
			selectCalcPCRecipt
			//select pc_code, sum(use_time) use_time, sum(use_fee) use_fee	from pc_history group by pc_code ;
			.append("	select pc_code, sum(use_time) use_time, sum(use_fee) use_fee	")
			.append("	from pc_history	")
			.append("	group by pc_code	") ;
			
			pstmt = conn.prepareStatement(selectCalcPCRecipt.toString()) ;
			
			// 4. bind ������ �� ����
			// ����
			
			// 5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery() ;
			CalcPCReciptVO cv = null ;
			while( rs.next() ) {
				cv = new CalcPCReciptVO(rs.getString("pc_code"), rs.getInt("use_time"), rs.getInt("use_fee")) ;
				list.add(cv) ;	// ��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
			} // end while
			
		} finally {
			// 6. ���� ����
			if ( rs != null) { rs.close(); } // end if
			if ( pstmt != null) { pstmt.close(); } // end if
			if ( conn != null) { conn.close(); } // end if
			
		} // end finally
		
		return list ;
	} // selectCalcPCRecipt
}
