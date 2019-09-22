package admin.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import admin.VO.AdminLoginVO;
import admin.VO.MemberVO;
import admin.VO.PCVO;

public class AdminDAO {
	
	private static AdminDAO aDAO;
	
	private static final int ID = 0;
	private static final int NAME = 1;
	private static final int PHONE = 2;
	private static final int JOIN_DATE = 3;
	
	private AdminDAO() {
		
	}//AdminDAO
	
	public static AdminDAO getInstance() {
		if(aDAO==null) {
			aDAO=new AdminDAO();
			
		}//end if
		return aDAO;
	}//getInstance
	
	/**
	 * DBMS�� ������ ��ȯ�ϴ� ��.
	 * @return ����� Ŀ�ؼ�
	 * @throws SQLException
	 */
	private Connection getConnection() throws SQLException { 
		Connection con=null;
		//1. ����̹� �ε� 
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}//end catch
		//2. Connection ���
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
//		String url="jdbc:oracle:thin:@211.63.89.132:1521:orcl";
//		String url="jdbc:oracle:thin:@211.63.89.133:1521:orcl";
		String id="pcbang";
		String pass="ezo";
		
		con=DriverManager.getConnection(url, id, pass);
		
		return con;
	}//getConnection
	
	/**
	 * ���̵�� ��й�ȣ�� �Է¹޾� admin_login ���̺��� ���̵�� ��й�ȣ�� �´´ٸ� �̸��� ��ȸ�ϴ� ��.
	 * 
	 * @param lv ���̵�� ����� ���� VO
	 * @return ���� - �̸�, ���� - empty
	 * @throws SQLException
	 */
	public String selectLogin(AdminLoginVO lv) throws SQLException { //boolean -> String
		String adminName="";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		try {
		//2. Ŀ�ؼ� ���
			con=getConnection();
		//3. ������ ������ü ���
			StringBuilder selectName=new StringBuilder();
			selectName
			.append("		select name		")
			.append("		from admin_account	")
			.append("		where id=? and pass=?	");
			
			pstmt=con.prepareStatement(selectName.toString());
			
		//4. ���ε� ������ �� �ֱ�
			pstmt.setString(1, lv.getId());
			pstmt.setString(2, lv.getPass());
		//5. ���� ���� �� ��� ���
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				adminName=rs.getString("name");
			}//end if
			
		}finally {
		//6. �������
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
		//2. Ŀ�ؼ� ���
			con=getConnection();
		//3. ������ ������ü ���
			StringBuilder selectPCip = new StringBuilder();
			selectPCip
			.append("   select ip_address   ")
			.append("   from pc   ")
			.append("   where pc_num = ?   ");
			
			pstmt=con.prepareStatement(selectPCip.toString());
			
		//4. ���ε� ������ �� �ֱ�
			pstmt.setInt(1, pcNum);
		//5. ���� ���� �� ��� ���
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				if (rs.getString("ip_address") != null) {
					register_flag = true;
				}//end if
			}//end if
			
		}finally {
		//6. �������
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
		//2. Ŀ�ؼ� ���
			con=getConnection();
		//3. ������ ������ü ���
			StringBuilder selectPCNum = new StringBuilder();
			selectPCNum
			.append("   select pc_num   ")
			.append("   from pc   ")
			.append("   where ip_address = ?   ");

			pstmt=con.prepareStatement(selectPCNum.toString());
			
		//4. ���ε� ������ �� �ֱ�
			pstmt.setString(1, pcIP);
		//5. ���� ���� �� ��� ���
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				pcNum = rs.getInt("pc_num");
			}//end if
			
		}finally {
		//6. �������
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
		//2. Ŀ�ؼ� ���
			con=getConnection();
		//3. ������ ������ü ���
			StringBuilder selectAllPC = new StringBuilder();
			selectAllPC
			.append("   select pc_num, ip_address, to_char(input_date,'yyyy\"��\" mm\"��\" dd\"��\" hh24:mi') input_date   ")
			.append("   from pc   ");

			pstmt=con.prepareStatement(selectAllPC.toString());
		//4. ���ε� ������ �� �ֱ�
			
		//5. ���� ���� �� ��� ���
			rs=pstmt.executeQuery();
			PCVO pcVO = null;
			while (rs.next()) {
				//ip_address, input_date, pc_num
				pcVO = new PCVO(
						rs.getString("ip_address"), 
						rs.getString("input_date"), 
						rs.getInt("pc_num")
						);
				list.add(pcVO);//��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
			}//end while
		}finally {
		//6. �������
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
		//2. Ŀ�ؼ� ���
			con = getConnection();
		//3. ������ ���� ��ü ���
			StringBuilder updateLunch = new StringBuilder();
			//PC_NUM	IP_ADDRESS	INPUT_DATE	
			if (msg.equals("���")||msg.equals("����")) {
				updateLunch
				.append("   update pc   ")
				.append("   set    IP_ADDRESS = ?, input_date = sysdate   ")
				.append("   where  PC_NUM = ?   ");
			}//end if
			if (msg.equals("����")) {
				updateLunch
				.append("   update pc   ")
				.append("   set    IP_ADDRESS = ?, input_date = null   ")
				.append("   where  PC_NUM = ?   ");
			}//end if
			
			pstmt = con.prepareStatement(updateLunch.toString());
		//4. ���ε� ���� ����
			pstmt.setString(1, inputIP);
			pstmt.setInt(2, pcNum);
		//5. ���� ���� �� �� ���
			updateFlag = (pstmt.executeUpdate()==1);
		} finally {
		//6. ���� ����
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
//		//2. Connection ���
//		con = getConnection();
//		//3. ������ ������ü ���: 
//		//{"ȸ����ȣ","ID","�̸�","��ȭ��ȣ","������"}
//		//ID	PASS	NAME	PHONE	QUESTION_VERIFY	ANSWER_VERIFY	JOIN_DATE	
//		StringBuilder selectAllMember = new StringBuilder();
//		selectAllMember
//		.append("   select   id, pass, name, phone, to_char(join_date,'yyyy-mm-dd hh24:mi') join_date   ")
//		.append("   from     member_account   ");
//		
//		pstmt = con.prepareStatement(selectAllMember.toString());
//		//4. ���ε� ���� �� �ֱ�
//		//5. ������ ���� �� �� ���
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
//			list.add(lVO);//��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
//		}//end while
//		} finally {
//		//6. ���� ����
//			if(rs != null) { rs.close(); }//end if
//			if(pstmt != null) { pstmt.close(); }//end if
//			if(con != null) { con.close(); }//end if
//		}//end finally
//		return list;
//	}//selectAllPC
	
	/**
	 * DBMS���̺� �����ϴ� ��� ȸ�� ����� ��ȸ
	 * @return ���ö� ���
	 * @throws SQLException
	 */
//	public List<MemberVO> selectAllMember() throws SQLException{
	public List<MemberVO> selectMember(int selectCondition, String selectWord) throws SQLException{
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		Connection con = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		
		try {
			
		//2. Connection ���
		con = getConnection();
		//3. ������ ������ü ���: 
		//{"ȸ����ȣ","ID","�̸�","��ȭ��ȣ","������"}
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
		.append("   select ma.id, ma.name, phone, to_char(join_date,'yyyy\"��\" mm\"��\" dd\"��\" hh24:mi') join_date, nvl(sum(pp.payment_time - login_time)*24*60,0) pc_use_time,nvl(sum(price*quantity),0) item_pay_sum   ")
		.append("   from   item i, item_order io, item_payment ip, pc_payment pp, pc_use pu, member_account ma   ")
		.append("   where  (pu.id(+)= ma.id and pp.pc_use_code(+)=pu.pc_use_code and io.pc_use_code(+)=pu.pc_use_code and io.item_code=i.item_code(+) and ip.order_code=io.order_code(+))   ");
		switch (selectCondition) {
		    case ID: selectMember.append("   and ma.id like '%" + selectWord + "%'   "); break;
		    case NAME: selectMember.append("   and ma.name like '%" + selectWord + "%'   "); break;
		    case PHONE: selectMember.append("   and phone like '%" + selectWord.replace("-", "") + "%'   "); break;
		    case JOIN_DATE: selectMember.append("   and to_char(join_date,'yyyy\"��\" mm\"��\" dd\"��\" hh24:mi') like '%" + selectWord + "%'   "); break;
		}//switch case
		selectMember.append("   group by ma.id, ma.name, phone, join_date   ");
		
		pstmt = con.prepareStatement(selectMember.toString());
		//4. ���ε� ���� �� �ֱ�
		//5. ������ ���� �� �� ���
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
			list.add(mVO);//��ȸ�� ���ڵ带 ������ VO�� list�� �߰�
		}//end while
		} finally {
		//6. ���� ����
			if(rs != null) { rs.close(); }//end if
			if(pstmt != null) { pstmt.close(); }//end if
			if(con != null) { con.close(); }//end if
		}//end finally
		return list;
	}//selectAllMember
	
//	public static void main(String[] args) {
//		AdminDAO ad=AdminDAO.getInstance();
//		try {
//			System.out.println(ad.selectLogin());  
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}//end catch
//	}//main
	
}//class