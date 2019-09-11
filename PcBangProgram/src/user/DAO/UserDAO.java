package user.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import prj2.user.VO.PcHistoryVO;
import prj2.user.VO.UseUserVO;
import prj2.user.VO.UserItemDetailVO;
import prj2.user.VO.UserItemVO;
import prj2.user.VO.UserOrderVO;

public class UserDAO { //singleton pattern
	private static UserDAO uDAO;
	
	private UserDAO() {
		
	}//userDAO
	
	public static UserDAO getInstance() {
		if(uDAO==null) {
			uDAO=new UserDAO();
		}//end if
		return uDAO;
	}//getInstance
	
	/**
	 * DBMS와 연동한 객체를 반환하는 일
	 * @return
	 * @throws SQLException
	 */
	private Connection getConn() throws SQLException{
		Connection con=null;
		
		//1.
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		
		//2.
		String url="jdbc:oralce:thin:@211.63.89.132:1521:orcl";
		String id="pcbang";
		String pass="ezo";
		
		con=DriverManager.getConnection(url,id,pass);
		
		return con;
	}//getConn
	
//	/**
//	 * 사용자 메인의 사용자정보를 띄우는 일
//	 * @param userId
//	 * @return
//	 * @throws SQLException
//	 */
//	public UseUserVO selectUser(String userId) throws SQLException {
//		UseUserVO uuVO=null;
//		
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		
//		try {
//		//2.
//			con=getConn();
//		//3.
//			StringBuilder selectUser=new StringBuilder();
//			selectUser
//			.append("")
//			.append("");
//		
//			pstmt=con.prepareStatement(selectUser.toString());
//		
//		//4.
//		//5.
//		}finally {
//			if(rs!=null) {rs.close();} //end if
//			if(pstmt!=null) {pstmt.close();} //end if
//			if(con!=null) {con.close();} //end if
//		}//end finally
//		
//		return uuVO;
//	}//selectUser
	
	/**
	 * 사용자의 사용기록을 PcHistory테이블에 추가하는 일
	 * @param phVO
	 * @return
	 * @throws SQLException
	 */
//	public void insertPcHistory(PcHistoryVO phVO) throws SQLException {
//		
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		
//		try {
//			con=getConn();
//		
//			StringBuilder insertPcHistory=new StringBuilder();
//			insertPcHistory
//			.append("insert into pc_history(pc_code,pc_num,start_time,use_time,use_fee,end_time,id)")
//			.append("values(seq_pcCode.nextval,?,?,?,?,sysdate,?)");
//			
//			pstmt=con.prepareStatement(insertPcHistory.toString());
//		//4.
//			pstmt.setInt(1, phVO.getPcNum());
//			pstmt.setString(2, phVO.getStartTime());
//			pstmt.setInt(3, phVO.getUseTime());
//			pstmt.setInt(4, phVO.getUseFee());
//			pstmt.setString(5, phVO.getUserId());
//		//5.
//			pstmt.executeUpdate();
//			
//		}finally {
//			if(rs!=null) {rs.close();} //end if
//			if(pstmt!=null) {pstmt.close();} //end if
//			if(con!=null) {con.close();} //end if
//		}//end finally
//		
//	}//insertPcHistory
	
	
	/**
	 * 상품 아이템을 조회하는 일
	 * @return
	 * @throws SQLException
	 */
	public List<UserItemVO> selectUserItemList(String category) throws SQLException{
		List<UserItemVO> list=new ArrayList<UserItemVO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		con=getConn();
		
		StringBuilder selectUserItemList=new StringBuilder();
		selectUserItemList
		.append("	select NAME,IMG,PRICE")
		.append("	from item")
		.append("	where state='Y' and category=?")
		.append("	order by input_date desc");
		
		pstmt=con.prepareStatement(selectUserItemList.toString());
		
		pstmt.setString(1,category);
		
		rs=pstmt.executeQuery();
		
		UserItemVO uiv=null;
		while(rs.next()) {
			uiv=new UserItemVO(rs.getString("name"), rs.getString("img"), rs.getInt("price"));
			list.add(uiv);
		}//end while
		
		}finally {
			if(rs!=null) {rs.close();} //end if
			if(pstmt!=null) {pstmt.close();} //end if
			if(con!=null) {con.close();} //end if
		}//end finally
		return list;
	}//selectUserItemList
	
	/**
	 * 상품의 상세정보를 띄우는 일
	 * @return
	 * @throws SQLException
	 */
	public UserItemDetailVO selectUserItemDetail(String itemCode) throws SQLException {
		UserItemDetailVO uidVO=null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=getConn();
			
			StringBuilder selectUserItemDetail=new StringBuilder();
			selectUserItemDetail
			.append("select CATEGORY,IMG,NAME,PRICE,DESCRIPTION")
			.append("from item")
			.append("where item_code=?");
			
			pstmt=con.prepareStatement(selectUserItemDetail.toString());
			
			pstmt.setString(1, itemCode);
			if(rs.next()) {
				uidVO=new UserItemDetailVO(rs.getString("category"), rs.getString("name"), rs.getString("img"), rs.getString("description"), rs.getInt("price"));
			}//end if
			
		}finally {
			if(rs!=null) {rs.close();} //end if
			if(pstmt!=null) {pstmt.close();} //end if
			if(con!=null) {con.close();} //end if
		}//end finally
		
		return uidVO;
	}//selectUserItemDetail
	
	/**
	 * 사용자의 주문을 주문테이블에 추가하는 일
	 * @param listUoVO
	 * @throws SQLException
	 */
//	public void insertOrder(List<UserOrderVO> listUoVO) throws SQLException {
//		
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		
//		try {
//		con=getConn();
//		
//		for(int i=0;i<listUoVO.size();i++) {
//			StringBuilder insertOrder=new StringBuilder();
//			insertOrder
//			.append("insert into ordering(order_num,ip_addr,item_code,quantity,status,order_date,id)")
//			.append("values(seq_ordering.nextval,?,?,?,'N',sysdate,?)");
//			
//			pstmt=con.prepareStatement(insertOrder.toString());
//			
//			pstmt.setString(1,listUoVO.get(i).getIpAddr());
//			pstmt.setString(2,listUoVO.get(i).getItemCode());
//			pstmt.setInt(3,listUoVO.get(i).getQuantity());
//			pstmt.setString(4,listUoVO.get(i).getId());
//		}//end for
//		
//		}finally {
//			if(rs!=null) {rs.close();} //end if
//			if(pstmt!=null) {pstmt.close();} //end if
//			if(con!=null) {con.close();} //end if
//		}//end finally
//		
//	}//insertOrder
	

}//class
