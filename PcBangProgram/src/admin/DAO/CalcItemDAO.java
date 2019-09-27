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
//		String url = "jdbc:oracle:thin:@localhost:1521:orcl" ;
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
		
		// 2. Connection ���
		conn = getConnection() ;
		

		try {
		// 3. ������ ���� ��ü ��� : pcbang���̺��� 
		StringBuilder selectCalcItem = new StringBuilder() ;

		selectCalcItem
		.append("	select io.order_code, pc_num, id, name, quantity, (price*quantity) total_price   ")
		.append("	from pc_use pu, item item, item_order io, item_payment ip   ")
		.append("	where (io.pc_use_code=pu.pc_use_code) and (io.item_code=item.item_code) and (ip.order_code = io.order_code) 	")
		.append("	      and to_char(ip.payment_time, 'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd') 	")
		.append("	      and payment_time is not null 	") ;
		
		
		pstmt = conn.prepareStatement(selectCalcItem.toString()) ;
		
		// 4. bind ������ �� ����
		// ����
		
		// 5. ���� ���� �� ��� ���
		rs = pstmt.executeQuery() ;
		CalcItemVO cv = null ;
		while( rs.next() ) {
			cv = new CalcItemVO(rs.getString("order_code"), rs.getString("id"), rs.getString("name"), rs.getInt("pc_num"), rs.getInt("quantity"), rs.getInt("total_price")) ;
			list.add(cv) ;	// ��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
		} // end while
		
		} finally {
		// 6. ���� ����
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
		
		// 2. Connection ���
		conn = getConnection() ;
		
		
		try {
			// 3. ������ ���� ��ü ��� : 
			StringBuilder selectCalcItemRecipt = new StringBuilder() ;
			selectCalcItemRecipt
			.append("	select i.name item_name, io.quantity quantity, (i.price*io.quantity) orderedPrice, i.price price, io.order_date order_date, payment_time	")
			.append("	from item_payment ip, item_order io, item i	") 
			.append("	where (ip.order_code=io.order_code)and(io.item_code=i.item_code)	")			
			.append("	      and to_char(ip.payment_time, 'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd')	")		
			.append("	      and payment_time is not null   ");		
			pstmt = conn.prepareStatement(selectCalcItemRecipt.toString()) ;
			
			// 4. bind ������ �� ����
			// ����
			
			// 5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery() ;
			CalcItemReciptVO cv = null ;
			while( rs.next() ) {
				cv = new CalcItemReciptVO(rs.getString("item_name"), rs.getString("payment_time"), rs.getInt("quantity"), rs.getInt("orderedPrice"), rs.getInt("price")) ;
				list.add(cv) ;	// ��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
			} // end while
			
		} finally {
			// 6. ���� ����
			if ( rs != null) { rs.close(); } // end if
			if ( pstmt != null) { pstmt.close(); } // end if
			if ( conn != null) { conn.close(); } // end if
			
		} // end finally
		
		return list ;
	} // selectCalcItemRecipt
	

	public List<CalcItemVO> selectCalcItem7() throws SQLException {
		List<CalcItemVO> list = new ArrayList<CalcItemVO>() ;
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		// 2. Connection ���
		conn = getConnection() ;
		

		try {
		// 3. ������ ���� ��ü ��� 
		StringBuilder selectCalcItem = new StringBuilder() ;
		selectCalcItem
		.append("	select io.order_code, pc_num, id, name, quantity, (price*quantity) total_price   ")
		.append("	from pc_use pu, item item, item_order io, item_payment ip   ")
		.append("	where (io.pc_use_code=pu.pc_use_code) and (io.item_code=item.item_code) and (ip.order_code = io.order_code) 	")
		.append("	      and ip.payment_time >= sysdate-7 	")
		.append("	      and payment_time is not null 	") ;
		
		
		pstmt = conn.prepareStatement(selectCalcItem.toString()) ;
		
		// 4. bind ������ �� ����
		// ����
		
		// 5. ���� ���� �� ��� ���
		rs = pstmt.executeQuery() ;
		CalcItemVO cv = null ;
		while( rs.next() ) {
			cv = new CalcItemVO(rs.getString("order_code"), rs.getString("id"), rs.getString("name"), rs.getInt("pc_num"), rs.getInt("quantity"), rs.getInt("total_price")) ;
			list.add(cv) ;	// ��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
		} // end while
		
		} finally {
		// 6. ���� ����
			if ( rs != null) { rs.close(); } // end if
			if ( pstmt != null) { pstmt.close(); } // end if
			if ( conn != null) { conn.close(); } // end if
			
		} // end finally
				
		return list ;
	} // selectCalcItem
	

	public List<CalcItemVO> selectCalcItemLstMonth() throws SQLException {
		List<CalcItemVO> list = new ArrayList<CalcItemVO>() ;
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		// 2. Connection ���
		conn = getConnection() ;
		

		try {
		// 3. ������ ���� ��ü ���
		StringBuilder selectCalcItem = new StringBuilder() ;
		selectCalcItem
		.append("	select io.order_code, pc_num, id, name, quantity, (price*quantity) total_price   ")
		.append("	from pc_use pu, item item, item_order io, item_payment ip   ")
		.append("	where (io.pc_use_code=pu.pc_use_code) and (io.item_code=item.item_code) and (ip.order_code = io.order_code) 	")
		.append("			and ip.payment_time >= add_months(sysdate, -1) 	") 
		.append("			and payment_time is not null 	") ;
		
		
		pstmt = conn.prepareStatement(selectCalcItem.toString()) ;
		
		// 4. bind ������ �� ����
		// ����
		
		// 5. ���� ���� �� ��� ���
		rs = pstmt.executeQuery() ;
		CalcItemVO cv = null ;
		while( rs.next() ) {
			cv = new CalcItemVO(rs.getString("order_code"), rs.getString("id"), rs.getString("name"), rs.getInt("pc_num"), rs.getInt("quantity"), rs.getInt("total_price")) ;
			list.add(cv) ;	// ��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
		} // end while
		
		} finally {
		// 6. ���� ����
			if ( rs != null) { rs.close(); } // end if
			if ( pstmt != null) { pstmt.close(); } // end if
			if ( conn != null) { conn.close(); } // end if
			
		} // end finally
				
		return list ;
	} // selectCalcItem
	
	
	public List<CalcItemVO> selectCalcItemLstCustom(String startDate, String endDate) throws SQLException {
		List<CalcItemVO> list = new ArrayList<CalcItemVO>() ;
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		// 2. Connection ���
		conn = getConnection() ;
		
		try {
			// 3. ������ ���� ��ü ��� 
			StringBuilder selectCalcItemLstCustom = new StringBuilder() ;
			selectCalcItemLstCustom
			.append("	select io.order_code, pc_num, id, name, quantity, (price*quantity) total_price   ")
			.append("	from pc_use pu, item item, item_order io, item_payment ip   ")
			.append("	where (io.pc_use_code=pu.pc_use_code) and (io.item_code=item.item_code) and (ip.order_code = io.order_code) 	")
			.append("			and to_date(?,'yyyy-mm-dd')<=payment_time and payment_time<= to_date(?,'yyyy-mm-dd') 	") 
			.append("			and payment_time is not null 	") ;
			
			pstmt = conn.prepareStatement(selectCalcItemLstCustom.toString()) ;
			
			// 4. bind ������ �� ����
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			
			// 5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery() ;
			CalcItemVO ciVO = null ;
			while( rs.next() ) {
				ciVO = new CalcItemVO(rs.getString("order_code"), rs.getString("id"), rs.getString("name"), rs.getInt("pc_num"), rs.getInt("quantity"), rs.getInt("total_price")) ;
				list.add(ciVO) ;	// ��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
			} // end while
			
		} finally {
			// 6. ���� ����
			if ( rs != null) { rs.close(); } // end if
			if ( pstmt != null) { pstmt.close(); } // end if
			if ( conn != null) { conn.close(); } // end if
			
		} // end finally
		
		return list ;
	} // selectCalcPCLstCustom
	
	
}
