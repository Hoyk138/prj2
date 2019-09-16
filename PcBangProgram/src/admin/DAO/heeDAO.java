package admin.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.VO.ProductAddVO;
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
//		String url="jdbc:oracle:thin:@211.63.89.133:1521:orcl";
		String id="pcbang";
		String pass="ezo";
		
		con=DriverManager.getConnection(url,id,pass);
		
		return con;
	
	}//getConnection
	
	public void AddProduct(ProductAddVO paVO) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=getConnection();
			
			StringBuilder insertProduct=new StringBuilder();
			
			insertProduct
			.append(" insert into item(item_code,img,name,price,category,description,state) ")
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
	
	public List<ProductViewVO> productView() throws SQLException{
		List<ProductViewVO> list=new ArrayList<ProductViewVO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=getConnection();
			StringBuilder selectProduct=new StringBuilder();
			
			selectProduct
			.append( " select item_code,img,name,price,description,to_char(input_date,'yyyy-mm-dd') inputdate,category " )
			.append(	 " from item " );
			
			pstmt=con.prepareStatement(selectProduct.toString());
			
			rs=pstmt.executeQuery();
			ProductViewVO pvVO=null;
			while(rs.next()) {
				pvVO=new ProductViewVO(rs.getString("item_code"),rs.getString("name"),rs.getString("img"),rs.getString("description"),
						rs.getString("inputdate"),rs.getString("category"),rs.getInt("price"));
				list.add(pvVO);
			}//end while
			
		}finally {
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			if(con!=null) {con.close();}
		}//end finally
		
		return list;
	}
	
}//class
