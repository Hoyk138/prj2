package admin.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.VO.ProductAddVO;
import admin.VO.ProductDeleteVO;
import admin.VO.ProductMDViewVO;
import admin.VO.ProductModifyVO;
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
	
	public void InsertProduct(ProductAddVO paVO) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		
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
			
			pstmt.executeUpdate();
		}finally {
			if(pstmt!=null) {pstmt.close();}
			if(con!=null) {con.close();}
		}
		
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
			
			cnt=pstmt.executeUpdate();
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
	
}//class





