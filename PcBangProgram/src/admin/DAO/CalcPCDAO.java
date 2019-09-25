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
//		String url = "jdbc:oracle:thin:@211.63.89.133:1521:orcl" ;
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
		// 3. 쿼리문 생성 객체 얻기
		StringBuilder selectCalcPC = new StringBuilder() ;
		selectCalcPC
		//select pc_num, pc_code, id, use_time, use_fee from pc_history
//		.append("	select pc_num, pc_code, id, use_time, use_fee	")
//		.append("	from pc_history	") ;
		.append("	select pu.pc_use_code, pc_num, id, ceil((payment_time - login_time)*24*60) use_time   ")
		.append("	from pc_use	pu, pc_payment pp   ")
		.append("	where pp.pc_use_code = pu.pc_use_code   ")
		.append("	and to_char(pu.login_time, 'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd')   ") ;
		
		pstmt = conn.prepareStatement(selectCalcPC.toString()) ;
		
		// 4. bind 변수에 값 설정
		// 없음
		
		// 5. 쿼리 수행 후 결과 얻기
		rs = pstmt.executeQuery() ;
		CalcPCVO cv = null ;
		while( rs.next() ) {
			//pc_num, pc_code, id, use_time, use_fee
			cv = new CalcPCVO(rs.getString("pc_use_code"),rs.getString("id"), rs.getInt("use_time"), rs.getInt("pc_num"), (rs.getInt("use_time"))*20) ;
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
			.append("	from pc_use	")
			.append("	group by pc_code	") 
			.append("	where to_char(login_time, 'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd')	") 
			;
			
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
	
	// 일주일 전 조회
	public List<CalcPCVO> selectCalcPC7() throws SQLException {
		List<CalcPCVO> list = new ArrayList<CalcPCVO>() ;
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		// 2. Connection 얻기
		conn = getConnection() ;
		
		
		try {
			// 3. 쿼리문 생성 객체 얻기 
			StringBuilder selectCalcPC7 = new StringBuilder() ;
			selectCalcPC7
			.append("	select pu.pc_use_code, pc_num, id, ceil((payment_time - login_time)*24*60) use_time	")
			.append("	from pc_use	pu, pc_payment pp	")
			.append("	where pp.pc_use_code = pu.pc_use_code	")
			.append("	and payment_time >= sysdate-7	") ;
			
			pstmt = conn.prepareStatement(selectCalcPC7.toString()) ;
			
			// 4. bind 변수에 값 설정
			// 없음
			
			// 5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery() ;
			CalcPCVO cv = null ;
			while( rs.next() ) {
				cv = new CalcPCVO(rs.getString("pc_use_code"),rs.getString("id"), rs.getInt("use_time"), rs.getInt("pc_num"), (rs.getInt("use_time"))*20) ;
				list.add(cv) ;	// 조회된 레코드를 저장한 VO를 list에 추가
			} // end while
			
		} finally {
			// 6. 연결 끊기
			if ( rs != null) { rs.close(); } // end if
			if ( pstmt != null) { pstmt.close(); } // end if
			if ( conn != null) { conn.close(); } // end if
			
		} // end finally
		
		return list ;
	} // selectCalcPC7
	
	// 한 달 전 조회
	public List<CalcPCVO> selectCalcPCLstMonth() throws SQLException {
		List<CalcPCVO> list = new ArrayList<CalcPCVO>() ;
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		// 2. Connection 얻기
		conn = getConnection() ;
		
		
		try {
			// 3. 쿼리문 생성 객체 얻기 
			StringBuilder selectCalcPCLstMonth = new StringBuilder() ;
			selectCalcPCLstMonth
			.append("	select pu.pc_use_code, pc_num, id, ceil((payment_time - login_time)*24*60) use_time	")
			.append("	from pc_use	pu, pc_payment pp	")
			.append("	where pp.pc_use_code = pu.pc_use_code	")
			.append("	and payment_time >= add_months(sysdate, -1)	") ;
			
			pstmt = conn.prepareStatement(selectCalcPCLstMonth.toString()) ;
			
			// 4. bind 변수에 값 설정
			// 없음
			
			// 5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery() ;
			CalcPCVO cv = null ;
			while( rs.next() ) {
				cv = new CalcPCVO(rs.getString("pc_use_code"),rs.getString("id"), rs.getInt("use_time"), rs.getInt("pc_num"), (rs.getInt("use_time"))*20) ;
				list.add(cv) ;	// 조회된 레코드를 저장한 VO를 list에 추가
			} // end while
			
		} finally {
			// 6. 연결 끊기
			if ( rs != null) { rs.close(); } // end if
			if ( pstmt != null) { pstmt.close(); } // end if
			if ( conn != null) { conn.close(); } // end if
			
		} // end finally
		
		return list ;
	} // selectCalcPCLstMonth
	
	
	// 사용자 정의 조회

	public List<CalcPCVO> selectCalcPCLstCustom(String startDate, String endDate) throws SQLException {
		List<CalcPCVO> list = new ArrayList<CalcPCVO>() ;
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		// 2. Connection 얻기
		conn = getConnection() ;
		
		
		try {
			// 3. 쿼리문 생성 객체 얻기 
			StringBuilder selectCalcPCLstCustom = new StringBuilder() ;
			selectCalcPCLstCustom
			.append("	select pu.pc_use_code, pc_num, id, ceil((payment_time - login_time)*24*60) use_time	")
			.append("	from pc_use	pu, pc_payment pp	")
			.append("	where pp.pc_use_code = pu.pc_use_code	")
			.append("		and to_date(?,'yyyy-mm-dd')<=payment_time and payment_time<= to_date(?,'yyyy-mm-dd')	") ;
			
			pstmt = conn.prepareStatement(selectCalcPCLstCustom.toString()) ;
			
			// 4. bind 변수에 값 설정
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			
			// 5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery() ;
			CalcPCVO cv = null ;
			while( rs.next() ) {
				cv = new CalcPCVO(rs.getString("pc_use_code"),rs.getString("id"), rs.getInt("use_time"), rs.getInt("pc_num"), (rs.getInt("use_time"))*20) ;
				list.add(cv) ;	// 조회된 레코드를 저장한 VO를 list에 추가
			} // end while
			
		} finally {
			// 6. 연결 끊기
			if ( rs != null) { rs.close(); } // end if
			if ( pstmt != null) { pstmt.close(); } // end if
			if ( conn != null) { conn.close(); } // end if
			
		} // end finally
		
		return list ;
	} // selectCalcPCLstCustom

	
} // class
