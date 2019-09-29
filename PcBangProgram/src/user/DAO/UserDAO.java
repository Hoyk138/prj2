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
	 * DBMS�� �����ϴ� ��
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
	
	
	//////////////////////////////�ΰ�//////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * ���̵�� ��й�ȣ�� �Է¹޾� member ���̺��� ���̵�� ��й�ȣ�� �´´ٸ� �α��ο� �����ϴ���
	 * 
	 * @param ulVO ���̵�� ����� ���� VO
	 * @return ���� - �̸�, ���� - empty
	 * @throws SQLException
	 */
	public String selectLogin(UserLoginVO ulVO) throws SQLException { //boolean -> String ����boolean�� �ٲܲ���
//		boolean selectLoginFlag=false;
		String userName="";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		//2. Ŀ�ؼ� ���
			con=getConn();
		//3. ������ ������ü ���
			StringBuilder selectName=new StringBuilder();
			selectName
			.append("		select name		")
			.append("		from member_account	")
			.append("		where id=? and pass=?	");
			
			pstmt=con.prepareStatement(selectName.toString());
			
		//4. ���ε� ������ �� �ֱ�
			pstmt.setString(1, ulVO.getId());
			pstmt.setString(2, ulVO.getPass());
		//5. ���� ���� �� ��� ���
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				userName=rs.getString("name");
			}//end if
			
		}finally {
		//6. �������
			if ( rs != null ) { rs.close(); } //end if
			if ( pstmt != null ) { pstmt.close(); } //end if
			if ( con != null ) { con.close(); } //end if
		}//end finally
		return userName;
	}//selectLogin
	
	
//	/**
//	 * �α������� ���̵� �ɷ�����~~
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
//		//2. Ŀ�ؼ� ���
//			con=getConn();
//		//3. ������ ������ü ���
//			StringBuilder checkLogin=new StringBuilder();
//			checkLogin
//			.append("		select pu.pc_use_code, id, pc_num, login_time, payment_time	")   
//			.append("		from pc_payment pp, pc_use pu										")
//			.append("		where (pp.pc_use_code(+)= pu.pc_use_code) and id = ?			")
//			.append("		order by pu.pc_use_code;												");
//			
//			pstmt=con.prepareStatement(checkLogin.toString());
//			
//		//4. ���ε� ������ �� �ֱ�
//			pstmt.setString(1, ulVO.getId());
//		//5. ���� ���� �� ��� ���
//			checkFlag=pstmt.executeUpdate()==1; //1�� ������ true;
//			
//		}finally {
//		//6. �������
//			if ( rs != null ) { rs.close(); } //end if
//			if ( pstmt != null ) { pstmt.close(); } //end if
//			if ( con != null ) { con.close(); } //end if
//		}//end finally
//		return checkFlag;  
//	}//selectPass
	
	
	/**
	 * �α��ν� �Է¹��� ���̵�� pc_payment, pc_use ���̺��� �ش� ���̵� ��������� Ȯ���Ͽ� ��ȯ�ϴ� ��
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
		//2. Ŀ�ؼ� ���
			con=getConn();
		//3. ������ ������ü ���
			StringBuilder checkLogin=new StringBuilder();
			checkLogin
			.append("		select pu.pc_use_code, id, pc_num, login_time, payment_time	")   
			.append("		from pc_payment pp, pc_use pu										")
			.append("		where (pp.pc_use_code(+)= pu.pc_use_code) and id = ?			")
			.append("		order by pu.pc_use_code											");
			
			pstmt=con.prepareStatement(checkLogin.toString());
			
		//4. ���ε� ������ �� �ֱ�
			pstmt.setString(1, id);
		//5. ���� ���� �� ��� ���
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				userId=rs.getString("id");
			}//end if
			
		}finally {
		//6. �������
			if ( rs != null ) { rs.close(); } //end if
			if ( pstmt != null ) { pstmt.close(); } //end if
			if ( con != null ) { con.close(); } //end if
		}//end finally
		return userId;
	}//overlapId
	
	
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	/**
	 * �̸��� ��ȭ��ȣ�� �Է¹޾� member ���̺��� ��ġ�ϴ� ���̵� ã�� ��ȯ�ϴ� ��
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
		//2. Ŀ�ؼ� ���
			con=getConn();
		//3. ������ ������ü ���
			StringBuilder selectName=new StringBuilder();
			selectName
			.append("		select id		")
			.append("		from member_account	")
			.append("		where name=? and phone=?	");
			
			pstmt=con.prepareStatement(selectName.toString());
			
		//4. ���ε� ������ �� �ֱ�
			pstmt.setString(1, fiVO.getName());
			pstmt.setString(2, fiVO.getPhone());
		//5. ���� ���� �� ��� ���
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				userId=rs.getString("id");
			}//end if
			
		}finally {
		//6. �������
			if ( rs != null ) { rs.close(); } //end if
			if ( pstmt != null ) { pstmt.close(); } //end if
			if ( con != null ) { con.close(); } //end if
		}//end finally
		return userId;
	}//selectLogin
	
	
	/**
	 * PassWordã��
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
		//2. Ŀ�ؼ� ���
			con=getConn();
		//3. ������ ������ü ���
			StringBuilder selectName=new StringBuilder();
			selectName
			.append("		select pass		")   
			.append("		from member_account	")
			.append("		where id=? and question_verify=? and answer_verify=?	");
			
			pstmt=con.prepareStatement(selectName.toString());
			
		//4. ���ε� ������ �� �ֱ�
			pstmt.setString(1, fpVO.getId());
			pstmt.setString(2, fpVO.getQuestion());
			pstmt.setString(3, fpVO.getAnswer());
		//5. ���� ���� �� ��� ���
			selectFlag=pstmt.executeUpdate()==1; //1�� ������ true;
			
		}finally {
		//6. �������
			if ( rs != null ) { rs.close(); } //end if
			if ( pstmt != null ) { pstmt.close(); } //end if
			if ( con != null ) { con.close(); } //end if
		}//end finally
		return selectFlag;  
	}//selectPass
	
	
	/**
	 * �Է¹��� ID�� ��ġ�ϴ� ȸ�������� ã�� ��й�ȣ�� �����ϴ� ��
	 * @param urpVO ���� ���� ��ü
	 * @return true- PW �缳���� ȸ������ ����, false- PW �缳���� ȸ������ ����X
	 * @throws SQLException DBMS���� �����߻�
	 */
	public boolean updatePass(UserRePassVO urpVO) throws SQLException { 
		boolean updateFlag=false;
		Connection con=null;
		PreparedStatement pstmt=null; //������Ʈ�� resulset�ʿ�X
		
		try {
		//2. Ŀ�ؼ� ���
			con=getConn();
		//3. ������ ������ü ���
			StringBuilder updatePass=new StringBuilder();
			updatePass
			.append("		update member_account		")
			.append("		set pass=? 						")
			.append("		where id=?						");
			
			pstmt=con.prepareStatement(updatePass.toString());
			
		//4. ���ε� ������ �� �ֱ�
			pstmt.setString(1, urpVO.getPass());
			pstmt.setString(2, urpVO.getId());
		//5. ���� ���� �� ��� ���
			updateFlag=pstmt.executeUpdate()==1; //1�� ������ true;
			
		}finally {
			
		//6. �������
			if ( pstmt != null ) { pstmt.close(); } //end if
			if ( con != null ) { con.close(); } //end if
		}//end finally
		return updateFlag;
		
	}//selectLogin
	
	/**
	 * ȸ�����������Է�
	 * 
	 * @param ujVO
	 * @throws SQLException
	 */
	public void insertMember(UserJoinVO ujVO) throws SQLException { 
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
		//2. Ŀ�ؼ� ���
			con=getConn();
		//3. ������ ������ü ���
			StringBuilder insertMember=new StringBuilder();
			insertMember
			.append("	insert into member_account(ID,PASS, NAME, PHONE, QUESTION_VERIFY,	ANSWER_VERIFY	, JOIN_DATE) values (?,?,?,?,?,?,sysdate)" );

			
			pstmt=con.prepareStatement(insertMember.toString());
			
		//4. ���ε� ������ �� �ֱ�
			pstmt.setString(1, ujVO.getId());
			pstmt.setString(2, ujVO.getPass());
			pstmt.setString(3, ujVO.getName());
			pstmt.setString(4, ujVO.getPhone());
			pstmt.setString(5, ujVO.getQuestion());
			pstmt.setString(6, ujVO.getAnswer());
		//5. ���� ���� �� ��� ���
			pstmt.executeUpdate();

		}finally {
		//6. �������
			if ( pstmt != null ) { pstmt.close(); } //end if
			if ( con != null ) { con.close(); } //end if
		}//end finally
	}//selectLogin
	

	/**
	 * ȸ�����Խ� �Է¹��� ���̵�� member_account ���̺��� ��ġ�ϴ� ���̵� ã�� �ߺ����̵� �ִ��� Ȯ���Ͽ� ��ȯ�ϴ� ��
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
		//2. Ŀ�ؼ� ���
			con=getConn();
		//3. ������ ������ü ���
			StringBuilder overlapId=new StringBuilder();
			overlapId
			.append("		select id						")
			.append("		from member_account	")
			.append("		where id=?					");
			
			pstmt=con.prepareStatement(overlapId.toString());
			
		//4. ���ε� ������ �� �ֱ�
			pstmt.setString(1, id);
		//5. ���� ���� �� ��� ���
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				userId=rs.getString("id");
			}//end if
			
		}finally {
		//6. �������
			if ( rs != null ) { rs.close(); } //end if
			if ( pstmt != null ) { pstmt.close(); } //end if
			if ( con != null ) { con.close(); } //end if
		}//end finally
		return userId;
	}//overlapId
	
	/**
	 * ȸ�����Խ� �Է¹��� ��ȭ��ȣ�� member_account ���̺��� ��ġ�ϴ� ��ȭ��ȣ�� ã�� �ߺ��Ǵ� ��ȣ�� �ִ��� Ȯ���Ͽ� ��ȯ�ϴ� ��
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
		//2. Ŀ�ؼ� ���
			con=getConn();
		//3. ������ ������ü ���
			StringBuilder overlapPhone=new StringBuilder();
			overlapPhone
			.append("		select phone						")
			.append("		from member_account	")
			.append("		where phone=?					");
			
			pstmt=con.prepareStatement(overlapPhone.toString());
			
		//4. ���ε� ������ �� �ֱ�
			pstmt.setString(1, phone);
		//5. ���� ���� �� ��� ���
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				userPhone=rs.getString("phone");
			}//end if
			
		}finally {
		//6. �������
			if ( rs != null ) { rs.close(); } //end if
			if ( pstmt != null ) { pstmt.close(); } //end if
			if ( con != null ) { con.close(); } //end if
		}//end finally
		return userPhone;
	}//overlapId
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////����///////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/**
	 * ip�ּҸ� �޾� ip�� �´� pc��ȣ�� ��ȸ�ϴ� ��
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
	 * ������� pc����� pcHistory���̺� �߰��ϴ� ��
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
		.append("	order by i.input_date desc 	"); //�ֽż�
		}else if(siVO.getIndex()==1) {
			selectOrderBy
			.append("	select i.item_code, nvl(sum(io.quantity),0) orderCnt  , i.item_code, i.name, i.img, c.category_name, i.price	")
			.append("	from item i, category c, item_order io 	")
			.append("	where (i.category=c.category_code) and (io.item_code(+)=i.item_code) and i.display_state='Y' and category=? 	")
			.append("	group by i.item_code, i.item_code, i.name, i.img, c.category_name, i.price	") 
			.append("	order by orderCnt desc 	"); //�α��
			
		}else if(siVO.getIndex()==2) {
			selectOrderBy
			.append("	select i.item_code, i.name, i.img, c.category_name, i.price	")
			.append("	from item i, category c 	")
			.append("	where (i.category=c.category_code) and display_state='Y' and category=? 	")
			.append("	order by i.price "); //���ݼ�
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
	 * �˻��� �˻�� ��ȸ�ϴ� ��
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
	 * itemCode�� �󼼺��� â�� ��ȸ�ϴ� ��
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
	 * ������� pc��ȣ�� ã�� pcUseCode�� ��ȸ�ϴ� �� (�����ֹ��� ���� �ڵ�)
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
	 * �ֹ���ϵ��� �ֹ����̺� �߰��ϴ� ��
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
	 * ����ڰ� PC ����� �������� �� DB�� PC_payment ���̺� ���� ���� ���
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
