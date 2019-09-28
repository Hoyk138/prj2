package admin.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import admin.DAO.AdminDAO;
import admin.VO.MemberVO;
import admin.view.ManageMember;

public class ManageMemberEvt implements ActionListener{

	private ManageMember mm;
	
	private int searchCondition = 0;
	private String searchWord = "";
	
	public ManageMemberEvt(ManageMember mm) {
		this.mm = mm;
		setMemberList(searchCondition,searchWord);
	}//ManageMemberEvt
	
	/**
	 * DBMS 테이블에서 조회한 회원 리스트를 JTable에 설정
	 */
	//method의 접근 지정자를 잘 모르면 public으로 하라
//	private void setMemberList(String searchWord) {
	private void setMemberList(int searchCondition, String searchWord) {
		DefaultTableModel dtm = mm.getDtmMember();

		// JTable의 레코드 초기화
		dtm.setRowCount(0);

		Object[] rowData = null;// JTable에 넣을 데이터

		// DBMS에서 조회
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			List<MemberVO> listMember = aDAO.selectMember(searchCondition,searchWord);
			if (listMember.isEmpty()) {// 도시락 제품이 없는 경우
				JOptionPane.showMessageDialog(mm, "조회된 회원이 없습니다.");
			} // end if
			MemberVO mVO = null;// list의 방의 값을 저장하기 위한 변수
			StringBuilder tempPhone = null; 
//			String useTime = "";
//			DecimalFormat df = new DecimalFormat("#,###");
			for (int i = 0; i < listMember.size(); i++) {
				mVO = listMember.get(i);
				// 조회 결과로 JTable 레코드에 들어갈 데이터를 생성하고
				rowData = new Object[7];
				// 배열에 값을 할당
				//{"회원번호","ID","이름","전화번호","가입일"}
//				rowData[0] = new Integer(i + 1);// auto boxing
				rowData[0] = mVO.getId();
				rowData[1] = mVO.getName();
				tempPhone = new StringBuilder(mVO.getPhone());
				rowData[2] = tempPhone.insert(7, '-').insert(3, '-').toString();
				rowData[3] = mVO.getJoin_date();
//				if (mVO.getPc_use_time() < 60) {
//					useTime = mVO.getPc_use_time()+"분";
//				} else {
//					useTime = (mVO.getPc_use_time()/60)+"시간 "+(mVO.getPc_use_time()%60)+"분";
//				}//if else
//				rowData[4] = useTime;
				rowData[4] = mVO.getPc_use_time();
//				rowData[5] = df.format(mVO.getItem_pay_sum());
				rowData[5] = mVO.getItem_pay_sum();
				// dtm에 추가
				dtm.addRow(rowData);
			} // end for

		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(mm, "서비스가 원활하지 못한 점 죄송합니다.");
			sqle.printStackTrace();
		} // end catch

	}// setMemberList

//	private void searchSpecificMember() {
//		searchCondition = mm.getJcbSearch().getSelectedIndex();
//		searchWord = mm.getJtfSearch().getText().trim();
////		System.out.println(searchCondition+"/"+searchWord);
//		setMemberList(searchCondition, searchWord);
//	}//searchSpecificMember

	private void setSearchCondition() {
		searchCondition = mm.getJcbSearch().getSelectedIndex();
		searchWord = mm.getJtfSearch().getText().trim();
	}//searchSpecificMember
	
	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource()==mm.getJtfSearch() || ae.getSource()==mm.getJbtnSearch()) {
//			System.out.println("조회");
//			searchSpecificMember();
			setSearchCondition();
			setMemberList(searchCondition, searchWord);
		}//end if
		if (ae.getSource()==mm.getJbtnRefresh()) {
			System.out.println("새로고침");
			setMemberList(searchCondition, searchWord);
		}//end if
	}//jcbSearch
	
}//class
