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
	public boolean insertMember(UserJoinVO ujVO) throws SQLException { 
		boolean insertFlag=false;
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
		//2. Ŀ�ؼ� ���
			con=getConn();
		//3. ������ ������ü ���
			StringBuilder insertMember=new StringBuilder();
			insertMember
			.append("	insert into member_account(ID,PASS, NAME, PHONE, QUESTION_VERIFY,	ANSWER_VERIFY	, JOIN_DATE) values (?,?,?,?,?,?)" );

			
			pstmt=con.prepareStatement(insertMember.toString());
			
		//4. ���ε� ������ �� �ֱ�
			pstmt.setString(1, ujVO.getId());
			pstmt.setString(2, ujVO.getPass());
			pstmt.setString(3, ujVO.getName());
			pstmt.setString(4, ujVO.getPhone());
			pstmt.setString(5, ujVO.getQuestion());
			pstmt.setString(6, ujVO.getAnswer());
		//5. ���� ���� �� ��� ���
			insertFlag=pstmt.executeUpdate()==1;

		}finally {
		//6. �������
			if ( pstmt != null ) { pstmt.close(); } //end if
			if ( con != null ) { con.close(); } //end if
		}//end finally
		return insertFlag;
	}//selectLogin
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	////////////////////////����///////////////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * ������� pc����� pcHistory���̺� �߰��ϴ� ��
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
	 * ��ǰ�� ����Ʈ�� ��ȸ�ϴ� ��
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
		.append("	select i.item_code, i.name, i.img, c.category_name, i.price	")
		.append("	from item i, category c 	")
		.append("	where (i.category=c.category_code) and display_state='Y' and category=? 	")
		.append("	order by i.input_date desc 	");
		
		pstmt=con.prepareStatement(selectUserItemList.toString());
		
		pstmt.setString(1,category);
		
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
	}//selectUserItemList
	
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
			.append("values(seq_order_code.nextval,?,?,?,sysdate)");
			
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
	

}//class
