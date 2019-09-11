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
		String url = "jdbc:oracle:thin:@localhost:1521:orcl" ;
		String id = "pcbang" ;
		String pass = "2zo" ;
		
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
		//select ordering.order_num, ordering.id, pc.pc_code, ordering.item_code, ordering.quantity, (item.price*ordering.quantity) price
		//from pc_history pc, ordering ordering, item item 
		//where (pc.id=ordering.id) and (ordering.item_code=item.item_code);
		.append("	select ordering.order_num order_num, ordering.id id, pc.pc_code pc_code, ordering.item_code item_code, item.name item_name, ordering.quantity quantity, (item.price*ordering.quantity) price	")
		.append("	pc_history pc, ordering ordering, item item	")
		.append("	where (pc.id=ordering.id) and (ordering.item_code=item.item_code) and item.state='Y' 	") ;
		
		pstmt = conn.prepareStatement(selectCalcItem.toString()) ;
		
		// 4. bind ������ �� ����
		// ����
		
		// 5. ���� ���� �� ��� ���
		rs = pstmt.executeQuery() ;
		CalcItemVO cv = null ;
		while( rs.next() ) {
			cv = new CalcItemVO(rs.getString("id"), rs.getString("pc_code"), rs.getString("item_code"), rs.getString("item_name"), rs.getInt("order_num"), rs.getInt("quantity"), rs.getInt("price")) ;
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
			//select item.name item_name, ordering.quantity quantity, (item.price*ordering.quantity) price, ordering.order_date order_date
			//from ordering ordering, item item ;
			selectCalcItemRecipt
			.append("	select item.name item_name, ordering.quantity quantity, (item.price*ordering.quantity) price, ordering.order_date order_date	")
			.append("	from ordering ordering, item item	") ;
			
			pstmt = conn.prepareStatement(selectCalcItemRecipt.toString()) ;
			
			// 4. bind ������ �� ����
			// ����
			
			// 5. ���� ���� �� ��� ���
			rs = pstmt.executeQuery() ;
			CalcItemReciptVO cv = null ;
			while( rs.next() ) {
				// String itemName, String sellingDate, int quantity, int price
				cv = new CalcItemReciptVO(rs.getString("itemName"), rs.getString("sellingDate"), rs.getInt("quantity"), rs.getInt("price")) ;
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
}
