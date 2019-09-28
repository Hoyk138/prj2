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
	 * DBMS ���̺��� ��ȸ�� ȸ�� ����Ʈ�� JTable�� ����
	 */
	//method�� ���� �����ڸ� �� �𸣸� public���� �϶�
//	private void setMemberList(String searchWord) {
	private void setMemberList(int searchCondition, String searchWord) {
		DefaultTableModel dtm = mm.getDtmMember();

		// JTable�� ���ڵ� �ʱ�ȭ
		dtm.setRowCount(0);

		Object[] rowData = null;// JTable�� ���� ������

		// DBMS���� ��ȸ
		AdminDAO aDAO = AdminDAO.getInstance();
		try {
			List<MemberVO> listMember = aDAO.selectMember(searchCondition,searchWord);
			if (listMember.isEmpty()) {// ���ö� ��ǰ�� ���� ���
				JOptionPane.showMessageDialog(mm, "��ȸ�� ȸ���� �����ϴ�.");
			} // end if
			MemberVO mVO = null;// list�� ���� ���� �����ϱ� ���� ����
			StringBuilder tempPhone = null; 
//			String useTime = "";
//			DecimalFormat df = new DecimalFormat("#,###");
			for (int i = 0; i < listMember.size(); i++) {
				mVO = listMember.get(i);
				// ��ȸ ����� JTable ���ڵ忡 �� �����͸� �����ϰ�
				rowData = new Object[7];
				// �迭�� ���� �Ҵ�
				//{"ȸ����ȣ","ID","�̸�","��ȭ��ȣ","������"}
//				rowData[0] = new Integer(i + 1);// auto boxing
				rowData[0] = mVO.getId();
				rowData[1] = mVO.getName();
				tempPhone = new StringBuilder(mVO.getPhone());
				rowData[2] = tempPhone.insert(7, '-').insert(3, '-').toString();
				rowData[3] = mVO.getJoin_date();
//				if (mVO.getPc_use_time() < 60) {
//					useTime = mVO.getPc_use_time()+"��";
//				} else {
//					useTime = (mVO.getPc_use_time()/60)+"�ð� "+(mVO.getPc_use_time()%60)+"��";
//				}//if else
//				rowData[4] = useTime;
				rowData[4] = mVO.getPc_use_time();
//				rowData[5] = df.format(mVO.getItem_pay_sum());
				rowData[5] = mVO.getItem_pay_sum();
				// dtm�� �߰�
				dtm.addRow(rowData);
			} // end for

		} catch (SQLException sqle) {
			JOptionPane.showMessageDialog(mm, "���񽺰� ��Ȱ���� ���� �� �˼��մϴ�.");
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
//			System.out.println("��ȸ");
//			searchSpecificMember();
			setSearchCondition();
			setMemberList(searchCondition, searchWord);
		}//end if
		if (ae.getSource()==mm.getJbtnRefresh()) {
			System.out.println("���ΰ�ħ");
			setMemberList(searchCondition, searchWord);
		}//end if
	}//jcbSearch
	
}//class
