package admin.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.DAO.CalcItemDAO;
import admin.VO.CalcItemReciptVO;
import admin.VO.CalcItemVO;

//import kr.co.sist.admin.vo.CalcVO;

public class CalcItemDAO {
	
	
	private static CalcItemDAO itemDAO ;
	
	private CalcItemDAO() {
		
		
	} // CalcPCDAO
	
	public static CalcItemDAO getInstance() {
		if (itemDAO == null) {
			itemDAO = new CalcItemDAO() ;
		} // end if
	return itemDAO ;
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
	
	
	public List<CalcItemVO> selectCalcItem() throws SQLException {
		List<CalcItemVO> list = new ArrayList<CalcItemVO>() ;
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		// 2. Connection 얻기
		conn = getConnection() ;
		

		try {
		// 3. 쿼리문 생성 객체 얻기 : pcbang테이블에서 
		StringBuilder selectCalcItem = new StringBuilder() ;
//		select o.order_num order_num, h.pc_code pc_code, h.id id, i.name item_name, o.quantity quantity, (o.quantity*i.price) price
//		from item i, ordering o, pc_history h
//		where (i.item_code=o.item_code) and (o.id=h.id) and o.status='Y' and to_char(o.order_date, 'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd')
		selectCalcItem
//		.append("	select o.order_num order_num, h.pc_code pc_code, h.id id, i.name item_name, o.quantity quantity, (o.quantity*i.price) price	")
//		.append("	from item i, ordering o, pc_history h	")
//		.append("	where (i.item_code=o.item_code) and (o.id=h.id) and o.status='Y' and to_char(o.order_date, 'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd') 	") ;
		
		.append("	select io.order_code, pc_num, id, name, quantity, (price*quantity) total_price   ")
		.append("	from pc_use pu, item item, item_order io, item_payment ip   ")
		.append("	where (io.pc_use_code=pu.pc_use_code) and (io.item_code=item.item_code) and (ip.order_code = io.order_code) 	")
		.append("	      and to_char(io.order_date, 'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd') 	")
		.append("	      and payment_time is not null 	") ;
		
		
		pstmt = conn.prepareStatement(selectCalcItem.toString()) ;
		
		// 4. bind 변수에 값 설정
		// 없음
		
		// 5. 쿼리 수행 후 결과 얻기
		rs = pstmt.executeQuery() ;
		CalcItemVO cv = null ;
		while( rs.next() ) {
			//String itemName, String sellingDate,   e
//			cv = new CalcItemVO(rs.getString("id"), rs.getString("pc_code"), rs.getString("item_name"), rs.getInt("order_num"), rs.getInt("quantity"), rs.getInt("price")) ;
			cv = new CalcItemVO(rs.getString("order_code"), rs.getString("id"), rs.getString("name"), rs.getInt("pc_num"), rs.getInt("quantity"), rs.getInt("total_price")) ;
			list.add(cv) ;	// 조회된 레코드를 저장한 VO를 list에 추가
		} // end while
		
		} finally {
		// 6. 연결 끊기
			if ( rs != null) { rs.close(); } // end if
			if ( pstmt != null) { pstmt.close(); } // end if
			if ( conn != null) { conn.close(); } // end if
			
		} // end finally
				
		return list ;
	} // selectCalcItem
	
	public List<CalcItemReciptVO> selectCalcItemRecipt() throws SQLException {
		List<CalcItemReciptVO> list = new ArrayList<CalcItemReciptVO>() ;
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		// 2. Connection 얻기
		conn = getConnection() ;
		
		
		try {
			// 3. 쿼리문 생성 객체 얻기 : 
			StringBuilder selectCalcItemRecipt = new StringBuilder() ;
			selectCalcItemRecipt
//			select i.name item_name, o.quantity quantity, (o.quantity*i.price) price
//			from ordering o, item i
//			where (i.item_code=o.item_code) and o.status='Y' and to_char(o.order_date, 'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd')
//			.append("	select i.name item_name, o.quantity quantity, (o.quantity*i.price) price	")
//			.append("	from ordering o, item i	")
//			.append("	where (i.item_code=o.item_code) and o.status='Y' and to_char(o.order_date, 'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd')	");
			
			.append("	select i.name item_name, io.quantity quantity, (i.price*io.quantity) price, io.order_date order_date, payment_time	")
			.append("	from item_payment ip, item_order io, item i	") 
			.append("	where (ip.order_code=io.order_code)and(io.item_code=i.item_code)	")			
			.append("	      and to_char(io.order_date, 'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd')	")		
			.append("	      and payment_time is not null   ");		
			pstmt = conn.prepareStatement(selectCalcItemRecipt.toString()) ;
			
			// 4. bind 변수에 값 설정
			// 없음
			
			// 5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery() ;
			CalcItemReciptVO cv = null ;
			while( rs.next() ) {
				//  String useTime, int pcNum, int usePrice
//				cv = new CalcItemReciptVO(rs.getString("itemName"), rs.getInt("quantity"), rs.getInt("price")) ;
				cv = new CalcItemReciptVO(rs.getString("item_name"), rs.getString("payment_time"), rs.getInt("quantity"), rs.getInt("price")) ;
				list.add(cv) ;	// 조회된 레코드를 저장한 VO를 list에 추가
			} // end while
			
		} finally {
			// 6. 연결 끊기
			if ( rs != null) { rs.close(); } // end if
			if ( pstmt != null) { pstmt.close(); } // end if
			if ( conn != null) { conn.close(); } // end if
			
		} // end finally
		
		return list ;
	} // selectCalcItemRecipt
}
