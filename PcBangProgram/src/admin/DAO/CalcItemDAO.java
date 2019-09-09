package admin.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import kr.co.sist.admin.vo.CalcVO;

public class CalcItemDAO {
	
	
	private static CalcItemDAO itemDAO ;
	
	private CalcItemDAO() {
		
		
	} // CalcPCDAO
	
	
//	public List<CalcVO> selectCalc() throws SQLException {
//		List<CalcVO> list = new ArrayList<CalcVO>() ;
//		
//		Connection conn = null ;
//		PreparedStatement pstmt = null ;
//		ResultSet rs = null ;
//		
//		// 2. Connection 얻기
//		conn = getConnection() ;
//		
//
//		try {
//		// 3. 쿼리문 생성 객체 얻기 : lunch 테이블에서 이름, 코드, 가격, 입력일을 가장 최근에 입력된 것 부터 조회
//		StringBuilder selectCalc = new StringBuilder() ;
//		selectCalc
//		.append("	select l.name, l.lunch_code, sum(o.quantity) quantity, sum(l.price*o.quantity) total_price	")
//		.append("	from ordering o, lunch l	")
//		.append("	where (o.lunch_code=l.lunch_code) and o.status='Y'	")
//		.append("	group by l.name, l.lunch_code	") ;
//		
//		pstmt = conn.prepareStatement(selectCalc.toString()) ;
//		
//		// 4. bind 변수에 값 설정
//		// 없음
//		
//		// 5. 쿼리 수행 후 결과 얻기
//		rs = pstmt.executeQuery() ;
//		CalcVO cv = null ;
//		while( rs.next() ) {
//			cv = new CalcVO(rs.getString("name"), rs.getString("lunch_code"), rs.getInt("quantity"), rs.getInt("total_price")) ;
//			list.add(cv) ;	// 조회된 레코드를 저장한 VO를 list에 추가
//		} // end while
//		
//		} finally {
//		// 6. 연결 끊기
//			if ( rs != null) { rs.close(); } // end if
//			if ( pstmt != null) { pstmt.close(); } // end if
//			if ( conn != null) { conn.close(); } // end if
//			
//		} // end finally
//				
//		return list ;
//	} // selectCalc
}
