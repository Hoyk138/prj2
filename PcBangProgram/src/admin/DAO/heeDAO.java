package admin.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import admin.VO.OrderStateVO;
import admin.VO.OrderVO;
import admin.VO.OrderViewVO;
import admin.VO.ProductAddVO;
import admin.VO.ProductDeleteVO;
import admin.VO.ProductMDViewVO;
import admin.VO.ProductModifyVO;
import admin.VO.ProductRealDeleteVO;
import admin.VO.ProductViewVO;

public class heeDAO {
private static heeDAO aDAO;
	
	private heeDAO() {
		
	}//AdminDAO

	public static heeDAO getInstance() {
		
		if(aDAO==null) {
			aDAO=new heeDAO();
		}//end if
		
		return aDAO;
		
	}//getInstance
	
	/**
	 * 	DBMS의 연결을 반환하는 일.
	 * @return 연결된 Connection
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException{
		
		Connection con=null;
		//1.드라이버 로딩(ojdbc8.jar)
		//개발할때는 빌드패스, 실행할때는 클래스패스
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		//2.Connection얻기
		String url="jdbc:oracle:thin:@211.63.89.132:1521:orcl";
		String id="pcbang";
		String pass="ezo";
		
		con=DriverManager.getConnection(url,id,pass);
		
		return con;
	
	}//getConnection
	
	public int InsertProduct(ProductAddVO paVO) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		int flag=0;
		
		try {
			con=getConnection();
			
			StringBuilder insertProduct=new StringBuilder();
			
			insertProduct
			.append(" insert into item(item_code,img,name,price,category,description,display_state) ")
			.append(" values(item_code,?,?,?,?,?,'Y') ");
			
			pstmt=con.prepareStatement(insertProduct.toString());
			
			String category=null;
			if(paVO.getCategory().equals("스낵")) {
				category="S";
			}else if(paVO.getCategory().equals("식사")) {
				category="F";
			}else {
				category="D";
			}
			
			pstmt.setString(1,paVO.getImg());
			pstmt.setString(2,paVO.getName());
			pstmt.setInt(3,paVO.getPrice());
			pstmt.setString(4,category);
			pstmt.setString(5,paVO.getExplain());
			
			try {
			flag=pstmt.executeUpdate();
			}catch (java.sql.SQLDataException e) {
				JOptionPane.showMessageDialog(null,"가격은 10만원을 넘을수 없습니다.");
			}
		}finally {
			if(pstmt!=null) {pstmt.close();}
			if(con!=null) {con.close();}
		}
		
		return flag;
	}//AddProduct
	
	public List<ProductViewVO> selectProductView() throws SQLException{
		List<ProductViewVO> list=new ArrayList<ProductViewVO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=getConnection();
			StringBuilder selectProduct=new StringBuilder();
			
			selectProduct
			.append( " select item_code,img,name,price,description,to_char(input_date,'yyyy-mm-dd') inputdate,category,display_state " )
			.append(	 " from item " );
			
			pstmt=con.prepareStatement(selectProduct.toString());
			
			rs=pstmt.executeQuery();
			ProductViewVO pvVO=null;
			while(rs.next()) {
				pvVO=new ProductViewVO(rs.getString("item_code"),rs.getString("name"),rs.getString("img"),rs.getString("description"),
						rs.getString("inputdate"),rs.getString("category"),rs.getString("display_state"),rs.getInt("price"));
				list.add(pvVO);
			}//end while
			
		}finally {
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			if(con!=null) {con.close();}
		}//end finally
		
		return list;
	}//productView
	
	public void selectDetailProduct(ProductMDViewVO pmdvVO)throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			
			con=getConnection();
			
			StringBuilder selectProduct=new StringBuilder();
			
			selectProduct
			.append(" select CATEGORY, DISPLAY_STATE, ITEM_CODE  ")
			.append(" from item  ")
			.append(" where name=? ");
			
			pstmt=con.prepareStatement(selectProduct.toString());
			
			pstmt.setString(1,pmdvVO.getName());
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				pmdvVO.setCategory(rs.getString("category"));
				pmdvVO.setState(rs.getString("DISPLAY_STATE"));
				pmdvVO.setItemCode(rs.getString("ITEM_CODE"));
			}
			
		}finally {
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			if(con!=null) {con.close();}
		}
	}//selectDetailProduct
	
	public int modifyProduct(ProductModifyVO pmVO) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		int cnt=0;
		
		try {
			
			con=getConnection();
			
			StringBuilder modifyProduct=new StringBuilder();
			
			modifyProduct
			.append(" update item  ")
			.append(" set img=?,description=?,name=?,price=?  ")
			.append(" where item_code=? ");
			
			
			
			pstmt=con.prepareStatement(modifyProduct.toString());
			
			pstmt.setString(1,pmVO.getImgPath());
			pstmt.setString(2,pmVO.getExplain());
			pstmt.setString(3,pmVO.getName());
			pstmt.setInt(4,pmVO.getPrice());
			pstmt.setString(5,pmVO.getItemCode());

			try {
				cnt=pstmt.executeUpdate();
				}catch (java.sql.SQLDataException e) {
					JOptionPane.showMessageDialog(null,"가격은 10만원을 넘을수 없습니다.");
				}
		}finally {

			if(pstmt!=null) {pstmt.close();}
			if(con!=null) {con.close();}
		}
		return cnt;
		
	}//modifyProduct
	
	public int DeleteProduct(ProductDeleteVO pdVO) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		int cnt=0;
		
		try {
			
			con=getConnection();
			
			StringBuilder deleteProduct=new StringBuilder();
			
			deleteProduct
			.append(" update item ")
			.append(" set DISPLAY_STATE='N'  ")
			.append(" where ITEM_CODE=? ");
			
			
			pstmt=con.prepareStatement(deleteProduct.toString());
			
			pstmt.setString(1,pdVO.getItemCode());
			
			cnt=pstmt.executeUpdate();
		}finally {

			if(pstmt!=null) {pstmt.close();}
			if(con!=null) {con.close();}
		}
		return cnt;
	}//DeleteProduct
	
	public List<OrderViewVO> OrderView() throws SQLException{
		List<OrderViewVO> list=new ArrayList<OrderViewVO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=getConnection();
			StringBuilder selectOrder=new StringBuilder();
			
			selectOrder
			.append( " select  io.ORDER_CODE order_code,pu.PC_NUM pc_num, " )
			.append(	 " to_char(io.ORDER_DATE,'yyyy-mm-dd am hh:mi') order_date, " )
			.append(	 " pu.ID id,io.QUANTITY quantity,i.NAME name,(i.price*io.QUANTITY) totalprice, " )
			.append(	 " nvl(to_char(ip.PAYMENT_TIME,'yyyy-mm-dd am hh:mi'),'결제 대기중 입니다.') payment_time " )
			.append(	 " from  ITEM_ORDER io, ITEM_PAYMENT ip, ITEM i, PC_USE pu " )
			.append(	 " where  (io.PC_USE_CODE=pu.PC_USE_CODE) and (io.ITEM_CODE=i.ITEM_CODE) " )
			.append(	 " and(ip.ORDER_CODE(+)=io.ORDER_CODE) " )
			.append(	 " order by payment_time desc, order_date desc " );


			
			pstmt=con.prepareStatement(selectOrder.toString());
			
			rs=pstmt.executeQuery();
			OrderViewVO ovVO=null;
			while(rs.next()) {
		
				ovVO=new OrderViewVO(rs.getString("order_code"),rs.getString("order_date"),
						rs.getString("id"),rs.getString("name"),rs.getString("payment_time"),rs.getInt("pc_num")
						,rs.getInt("quantity"),rs.getInt("totalprice"));
				list.add(ovVO);
			}//end while
			
		}finally {
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			if(con!=null) {con.close();}
		}//end finally
		
		return list;
	}//OrderView
	
	public int orderState(String orderCode,admin.view.OrderView ov) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		int cnt=0;
		
		try {
			con=getConnection();
			StringBuilder selectpayment=new StringBuilder();
			selectpayment
			.append(" select nvl(to_char(ip.PAYMENT_TIME,'yyyy-mm-dd am hh:mi'),'nopay') payment_time,io.order_code order_code ")			
			.append(" from ITEM_ORDER io, ITEM_PAYMENT ip ")
			.append(" where  (ip.ORDER_CODE(+)=io.ORDER_CODE) and (io.ORDER_CODE=?) ");
			
			
			pstmt=con.prepareStatement(selectpayment.toString());
			pstmt.setString(1,orderCode);
			
			rs=pstmt.executeQuery();
			OrderStateVO osVO=null;
			if(rs.next()) {
				osVO=new OrderStateVO(rs.getString("order_code"),rs.getString("payment_time"));
			}//if end
			
			
			if(!rs.getString("payment_time").equals("nopay")) {
				JOptionPane.showMessageDialog(ov,"결제된 상품입니다.");
			}else {
			con.close();
			rs.close();
			con=getConnection();
			pstmt.close();
			
			StringBuilder payment_state=new StringBuilder();
			payment_state
			.append(" insert into item_payment(ORDER_CODE) ")
			.append(" values(?) ");		
			
			pstmt=con.prepareStatement(payment_state.toString());
			
			OrderVO oVO=new OrderVO(osVO.getOrderCode());
			
			pstmt.setString(1,oVO.getOrderCode());
			
			cnt=pstmt.executeUpdate();
			}//end if
		}finally {
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			if(con!=null) {con.close();}
		}
		return cnt;
	}//orderState
	
	public int revive(ProductDeleteVO pdVO) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		int cnt=0;
		
		try {
			
			con=getConnection();
			
			StringBuilder reviveProduct=new StringBuilder();
			
			reviveProduct
			.append(" update item ")
			.append(" set DISPLAY_STATE='Y' ")
			.append(" where ITEM_CODE=? ");
			
			
			pstmt=con.prepareStatement(reviveProduct.toString());
			
			pstmt.setString(1,pdVO.getItemCode());
			
			cnt=pstmt.executeUpdate();
		}finally {

			if(pstmt!=null) {pstmt.close();}
			if(con!=null) {con.close();}
		}
		return cnt;
	}//DeleteProduct
	
	public int RealDelete(ProductRealDeleteVO prdVO) throws SQLException {
		int cnt=0;
		String itemCode=prdVO.getItemCode();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			
			con=getConnection();
			
			StringBuilder RealDeleteProduct=new StringBuilder();
			
			RealDeleteProduct
			.append(" delete from item ")
			.append(" where item_code=? ");
			
			pstmt=con.prepareStatement(RealDeleteProduct.toString());
			
			pstmt.setString(1,itemCode);
			
			cnt=pstmt.executeUpdate();
		}finally {

			if(pstmt!=null) {pstmt.close();}
			if(con!=null) {con.close();}
		}
		
		return cnt;
	}//RealDelete
	
}//class





