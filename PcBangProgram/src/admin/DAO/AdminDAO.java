package admin.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import admin.VO.AdminLoginVO;
import admin.VO.CalcItemReciptVO;
import admin.VO.CalcItemVO;
import admin.VO.CalcPCReciptVO;
import admin.VO.CalcPCVO;
import admin.VO.MemberVO;
import admin.VO.OrderStateVO;
import admin.VO.OrderVO;
import admin.VO.OrderViewVO;
import admin.VO.OrderedItemAndQuantityVO;
import admin.VO.PCVO;
import admin.VO.ProductAddVO;
import admin.VO.ProductDeleteVO;
import admin.VO.ProductMDViewVO;
import admin.VO.ProductModifyVO;
import admin.VO.ProductRealDeleteVO;
import admin.VO.ProductViewVO;
import admin.VO.UsePCVO;

public class AdminDAO {
	
	private static AdminDAO aDAO;
	
	private static final int ID = 1;
	private static final int NAME = 2;
	private static final int PHONE = 3;
	private static final int JOIN_DATE = 4;
	
	private AdminDAO() {
		
	}//AdminDAO
	
	public static AdminDAO getInstance() {
		if(aDAO==null) {
			aDAO=new AdminDAO();
		}//end if
		return aDAO;
	}//getInstance
	
	/**
	 * DBMS의 연결을 반환하는 일.
	 * @return 연결된 커넥션
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException { 
		Connection con=null;
		//1. 드라이버 로딩 
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		//2. Connection 얻기
		String url="jdbc:oracle:thin:@211.63.89.132:1521:orcl";
//		String url="jdbc:oracle:thin:@192.168.1.16:1521:orcl";
//		String url="jdbc:oracle:thin:@211.63.89.132:1521:orcl";
//		String url="jdbc:oracle:thin:@211.63.89.133:1521:orcl";
		String id="pcbang";
		String pass="ezo";
		
		con=DriverManager.getConnection(url, id, pass);
		
		return con;
	}//getConnection
	
	
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 아이디와 비밀번호를 입력받아 admin_login 테이블에서 아이디와 비밀번호가 맞는다면 이름을 조회하는 일.
	 * 
	 * @param lv 아이디와 비번을 가진 VO
	 * @return 성공 - 이름, 실패 - empty
	 * @throws SQLException
	 */
	public String selectLogin(AdminLoginVO lv) throws SQLException { //boolean -> String
		String adminName="";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		//2. 커넥션 얻기
			con=getConnection();
		//3. 쿼리문 생성객체 얻기
			StringBuilder selectName=new StringBuilder();
			selectName
			.append("		select name		")
			.append("		from admin_account	")
			.append("		where id=? and pass=?	");
			
			pstmt=con.prepareStatement(selectName.toString());
			
		//4. 바인드 변수에 값 넣기
			pstmt.setString(1, lv.getId());
			pstmt.setString(2, lv.getPass());
		//5. 쿼리 실행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				adminName=rs.getString("name");
			}//end if
			
		}finally {
		//6. 연결끊기
			if ( rs != null ) { rs.close(); } //end if
			if ( pstmt != null ) { pstmt.close(); } //end if
			if ( con != null ) { con.close(); } //end if
		}//end finally
		return adminName;
	}//selectLogin
	
	public boolean selectPCip(int pcNum) throws SQLException {
		boolean register_flag = false;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		//2. 커넥션 얻기
			con=getConnection();
		//3. 쿼리문 생성객체 얻기
			StringBuilder selectPCip = new StringBuilder();
			selectPCip
			.append("   select ip_address   ")
			.append("   from pc   ")
			.append("   where pc_num = ?   ");
			
			pstmt=con.prepareStatement(selectPCip.toString());
			
		//4. 바인드 변수에 값 넣기
			pstmt.setInt(1, pcNum);
		//5. 쿼리 실행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				if (rs.getString("ip_address") != null) {
					register_flag = true;
				}//end if
			}//end if
			
		}finally {
		//6. 연결끊기
			if ( rs != null ) { rs.close(); } //end if
			if ( pstmt != null ) { pstmt.close(); } //end if
			if ( con != null ) { con.close(); } //end if
		}//end finally
		return register_flag;
	}//selectPCip
	
	public int selectPCNum(String pcIP) throws SQLException {
		int pcNum = 0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		//2. 커넥션 얻기
			con=getConnection();
		//3. 쿼리문 생성객체 얻기
			StringBuilder selectPCNum = new StringBuilder();
			selectPCNum
			.append("   select pc_num   ")
			.append("   from pc   ")
			.append("   where ip_address = ?   ");

			pstmt=con.prepareStatement(selectPCNum.toString());
			
		//4. 바인드 변수에 값 넣기
			pstmt.setString(1, pcIP);
		//5. 쿼리 실행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				pcNum = rs.getInt("pc_num");
			}//end if
			
		}finally {
		//6. 연결끊기
			if ( rs != null ) { rs.close(); } //end if
			if ( pstmt != null ) { pstmt.close(); } //end if
			if ( con != null ) { con.close(); } //end if
		}//end finally
		return pcNum;
	}//selectPCNum
	
	public List<PCVO> selectAllPC() throws SQLException {
		List<PCVO> list = new ArrayList<PCVO>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		//2. 커넥션 얻기
			con=getConnection();
		//3. 쿼리문 생성객체 얻기
			StringBuilder selectAllPC = new StringBuilder();
			selectAllPC
			.append("   select pc_num, ip_address, to_char(input_date,'yyyy\"년\" mm\"월\" dd\"일\" hh24:mi') input_date   ")
			.append("   from pc   ")
			.append("   order by pc_num asc   ");

			pstmt=con.prepareStatement(selectAllPC.toString());
		//4. 바인드 변수에 값 넣기
			
		//5. 쿼리 실행 후 결과 얻기
			rs=pstmt.executeQuery();
			PCVO pcVO = null;
			while (rs.next()) {
				//ip_address, input_date, pc_num
				pcVO = new PCVO(
						rs.getString("ip_address"), 
						rs.getString("input_date"), 
						rs.getInt("pc_num")
						);
				list.add(pcVO);//조회된 레코드를 저장한 VO를 list에 추가
			}//end while
		}finally {
		//6. 연결끊기
			if ( rs != null ) { rs.close(); } //end if
			if ( pstmt != null ) { pstmt.close(); } //end if
			if ( con != null ) { con.close(); } //end if
		}//end finally
		return list;
	}//selectAllPC
	
	public boolean updatePCIP(int pcNum, String inputIP, String msg) throws SQLException{
		boolean updateFlag = false;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
		//2. 커넥션 얻기
			con = getConnection();
		//3. 쿼리문 생성 객체 얻기
			StringBuilder updateLunch = new StringBuilder();
			//PC_NUM	IP_ADDRESS	INPUT_DATE	
			if (msg.equals("등록")||msg.equals("수정")) {
				updateLunch
				.append("   update pc   ")
				.append("   set    IP_ADDRESS = ?, input_date = sysdate   ")
				.append("   where  PC_NUM = ?   ");
			}//end if
			if (msg.equals("삭제")) {
				updateLunch
				.append("   update pc   ")
				.append("   set    IP_ADDRESS = ?, input_date = null   ")
				.append("   where  PC_NUM = ?   ");
			}//end if
			
			pstmt = con.prepareStatement(updateLunch.toString());
		//4. 바인드 변수 설정
			pstmt.setString(1, inputIP);
			pstmt.setInt(2, pcNum);
		//5. 쿼리 수행 후 값 얻기
			updateFlag = (pstmt.executeUpdate()==1);
		} finally {
		//6. 연결 끊기
			if(pstmt != null) { pstmt.close(); }//end if
			if(con != null) { con.close(); }//end if
		}//end finally
		return updateFlag;
	}//updatePC
	
//	public List<MemberVO> selectAllPC() throws SQLException{
//		List<MemberVO> list = new ArrayList<MemberVO>();
//		
//		Connection con = null;
//		PreparedStatement pstmt =null;
//		ResultSet rs = null;
//		
//		try {
//			
//		//2. Connection 얻기
//		con = getConnection();
//		//3. 쿼리문 생성객체 얻기: 
//		//{"회원번호","ID","이름","전화번호","가입일"}
//		//ID	PASS	NAME	PHONE	QUESTION_VERIFY	ANSWER_VERIFY	JOIN_DATE	
//		StringBuilder selectAllMember = new StringBuilder();
//		selectAllMember
//		.append("   select   id, pass, name, phone, to_char(join_date,'yyyy-mm-dd hh24:mi') join_date   ")
//		.append("   from     member_account   ");
//		
//		pstmt = con.prepareStatement(selectAllMember.toString());
//		//4. 바인드 변수 값 넣기
//		//5. 쿼리문 실행 후 값 얻기
//		rs = pstmt.executeQuery();
//		MemberVO lVO = null;
//		while (rs.next()) {
//			//(String id, String name, String phone, String join_date)
//			lVO = new MemberVO(
//					rs.getString("id"), 
//					rs.getString("name"), 
//					rs.getString("phone"), 
//					rs.getString("join_date") 
//					);
//			list.add(lVO);//조회된 레코드를 저장한 VO를 list에 추가
//		}//end while
//		} finally {
//		//6. 연결 끊기
//			if(rs != null) { rs.close(); }//end if
//			if(pstmt != null) { pstmt.close(); }//end if
//			if(con != null) { con.close(); }//end if
//		}//end finally
//		return list;
//	}//selectAllPC
	
	/**
	 * DBMS테이블에 존재하는 모든 회원 목록을 조회
	 * @return 회원 목록
	 * @throws SQLException
	 */
//	public List<MemberVO> selectAllMember() throws SQLException{
	public List<MemberVO> selectMember(int selectCondition, String selectWord) throws SQLException{
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		try {
			
		//2. Connection 얻기
		con = getConnection();
		//3. 쿼리문 생성객체 얻기: 
		//{"회원번호","ID","이름","전화번호","가입일"}
		//ID	PASS	NAME	PHONE	QUESTION_VERIFY	ANSWER_VERIFY	JOIN_DATE	
		StringBuilder selectMember = new StringBuilder();
//		selectAllMember
//		.append("   select   id, name, phone, to_char(join_date,'yyyy-mm-dd hh24:mi') join_date   ")
//		.append("   from     member_account   ");
//		if (selectCondition == -1) {
//			selectMember
//			.append("   select ma.id, ma.name, phone, to_char(join_date,'yyyy-mm-dd hh24:mi') join_date, nvl(sum(pp.payment_time - login_time)*24*60,0) pc_use_time,nvl(sum(price*quantity),0) item_pay_sum   ")
//			.append("   from   item i, item_order io, item_payment ip, pc_payment pp, pc_use pu, member_account ma   ")
//			.append("   where  (pu.id(+)= ma.id and pp.pc_use_code(+)=pu.pc_use_code and io.pc_use_code(+)=pu.pc_use_code and io.item_code=i.item_code(+) and ip.order_code=io.order_code(+))   ")
//			.append("   group by ma.id, ma.name, phone, join_date   ");
//		}//end if
		selectMember
		.append("   select ma.id, ma.name, phone, to_char(join_date,'yyyy\"년\" mm\"월\" dd\"일\" hh24:mi') join_date, nvl(sum(pp.payment_time - login_time)*24*60,0) pc_use_time,nvl(sum(price*quantity),0) item_pay_sum   ")
		.append("   from   item i, item_order io, item_payment ip, pc_payment pp, pc_use pu, member_account ma   ")
		.append("   where  (pu.id(+)= ma.id and pp.pc_use_code(+)=pu.pc_use_code and io.pc_use_code(+)=pu.pc_use_code and io.item_code=i.item_code(+) and ip.order_code=io.order_code(+))   ");
		switch (selectCondition) {
		    case ID: selectMember.append("   and ma.id like '%" + selectWord + "%'   "); break;
		    case NAME: selectMember.append("   and ma.name like '%" + selectWord + "%'   "); break;
		    case PHONE: selectMember.append("   and phone like '%" + selectWord.replace("-", "") + "%'   "); break;
		    case JOIN_DATE: selectMember.append("   and to_char(join_date,'yyyy\"년\" mm\"월\" dd\"일\" hh24:mi') like '%" + selectWord + "%'   "); break;
		}//switch case
		selectMember.append("   group by ma.id, ma.name, phone, join_date   ");
		
		pstmt = con.prepareStatement(selectMember.toString());
		//4. 바인드 변수 값 넣기
		//5. 쿼리문 실행 후 값 얻기
		rs = pstmt.executeQuery();
		MemberVO mVO = null;
		while (rs.next()) {
			//id, name, phone, join_date, pc_use_time, item_pay_sum
			mVO = new MemberVO(
					rs.getString("id"), 
					rs.getString("name"), 
					rs.getString("phone"), 
					rs.getString("join_date"), 
					rs.getInt("pc_use_time"), 
					rs.getInt("item_pay_sum")
					);
			list.add(mVO);//조회된 레코드를 저장한 VO를 list에 추가
		}//end while
		} finally {
		//6. 연결 끊기
			if(rs != null) { rs.close(); }//end if
			if(pstmt != null) { pstmt.close(); }//end if
			if(con != null) { con.close(); }//end if
		}//end finally
		return list;
	}//selectAllMember
	
	/**
	 * PC방 사용자가 방금 주문한 목록 조회
	 * @return 회원 목록
	 * @throws SQLException
	 */
	public List<OrderedItemAndQuantityVO> selectJustOrder(UsePCVO upcVO) throws SQLException{
		List<OrderedItemAndQuantityVO> list = new ArrayList<OrderedItemAndQuantityVO>();
		
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		try {
			
		//2. Connection 얻기
		con = getConnection();
		//3. 쿼리문 생성객체 얻기 
		StringBuilder selectPCUseCode = new StringBuilder();
//		select pc_use_code
//      from (select pu.pc_use_code, pc_num, id, login_time, payment_time
//            from pc_use pu, pc_payment pp
//            where (pp.pc_use_code(+) = pu.pc_use_code)
//                   and pc_num = 24 and id = 'kgg'
//                   and payment_time is null
//            order by login_time desc)
//      where rownum = 1
		selectPCUseCode
		.append("   select pc_use_code   ")
		.append("   from (select pu.pc_use_code, pc_num, id, login_time, payment_time   ")
		.append("         from pc_use pu, pc_payment pp   ")
		.append("         where (pp.pc_use_code(+) = pu.pc_use_code)   ")
		.append("                and pc_num = ? and id = ?   ")
		.append("                and payment_time is null   ")
		.append("         order by login_time desc)   ")
		.append("   where rownum = 1   ");
		
		pstmt = con.prepareStatement(selectPCUseCode.toString());
		//4. 바인드 변수 값 넣기
		pstmt.setInt(1, upcVO.getPcNum());
		pstmt.setString(2, upcVO.getUserID());
		
		//5. 쿼리문 실행 후 값 얻기
		rs = pstmt.executeQuery();
		String pcUseCode = null;
		if(rs.next()) {
			pcUseCode = rs.getString("pc_use_code");
		}//end if
		
		rs.close();
		pstmt.close();
		
		//3. 쿼리문 생성객체 얻기: 
		StringBuilder selectJustItemAndQuantity = new StringBuilder();
//		select name, quantity
//		from item_order io, item i
//		where (io.item_code = i.item_code)
//		      and order_date =(select order_date
//		                       from (select order_date
//		                             from item_order
//		                             where pc_use_code = ?
//		                             order by order_date desc)
//		                       where rownum = 1)
//		      and pc_use_code = ?
		selectJustItemAndQuantity
		.append("   select category, name, quantity   ")
		.append("   from item_order io, item i   ")
		.append("   where (io.item_code = i.item_code)   ")
		.append("         and order_date =(select order_date   ")
		.append("                          from (select order_date   ")
		.append("                                from item_order   ")
		.append("                                where pc_use_code = ?   ")
		.append("                                order by order_date desc)   ")
		.append("                          where rownum = 1)   ")
		.append("        and pc_use_code = ?   ");
		
		pstmt = con.prepareStatement(selectJustItemAndQuantity.toString());
		//4. 바인드 변수 값 넣기
		pstmt.setString(1, pcUseCode);
		pstmt.setString(2, pcUseCode);
		
		//5. 쿼리문 실행 후 값 얻기
		rs = pstmt.executeQuery();
		OrderedItemAndQuantityVO oiaqVO  = null;
		while (rs.next()) {
			oiaqVO = new OrderedItemAndQuantityVO(
					rs.getString("category"), 
					rs.getString("name"), 
					rs.getInt("quantity")
					);
			list.add(oiaqVO);//조회된 레코드를 저장한 VO를 list에 추가
		}//end while
		} finally {
		//6. 연결 끊기
			if(rs != null) { rs.close(); }//end if
			if(pstmt != null) { pstmt.close(); }//end if
			if(con != null) { con.close(); }//end if
		}//end finally
		return list;
	}//selectJustOrder
	
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
	
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
			}catch(java.sql.SQLIntegrityConstraintViolationException e) {
				JOptionPane.showMessageDialog(null,"사진을 넣어주세요");
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
	
/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////	
	
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
		.append("	select pu.pc_use_code, pc_num, id, ceil((payment_time - login_time)*24*60) use_time   ")
		.append("	from pc_use	pu, pc_payment pp   ")
		.append("	where pp.pc_use_code = pu.pc_use_code   ")
		.append("	and to_char(pu.login_time, 'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd')   ")
		.append("   order by pu.pc_use_code asc   ") ;
		pstmt = conn.prepareStatement(selectCalcPC.toString()) ;
		
		// 4. bind 변수에 값 설정
		// 없음
		
		// 5. 쿼리 수행 후 결과 얻기
		rs = pstmt.executeQuery() ;
		CalcPCVO cv = null ;
		while( rs.next() ) {
			cv = new CalcPCVO(rs.getString("pc_use_code"),rs.getString("id"), rs.getInt("use_time"), rs.getInt("pc_num"), ((int)Math.ceil( (rs.getInt("use_time")/5D) )*100));
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
			.append("	select pc_code, sum(use_time) use_time, sum(use_fee) use_fee	")
			.append("	from pc_use	")
			.append("	group by pc_code	") 
			.append("	where to_char(login_time, 'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd')	");
			
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
			.append("	and payment_time >= sysdate-7	")
			.append("   order by pu.pc_use_code asc   ") ;
			
			pstmt = conn.prepareStatement(selectCalcPC7.toString()) ;
			
			// 4. bind 변수에 값 설정
			// 없음
			
			// 5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery() ;
			CalcPCVO cv = null ;
			while( rs.next() ) {
				cv = new CalcPCVO(rs.getString("pc_use_code"),rs.getString("id"), rs.getInt("use_time"), rs.getInt("pc_num"), ((int)Math.ceil( (rs.getInt("use_time")/5D) )*100));
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
			.append("	and payment_time >= add_months(sysdate, -1)	")
			.append("   order by pu.pc_use_code asc   ") ;
			
			pstmt = conn.prepareStatement(selectCalcPCLstMonth.toString()) ;
			
			// 4. bind 변수에 값 설정
			// 없음
			
			// 5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery() ;
			CalcPCVO cv = null ;
			while( rs.next() ) {
				cv = new CalcPCVO(rs.getString("pc_use_code"),rs.getString("id"), rs.getInt("use_time"), rs.getInt("pc_num"), ((int)Math.ceil( (rs.getInt("use_time")/5D) )*100)) ;
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
			.append("		and to_date(?,'yyyy-mm-dd')<=payment_time and payment_time<= to_date(?,'yyyy-mm-dd')	")
			.append("   order by pu.pc_use_code asc   ");
			
			pstmt = conn.prepareStatement(selectCalcPCLstCustom.toString()) ;
			
			// 4. bind 변수에 값 설정
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			
			// 5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery() ;
			CalcPCVO cv = null ;
			while( rs.next() ) {
				cv = new CalcPCVO(rs.getString("pc_use_code"),rs.getString("id"), rs.getInt("use_time"), rs.getInt("pc_num"), ((int)Math.ceil( (rs.getInt("use_time")/5D) )*100)) ;
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

/////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////	
	
	public List<String> selectItem(String category) throws SQLException {
		List<String> list = new ArrayList<String>() ;
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		// 2. Connection 얻기
		conn = getConnection() ;
		
		try {
		// 3. 쿼리문 생성 객체 얻기 : pcbang테이블에서 
		StringBuilder selectItem = new StringBuilder() ;
//		select category, name
//		from item
//		order by category, input_date
		selectItem
		.append("	select name   ")
		.append("	from item   ")
		.append("	where category = ?   ")
		.append("   order by input_date   ") ;
		
		pstmt = conn.prepareStatement(selectItem.toString()) ;
		
		// 4. bind 변수에 값 설정
		pstmt.setString(1, category);
		
		// 5. 쿼리 수행 후 결과 얻기
		rs = pstmt.executeQuery();
		while( rs.next() ) {
			list.add(rs.getString("name")) ;	// 조회된 레코드를 저장한 VO를 list에 추가
		} // end while
		
		} finally {
		// 6. 연결 끊기
			if ( rs != null) { rs.close(); } // end if
			if ( pstmt != null) { pstmt.close(); } // end if
			if ( conn != null) { conn.close(); } // end if
			
		} // end finally
				
		return list ;
	} // selectItem
	
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

		selectCalcItem
		.append("	select io.order_code, pc_num, id, name, quantity, (price*quantity) total_price   ")
		.append("	from pc_use pu, item item, item_order io, item_payment ip   ")
		.append("	where (io.pc_use_code=pu.pc_use_code) and (io.item_code=item.item_code) and (ip.order_code = io.order_code) 	")
		.append("	      and to_char(ip.payment_time, 'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd') 	")
		.append("	      and payment_time is not null 	")
		.append("   order by io.order_code asc   ") ;
		
		pstmt = conn.prepareStatement(selectCalcItem.toString()) ;
		
		// 4. bind 변수에 값 설정
		// 없음
		
		// 5. 쿼리 수행 후 결과 얻기
		rs = pstmt.executeQuery() ;
		CalcItemVO cv = null ;
		while( rs.next() ) {
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
			.append("	select i.name item_name, io.quantity quantity, (i.price*io.quantity) orderedPrice, i.price price, io.order_date order_date, payment_time	")
			.append("	from item_payment ip, item_order io, item i	") 
			.append("	where (ip.order_code=io.order_code)and(io.item_code=i.item_code)	")			
			.append("	      and to_char(ip.payment_time, 'yyyy-mm-dd')=to_char(sysdate,'yyyy-mm-dd')	")		
			.append("	      and payment_time is not null   ");		

			pstmt = conn.prepareStatement(selectCalcItemRecipt.toString()) ;
			
			// 4. bind 변수에 값 설정
			// 없음
			
			// 5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery() ;
			CalcItemReciptVO cv = null ;
			while( rs.next() ) {
				cv = new CalcItemReciptVO(rs.getString("item_name"), rs.getString("payment_time"), rs.getInt("quantity"), rs.getInt("orderedPrice"), rs.getInt("price")) ;
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
	

	public List<CalcItemVO> selectCalcItem7() throws SQLException {
		List<CalcItemVO> list = new ArrayList<CalcItemVO>() ;
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		// 2. Connection 얻기
		conn = getConnection() ;
		

		try {
		// 3. 쿼리문 생성 객체 얻기 
		StringBuilder selectCalcItem = new StringBuilder() ;
		selectCalcItem
		.append("	select io.order_code, pc_num, id, name, quantity, (price*quantity) total_price   ")
		.append("	from pc_use pu, item item, item_order io, item_payment ip   ")
		.append("	where (io.pc_use_code=pu.pc_use_code) and (io.item_code=item.item_code) and (ip.order_code = io.order_code) 	")
		.append("	      and ip.payment_time >= sysdate-7 	")
		.append("	      and payment_time is not null 	")
		.append("   order by io.order_code asc   ") ;
		
		pstmt = conn.prepareStatement(selectCalcItem.toString()) ;
		
		// 4. bind 변수에 값 설정
		// 없음
		
		// 5. 쿼리 수행 후 결과 얻기
		rs = pstmt.executeQuery() ;
		CalcItemVO cv = null ;
		while( rs.next() ) {
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
	

	public List<CalcItemVO> selectCalcItemLstMonth() throws SQLException {
		List<CalcItemVO> list = new ArrayList<CalcItemVO>() ;
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		// 2. Connection 얻기
		conn = getConnection() ;
		

		try {
		// 3. 쿼리문 생성 객체 얻기
		StringBuilder selectCalcItem = new StringBuilder() ;
		selectCalcItem
		.append("	select io.order_code, pc_num, id, name, quantity, (price*quantity) total_price   ")
		.append("	from pc_use pu, item item, item_order io, item_payment ip   ")
		.append("	where (io.pc_use_code=pu.pc_use_code) and (io.item_code=item.item_code) and (ip.order_code = io.order_code) 	")
		.append("			and ip.payment_time >= add_months(sysdate, -1) 	") 
		.append("			and payment_time is not null 	")
		.append("   order by io.order_code asc   ") ;
		
		pstmt = conn.prepareStatement(selectCalcItem.toString()) ;
		
		// 4. bind 변수에 값 설정
		// 없음
		
		// 5. 쿼리 수행 후 결과 얻기
		rs = pstmt.executeQuery() ;
		CalcItemVO cv = null ;
		while( rs.next() ) {
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
	
	
	public List<CalcItemVO> selectCalcItemLstCustom(String startDate, String endDate) throws SQLException {
		List<CalcItemVO> list = new ArrayList<CalcItemVO>() ;
		
		Connection conn = null ;
		PreparedStatement pstmt = null ;
		ResultSet rs = null ;
		
		// 2. Connection 얻기
		conn = getConnection() ;
		
		try {
			// 3. 쿼리문 생성 객체 얻기 
			StringBuilder selectCalcItemLstCustom = new StringBuilder() ;
			selectCalcItemLstCustom
			.append("	select io.order_code, pc_num, id, name, quantity, (price*quantity) total_price   ")
			.append("	from pc_use pu, item item, item_order io, item_payment ip   ")
			.append("	where (io.pc_use_code=pu.pc_use_code) and (io.item_code=item.item_code) and (ip.order_code = io.order_code) 	")
			.append("			and to_date(?,'yyyy-mm-dd')<=payment_time and payment_time<= to_date(?,'yyyy-mm-dd') 	") 
			.append("			and payment_time is not null 	")
			.append("   order by io.order_code asc   ") ;
			pstmt = conn.prepareStatement(selectCalcItemLstCustom.toString()) ;
			
			// 4. bind 변수에 값 설정
			pstmt.setString(1, startDate);
			pstmt.setString(2, endDate);
			
			// 5. 쿼리 수행 후 결과 얻기
			rs = pstmt.executeQuery() ;
			CalcItemVO ciVO = null ;
			while( rs.next() ) {
				ciVO = new CalcItemVO(rs.getString("order_code"), rs.getString("id"), rs.getString("name"), rs.getInt("pc_num"), rs.getInt("quantity"), rs.getInt("total_price")) ;
				list.add(ciVO) ;	// 조회된 레코드를 저장한 VO를 list에 추가
			} // end while
			
		} finally {
			// 6. 연결 끊기
			if ( rs != null) { rs.close(); } // end if
			if ( pstmt != null) { pstmt.close(); } // end if
			if ( conn != null) { conn.close(); } // end if
			
		} // end finally
		
		return list ;
	} // selectCalcPCLstCustom
	
//	public static void main(String[] args) {
//		AdminDAO ad=AdminDAO.getInstance();
//		try {
//			System.out.println(ad.selectLogin());  
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
	
}//class