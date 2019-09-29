package user.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import user.VO.FindIdVO;
import user.VO.FindPassVO;
import user.VO.PcHistoryVO;
import user.VO.UserItemDetailVO;
import user.VO.UserItemVO;
import user.VO.UserJoinVO;
import user.VO.UserLoginVO;
import user.VO.UserOrderVO;
import user.VO.UserRePassVO;
import user.VO.selectItemVO;
import user.VO.selectSearchVO;

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
	 * DBMS와 연동하는 일
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
		String url="jdbc:oracle:thin:@211.63.89.132:1521:orcl";
//		String url="jdbc:oracle:thin:@192.168.1.16:1521:orcl";
//		String url="jdbc:oracle:thin:@211.63.89.132:1521:orcl";
		String id="pcbang";
		String pass="ezo";
		
		con=DriverManager.getConnection(url,id,pass);
		
		return con;
	}//getConn
	
	
	//////////////////////////////민경//////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 아이디와 비밀번호를 입력받아 member 테이블에서 아이디와 비밀번호가 맞는다면 로그인에 성공하는일
	 * 
	 * @param ulVO 아이디와 비번을 가진 VO
	 * @return 성공 - 이름, 실패 - empty
	 * @throws SQLException
	 */
	public String selectLogin(UserLoginVO ulVO) throws SQLException { //boolean -> String 원래boolean임 바꿀꺼임
//		boolean selectLoginFlag=false;
		String userName="";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		//2. 커넥션 얻기
			con=getConn();
		//3. 쿼리문 생성객체 얻기
			StringBuilder selectName=new StringBuilder();
			selectName
			.append("		select name		")
			.append("		from member_account	")
			.append("		where id=? and pass=?	");
			
			pstmt=con.prepareStatement(selectName.toString());
			
		//4. 바인드 변수에 값 넣기
			pstmt.setString(1, ulVO.getId());
			pstmt.setString(2, ulVO.getPass());
		//5. 쿼리 실행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				userName=rs.getString("name");
			}//end if
			
		}finally {
		//6. 연결끊기
			if ( rs != null ) { rs.close(); } //end if
			if ( pstmt != null ) { pstmt.close(); } //end if
			if ( con != null ) { con.close(); } //end if
		}//end finally
		return userName;
	}//selectLogin
	
	
//	/**
//	 * 로그인중인 아이디 걸러내기~~
//	 * @param id
//	 * @return
//	 * @throws SQLException
//	 */
//	public boolean checkLogin(UserLoginVO ulVO) throws SQLException { 
//		boolean checkFlag=false;
//		Connection con=null;
//		PreparedStatement pstmt=null;
//		ResultSet rs=null;
//		
//		try {
//		//2. 커넥션 얻기
//			con=getConn();
//		//3. 쿼리문 생성객체 얻기
//			StringBuilder checkLogin=new StringBuilder();
//			checkLogin
//			.append("		select pu.pc_use_code, id, pc_num, login_time, payment_time	")   
//			.append("		from pc_payment pp, pc_use pu										")
//			.append("		where (pp.pc_use_code(+)= pu.pc_use_code) and id = ?			")
//			.append("		order by pu.pc_use_code;												");
//			
//			pstmt=con.prepareStatement(checkLogin.toString());
//			
//		//4. 바인드 변수에 값 넣기
//			pstmt.setString(1, ulVO.getId());
//		//5. 쿼리 실행 후 결과 얻기
//			checkFlag=pstmt.executeUpdate()==1; //1과 같으면 true;
//			
//		}finally {
//		//6. 연결끊기
//			if ( rs != null ) { rs.close(); } //end if
//			if ( pstmt != null ) { pstmt.close(); } //end if
//			if ( con != null ) { con.close(); } //end if
//		}//end finally
//		return checkFlag;  
//	}//selectPass
	
	
	/**
	 * 로그인시 입력받은 아이디로 pc_payment, pc_use 테이블에서 해당 아이디를 사용중인지 확인하여 반환하는 일
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public String checkLogin(String id) throws SQLException { 
		String userId="";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		//2. 커넥션 얻기
			con=getConn();
		//3. 쿼리문 생성객체 얻기
			StringBuilder checkLogin=new StringBuilder();
			checkLogin
			.append("		select pu.pc_use_code, id, pc_num, login_time, payment_time	")   
			.append("		from pc_payment pp, pc_use pu										")
			.append("		where (pp.pc_use_code(+)= pu.pc_use_code) and id = ?			")
			.append("		order by pu.pc_use_code											");
			
			pstmt=con.prepareStatement(checkLogin.toString());
			
		//4. 바인드 변수에 값 넣기
			pstmt.setString(1, id);
		//5. 쿼리 실행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				userId=rs.getString("id");
			}//end if
			
		}finally {
		//6. 연결끊기
			if ( rs != null ) { rs.close(); } //end if
			if ( pstmt != null ) { pstmt.close(); } //end if
			if ( con != null ) { con.close(); } //end if
		}//end finally
		return userId;
	}//overlapId
	
	
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	/**
	 * 이름과 전화번호를 입력받아 member 테이블에서 일치하는 아이디를 찾아 반환하는 일
	 * 
	 * @param fiVO
	 * @return 
	 * @throws SQLException
	 */
	public String selectId(FindIdVO fiVO) throws SQLException { 
		String userId="";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		//2. 커넥션 얻기
			con=getConn();
		//3. 쿼리문 생성객체 얻기
			StringBuilder selectName=new StringBuilder();
			selectName
			.append("		select id		")
			.append("		from member_account	")
			.append("		where name=? and phone=?	");
			
			pstmt=con.prepareStatement(selectName.toString());
			
		//4. 바인드 변수에 값 넣기
			pstmt.setString(1, fiVO.getName());
			pstmt.setString(2, fiVO.getPhone());
		//5. 쿼리 실행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				userId=rs.getString("id");
			}//end if
			
		}finally {
		//6. 연결끊기
			if ( rs != null ) { rs.close(); } //end if
			if ( pstmt != null ) { pstmt.close(); } //end if
			if ( con != null ) { con.close(); } //end if
		}//end finally
		return userId;
	}//selectLogin
	
	
	/**
	 * PassWord찾기
	 * 
	 * @param fpVO
	 * @return 
	 * @throws SQLException
	 */
	public boolean selectPass(FindPassVO fpVO) throws SQLException { 
		boolean selectFlag=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		//2. 커넥션 얻기
			con=getConn();
		//3. 쿼리문 생성객체 얻기
			StringBuilder selectName=new StringBuilder();
			selectName
			.append("		select pass		")   
			.append("		from member_account	")
			.append("		where id=? and question_verify=? and answer_verify=?	");
			
			pstmt=con.prepareStatement(selectName.toString());
			
		//4. 바인드 변수에 값 넣기
			pstmt.setString(1, fpVO.getId());
			pstmt.setString(2, fpVO.getQuestion());
			pstmt.setString(3, fpVO.getAnswer());
		//5. 쿼리 실행 후 결과 얻기
			selectFlag=pstmt.executeUpdate()==1; //1과 같으면 true;
			
		}finally {
		//6. 연결끊기
			if ( rs != null ) { rs.close(); } //end if
			if ( pstmt != null ) { pstmt.close(); } //end if
			if ( con != null ) { con.close(); } //end if
		}//end finally
		return selectFlag;  
	}//selectPass
	
	
	/**
	 * 입력받은 ID와 일치하는 회원정보를 찾아 비밀번호를 변경하는 일
	 * @param urpVO 값을 가진 객체
	 * @return true- PW 재설정된 회원정보 존재, false- PW 재설정된 회원정보 존재X
	 * @throws SQLException DBMS에서 문제발생
	 */
	public boolean updatePass(UserRePassVO urpVO) throws SQLException { 
		boolean updateFlag=false;
		Connection con=null;
		PreparedStatement pstmt=null; //업데이트라서 resulset필요X
		
		try {
		//2. 커넥션 얻기
			con=getConn();
		//3. 쿼리문 생성객체 얻기
			StringBuilder updatePass=new StringBuilder();
			updatePass
			.append("		update member_account		")
			.append("		set pass=? 						")
			.append("		where id=?						");
			
			pstmt=con.prepareStatement(updatePass.toString());
			
		//4. 바인드 변수에 값 넣기
			pstmt.setString(1, urpVO.getPass());
			pstmt.setString(2, urpVO.getId());
		//5. 쿼리 실행 후 결과 얻기
			updateFlag=pstmt.executeUpdate()==1; //1과 같으면 true;
			
		}finally {
			
		//6. 연결끊기
			if ( pstmt != null ) { pstmt.close(); } //end if
			if ( con != null ) { con.close(); } //end if
		}//end finally
		return updateFlag;
		
	}//selectLogin
	
	/**
	 * 회원가입정보입력
	 * 
	 * @param ujVO
	 * @throws SQLException
	 */
	public void insertMember(UserJoinVO ujVO) throws SQLException { 
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
		//2. 커넥션 얻기
			con=getConn();
		//3. 쿼리문 생성객체 얻기
			StringBuilder insertMember=new StringBuilder();
			insertMember
			.append("	insert into member_account(ID,PASS, NAME, PHONE, QUESTION_VERIFY,	ANSWER_VERIFY	, JOIN_DATE) values (?,?,?,?,?,?,sysdate)" );

			
			pstmt=con.prepareStatement(insertMember.toString());
			
		//4. 바인드 변수에 값 넣기
			pstmt.setString(1, ujVO.getId());
			pstmt.setString(2, ujVO.getPass());
			pstmt.setString(3, ujVO.getName());
			pstmt.setString(4, ujVO.getPhone());
			pstmt.setString(5, ujVO.getQuestion());
			pstmt.setString(6, ujVO.getAnswer());
		//5. 쿼리 실행 후 결과 얻기
			pstmt.executeUpdate();

		}finally {
		//6. 연결끊기
			if ( pstmt != null ) { pstmt.close(); } //end if
			if ( con != null ) { con.close(); } //end if
		}//end finally
	}//selectLogin
	

	/**
	 * 회원가입시 입력받은 아이디로 member_account 테이블에서 일치하는 아이디를 찾아 중복아이디가 있는지 확인하여 반환하는 일
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public String overlapId(String id) throws SQLException { 
		String userId="";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		//2. 커넥션 얻기
			con=getConn();
		//3. 쿼리문 생성객체 얻기
			StringBuilder overlapId=new StringBuilder();
			overlapId
			.append("		select id						")
			.append("		from member_account	")
			.append("		where id=?					");
			
			pstmt=con.prepareStatement(overlapId.toString());
			
		//4. 바인드 변수에 값 넣기
			pstmt.setString(1, id);
		//5. 쿼리 실행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				userId=rs.getString("id");
			}//end if
			
		}finally {
		//6. 연결끊기
			if ( rs != null ) { rs.close(); } //end if
			if ( pstmt != null ) { pstmt.close(); } //end if
			if ( con != null ) { con.close(); } //end if
		}//end finally
		return userId;
	}//overlapId
	
	/**
	 * 회원가입시 입력받은 전화번호로 member_account 테이블에서 일치하는 전화번호를 찾아 중복되는 번호가 있는지 확인하여 반환하는 일
	 * @param phone
	 * @return
	 * @throws SQLException
	 */
	public String overlapPhone(String phone) throws SQLException { 
		String userPhone="";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		//2. 커넥션 얻기
			con=getConn();
		//3. 쿼리문 생성객체 얻기
			StringBuilder overlapPhone=new StringBuilder();
			overlapPhone
			.append("		select phone						")
			.append("		from member_account	")
			.append("		where phone=?					");
			
			pstmt=con.prepareStatement(overlapPhone.toString());
			
		//4. 바인드 변수에 값 넣기
			pstmt.setString(1, phone);
		//5. 쿼리 실행 후 결과 얻기
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				userPhone=rs.getString("phone");
			}//end if
			
		}finally {
		//6. 연결끊기
			if ( rs != null ) { rs.close(); } //end if
			if ( pstmt != null ) { pstmt.close(); } //end if
			if ( con != null ) { con.close(); } //end if
		}//end finally
		return userPhone;
	}//overlapId
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////소현///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * ip주소를 받아 ip에 맞는 pc번호를 조회하는 일
	 * @param ipAddr
	 * @return
	 * @throws SQLException
	 */
	public int selectPcNum(String ipAddr) throws SQLException {
		int num=0;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=getConn();
			
			StringBuilder selectPcNum=new StringBuilder();
			selectPcNum
			.append(" 	select pc_num		")
			.append("	from pc		")
			.append("	where ip_address=?		");
			
			pstmt=con.prepareStatement(selectPcNum.toString());
			//
			pstmt.setString(1,ipAddr);
			//
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				num=rs.getInt("pc_num");
			}//end if
			
		}finally {
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			if(con!=null) {con.close();}
		}//end catch
		return num;
	}//selectPcNum
	
	
	
	/**
	 * 사용자의 pc기록을 pcHistory테이블에 추가하는 일
	 * @param phVO
	 * @return
	 * @throws SQLException
	 */
	public String insertPcHistory(PcHistoryVO phVO) throws SQLException {
		
		String pcUseCode=null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=getConn();
		
			StringBuilder insertPcHistory=new StringBuilder();
			insertPcHistory
			.append("insert into pc_use(pc_use_code, pc_num, id, login_time)")
			.append("values(pc_use_code,?,?,sysdate)");
			
			pstmt=con.prepareStatement(insertPcHistory.toString());
		//4.
			System.out.println(phVO.getPcNum()+"/"+phVO.getUserId());
			pstmt.setInt(1, phVO.getPcNum());
			pstmt.setString(2, phVO.getUserId());
		//5.
			pstmt.executeUpdate();
			
		}finally {
			if(rs!=null) {rs.close();} //end if
			if(pstmt!=null) {pstmt.close();} //end if
			if(con!=null) {con.close();} //end if
		}//end finally
		
		return pcUseCode;
		
	}//insertPcHistory
	
	
	//////////////////////////////////
	public List<UserItemVO> selectOrderBy(selectItemVO siVO) throws SQLException {
		List<UserItemVO> list=new ArrayList<UserItemVO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		con=getConn();
		
		StringBuilder selectOrderBy=new StringBuilder();
		if(siVO.getIndex()==0) {
			selectOrderBy
		.append("	select i.item_code, i.name, i.img, c.category_name, i.price	")
		.append("	from item i, category c	")
		.append("	where (i.category=c.category_code) and display_state='Y' and category=? 	")
		.append("	order by i.input_date desc 	"); //최신순
		}else if(siVO.getIndex()==1) {
			selectOrderBy
			.append("	select i.item_code, nvl(sum(io.quantity),0) orderCnt  , i.item_code, i.name, i.img, c.category_name, i.price	")
			.append("	from item i, category c, item_order io 	")
			.append("	where (i.category=c.category_code) and (io.item_code(+)=i.item_code) and i.display_state='Y' and category=? 	")
			.append("	group by i.item_code, i.item_code, i.name, i.img, c.category_name, i.price	") 
			.append("	order by orderCnt desc 	"); //인기순
			
		}else if(siVO.getIndex()==2) {
			selectOrderBy
			.append("	select i.item_code, i.name, i.img, c.category_name, i.price	")
			.append("	from item i, category c 	")
			.append("	where (i.category=c.category_code) and display_state='Y' and category=? 	")
			.append("	order by i.price "); //가격순
		}//end else
		
		pstmt=con.prepareStatement(selectOrderBy.toString());
		
		pstmt.setString(1,siVO.getCategory());
		
		rs=pstmt.executeQuery();
		UserItemVO uiv=null;
		while(rs.next()) {
			uiv=new UserItemVO(rs.getString("item_code"),rs.getString("name"), rs.getString("img"),rs.getString("category_name"),rs.getInt("price"));
			list.add(uiv);
		}//end while
		
		}finally {
			if(rs!=null) {rs.close();} //end if
			if(pstmt!=null) {pstmt.close();} //end if
			if(con!=null) {con.close();} //end if
		}//end finally
		return list;
	}//selectOrderBy
	
	/**
	 * 검색한 검색어를 조회하는 일
	 * @throws SQLException 
	 */
	public List<selectSearchVO> selectSearch(String str) throws SQLException {
		List<selectSearchVO> list=new ArrayList<selectSearchVO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		con=getConn();
		
		StringBuilder selectSearch=new StringBuilder();
		selectSearch
		.append("	select item_code, name, price	")
		.append("	from item 	")
		.append("	where name like '%"+str+"%'	");
		
		pstmt=con.prepareStatement(selectSearch.toString());
		
//		pstmt.setString(1,str);
		
		rs=pstmt.executeQuery();
		selectSearchVO ssv=null;
		while(rs.next()) {
			ssv=new selectSearchVO(rs.getString("item_code"),rs.getString("name"),rs.getInt("price"));
			list.add(ssv);
		}//end while
		
		}finally {
			if(rs!=null) {rs.close();} //end if
			if(pstmt!=null) {pstmt.close();} //end if
			if(con!=null) {con.close();} //end if
		}//end finally
		return list;
	}//SelectSearch
	
	public void SelectSearchDetail() throws SQLException {
		
		
	}//SelectSearchDetail
	
	/**
	 * itemCode로 상세보기 창에 조회하는 일
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
			.append("	select i.img,c.category_name,i.NAME,i.DESCRIPTION,i.PRICE	")
			.append("	from item i, category c	")
			.append("	where (i.category=c.category_code) and item_code=?	");
			
			pstmt=con.prepareStatement(selectUserItemDetail.toString());
			
			pstmt.setString(1, itemCode);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				uidVO=new UserItemDetailVO(rs.getString("category_name"), rs.getString("name"), rs.getString("img"), rs.getString("description"), rs.getInt("price"));
			}//end if
			
		}finally {
			if(rs!=null) {rs.close();} //end if
			if(pstmt!=null) {pstmt.close();} //end if
			if(con!=null) {con.close();} //end if
		}//end finally
		
		return uidVO;
	}//selectUserItemDetail
	
	/**
	 * 사용중인 pc번호를 찾아 pcUseCode를 조회하는 일 (음식주문을 위한 코드)
	 * @param pcNum
	 * @return
	 * @throws SQLException
	 */
	public String selectPcUseCode(int pcNum) throws SQLException {
		String useCode=null;
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
			con=getConn();
			
			StringBuilder selectPcUseCode=new StringBuilder();
			selectPcUseCode
			.append("	select pc_use_code	")
			.append("	from pc_use	")
			.append("	where pc_num=?	")
			.append(" order by login_time desc");
			
			pstmt=con.prepareStatement(selectPcUseCode.toString());
			
			pstmt.setInt(1,pcNum);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				useCode=rs.getString("pc_use_code");
			}//end if
		
		}finally {
			if(rs!=null) {rs.close();}//end if
			if(pstmt!=null) {pstmt.close();}//end if
			if(con!=null) {con.close();}//end if
		}//end finally
		
		return useCode;
	}//selectPcUseCode
	
	/**
	 * 주문목록들을 주문테이블에 추가하는 일
	 * @param listUoVO
	 * @throws SQLException
	 */
	public void insertOrder(List<UserOrderVO> listUoVO) throws SQLException {
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		con=getConn();
		
		for(int i=0;i<listUoVO.size();i++) {
			StringBuilder insertOrder=new StringBuilder();
			insertOrder
			.append("insert into item_order(order_code,pc_use_code,item_code,quantity,order_date)")
			.append("values(order_code,?,?,?,sysdate)");
			
			pstmt=con.prepareStatement(insertOrder.toString());
			
			pstmt.setString(1,listUoVO.get(i).getPcUserCode());
			pstmt.setString(2,listUoVO.get(i).getItemCode());
			pstmt.setInt(3,listUoVO.get(i).getQuantity());
			
			pstmt.executeUpdate();
		}//end for
		
		}finally {
			if(rs!=null) {rs.close();} //end if
			if(pstmt!=null) {pstmt.close();} //end if
			if(con!=null) {con.close();} //end if
		}//end finally
		
	}//insertOrder
	
	
	/**
	 * 사용자가 PC 사용을 종료했을 떼 DB의 PC_payment 테이블에 결제 내용 기록
	 * @return
	 * @throws SQLException
	 */
	public boolean insertPCPayment(int pcNum) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		boolean flag = false;
		String useCode = "";
		
		try {
			con=getConn();
			
			StringBuilder selectPcUseCode=new StringBuilder();
			selectPcUseCode
			.append("	select pc_use_code	")
			.append("	from pc_use	")
			.append("	where pc_num=?	")
			.append(" order by login_time desc");
			
			pstmt=con.prepareStatement(selectPcUseCode.toString());
			
			pstmt.setInt(1,pcNum);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				useCode=rs.getString("pc_use_code");
			
				pstmt.close();

				StringBuilder payment_state = new StringBuilder();
				payment_state
				.append(" insert into pc_payment(pc_use_code,payment_time) ")
				.append(" values(?,sysdate) ");

				pstmt = con.prepareStatement(payment_state.toString());

				pstmt.setString(1, useCode);

				flag = (pstmt.executeUpdate() == 1);
			
			}//end if
			
		}finally {
			if(rs!=null) {rs.close();}
			if(pstmt!=null) {pstmt.close();}
			if(con!=null) {con.close();}
		}
		return flag;
	}//insertPCPayment

}//class
