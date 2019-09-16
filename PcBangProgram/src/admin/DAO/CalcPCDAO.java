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
	 * DBMS의 연결을 반환하는 일
	 * 
	 * @return 연결된 커넥션
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException {
		Connection conn = null ;
		// 1. 드라이버 로딩 (ojdbc8.jar
		try {
			Class.forName("oracle.jdbc.OracleDriver") ;
			
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} // end catch
		
		// 2. Connection 얻기
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
		
		// 2. Connection 얻기
		conn = getConnection() ;
		

		try {
		// 3. 쿼리문 생성 객체 얻기 : lunch 테이블에서 이름, 코드, 가격, 입력일을 가장 최근에 입력된 것 부터 조회
		StringBuilder selectCalcPC = new StringBuilder() ;
		selectCalcPC
		//select pc_num, pc_code, id, use_time, use_fee from pc_history
		.append("	select pc_num, pc_code, id, use_time, use_fee	")
		.append("	from pc_history	") ;
		
		pstmt = conn.prepareStatement(selectCalcPC.toString()) ;
		
		// 4. bind 변수에 값 설정
		// 없음
		
		// 5. 쿼리 수행 후 결과 얻기
		rs = pstmt.executeQuery() ;
		CalcPCVO cv = null ;
		while( rs.next() ) {
			//pc_num, pc_code, id, use_time, use_fee
			cv = new CalcPCVO(rs.getString("pc_code"),rs.getString("id"), rs.getString("use_time"), rs.getInt("pc_num"), rs.getInt("use_fee")) ;
			list.add(cv) ;	// 조회된 레코드를 저장한 VO를 list에 추가
		} // end while
		
		} finally {
		// 6. 연결 끊기
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
		
		// 2. Connection 얻기
		conn = getConnection() ;
		
		
		try {
			// 3. 쿼리문 생성 객체 얻기 
			StringBuilder selectCalcPCRecipt = new StringBuilder() ;
			selectCalcPCRecipt
			//select pc_code, sum(use_time) use_time, sum(use_fee) use_fee	from pc_history group by pc_code ;
			.append("	select pc_code, sum(use_time) use_time, sum(use_fee) use_fee	")
			.append("	from pc_history	")
			.append("	group by pc_code	") ;
			
			pstmt = conn.prepareStatement(selectCalcPCRecipt.toString()) ;
			
			// 4. bind 변수에 값 설정
			// 없음
			
			// 5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery() ;
			CalcPCReciptVO cv = null ;
			while( rs.next() ) {
				cv = new CalcPCReciptVO(rs.getString("pc_code"), rs.getInt("use_time"), rs.getInt("use_fee")) ;
				list.add(cv) ;	// 조회된 레코드를 저장한 VO를 list에 추가
			} // end while
			
		} finally {
			// 6. 연결 끊기
			if ( rs != null) { rs.close(); } // end if
			if ( pstmt != null) { pstmt.close(); } // end if
			if ( conn != null) { conn.close(); } // end if
			
		} // end finally
		
		return list ;
	} // selectCalcPCRecipt
}
